package ohtu.intjoukkosovellus;

public class IntJoukko {

    private final static int KAPASITEETTI_OLETUS = 5,
            KASVATUSKOKO_OLETUS = 5;

    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioidenLkm;

    public IntJoukko() {
        this(KAPASITEETTI_OLETUS, KASVATUSKOKO_OLETUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, KASVATUSKOKO_OLETUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        varmistaSopivatArvot(kapasiteetti, kasvatuskoko);
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private void varmistaSopivatArvot(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0) {
            throw new IllegalArgumentException("Kapasiteetti liian pieni: " + kapasiteetti);
        }
        if (kasvatuskoko <= 0) {
            throw new IllegalArgumentException("Kasvatuskoko liian pieni: " + kasvatuskoko);
        }
    }

    public boolean lisaa(int luku) {
        if (!sisaltaa(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujono.length) {
                kasvata();
            }
            return true;
        }
        return false;
    }

    private void kasvata() {
        int[] taulukkoOld = new int[lukujono.length];
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + KASVATUSKOKO_OLETUS];
        kopioiTaulukko(taulukkoOld, lukujono);
    }

    public boolean sisaltaa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = poistaLuku(luku);
        return siirraLuvut(indeksi);
    }

    private int poistaLuku(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                indeksi = i;
                lukujono[indeksi] = 0;
                break;
            }
        }
        return indeksi;
    }

    private boolean siirraLuvut(int indeksi) {
        if (indeksi >= 0) {
            for (int i = indeksi; i < alkioidenLkm - 1; i++) {
                lukujono[i] = lukujono[i + 1];
            }
            lukujono[alkioidenLkm - 1] = 0;
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    public int[] toIntArray() {
        int[] jono = new int[alkioidenLkm];
        for (int i = 0; i < jono.length; i++) {
            jono[i] = lukujono[i];
        }
        return jono;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += lukujono[i];
            if (i < alkioidenLkm - 1) {
                tuotos += ", ";
            }
        }
        tuotos += "}";
        return tuotos;
    }

    private static void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
        if (vanha.length < uusi.length) {
            for (int i = vanha.length; i < uusi.length; i++) {
                uusi[i] = 0;
            }
        }
    }

    public static IntJoukko yhdiste(IntJoukko aJoukko, IntJoukko bJoukko) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = aJoukko.toIntArray();
        int[] bTaulu = bJoukko.toIntArray();
        lisaaKaikki(joukko, aTaulu);
        lisaaKaikki(joukko, bTaulu);
        return joukko;
    }

    public static IntJoukko leikkaus(IntJoukko aJoukko, IntJoukko bJoukko) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = aJoukko.toIntArray();
        int[] bTaulu = bJoukko.toIntArray();
        lisaaJosMolemmissa(joukko, aTaulu, bTaulu);
        return joukko;
    }

    public static IntJoukko erotus(IntJoukko aJoukko, IntJoukko bJoukko) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = aJoukko.toIntArray();
        int[] bTaulu = bJoukko.toIntArray();
        lisaaKaikki(joukko, aTaulu);
        poistaKaikki(joukko, bTaulu);
        return joukko;
    }

    private static void lisaaKaikki(IntJoukko joukko, int[] arvot) {
        for (int i = 0; i < arvot.length; i++) {
            joukko.lisaa(arvot[i]);
        }
    }

    private static void poistaKaikki(IntJoukko joukko, int[] arvot) {
        for (int i = 0; i < arvot.length; i++) {
            joukko.poista(arvot[i]);
        }
    }

    private static void lisaaJosMolemmissa(IntJoukko joukko, int[] aTaulu, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    joukko.lisaa(bTaulu[j]);
                }
            }
        }
    }
}
