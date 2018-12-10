package ohtu.kivipaperisakset;

public class KPS {
    
    public static KPS luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }
    
    public static KPS luoHelppoAIPeli() {
        return new KPSTekoaly();
    }
    
    public static KPS luoVaikeaAIPeli() {
        return new KPSParempiTekoaly();
    }
    
    public void pelaa() {
        System.out.println("Please overwrite for now");
    }
    
}
