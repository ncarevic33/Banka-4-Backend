package rs.edu.raf.korisnik.exceptions;

import org.springframework.http.HttpStatus;

public class MailNotSendException extends CustomException{

    public MailNotSendException(String message) {
        super(message,ErrorCode.MAIL_NOT_SEND, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
