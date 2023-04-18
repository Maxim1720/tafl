package kz.trankwilizator.tafl.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TemporaryUserDto extends AbsUserDto {
    private Date expireDate;
}
