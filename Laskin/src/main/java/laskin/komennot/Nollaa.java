package laskin.komennot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Nollaa implements Komento {
    
    private TextField tulosKentta;
    private TextField syoteKentta;
    private Button nollaa;
    private Button peru;
    private Sovelluslogiikka sovellus;
    
    private int edellinenTulos;
    
    public Nollaa(TextField tulosKentta, TextField syoteKentta, Button nollaa, Button peru, Sovelluslogiikka sovellus) {
        this.tulosKentta = tulosKentta;
        this.syoteKentta = syoteKentta;
        this.nollaa = nollaa;
        this.peru = peru;
        this.sovellus = sovellus;
        
        edellinenTulos = 0;
    }

    @Override
    public void suorita() {
        try {
            edellinenTulos = Integer.parseInt(tulosKentta.getText());
        } catch (Exception e) {
            System.out.println("Tulos ei luku: " + tulosKentta.getText());
        }

        sovellus.nollaa();

        int laskunTulos = sovellus.tulos();

        syoteKentta.setText("");
        tulosKentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        peru.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenTulos);
        int laskunTulos = sovellus.tulos();
        tulosKentta.setText("" + laskunTulos);
        peru.disableProperty().set(true);
    }
    
}
