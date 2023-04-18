package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity(name = "temp_user")
@Table(name = "temporary_user")
public class TemporaryUser extends AbsUser{
    @Column(nullable = false)
    private LocalDateTime expiryAt;
}
