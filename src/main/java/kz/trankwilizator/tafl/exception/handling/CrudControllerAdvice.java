package kz.trankwilizator.tafl.exception.handling;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.error.ErrorDto;
import kz.trankwilizator.tafl.exception.ExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CrudControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorDto> handleEntityExistsException(RuntimeException e, HttpServletRequest request){
        StringBuilder message = new StringBuilder();

        for (Throwable t: e.getSuppressed()){
            message.append(t.getMessage()).append(' ');
        }
        message.append(e.getMessage()).append(' ');
        return errorResponse(HttpStatus.NOT_FOUND, message.toString(), request.getRequestURI(), null);
    }


    @ExceptionHandler(value = {ExistsException.class})
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(ExistsException e, HttpServletRequest request){
        StringBuilder message = new StringBuilder();

        for (Throwable t: e.getSuppressed()){
            message.append(t.getMessage()).append("\n");
        }
        message.append(e.getMessage()).append("\n");
        return errorResponse(HttpStatus.CONFLICT, message.toString(), request.getRequestURI(), e.getBody());
    }

    public static ResponseEntity<ErrorDto> errorResponse(HttpStatus status, String message, String path, Object body) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(message);
        errorDto.setStatus(status.value());
        errorDto.setPath(path);
        errorDto.setBody(body);
        return ResponseEntity.status(status).body(errorDto);
    }
}
