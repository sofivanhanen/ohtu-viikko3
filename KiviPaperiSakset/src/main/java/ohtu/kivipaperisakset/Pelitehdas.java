package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pelitehdas {

    public static void aloitaPeli(String komento) {
        KPS peli = paataSopivaPeli(komento);
        if (peli != null) {
            peli.pelaa();
        } else {
            System.out.println("Epäkelpo pelimoodi valittu.");
        }
    }

    private static KPS paataSopivaPeli(String komento) {
        if (komento.endsWith("a")) {
            return KPS.luoKaksinpeli();
        } else if (komento.endsWith("b")) {
            return KPS.luoHelppoAIPeli();
        } else if (komento.endsWith("c")) {
            return KPS.luoVaikeaAIPeli();
        }
        return null;
    }

}
