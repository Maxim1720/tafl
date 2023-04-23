package kz.trankwilizator.tafl.auth.reg;

import jakarta.validation.Valid;
import kz.trankwilizator.tafl.dto.PermanentUserDto;

public interface PermanentUserRegistrationService {
    PermanentUserDto createUser(@Valid PermanentUserDto user);
}
