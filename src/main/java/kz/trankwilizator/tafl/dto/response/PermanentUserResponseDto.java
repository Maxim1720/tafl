package kz.trankwilizator.tafl.dto.response;

import kz.trankwilizator.tafl.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentUserResponseDto extends UserDto {
    private String firstname;
    private String lastname;
    private String secondName;
    private String username;
    private String email;
}
