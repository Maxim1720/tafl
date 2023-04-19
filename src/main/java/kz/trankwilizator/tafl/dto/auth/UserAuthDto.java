package kz.trankwilizator.tafl.dto.auth;

import lombok.Data;

import java.util.Date;

@Data
public abstract class UserAuthDto {
    private String username;
    private Date createdAt;
}
