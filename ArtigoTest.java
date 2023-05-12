import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtigoTest {
    
    public ArtigoTest()
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
    public void testConstructors() {
        Artigo art = new Artigo();
        assertTrue(art != null);
        art = new Artigo(Artigo.St.EXCELENTE, 0, new Transportadora(), "Calças de ganga preta", "Levi's", "30", 0.2);
        assertTrue(art != null);
    }

    @Test
    public void testGetters() {
        Artigo artigo = new Artigo(Artigo.St.EXCELENTE, 2, null, "Calças de ganga preta", "Levi's", 30, 0.2);
        assertEquals(1, artigo.getCodBarras());
        assertEquals(Artigo.St.EXCELENTE, artigo.getEstado());
        assertEquals(2, artigo.getNumDonos());
        assertEquals(null, artigo.getTransportadora());
        assertEquals("Calças de ganga preta", artigo.getDescricao());
        assertEquals("Levi's", artigo.getMarca());
        assertEquals(30, artigo.getPrecoBase());
        assertEquals(0.2, artigo.getCorrecaoPreco());
    }

    @Test
    public void testSetters() {
        Artigo artigo = new Artigo();
        artigo.setEstado(Artigo.St.MEDIO);
        assertEquals(Artigo.St.MEDIO, artigo.getEstado());
        artigo.setNumDonos(4);
        assertEquals(4, artigo.getNumDonos());
        artigo.setTransportadora(new Transportadora());
        assertEquals(new Transportadora(), artigo.getTransportadora());
        artigo.setDescricao("A Mensagem");
        assertEquals("A Mensagem", artigo.getDescricao());
        artigo.setMarca("Fernando Pessoa");
        assertEquals("Fernando Pessoa", artigo.getMarca());
        artigo.setPrecoBase(20.0);
        assertEquals(20.0, artigo.getPrecoBase());
        artigo.setCorrecaoPreco(0.1);
        assertEquals(0.1, artigo.getCorrecaoPreco());
    }

    @Test
    public void testClone() {
        Artigo artigo1 = new Artigo(Artigo.St.EXCELENTE, 0, new Transportadora(), "Calças de ganga preta", "Levi's", "30", 0.2);
        Artigo artigo2 = artigo1.clone();
        assertEquals(artigo1, artigo2);
    }
}
