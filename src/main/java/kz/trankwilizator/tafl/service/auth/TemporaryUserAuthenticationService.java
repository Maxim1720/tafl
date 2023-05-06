package kz.trankwilizator.tafl.service.auth;

import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import kz.trankwilizator.tafl.dto.auth.TemporaryUserAuthDto;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.util.JwtTokenCreator;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class TemporaryUserAuthenticationService extends AuthenticationService<TemporaryUserAuthDto, TemporaryUser> {
    public TemporaryUserAuthenticationService(JwtTokenCrudService jwtTokenCrudService,
                                              JwtTokenCreator jwtTokenCreator,
                                              JwtTokenProvider jwtTokenProvider,
                                              UserCrudService<TemporaryUser> userUserCrudService) {
        super(jwtTokenCrudService, jwtTokenCreator, jwtTokenProvider, userUserCrudService);
    }
}
