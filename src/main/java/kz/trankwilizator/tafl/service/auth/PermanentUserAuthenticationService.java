package kz.trankwilizator.tafl.service.auth;

import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import kz.trankwilizator.tafl.service.crud.user.PermanentUserCrudService;
import kz.trankwilizator.tafl.service.util.JwtTokenCreator;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log
@Service
public class PermanentUserAuthenticationService extends AuthenticationService<PermanentUserAuthDto, PermanentUser> {


    private final PasswordEncoder passwordEncoder;
    private final PermanentUserCrudService permanentUserCrudService;
    public PermanentUserAuthenticationService(JwtTokenCrudService jwtTokenCrudService,
                                              JwtTokenCreator jwtTokenCreator,
                                              JwtTokenProvider jwtTokenProvider,
                                              PermanentUserCrudService permanentUserCrudService,
                                              PasswordEncoder passwordEncoder) {
        super(jwtTokenCrudService, jwtTokenCreator, jwtTokenProvider, permanentUserCrudService);
        this.passwordEncoder = passwordEncoder;
        this.permanentUserCrudService = permanentUserCrudService;
    }

    @Override
    public AuthToken authenticate(PermanentUserAuthDto userAuthDto) {
        PermanentUser user = permanentUserCrudService.getByUsername(userAuthDto.getUsername());
        if(!passwordEncoder.matches(userAuthDto.getPassword(),
                                    Arrays.toString(user.getPassword()))){
            throw new EntityNotFoundException("Incorrect username or password");
        }
        return super.authenticate(userAuthDto);
    }
}
