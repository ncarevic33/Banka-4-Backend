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
public class PrenosSredstava {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //one RACUN to many PRENOS SREDSTAVA?


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
        PrenosSredstava that = (PrenosSredstava) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
