package kz.trankwilizator.tafl.security.temp;

import io.jsonwebtoken.*;
import kz.trankwilizator.tafl.auth.reg.temp.UniqueStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value(value = "${jwt.expiration.ms}")
    private Long jwtExpiration;

    public String generateToken(String username) {
        Date currentDate = new Date();
        return Jwts.builder()
                .setSubject(createSubject(username))
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime()+jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return parseUsername(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    private String parseUsername(String subject){
        return subject.split(" ")[0];
    }

    private String createSubject(String username){
        return String.format("%s %s", username, UniqueStringGenerator.generateUniqueString());
    }

}
