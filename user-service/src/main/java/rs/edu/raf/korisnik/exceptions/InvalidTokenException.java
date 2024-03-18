package rs.edu.raf.korisnik.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CustomException{

    public InvalidTokenException(String message) {
        super(message,ErrorCode.INVALID_TOKEN, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
