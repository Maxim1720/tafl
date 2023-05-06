package kz.trankwilizator.tafl.security.logout;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
@Log
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {
    private final JwtTokenCrudService jwtTokenCrudService;
    private final RequestUtil requestUtil;
    private final JwtTokenProvider jwtTokenProvider;

    public LogoutHandler(JwtTokenCrudService jwtTokenCrudService, RequestUtil requestUtil, JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenCrudService = jwtTokenCrudService;
        this.requestUtil = requestUtil;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        log.info("authentication before logout: " + authentication);
        String token = requestUtil.getJwtTokenFromRequest(request);

        if (!token.equals("") && jwtTokenProvider.validateToken(token)) {

            String username = jwtTokenProvider.getUsernameFromToken(token);
            Optional<JwtToken> optional = jwtTokenCrudService.getActual(username);

            if(optional.isPresent()){
                logoutUser(request, optional.get());
            }
            else {
                response = toUnauthorizedRequest(response,request);
            }
        } else {
            response = toUnauthorizedRequest(response,request);
        }
    }

    private void logoutUser(HttpServletRequest request, JwtToken token){
        invalidateToken(token);
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    private HttpServletResponse toUnauthorizedRequest(HttpServletResponse response, HttpServletRequest request){
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            new JsonMapper().writeValue(response.getWriter(), createErrorDto(request));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    private void invalidateToken(JwtToken jwtToken){
        jwtToken.setExpiryAt(new Date());
        jwtTokenCrudService.save(jwtToken);
    }

    private ErrorDto createErrorDto(HttpServletRequest request){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setPath(request.getRequestURI());
        errorDto.setMessage("user not logged in");
        errorDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        if(request.getHeader("Authorization") != null){
            String authHeader = request.getHeader("Authorization");
            if(authHeader.split(" ").length == 2){
                errorDto.setBody(authHeader.split(" ")[1]);
            }
        }
        else{
            log.warning("Authorization header doesn't exists");
        }
        return errorDto;
    }
}
