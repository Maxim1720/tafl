package kz.trankwilizator.tafl.service.crud;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.JwtTokenRepository;
import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class JwtTokenCrudService implements Crud<JwtToken>{
    private final JwtTokenRepository jwtTokenRepository;

    public JwtTokenCrudService(JwtTokenRepository jwtTokenRepository,
                               UserCrudService<?> temporaryUserCrudService) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public Set<JwtToken> getByUsername(String username){
        return jwtTokenRepository.findJwtTokensByUserUsername(username);
    }

    public JwtToken getByToken(String token){
        return getFromOptional(jwtTokenRepository.findByToken(token));
    }

    private JwtToken getFromOptional(Optional<JwtToken> optionalJwtToken){
        return optionalJwtToken.orElseThrow(()->new EntityExistsException("token doesn't exists"));
    }

    @Override
    public JwtToken getById(Long id) {
        return getFromOptional(jwtTokenRepository.findById(id));
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
