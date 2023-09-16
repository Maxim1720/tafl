package kz.trankwilizator.tafl.controller.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.response.ResponseDto;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.mapper.PermanentUserMapper;
import kz.trankwilizator.tafl.service.crud.user.PermanentUserCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Secured({"READ", "PERMANENT"})
@RestController
@RequestMapping("/user-profile")
@OpenAPIDefinition(security = @SecurityRequirement(name = "PERMANENT USER"))
public class UserProfileRestController {

    private final PermanentUserCrudService permanentUserCrudService;
    private final PermanentUserMapper permanentUserMapper;

    private final PasswordEncoder passwordEncoder;

    public UserProfileRestController(PermanentUserCrudService permanentUserCrudService, PermanentUserMapper permanentUserMapper, PasswordEncoder passwordEncoder) {
        this.permanentUserCrudService = permanentUserCrudService;
        this.permanentUserMapper = permanentUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseDto profile(HttpServletRequest request){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setPath(request.getRequestURI());
        responseDto.setBody(permanentUserMapper.toDto(
                permanentUserCrudService
                        .getByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        ));
        responseDto.setMessage("User info");
        return responseDto;
    }

    @PutMapping("/password/change")
    public ResponseDto changePassword(@RequestParam(name = "password") String password, HttpServletRequest request){
        PermanentUser user = permanentUserCrudService.getByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        user.setPassword(passwordEncoder.encode(password).toCharArray());

        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(httpStatus.value());
        responseDto.setPath(request.getRequestURI());
        responseDto.setMessage("Password was changed!");
        responseDto.setBody(permanentUserMapper.toResponse(permanentUserCrudService.save(user)));
        return responseDto;
    }

    @PutMapping(path = "/username/change")
    public ResponseDto changeUsername(HttpServletRequest request, @RequestParam("username") String newUsername){
        PermanentUser user = permanentUserCrudService.getByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        user.setUsername(newUsername);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Username changed to " + newUsername);
        responseDto.setPath(request.getRequestURI());
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setBody(permanentUserMapper.toResponse(permanentUserCrudService.save(user)));
        return responseDto;
    }

    @PostMapping
    public ResponseDto resetPassword(HttpServletRequest request){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Password was reset, check your email");
        responseDto.setStatus(HttpStatus.ACCEPTED.value());
        responseDto.setPath(request.getRequestURI());

        return responseDto;
    }
}
