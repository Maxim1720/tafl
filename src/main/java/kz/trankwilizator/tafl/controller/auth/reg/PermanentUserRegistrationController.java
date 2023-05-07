package kz.trankwilizator.tafl.controller.auth.reg;

import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.service.auth.reg.PermanentUserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/permanent")
public class PermanentUserRegistrationController {

    private final PermanentUserRegistrationService permanentUserRegistrationService;

    public PermanentUserRegistrationController(PermanentUserRegistrationService permanentUserRegistrationService) {
        this.permanentUserRegistrationService = permanentUserRegistrationService;
    }

    @PostMapping("/reg")
    public ResponseEntity<PermanentUserDto> createUser(@RequestBody PermanentUserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(permanentUserRegistrationService.createUser(user));
    }

}
