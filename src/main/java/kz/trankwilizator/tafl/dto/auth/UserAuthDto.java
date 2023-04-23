package kz.trankwilizator.tafl.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public abstract class UserAuthDto {
    private String username;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
}
