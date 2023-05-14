import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MalaPremiumTest {
        
    public MalaPremiumTest()
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
        MalaPremium malaP = new MalaPremium();
        assertTrue(malaP != null);
        assertTrue(malaP.getCodBarras() > 0);
        assertNull(malaP.getEstado());
        assertEquals(0,malaP.getNumDonos());
        assertNull(malaP.getTransportadora());
        assertEquals("",malaP.getDescricao());
        assertEquals("",malaP.getMarca());
        assertEquals(0,malaP.getPrecoBase());
        assertEquals(0,malaP.getCorrecaoPreco());
        assertNull(malaP.getDimensao());
        assertEquals("",malaP.getMaterial());
        assertEquals(-1,malaP.getAnoLancamento());

        Transportadora trans = new TransportadoraPremium();
        malaP = new MalaPremium(Artigo.St.EXCELENTE, 0, trans, "Mala preta", "Balenciaga", 100, 0.23, Mala.Dim.MEDIA, "Cabedal", 2022);
        assertTrue(malaP != null);
        assertTrue(malaP.getCodBarras() > 0);
        assertEquals(Artigo.St.EXCELENTE,malaP.getEstado());
        assertEquals(0,malaP.getNumDonos());
        assertEquals(trans,malaP.getTransportadora());
        assertEquals("Mala preta",malaP.getDescricao());
        assertEquals("Balenciaga",malaP.getMarca());
        assertEquals(100,malaP.getPrecoBase());
        assertEquals(0.23,malaP.getCorrecaoPreco());
        assertEquals(Mala.Dim.MEDIA,malaP.getDimensao());
        assertEquals("Cabedal",malaP.getMaterial());
        assertEquals(2022,malaP.getAnoLancamento());
    }

    @Test
    public void testSetters() {
        MalaPremium malaP = new MalaPremium();
        malaP.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO,malaP.getEstado());
        malaP.setNumDonos(4);
        assertEquals(4,malaP.getNumDonos());
        Transportadora trans = new TransportadoraPremium();
        malaP.setTransportadora(trans);
        assertEquals(trans,malaP.getTransportadora());
        malaP.setDescricao("Grande demais");
        assertEquals("Grande demais",malaP.getDescricao());
        malaP.setMarca("Nike");
        assertEquals("Nike",malaP.getMarca());
        malaP.setPrecoBase(25);
        assertEquals(25,malaP.getPrecoBase());
        malaP.setCorrecaoPreco(0.2);
        assertEquals(0.2,malaP.getCorrecaoPreco());
        malaP.setDimensao(Mala.Dim.PEQUENA);
        assertEquals(Mala.Dim.PEQUENA,malaP.getDimensao());
        malaP.setMaterial("Couro");
        assertEquals("Couro",malaP.getMaterial());
        malaP.setAnoLancamento(2000);
        assertEquals(2000,malaP.getAnoLancamento());
    }

    @Test
    public void testCalculaValorArtigoNU() {
        Transportadora trans = new TransportadoraPremium();
        MalaPremium malaP = new MalaPremium(Artigo.St.EXCELENTE, 0, trans, "Mala preta", "Balenciaga", 100, 0.23, Mala.Dim.MEDIA, "Cabedal", 1989);
        assertEquals(440,malaP.calcularValorArtigoPremium(2023),0.01);
    }
}
