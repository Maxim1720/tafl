package kz.trankwilizator.tafl.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermanentUserDto extends AbsUserDto {
    private String firstname;
    private String lastname;
    private String secondName;
    private String email;
    private String password;
}
