
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
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti liian pieni: " + kapasiteetti);
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoko liian pieni: " + kasvatuskoko);
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!sisaltaa(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujono.length) {
                int[] taulukkoOld = new int[lukujono.length];
                kopioiTaulukko(lukujono, taulukkoOld);
                lukujono = new int[alkioidenLkm + KASVATUSKOKO_OLETUS];
                kopioiTaulukko(taulukkoOld, lukujono);
            }
            return true;
        }
        return false;
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
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                indeksi = i;
                lukujono[indeksi] = 0;
                break;
            }
        }
        if (indeksi != -1) {
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
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            joukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            joukko.lisaa(bTaulu[i]);
        }
        return joukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    joukko.lisaa(bTaulu[j]);
                }
            }
        }
        return joukko;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            joukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            joukko.poista(i);
        }
        return joukko;
    }       
}