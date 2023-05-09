package kz.trankwilizator.tafl.security.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class RequestUtil {
    public String getJwtTokenFromRequest(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.isEmpty(token)){
            return token.split(" ")[1];
        }
        return "";
    }



}
