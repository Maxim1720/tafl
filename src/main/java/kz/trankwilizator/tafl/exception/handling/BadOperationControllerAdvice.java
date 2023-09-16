package kz.trankwilizator.tafl.exception.handling;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import kz.trankwilizator.tafl.exception.BadOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;

@ControllerAdvice
public class BadOperationControllerAdvice {

    @ExceptionHandler(BadOperationException.class)
    public ResponseEntity<ErrorDto> handle(BadOperationException e, HttpServletRequest request){
        return CrudControllerAdvice.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), e.getBody());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handle(ConstraintViolationException e, HttpServletRequest request) throws IOException {
        String problem = Arrays.toString(e.getConstraintViolations().stream()
                .map(c -> c.getRootBeanClass()
                        .getSimpleName()
                        + "." + c.getPropertyPath()
                        + " "
                        + c.getMessage()
                        + ": "
                        + c.getInvalidValue()
                        + ";")
                .toArray()).replace("[","").replace("]","\n");

        return CrudControllerAdvice.errorResponse(
                HttpStatus.BAD_REQUEST, problem, request.getRequestURI(),
                Arrays.toString(request.getInputStream().readAllBytes())
        );
    }
}
