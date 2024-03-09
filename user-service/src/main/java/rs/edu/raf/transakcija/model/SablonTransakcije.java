package rs.edu.raf.transakcija.model;

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
                //SablonTransakcije NASTAJE IZ PODATKA NEKE TRANSAKCIJE KADA SE TRANSAKCIJA DODA U SABLON
public class SablonTransakcije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //KORISNIK CIJI SU SABLONI
    @ManyToOne
    private Korisnik client;

    //PREKOPIRACE SE IZ POSTOJECE TRANSAKCIJE
    @OneToOne
    private Korisnik from;

    //PREKOPIRACE SE IZ POSTOJECE TRANSAKCIJE
    @OneToOne
    private Korisnik to;

    //PREKOPIRACE SE IZ POSTOJECE TRANSAKCIJE
    @Column
    private Integer suma;

}
