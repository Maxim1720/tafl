package kz.trankwilizator.tafl.exception.handling;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class JwtExceptionControllerAdvice {
    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<ErrorDto> handleJwt(JwtException e, WebRequest request) {
        return CrudControllerAdvice.errorResponse(HttpStatus.BAD_REQUEST,
                e.getMessage(),
                request.getContextPath(), null);
    }
}
