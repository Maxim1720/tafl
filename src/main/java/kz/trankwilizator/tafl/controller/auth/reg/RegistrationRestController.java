package kz.trankwilizator.tafl.controller.auth.reg;

import kz.trankwilizator.tafl.auth.reg.PermanentUserRegistrationService;
import kz.trankwilizator.tafl.auth.reg.temp.RegistrationTemporaryUserService;
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
    private final PermanentUserRegistrationService permanentUserRegistrationService;
    private final RegistrationTemporaryUserService registrationTemporaryUserService;

    public RegistrationRestController(PermanentUserRegistrationService permanentUserRegistrationService,
                                      RegistrationTemporaryUserService registrationTemporaryUserService) {
        this.permanentUserRegistrationService = permanentUserRegistrationService;
        this.registrationTemporaryUserService = registrationTemporaryUserService;
    }

    @PostMapping("/permanent")
    public ResponseEntity<PermanentUserDto> createUser(@RequestBody PermanentUserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(permanentUserRegistrationService.createUser(user));
    }
    @PostMapping("/temp")
    public ResponseEntity<TemporaryUserDto> createCheck(){
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationTemporaryUserService.create());
    }

}
