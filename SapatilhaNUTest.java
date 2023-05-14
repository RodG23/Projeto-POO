import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SapatilhaNUTest {
            
    public SapatilhaNUTest()
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
        SapatilhaNU sapatilhaNU = new SapatilhaNU();
        assertTrue(sapatilhaNU != null);
        assertTrue(sapatilhaNU.getCodBarras() > 0);
        assertNull(sapatilhaNU.getEstado());
        assertEquals(0,sapatilhaNU.getNumDonos());
        assertNull(sapatilhaNU.getTransportadora());
        assertEquals("",sapatilhaNU.getDescricao());
        assertEquals("",sapatilhaNU.getMarca());
        assertEquals(0,sapatilhaNU.getPrecoBase());
        assertEquals(0,sapatilhaNU.getCorrecaoPreco());
        assertEquals(-1, sapatilhaNU.getTamanho());
        assertFalse(sapatilhaNU.getAtacadores());
        assertEquals("",sapatilhaNU.getCor());
        assertEquals(-1,sapatilhaNU.getAnoLancamento());

        Transportadora trans = new TransportadoraNormal();
        sapatilhaNU = new SapatilhaNU(Artigo.St.EXCELENTE, 0, trans, "Sapatilhas novas", "Adidas", 100, 0.23, 42, true, "Azul", 2023);
        assertTrue(sapatilhaNU != null);
        assertTrue(sapatilhaNU.getCodBarras() > 0);
        assertEquals(Artigo.St.EXCELENTE,sapatilhaNU.getEstado());
        assertEquals(0,sapatilhaNU.getNumDonos());
        assertEquals(trans,sapatilhaNU.getTransportadora());
        assertEquals("Sapatilhas novas",sapatilhaNU.getDescricao());
        assertEquals("Adidas",sapatilhaNU.getMarca());
        assertEquals(100,sapatilhaNU.getPrecoBase());
        assertEquals(0.23,sapatilhaNU.getCorrecaoPreco());
        assertEquals(42, sapatilhaNU.getTamanho());
        assertTrue(sapatilhaNU.getAtacadores());
        assertEquals("Azul",sapatilhaNU.getCor());
        assertEquals(2023,sapatilhaNU.getAnoLancamento());
    }

    @Test
    public void testSetters() {
        SapatilhaNU sapatilhaNU = new SapatilhaNU();
        sapatilhaNU.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO,sapatilhaNU.getEstado());
        sapatilhaNU.setNumDonos(4);
        assertEquals(4,sapatilhaNU.getNumDonos());
        Transportadora trans = new TransportadoraNormal();
        sapatilhaNU.setTransportadora(trans);
        assertEquals(trans,sapatilhaNU.getTransportadora());
        sapatilhaNU.setDescricao("Grande demais");
        assertEquals("Grande demais",sapatilhaNU.getDescricao());
        sapatilhaNU.setMarca("Nike");
        assertEquals("Nike",sapatilhaNU.getMarca());
        sapatilhaNU.setPrecoBase(25);
        assertEquals(25,sapatilhaNU.getPrecoBase());
        sapatilhaNU.setCorrecaoPreco(0.2);
        assertEquals(0.2,sapatilhaNU.getCorrecaoPreco());
        sapatilhaNU.setTamanho(41);
        assertEquals(41,sapatilhaNU.getTamanho());
        sapatilhaNU.setAtacadores(true);
        assertTrue(sapatilhaNU.getAtacadores());
        sapatilhaNU.setCor("Rosa");
        assertEquals("Rosa",sapatilhaNU.getCor());
        sapatilhaNU.setAnoLancamento(2000);
        assertEquals(2000,sapatilhaNU.getAnoLancamento());
    }

    @Test
    public void testCalculaValorArtigoNU() {
        Transportadora trans = new TransportadoraNormal();
        SapatilhaNU sapatilhaNU = new SapatilhaNU(Artigo.St.EXCELENTE, 0, trans, "Sapatilha nova", "Adidas", 100, 0.23, 38, true, "Amarelo", 2022);
        assertEquals(95,sapatilhaNU.calcularValorArtigoNU(2023),0.01);
    }
}
