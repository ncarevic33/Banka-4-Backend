package rs.edu.raf.korisnik.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Date;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String Ime;

    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String Prezime;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])(\\\\d{2})(\\\\d{3})$\n", message = "Nepravilan JMBG!")
    private Long JMBG;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Datum mora biti formata yyyy-MM-dd!")
    private Date DatumRodjenja;

    @Pattern(regexp = "^[M|F]$", message = "Pol može biti M ili F!")
    private String Pol;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email adresa mora biti validna!")
    private String Email;

    @Pattern(regexp = "^(\\+381|0)6[0-6]\\d{6,7}$\n", message = "Broj telefona mora biti formata +381/0 6 0-6 praćen sa 6 ili 7 cifara!")
    private String BrojTelefona;

    @NotNull
    private String Adresa;

    private String Password;

    private String SaltPassword;

    @ElementCollection
    private List<Integer> PovezaniRacuni;

    private boolean Aktivan;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public void setPol(String pol) {
        Pol = pol;
    }

    public void setBrojTelefona(String brojTelefona) {
        BrojTelefona = brojTelefona;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPovezaniRacuni(List<Integer> povezaniRacuni) {
        PovezaniRacuni = povezaniRacuni;
    }

    public void setAktivan(boolean aktivan) {
        Aktivan = aktivan;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public Long getJMBG() {
        return JMBG;
    }

    public Date getDatumRodjenja() {
        return DatumRodjenja;
    }

    public String getPol() {
        return Pol;
    }

    public String getEmail() {
        return Email;
    }

    public String getBrojTelefona() {
        return BrojTelefona;
    }

    public String getAdresa() {
        return Adresa;
    }

    public String getPassword() {
        return Password;
    }

    public String getSaltPassword() {
        return SaltPassword;
    }

    public List<Integer> getPovezaniRacuni() {
        return PovezaniRacuni;
    }

    public boolean isAktivan() {
        return Aktivan;
    }
}
