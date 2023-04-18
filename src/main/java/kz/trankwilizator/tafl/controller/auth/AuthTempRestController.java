package kz.trankwilizator.tafl.controller.auth;


import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.dto.auth.TempUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTempRestController extends AuthorizationController<TempUserAuthDto>{
    public AuthTempRestController(AuthService<TempUserAuthDto> authService) {
        super(authService);
    }

    @PostMapping("/temp")
    @Override
    public ResponseEntity<AuthToken> authorization(@RequestBody TempUserAuthDto user) {
        return super.authorization(user);
    }
}
