package rs.edu.raf.transakcija.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.edu.raf.korisnik.model.Korisnik;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Uplata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //one RACUN to many UPLATA?

    @OneToOne
    private Korisnik from;

    @OneToOne
    private Korisnik to;

    @Column
    private Integer suma;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uplata uplata = (Uplata) o;
        return Objects.equals(from, uplata.from) && Objects.equals(to, uplata.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
