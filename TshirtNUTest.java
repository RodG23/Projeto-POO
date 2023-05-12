import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TshirtNUTest {
    
    public TshirtNUTest()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testConstructorsAndGetters() {
        TshirtNU tshirtNU = new TshirtNU();
        assertEquals(0.5,tshirtNU.getDescBase());
        assertTrue(tshirtNU != null);
        assertTrue(tshirtNU.getCodBarras() > 0);
        assertNull(tshirtNU.getEstado());
        assertEquals(0,tshirtNU.getNumDonos());
        assertNull(tshirtNU.getTransportadora());
        assertEquals("",tshirtNU.getDescricao());
        assertEquals("",tshirtNU.getMarca());
        assertEquals(0,tshirtNU.getPrecoBase());
        assertEquals(0,tshirtNU.getCorrecaoPreco());
        assertNull(tshirtNU.getTamanho());
        assertNull(tshirtNU.getPadrao());

        Transportadora trans = new TransportadoraNormal();
        tshirtNU = new TshirtNU(Artigo.St.EXCELENTE, 0, trans, "Tshirt Top", "Karl Langerfeld", 120, 0.23, Tshirt.Tam.M,Tshirt.Pad.PALMEIRAS);
        assertTrue(tshirtNU != null);
        assertTrue(tshirtNU.getCodBarras() > 0);
        assertEquals(Artigo.St.EXCELENTE,tshirtNU.getEstado());
        assertEquals(0,tshirtNU.getNumDonos());
        assertEquals(trans,tshirtNU.getTransportadora());
        assertEquals("Tshirt Top",tshirtNU.getDescricao());
        assertEquals("Karl Langerfeld",tshirtNU.getMarca());
        assertEquals(120,tshirtNU.getPrecoBase());
        assertEquals(0.23,tshirtNU.getCorrecaoPreco());
        assertEquals(Tshirt.Tam.M,tshirtNU.getTamanho());
        assertEquals(Tshirt.Pad.PALMEIRAS,tshirtNU.getPadrao());
    }

    @Test
    public void testSetters() {
        TshirtNU tshirtNU = new TshirtNU();
        tshirtNU.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO,tshirtNU.getEstado());
        tshirtNU.setNumDonos(4);
        assertEquals(4,tshirtNU.getNumDonos());
        Transportadora trans = new TransportadoraNormal();
        tshirtNU.setTransportadora(trans);
        assertEquals(trans,tshirtNU.getTransportadora());
        tshirtNU.setDescricao("Veste L");
        assertEquals("Veste L",tshirtNU.getDescricao());
        tshirtNU.setMarca("Zara");
        assertEquals("Zara",tshirtNU.getMarca());
        tshirtNU.setPrecoBase(31);
        assertEquals(31,tshirtNU.getPrecoBase());
        tshirtNU.setCorrecaoPreco(0.2);
        assertEquals(0.2,tshirtNU.getCorrecaoPreco());
        tshirtNU.setTamanho(Tshirt.Tam.M);
        assertEquals(Tshirt.Tam.M,tshirtNU.getTamanho());
        tshirtNU.setPadrao(Tshirt.Pad.RISCAS);
        assertEquals(Tshirt.Pad.RISCAS,tshirtNU.getPadrao());
    }

    @Test
    public void testCalculaValorArtigoNU() {
        Transportadora trans = new TransportadoraNormal();
        TshirtNU tshirtNU = new TshirtNU(Artigo.St.EXCELENTE, 1, trans, "Tshirt top", "Karl Langerfeld", 120, 0.23, Tshirt.Tam.M,Tshirt.Pad.PALMEIRAS);
        assertEquals(60,tshirtNU.calcularValorArtigoNU(2023));
        TshirtNU tshirtNU2 = new TshirtNU(Artigo.St.EXCELENTE, 3, trans, "Tshirt top", "Karl Langerfeld", 120, 0.23, Tshirt.Tam.M,Tshirt.Pad.LISO);
        assertEquals(120,tshirtNU2.calcularValorArtigoNU(2023));
    }
}