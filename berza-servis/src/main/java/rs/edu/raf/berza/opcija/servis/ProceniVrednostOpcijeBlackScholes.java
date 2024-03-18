package rs.edu.raf.berza.opcija.servis;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ProceniVrednostOpcijeBlackScholes {

    //opcija se kupuje,prodaje ili iskoriscava
    //opcija ima predefinisanu cenu prodaje ili kupovine akcija i predefinisan datum isteka
    //opcija se moze iskoristiti bilo kada pre datuma isteka


    //glavna razlika opcija i kupovine akcije u realnom vremenu jeste sto opcija ima fiksnu cenu akcije
    //odnosno iako trenutna cena akcije menja vrednost, cena akcije u opciji ostaje nepromenjena

                        //i za call i za put opcije
    public static double proceniVrednostOpcije(
                                        //stvarna trenutna trzisna cena akcije koja se menja u svakom trenutku
                                        double trenutnaCenaOsnovneAkcije,

                                        //cena izvrsenja opcije je kupovina(call) ili prodaja(put) akcija u okviru opcije bilo kada do isteka opcije
                                        double cenaIzvrsenjaOpcije,
                                        double stopaBezRizika,
                                        double brojGodinaDoIstekaOpcije,
                                        double volatilnostCeneOsnovneAkcije){

        double d1 = (Math.log(trenutnaCenaOsnovneAkcije / cenaIzvrsenjaOpcije) +
                    (stopaBezRizika + (volatilnostCeneOsnovneAkcije * volatilnostCeneOsnovneAkcije) / 2) * brojGodinaDoIstekaOpcije)
                     / (volatilnostCeneOsnovneAkcije * Math.sqrt(brojGodinaDoIstekaOpcije));

        double d2 = d1 - volatilnostCeneOsnovneAkcije * Math.sqrt(brojGodinaDoIstekaOpcije);

        NormalDistribution normalDistribution = new NormalDistribution();
        double Nd1 = normalDistribution.cumulativeProbability(d1);
        double Nd2 = normalDistribution.cumulativeProbability(d2);


        double vrednostOpcije = trenutnaCenaOsnovneAkcije * Nd1 - cenaIzvrsenjaOpcije *
                                Math.exp(-stopaBezRizika * brojGodinaDoIstekaOpcije) * Nd2;

        return vrednostOpcije;
    }


}
