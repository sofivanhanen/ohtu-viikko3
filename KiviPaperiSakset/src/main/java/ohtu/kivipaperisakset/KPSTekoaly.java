package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS {

    private Tekoaly tekoaly = new Tekoaly();

    @Override
    protected String toisenOsapuolenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }
}
