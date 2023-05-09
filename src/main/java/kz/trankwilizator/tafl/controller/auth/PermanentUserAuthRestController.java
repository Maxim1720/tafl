package kz.trankwilizator.tafl.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.auth.PermanentUserAuthDto;
import kz.trankwilizator.tafl.dto.response.ResponseDto;
import kz.trankwilizator.tafl.service.auth.AuthService;
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
    public ResponseEntity<ResponseDto> authorization(@RequestBody PermanentUserAuthDto user, HttpServletRequest request) {
        return super.authorization(user, request);
    }
}
