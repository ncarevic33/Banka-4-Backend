package rs.edu.raf.korisnik.servis;


import rs.edu.raf.korisnik.dto.OmiljeniKorisnikDTO;
import rs.edu.raf.korisnik.model.OmiljeniKorisnik;

public class OmiljeniKorisnikMapper {

    public static OmiljeniKorisnikDTO toDTO(OmiljeniKorisnik source) {
        return OmiljeniKorisnikDTO.builder()
                .id(source.getId())
                .idKorisnika(source.getIdKorisnika())
                .idRacunaPosaljioca(source.getIdRacunaPosaljioca())
                .nazivPrimaoca(source.getNazivPrimaoca())
                .idRacunaPrimaoca(source.getIdRacunaPrimaoca())
                .broj(source.getBroj())
                .sifraPlacanja(source.getSifraPlacanja())
                .build();
    }

    public static OmiljeniKorisnik toEntity(OmiljeniKorisnikDTO source) {
        OmiljeniKorisnik omiljeniKorisnik = new OmiljeniKorisnik();
        omiljeniKorisnik.setId(source.getId());
        omiljeniKorisnik.setIdKorisnika(source.getIdKorisnika());
        omiljeniKorisnik.setIdRacunaPosaljioca(source.getIdRacunaPosaljioca());
        omiljeniKorisnik.setNazivPrimaoca(source.getNazivPrimaoca());
        omiljeniKorisnik.setIdRacunaPrimaoca(source.getIdRacunaPrimaoca());
        omiljeniKorisnik.setBroj(source.getBroj());
        omiljeniKorisnik.setSifraPlacanja(source.getSifraPlacanja());

        return omiljeniKorisnik;
    }

}
