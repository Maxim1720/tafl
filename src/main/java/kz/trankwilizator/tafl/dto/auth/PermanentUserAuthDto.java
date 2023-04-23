package kz.trankwilizator.tafl.dto.auth;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermanentUserAuthDto extends UserAuthDto{
    private String password;
}
