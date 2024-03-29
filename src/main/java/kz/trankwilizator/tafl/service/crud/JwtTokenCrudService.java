package kz.trankwilizator.tafl.service.crud;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dao.JwtTokenRepository;
import kz.trankwilizator.tafl.entity.JwtToken;
import lombok.extern.java.Log;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.Collection;

@Transactional
@Service
@Log
public class JwtTokenCrudService implements ExistenceChecker<JwtToken, Long>, Saver<JwtToken>, Finder<JwtToken, Long>{
    private final JwtTokenRepository jwtTokenRepository;

    public JwtTokenCrudService(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public Set<JwtToken> getByUsername(String username){
        return jwtTokenRepository.findJwtTokensByUserUsername(username);
    }

    public JwtToken getByToken(String token){
        try {
            return getFromOptional(jwtTokenRepository.findByToken(token));
        }
        catch (EntityNotFoundException e){
            e.addSuppressed(new EntityNotFoundException("with value = " + token));
            throw e;
        }
    }

    private JwtToken getFromOptional(Optional<JwtToken> optionalJwtToken){
        return optionalJwtToken.orElseThrow(()->{
            log.warning("token doesn't exists");
            return new EntityNotFoundException("token doesn't exists");
        });
    }

    @Override
    public JwtToken findById(Long id) {
        try {
            return getFromOptional(jwtTokenRepository.findById(id));
        }catch (EntityExistsException e){
            e.addSuppressed(new EntityNotFoundException("with id: "+ id));
            throw e;
        }
    }

    @Override
    public Collection<JwtToken> findAll(){
        return jwtTokenRepository.findAll();
    }

    @Override
    public JwtToken save(JwtToken jwtToken){
        return jwtTokenRepository.save(jwtToken);
    }

    
    @Override
    public boolean exists(JwtToken jwtToken) {
        return jwtTokenRepository.exists(new Example<>() {
            @Override
            public JwtToken getProbe() {
                return jwtToken;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return ExampleMatcher.matchingAll();
            }
        });
    }

    @Override
    public boolean existsWithId(Long id){
        return jwtTokenRepository.findById(id).isPresent();
    }

    public boolean existsByUsername(String username) {
        return !jwtTokenRepository.findJwtTokensByUserUsername(username).isEmpty();
    }

    public Collection<JwtToken> saveAll(Collection<JwtToken> jwtTokens){
        return jwtTokenRepository.saveAll(jwtTokens);
    }

    public Optional<JwtToken> getActual(String username) {
        return getByUsername(username).stream().filter((t) -> t.getExpiryAt().after(new Date())).findFirst();
    }
}
