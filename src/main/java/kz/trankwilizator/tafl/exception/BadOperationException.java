package kz.trankwilizator.tafl.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadOperationException extends RuntimeException{

    @Getter
    private final Object body;

    public BadOperationException(String message, Object body){
        super(message);
        this.body = body;
    }
}
