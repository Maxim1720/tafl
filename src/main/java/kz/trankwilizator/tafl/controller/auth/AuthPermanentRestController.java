package kz.trankwilizator.tafl.controller.auth;

import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthPermanentRestController extends AuthorizationController<PermanentUserAuthDto>{
    public AuthPermanentRestController(AuthService<PermanentUserAuthDto> authService) {
        super(authService);
    }

    @PostMapping("/permanent")
    @Override
    public ResponseEntity<AuthToken> authorization(@RequestBody PermanentUserAuthDto user) {
        return super.authorization(user);
    }
}
