package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "temp_user")
@Table(name = "temporary_user")
public class TemporaryUser extends AbsUser{

    private Date expiryAt;

    @Column(nullable = false)
    public Date expiryAt(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return Date.from(calendar.toInstant());
    }
}
