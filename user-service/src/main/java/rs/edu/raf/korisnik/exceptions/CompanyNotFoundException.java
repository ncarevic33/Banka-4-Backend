package rs.edu.raf.korisnik.exceptions;

import org.springframework.http.HttpStatus;

public class CompanyNotFoundException extends CustomException{

    public CompanyNotFoundException(String message) {
        super(message,ErrorCode.COMPANY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
