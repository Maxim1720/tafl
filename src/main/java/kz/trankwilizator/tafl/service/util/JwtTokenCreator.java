package kz.trankwilizator.tafl.service.util;

import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.entity.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@PropertySource("classpath:jwt.properties")
public class JwtTokenCreator {

    @Value("${jwt.expiration.h}")
    private Long expiryTime;

    public JwtTokenCreator() {
    }

    public JwtToken create(String token, User user){
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setExpiryAt(Date.from(new Date().toInstant().plus(expiryTime, ChronoUnit.HOURS)));
        jwtToken.setUser(user);
        return jwtToken;
    }

}
