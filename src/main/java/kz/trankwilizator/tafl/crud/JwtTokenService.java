package kz.trankwilizator.tafl.crud;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.temp.JwtTokenRepository;
import kz.trankwilizator.tafl.entity.user.temp.JwtToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class JwtTokenService {
    private final JwtTokenRepository jwtTokenRepository;
    private final TemporaryUserCrudService temporaryUserCrudService;

    public JwtTokenService(JwtTokenRepository jwtTokenRepository,
                           TemporaryUserCrudService temporaryUserCrudService) {
        this.jwtTokenRepository = jwtTokenRepository;
        this.temporaryUserCrudService = temporaryUserCrudService;
    }

    public Set<JwtToken> getByUsername(String username){
        return jwtTokenRepository
                .findByTemporaryUser(
                        temporaryUserCrudService.getByUsername(username)
                );
    }

    public JwtToken getByToken(String token){
        return getFromOptional(jwtTokenRepository.findByToken(token));
    }

    private JwtToken getFromOptional(Optional<JwtToken> optionalJwtToken){
        return optionalJwtToken.orElseThrow(()->new EntityExistsException("token doesn't exists"));
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
