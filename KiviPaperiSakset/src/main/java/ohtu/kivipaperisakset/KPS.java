package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {

    private static final int MUISTINKOKO_DEFAULT = 20;

    protected Scanner scanner = new Scanner(System.in);
    private Tuomari tuomari = new Tuomari();

    protected KPS() {
    }
    
    public static KPS luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPS luoHelppoAIPeli() {
        return new KPSTekoaly();
    }

    public static KPS luoVaikeaAIPeli() {
        return new KPSParempiTekoaly(MUISTINKOKO_DEFAULT);
    }

    public final void pelaa() {
        System.out.println("Tervetuloa peliin.");
        System.out.println("Peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        String ensimmainen;
        String toinen;
        while (true) {
            ensimmainen = ensimmaisenOsapuolenSiirto();
            if (!onkoOkSiirto(ensimmainen)) break;
            toinen = toisenOsapuolenSiirto();
            if (!onkoOkSiirto(toinen)) break;
            tuomari.kirjaaSiirto(ensimmainen, toinen);
            System.out.println(tuomari);
            System.out.println();
        }
        printtaaLopetustekstit();
    }

    private boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    private String ensimmaisenOsapuolenSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    private void printtaaLopetustekstit() {
        System.out.println("\nLopullinen pistetilanne:");
        System.out.println(tuomari);
        System.out.println("\nKiitos kun pelasit!");
    }

    protected abstract String toisenOsapuolenSiirto();

}
