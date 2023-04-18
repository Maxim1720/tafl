package kz.trankwilizator.tafl.controller.auth;

import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.dto.AbsUserDto;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public abstract class AuthorizationController<UserDto extends UserAuthDto> {
    private final AuthService<UserDto> authService;
    public AuthorizationController(AuthService<UserDto> authService) {
        this.authService = authService;
    }
    public ResponseEntity<AuthToken> authorization(@RequestBody UserDto user){
        return ResponseEntity.accepted().body(authService.authorization(user));
    }
}
