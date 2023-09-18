package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "temp_user")
@Table(name = "temporary_user")
@Getter
@Setter
@ToString
@DiscriminatorValue(value = "temporary")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryUser extends User {

    @Transient
    private Date expiryAt;

    @Column(nullable = false)
    public Date getExpiryAt(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        expiryAt = Date.from(calendar.toInstant());
        return expiryAt;
    }
}
