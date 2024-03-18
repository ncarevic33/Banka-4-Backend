package rs.edu.raf.korisnik.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException{

    public UserNotFoundException(String message) {
        super(message, ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
