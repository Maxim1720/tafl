package kz.trankwilizator.tafl.controller.auth.reg;

import kz.trankwilizator.tafl.auth.RegistrationService;
import kz.trankwilizator.tafl.auth.TemporaryUserService;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.dto.PermanentUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/reg")
public class RegistrationRestController {
    private final RegistrationService registrationService;
    private final TemporaryUserService temporaryUserService;

    public RegistrationRestController(RegistrationService registrationService,
                                      TemporaryUserService temporaryUserService) {
        this.registrationService = registrationService;
        this.temporaryUserService = temporaryUserService;
    }

    @PostMapping("/permanent")
    public ResponseEntity<PermanentUserDto> createUser(@RequestBody PermanentUserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.createUser(user));
    }
    @PostMapping("/temp")
    public ResponseEntity<TemporaryUserDto> createCheck(){
        return ResponseEntity.status(HttpStatus.CREATED).body(temporaryUserService.create());
    }

}
