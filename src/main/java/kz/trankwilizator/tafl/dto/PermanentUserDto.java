package kz.trankwilizator.tafl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentUserDto extends UserDto {
    private String firstname;
    private String lastname;
    private String secondName;
    private String email;
    private String password;
}
