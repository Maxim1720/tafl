package kz.trankwilizator.tafl.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistsException extends EntityExistsException {

    private String message;
    private Object body;
    public ExistsException(String message, Object body){
        super(message);
        this.message = message;
        this.body = body;
    }
}
