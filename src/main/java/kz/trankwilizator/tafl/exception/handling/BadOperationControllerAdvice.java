package kz.trankwilizator.tafl.exception.handling;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import kz.trankwilizator.tafl.exception.BadOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadOperationControllerAdvice {

    @ExceptionHandler(BadOperationException.class)
    public ResponseEntity<ErrorDto> handle(BadOperationException e, HttpServletRequest request){
        return CrudControllerAdvice.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), e.getBody());
    }
}
