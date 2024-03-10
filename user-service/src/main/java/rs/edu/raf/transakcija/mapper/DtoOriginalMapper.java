package rs.edu.raf.transakcija.mapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.transakcija.dto.*;
import rs.edu.raf.transakcija.model.*;
import rs.edu.raf.transakcija.model.SablonTransakcije;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class DtoOriginalMapper {

    public Object newDtoToNewOriginal(Object dto){

        if(dto instanceof NovaUplataDTO){

            //NEMA ID SVE DOK SE NE UPISE
            Uplata novaUplata = new Uplata();
            //..
            return novaUplata;
        }else if(dto instanceof NoviPrenostSredstavaDTO){
            PrenosSredstava noviPrenosSredstava = new PrenosSredstava();
            //..
            return noviPrenosSredstava;
        }else if(dto instanceof NoviSablonTransakcijeDTO){
            SablonTransakcije sablonTransakcije = new SablonTransakcije();
            //..
            return sablonTransakcije;
        }

        return null;
    }

    public Object originalToDtoWithId(Object original){

        List<Object> dtos = new ArrayList<>();

        if(original instanceof ArrayList){
            for(Object o:(ArrayList)original)
                dtos.add(doMappingOriginalToDtoWithId(o));

            return dtos;
        }else return doMappingOriginalToDtoWithId(original);


    }

    private Object doMappingOriginalToDtoWithId(Object original){

        if(original instanceof Korisnik) {
            return null;
        }else if(original instanceof Uplata){
            UplataDTO uplataDTO = new UplataDTO();
            //..
            return uplataDTO;
        }else if(original instanceof PrenosSredstava){
            PrenosSredstavaDTO prenosSredstavaDTO = new PrenosSredstavaDTO();
            //..
            return prenosSredstavaDTO;
        }else if(original instanceof SablonTransakcije){
            rs.edu.raf.transakcija.dto.SablonTransakcijeDTO sablonTransakcijeDTO = new rs.edu.raf.transakcija.dto.SablonTransakcijeDTO();
            //..
            return sablonTransakcijeDTO;
        }
        return null;
    }
}
