package kz.trankwilizator.tafl.controller.auth;


import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.response.ResponseDto;
import kz.trankwilizator.tafl.service.auth.AuthService;
import kz.trankwilizator.tafl.dto.auth.TemporaryUserAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/temp")
public class TemporaryUserAuthRestController extends AuthorizationRestController<TemporaryUserAuthDto> {
    public TemporaryUserAuthRestController(AuthService<TemporaryUserAuthDto> authService) {
        super(authService);
    }
    @Override
    public ResponseEntity<ResponseDto> authorization(@RequestBody TemporaryUserAuthDto user, HttpServletRequest request) {
        return super.authorization(user, request);
    }
}
