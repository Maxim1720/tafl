package kz.trankwilizator.tafl.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.auth.UserAuthDto;
import kz.trankwilizator.tafl.dto.response.ResponseDto;
import kz.trankwilizator.tafl.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AuthorizationRestController<UserDto extends UserAuthDto> {
    private final AuthService<UserDto> authService;
    protected AuthorizationRestController(AuthService<UserDto> authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> authorization(@RequestBody UserDto user, HttpServletRequest request){
        HttpStatus status = HttpStatus.ACCEPTED;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(authService.authenticate(user));
        responseDto.setPath(request.getRequestURI());
        responseDto.setMessage("User authenticated");
        responseDto.setStatus(status.value());
        return ResponseEntity.status(status).body(responseDto);
    }
}
