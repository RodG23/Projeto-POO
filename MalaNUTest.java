import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MalaNUTest {
    
    public MalaNUTest()
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
        MalaNU malaNU = new MalaNU();
        assertTrue(malaNU != null);
        assertTrue(malaNU.getCodBarras() > 0);
        assertNull(malaNU.getEstado());
        assertEquals(0,malaNU.getNumDonos());
        assertNull(malaNU.getTransportadora());
        assertEquals("",malaNU.getDescricao());
        assertEquals("",malaNU.getMarca());
        assertEquals(0,malaNU.getPrecoBase());
        assertEquals(0,malaNU.getCorrecaoPreco());
        assertNull(malaNU.getDimensao());
        assertEquals("",malaNU.getMaterial());
        assertEquals(-1,malaNU.getAnoLancamento());

        Transportadora trans = new TransportadoraNormal();
        malaNU = new MalaNU(Artigo.St.EXCELENTE, 0, trans, "Mala preta", "Balenciaga", 100, 0.23, Mala.Dim.MEDIA, "Cabedal", 2022);
        assertTrue(malaNU != null);
        assertTrue(malaNU.getCodBarras() > 0);
        assertEquals(Artigo.St.EXCELENTE,malaNU.getEstado());
        assertEquals(0,malaNU.getNumDonos());
        assertEquals(trans,malaNU.getTransportadora());
        assertEquals("Mala preta",malaNU.getDescricao());
        assertEquals("Balenciaga",malaNU.getMarca());
        assertEquals(100,malaNU.getPrecoBase());
        assertEquals(0.23,malaNU.getCorrecaoPreco());
        assertEquals(Mala.Dim.MEDIA,malaNU.getDimensao());
        assertEquals("Cabedal",malaNU.getMaterial());
        assertEquals(2022,malaNU.getAnoLancamento());
    }

    @Test
    public void testSetters() {
        MalaNU malaNU = new MalaNU();
        malaNU.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO,malaNU.getEstado());
        malaNU.setNumDonos(4);
        assertEquals(4,malaNU.getNumDonos());
        Transportadora trans = new TransportadoraNormal();
        malaNU.setTransportadora(trans);
        assertEquals(trans,malaNU.getTransportadora());
        malaNU.setDescricao("Grande demais");
        assertEquals("Grande demais",malaNU.getDescricao());
        malaNU.setMarca("Nike");
        assertEquals("Nike",malaNU.getMarca());
        malaNU.setPrecoBase(25);
        assertEquals(25,malaNU.getPrecoBase());
        malaNU.setCorrecaoPreco(0.2);
        assertEquals(0.2,malaNU.getCorrecaoPreco());
        malaNU.setDimensao(Mala.Dim.PEQUENA);
        assertEquals(Mala.Dim.PEQUENA,malaNU.getDimensao());
        malaNU.setMaterial("Couro");
        assertEquals("Couro",malaNU.getMaterial());
        malaNU.setAnoLancamento(2000);
        assertEquals(2000,malaNU.getAnoLancamento());
    }

    @Test
    public void testCalculaValorArtigoNU() {
        Transportadora trans = new TransportadoraNormal();
        MalaNU malaNU = new MalaNU(Artigo.St.EXCELENTE, 0, trans, "Mala preta", "Balenciaga", 100, 0.23, Mala.Dim.MEDIA, "Cabedal", 2022);
        assertEquals(95,malaNU.calcularValorArtigoNU(2023));
    }
}
