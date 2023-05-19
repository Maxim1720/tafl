package kz.trankwilizator.tafl.controller.auth.reg;

import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.dto.response.PermanentUserResponseDto;
import kz.trankwilizator.tafl.mapper.PermanentUserMapper;
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
    private final PermanentUserMapper permanentUserMapper;

    public PermanentUserRegistrationController(PermanentUserRegistrationService permanentUserRegistrationService, PermanentUserMapper permanentUserMapper) {
        this.permanentUserRegistrationService = permanentUserRegistrationService;
        this.permanentUserMapper = permanentUserMapper;
    }

    @PostMapping("/reg")
    public ResponseEntity<PermanentUserResponseDto> createUser(@RequestBody PermanentUserDto user){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(permanentUserMapper
                        .toResponse(permanentUserMapper
                                .toEntity(permanentUserRegistrationService.createUser(user)
                                )
                        )
                );
    }

}
