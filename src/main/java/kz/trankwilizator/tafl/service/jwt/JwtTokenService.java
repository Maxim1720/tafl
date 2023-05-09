package kz.trankwilizator.tafl.service.jwt;

import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private final JwtTokenCrudService jwtTokenCrudService;

    public JwtTokenService(JwtTokenCrudService jwtTokenCrudService) {
        this.jwtTokenCrudService = jwtTokenCrudService;
    }

    public boolean isExpired(String token){
        return isExpired(jwtTokenCrudService.getByToken(token));
    }

    public boolean isExpired(JwtToken jwtToken){
        return jwtToken.getExpiryAt().before(new Date());
    }
}
