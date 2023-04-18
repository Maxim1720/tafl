package kz.trankwilizator.tafl.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PermanentUserAuthDto extends UserAuthDto{
    private String password;
}
