package kz.trankwilizator.tafl.auth;

import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import org.springframework.stereotype.Service;

@Service
public class PermanentAuthenticationService implements AuthService<PermanentUserAuthDto> {
    @Override
    public AuthToken authenticate(PermanentUserAuthDto userAuthDto) {
        return null;
    }

    @Override
    public void logout() {

    }
}
