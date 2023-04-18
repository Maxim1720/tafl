package kz.trankwilizator.tafl.dto;

import lombok.Data;

@Data
public abstract class AbsUserDto {
    private String username;
    private Double balance;
}
