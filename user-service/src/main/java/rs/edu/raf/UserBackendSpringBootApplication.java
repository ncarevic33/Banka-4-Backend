package rs.edu.raf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rs.edu.raf.korisnik.servis.KorisnikServis;
import rs.edu.raf.korisnik.servis.impl.KorisnikServisImpl;


@SpringBootApplication
public class UserBackendSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBackendSpringBootApplication.class,args);
    }
}