package kz.trankwilizator.tafl.auth.temp;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.crud.JwtTokenService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.TempUserAuthDto;
import kz.trankwilizator.tafl.entity.user.temp.JwtToken;
import kz.trankwilizator.tafl.security.temp.JwtTokenProvider;
import kz.trankwilizator.tafl.service.JwtTokenCreator;
import lombok.extern.java.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@Log
public class TempAuthenticationService implements AuthService<TempUserAuthDto> {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtTokenService jwtTokenService;

    private final JwtTokenCreator jwtTokenCreator;

    public TempAuthenticationService(JwtTokenProvider jwtTokenProvider,
                                     JwtTokenService jwtTokenService,
                                     JwtTokenCreator jwtTokenCreator) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenService = jwtTokenService;
        this.jwtTokenCreator = jwtTokenCreator;
    }

    @Override
    public AuthToken authenticate(TempUserAuthDto tempUserAuthDto) {
        Optional<JwtToken> t = jwtTokenService.getActual(tempUserAuthDto.getUsername());

        JwtToken actualToken = t.orElseGet(() -> {
                    JwtToken jwtToken = jwtTokenCreator.create(jwtTokenProvider.generateToken(tempUserAuthDto.getUsername()), tempUserAuthDto.getUsername());
                    try {
                        jwtTokenService.getByToken(jwtToken.getToken());
                        log.warning(jwtToken.getToken());
                    }
                    catch (EntityExistsException ignored){
                        log.info("token doesn't exists");
                    }
                    jwtTokenService.save(jwtToken);
                    return jwtToken;
                }
        );
        return new AuthToken(actualToken.getToken());
    }
    @Override
    public void logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Set<JwtToken> jwtTokenSet = jwtTokenService.getByUsername(username);
        jwtTokenSet.forEach(t -> {
            Date curDate = new Date();
            if(t.getExpiryAt().after(curDate)){
                t.setExpiryAt(curDate);
            }
        });
        jwtTokenService.saveAll(jwtTokenSet);
    }
}
