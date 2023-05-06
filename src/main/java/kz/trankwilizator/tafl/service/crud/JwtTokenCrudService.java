package kz.trankwilizator.tafl.service.crud;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.JwtTokenRepository;
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

@Transactional
@Service
@Log
public class JwtTokenCrudService implements Crud<JwtToken>{
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
    public JwtToken getById(Long id) {
        try {
            return getFromOptional(jwtTokenRepository.findById(id));
        }catch (EntityExistsException e){
            e.addSuppressed(new EntityNotFoundException("with id: "+ id));
            throw e;
        }
    }

    public JwtToken save(JwtToken jwtToken){
        return jwtTokenRepository.save(jwtToken);
    }

    public Collection<JwtToken> saveAll(Collection<JwtToken> jwtTokens){
        return jwtTokenRepository.saveAll(jwtTokens);
    }

    public Optional<JwtToken> getActual(String username) {
        return getByUsername(username).stream().filter((t) -> t.getExpiryAt().after(new Date())).findFirst();
    }
}
