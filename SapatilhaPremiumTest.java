import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SapatilhaPremiumTest {
                
    public SapatilhaPremiumTest()
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
        SapatilhaPremium sapatilhaP = new SapatilhaPremium();
        assertTrue(sapatilhaP != null);
        assertTrue(sapatilhaP.getCodBarras() > 0);
        assertNull(sapatilhaP.getEstado());
        assertEquals(0,sapatilhaP.getNumDonos());
        assertNull(sapatilhaP.getTransportadora());
        assertEquals("",sapatilhaP.getDescricao());
        assertEquals("",sapatilhaP.getMarca());
        assertEquals(0,sapatilhaP.getPrecoBase());
        assertEquals(0,sapatilhaP.getCorrecaoPreco());
        assertEquals(-1, sapatilhaP.getTamanho());
        assertFalse(sapatilhaP.getAtacadores());
        assertEquals("",sapatilhaP.getCor());
        assertEquals(-1,sapatilhaP.getAnoLancamento());

        Transportadora trans = new TransportadoraPremium();
        sapatilhaP = new SapatilhaPremium(Artigo.St.EXCELENTE, 0, trans, "Sapatilhas novas", "Adidas", 1000, 0.23, 42, true, "Azul premium", 2018);
        assertTrue(sapatilhaP != null);
        assertTrue(sapatilhaP.getCodBarras() > 0);
        assertEquals(Artigo.St.EXCELENTE,sapatilhaP.getEstado());
        assertEquals(0,sapatilhaP.getNumDonos());
        assertEquals(trans,sapatilhaP.getTransportadora());
        assertEquals("Sapatilhas novas",sapatilhaP.getDescricao());
        assertEquals("Adidas",sapatilhaP.getMarca());
        assertEquals(1000,sapatilhaP.getPrecoBase());
        assertEquals(0.23,sapatilhaP.getCorrecaoPreco());
        assertEquals(42, sapatilhaP.getTamanho());
        assertTrue(sapatilhaP.getAtacadores());
        assertEquals("Azul premium",sapatilhaP.getCor());
        assertEquals(2018,sapatilhaP.getAnoLancamento());
    }

    @Test
    public void testSetters() {
        SapatilhaPremium sapatilhaP = new SapatilhaPremium();
        sapatilhaP.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO,sapatilhaP.getEstado());
        sapatilhaP.setNumDonos(4);
        assertEquals(4,sapatilhaP.getNumDonos());
        Transportadora trans = new TransportadoraPremium();
        sapatilhaP.setTransportadora(trans);
        assertEquals(trans,sapatilhaP.getTransportadora());
        sapatilhaP.setDescricao("Grande demais");
        assertEquals("Grande demais",sapatilhaP.getDescricao());
        sapatilhaP.setMarca("Nike");
        assertEquals("Nike",sapatilhaP.getMarca());
        sapatilhaP.setPrecoBase(25);
        assertEquals(25,sapatilhaP.getPrecoBase());
        sapatilhaP.setCorrecaoPreco(0.2);
        assertEquals(0.2,sapatilhaP.getCorrecaoPreco());
        sapatilhaP.setTamanho(41);
        assertEquals(41,sapatilhaP.getTamanho());
        sapatilhaP.setAtacadores(true);
        assertTrue(sapatilhaP.getAtacadores());
        sapatilhaP.setCor("Rosa");
        assertEquals("Rosa",sapatilhaP.getCor());
        sapatilhaP.setAnoLancamento(2000);
        assertEquals(2000,sapatilhaP.getAnoLancamento());
    }

    @Test
    public void testCalculaValorArtigoNU() {
        Transportadora trans = new TransportadoraPremium();
        SapatilhaPremium sapatilhaP = new SapatilhaPremium(Artigo.St.EXCELENTE, 0, trans, "Sapatilhas novas", "Adidas", 1000, 0.23, 42, true, "Azul premium", 2018);
        assertEquals(1500,sapatilhaP.calcularValorArtigoPremium(2023));
    }
}
