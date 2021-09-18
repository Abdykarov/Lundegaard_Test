package cz.abdykili.lundegaard.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ContactFormNotFoundException extends AppRequestException {
    private final String message;
    private final HttpStatus httpStatus;

    public ContactFormNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
