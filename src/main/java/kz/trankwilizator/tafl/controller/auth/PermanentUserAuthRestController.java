package kz.trankwilizator.tafl.controller.auth;

import kz.trankwilizator.tafl.service.auth.AuthService;
import kz.trankwilizator.tafl.dto.AuthToken;
import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/permanent")
public class PermanentUserAuthRestController extends AuthorizationRestController<PermanentUserAuthDto> {
    public PermanentUserAuthRestController(AuthService<PermanentUserAuthDto> authService) {
        super(authService);
    }

    @Override
    public ResponseEntity<AuthToken> authorization(@RequestBody PermanentUserAuthDto user) {
        return super.authorization(user);
    }
}