package kz.trankwilizator.tafl.service.auth;

import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;
import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.util.JwtTokenCreator;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Log
public abstract class AuthenticationService<D extends UserAuthDto, U extends User> implements AuthService<D>{

    private final JwtTokenCrudService jwtTokenCrudService;
    private final JwtTokenCreator jwtTokenCreator;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserCrudService<U> userCrudService;
    public AuthenticationService(JwtTokenCrudService jwtTokenCrudService,
                                 JwtTokenCreator jwtTokenCreator,
                                 JwtTokenProvider jwtTokenProvider,
                                 UserCrudService<U> userCrudService) {
        this.jwtTokenCrudService = jwtTokenCrudService;
        this.jwtTokenCreator = jwtTokenCreator;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userCrudService = userCrudService;
    }

    @Override
    public AuthToken authenticate(D userAuthDto) {
        Optional<JwtToken> t = jwtTokenCrudService.getActual(userAuthDto.getUsername());

        JwtToken actualToken = t.orElseGet(() -> {
                    JwtToken jwtToken =
                            jwtTokenCreator.create(
                                    jwtTokenProvider.generateToken(userAuthDto.getUsername()),
                                    userCrudService.getByUsername(userAuthDto.getUsername())
                            );
                    return jwtTokenCrudService.save(jwtToken);

                }
        );
        return new AuthToken(actualToken.getToken());
    }

    @Override
    public void logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Set<JwtToken> jwtTokenSet = jwtTokenCrudService.getByUsername(username);

        if(jwtTokenSet.isEmpty()){
            throw new AuthenticationServiceException("user not logged in");
        }

        jwtTokenSet.forEach(t -> {
            Date curDate = new Date();
            if(t.getExpiryAt().after(curDate)){
                t.setExpiryAt(curDate);
            }
        });
        jwtTokenCrudService.saveAll(jwtTokenSet);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
