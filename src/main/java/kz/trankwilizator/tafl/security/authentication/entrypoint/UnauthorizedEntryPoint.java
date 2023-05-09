package kz.trankwilizator.tafl.security.authentication.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final RequestUtil requestUtil;

    public UnauthorizedEntryPoint(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(authException.getMessage());
        errorDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDto.setPath(request.getRequestURI());

        String jwt = requestUtil.getJwtTokenFromRequest(request);
        if(!jwt.equals("")){
            errorDto.setBody(jwt);
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), errorDto);


    }
}
