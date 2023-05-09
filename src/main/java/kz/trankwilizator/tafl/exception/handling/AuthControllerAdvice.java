package kz.trankwilizator.tafl.exception.handling;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorDto> handleUnauthorized(AuthenticationException e, HttpServletRequest request){
        return CrudControllerAdvice.errorResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI(), null);
    }

}
