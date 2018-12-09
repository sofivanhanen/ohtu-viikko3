package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komennot.Erotus;
import laskin.komennot.Komento;
import laskin.komennot.Nollaa;
import laskin.komennot.Summa;

public class Tapahtumankuuntelija implements EventHandler {
    private Button peru;
    private Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button peru) {
        this.peru = peru;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, peru, sovellus) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, peru, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, peru, sovellus) );
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != peru) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }        
    }
}
