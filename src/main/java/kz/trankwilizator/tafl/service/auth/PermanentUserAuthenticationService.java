package kz.trankwilizator.tafl.service.auth;

import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.util.JwtTokenCreator;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class PermanentUserAuthenticationService extends AuthenticationService<PermanentUserAuthDto, PermanentUser> {

    public PermanentUserAuthenticationService(JwtTokenCrudService jwtTokenCrudService,
                                              JwtTokenCreator jwtTokenCreator,
                                              JwtTokenProvider jwtTokenProvider,
                                              UserCrudService<PermanentUser> userUserCrudService) {
        super(jwtTokenCrudService, jwtTokenCreator, jwtTokenProvider, userUserCrudService);
    }
}
