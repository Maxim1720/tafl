package kz.trankwilizator.tafl.service.auth;

import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;

public interface AuthService<User extends UserAuthDto> {
    AuthToken authenticate(User user);
    void logout();
}
