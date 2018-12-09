package laskin.komennot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Summa implements Komento {
    
    private TextField tulosKentta;
    private TextField syoteKentta;
    private Button nollaa;
    private Button peru;
    private Sovelluslogiikka sovellus;
    
    public Summa(TextField tulosKentta, TextField syoteKentta, Button nollaa, Button peru, Sovelluslogiikka sovellus) {
        this.tulosKentta = tulosKentta;
        this.syoteKentta = syoteKentta;
        this.nollaa = nollaa;
        this.peru = peru;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syoteKentta.getText());
        } catch (Exception e) {
        }

        sovellus.plus(arvo);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
