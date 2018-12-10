package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KPS {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly(int muistinKoko) {
        tekoaly = new TekoalyParannettu(muistinKoko);
    }

    private KPSParempiTekoaly() {
    }

    @Override
    protected String toisenOsapuolenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(tokanSiirto);
        return tokanSiirto;
    }
}
