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

    private int edellinenMuuttuja;

    public Summa(TextField tulosKentta, TextField syoteKentta, Button nollaa, Button peru, Sovelluslogiikka sovellus) {
        this.tulosKentta = tulosKentta;
        this.syoteKentta = syoteKentta;
        this.nollaa = nollaa;
        this.peru = peru;
        this.sovellus = sovellus;

        edellinenMuuttuja = 0;
    }

    @Override
    public void suorita() {
        edellinenMuuttuja = 0;

        try {
            edellinenMuuttuja = Integer.parseInt(syoteKentta.getText());
        } catch (Exception e) {
        }

        sovellus.plus(edellinenMuuttuja);

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
        sovellus.miinus(edellinenMuuttuja);
        int laskunTulos = sovellus.tulos();
        tulosKentta.setText("" + laskunTulos);
        peru.disableProperty().set(true);
    }

}
