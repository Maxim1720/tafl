package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "temp_user")
@Table(name = "temporary_user")
@ToString
@DiscriminatorValue(value = "temporary")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryUser extends User {

    @Future
    @Setter
    @Transient
    private Date expiryAt;

    @Column(nullable = false)
    public Date getExpiryAt(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        expiryAt = Date.from(calendar.toInstant());
        return expiryAt;
    }
    @Size(min = 12, max = 12) @Pattern(regexp = "[0-9]{12}")
    @Column(nullable = false, length = 12, unique = true, updatable = false)
    public String getUsername() {
        return username;
    }
}
