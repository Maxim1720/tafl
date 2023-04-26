package kz.trankwilizator.tafl.controller.auth;


import kz.trankwilizator.tafl.service.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.TempUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/temp")
public class TemporaryUserAuthRestController extends AuthorizationRestController<TempUserAuthDto> {
    public TemporaryUserAuthRestController(AuthService<TempUserAuthDto> authService) {
        super(authService);
    }
    @Override
    public ResponseEntity<AuthToken> authorization(@RequestBody TempUserAuthDto user) {
        return super.authorization(user);
    }
}
