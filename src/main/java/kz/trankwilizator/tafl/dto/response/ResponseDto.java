package kz.trankwilizator.tafl.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ResponseDto {
    private Integer status;
    private String path;
    private String message;
    private Object body;

    @Setter(value = AccessLevel.NONE)
    private Date timestamp = new Date();
}

