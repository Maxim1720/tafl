package kz.trankwilizator.tafl.controller.auth;


import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.TempUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/temp")
public class TempAuthRestController extends AuthorizationRestController<TempUserAuthDto> {
    public TempAuthRestController(AuthService<TempUserAuthDto> authService) {
        super(authService);
    }
    @Override
    public ResponseEntity<AuthToken> authorization(@RequestBody TempUserAuthDto user) {
        return super.authorization(user);
    }
}
