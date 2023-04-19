package kz.trankwilizator.tafl.auth.reg;

import jakarta.validation.Valid;
import kz.trankwilizator.tafl.dto.PermanentUserDto;

public interface RegistrationService {
    PermanentUserDto createUser(@Valid PermanentUserDto user);
}
