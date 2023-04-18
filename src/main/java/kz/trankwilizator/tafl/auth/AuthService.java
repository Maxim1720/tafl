package kz.trankwilizator.tafl.auth;

import kz.trankwilizator.tafl.dto.AbsUserDto;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;

public interface AuthService<User extends UserAuthDto> {
    AuthToken authorization(User user);
}
