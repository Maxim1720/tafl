package kz.trankwilizator.tafl.entity.user.permanent;

import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noPassword", types = PermanentUser.class)
public interface NoPasswordPermanentUser {
    String getFirstname();

    String getLastname();

    String getEmail();

    String getSecondName();

}
