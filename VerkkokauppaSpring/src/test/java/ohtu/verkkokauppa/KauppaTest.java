package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Kauppa kauppa;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "appelsiinimehu", 8));
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "suklaalevy", 50));

        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void maidonOstaminenOnnistuu() {        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void maidonJaMehunOstaminenOnnistuu() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5 + 8));
    }
    
    @Test
    public void kahdenMaidonOstaminenOnnistuu() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5 + 5));
    }
    
    @Test
    public void maitoVeloitetaanSuklaaEi() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void aloitaAsiontiNollaaOstoskorin() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("anneli", "98765");
        
        verify(pankki).tilisiirto(eq("anneli"), anyInt(), eq("98765"), anyString(), eq(8));
    }
    
    @Test
    public void viitenumeroUusitaanAina() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("hilda", "76543");
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("anneli", "98765");
        
        verify(viite, times(2)).uusi();
    }
    
}
