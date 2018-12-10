package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS{
    
    private TekoalyParannettu tekoaly;
    
    public KPSParempiTekoaly(int muistinKoko) {
        tekoaly = new TekoalyParannettu(muistinKoko);
    }

    @Override
    protected String toisenOsapuolenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }
}
