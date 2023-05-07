package kz.trankwilizator.tafl.controller.auth.reg;

import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.service.auth.reg.temp.TemporaryUserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/temp")
public class TemporaryUserRegistrationController {
    private final TemporaryUserRegistrationService temporaryUserRegistrationService;

    public TemporaryUserRegistrationController(TemporaryUserRegistrationService temporaryUserRegistrationService) {
        this.temporaryUserRegistrationService = temporaryUserRegistrationService;
    }

    @PostMapping("/reg")
    public ResponseEntity<TemporaryUserDto> createUser(){
        return ResponseEntity.status(HttpStatus.CREATED).body(temporaryUserRegistrationService.create());
    }
}
