package kz.trankwilizator.tafl.entity.user.temp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import kz.trankwilizator.tafl.entity.user.AbsUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "temp_user")
@Table(name = "temporary_user")
@Getter
@Setter
@ToString
public class TemporaryUser extends AbsUser {

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
