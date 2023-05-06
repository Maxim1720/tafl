package kz.trankwilizator.tafl.controller.auth;

import kz.trankwilizator.tafl.service.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AuthorizationRestController<UserDto extends UserAuthDto> {
    private final AuthService<UserDto> authService;
    protected AuthorizationRestController(AuthService<UserDto> authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthToken> authorization(@RequestBody UserDto user){
        return ResponseEntity.accepted().body(authService.authenticate(user));
    }
}
