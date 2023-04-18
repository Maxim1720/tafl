package kz.trankwilizator.tafl.auth;

import kz.trankwilizator.tafl.dto.TemporaryUserDto;

public interface TemporaryUserService {
    TemporaryUserDto create();
    TemporaryUserDto replenishBalance(Double amount);
}
