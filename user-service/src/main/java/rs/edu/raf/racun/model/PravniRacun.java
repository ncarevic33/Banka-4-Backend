package rs.edu.raf.racun.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.edu.raf.korisnik.model.Korisnik;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PravniRacun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Korisnik client;
}