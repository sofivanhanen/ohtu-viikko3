package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPS {

    @Override
    protected String toisenOsapuolenSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }
}
