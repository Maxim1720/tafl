package kz.trankwilizator.tafl.exception.handling;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorDto> handleUnauthorized(AuthenticationException e, HttpServletRequest request){
        return CrudControllerAdvice.errorResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return CrudControllerAdvice.errorResponse(HttpStatus.FORBIDDEN, ex.getMessage(), request.getRequestURI(), null);
    }
}
