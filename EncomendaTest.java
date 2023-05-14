import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EncomendaTest {
    public EncomendaTest()
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
        Encomenda encomenda = new Encomenda();
        assertTrue(encomenda != null);
        assertTrue(encomenda.getId() > 0);
        assertEquals(Encomenda.St.PENDENTE, encomenda.getEstado());
        assertEquals(null, encomenda.getDimensao());
        assertTrue(encomenda.getArtigos().isEmpty());
        assertEquals(0, encomenda.getPrecoFinal());
        assertNull(encomenda.getDataEntrega());

        Map<Integer,Artigo> art = new HashMap<>();
        encomenda = new Encomenda(Encomenda.St.PENDENTE, Encomenda.Dim.PEQUENA, art, 0, LocalDate.of(2023, 5, 17));
        assertTrue(encomenda != null);
        assertTrue(encomenda.getId() > 0);
        assertEquals(Encomenda.St.PENDENTE, encomenda.getEstado());
        assertEquals(Encomenda.Dim.PEQUENA, encomenda.getDimensao());
        assertEquals(art, encomenda.getArtigos());
        assertEquals(0, encomenda.getPrecoFinal());
        assertEquals(LocalDate.of(2023, 5, 17), encomenda.getDataEntrega());
    }

    @Test
    public void testSetters() {
        Encomenda encomenda = new Encomenda();
        encomenda.setEstado(Encomenda.St.FINALIZADA);
        assertEquals(Encomenda.St.FINALIZADA, encomenda.getEstado());
        encomenda.setDimesao(Encomenda.Dim.MEDIA);
        assertEquals(Encomenda.Dim.MEDIA, encomenda.getDimensao());
        encomenda.setArtigos(new HashMap<Integer,Artigo>());
        assertEquals(new HashMap<Integer,Artigo>(), encomenda.getArtigos());
        encomenda.setDataCriacao(LocalDate.of(2023,5,17));
        assertEquals(LocalDate.of(2023,5,17), encomenda.getDataCriacao());
        encomenda.setDataEntrega(LocalDate.of(2023, 5, 18));
        assertEquals(LocalDate.of(2023, 5, 18), encomenda.getDataEntrega());
        encomenda.setPrecoFinal(20.0);
        assertEquals(20.0, encomenda.getPrecoFinal());
    }

    @Test
    public void testAddArtigo() {
        Artigo mala = new MalaNU(Artigo.St.EXCELENTE, 0, new TransportadoraNormal(), "Mala Preta", "Chanel", 99.99, 0.23, Mala.Dim.MEDIA, "Pele de cobra", 1990);
        Encomenda enc = new Encomenda();
        assertFalse(enc.getArtigos().containsValue(mala));
        enc.addArtigoEncomenda(mala);
        assertTrue(enc.getArtigos().containsValue(mala));
    }

    @Test
    public void testRemoveArtigo() {
        Artigo mala = new MalaNU(Artigo.St.EXCELENTE, 0, new TransportadoraNormal(), "Mala Preta", "Chanel", 99.99, 0.23, Mala.Dim.MEDIA, "Pele de cobra", 1990);
        Encomenda enc = new Encomenda();
        enc.addArtigoEncomenda(mala);
        assertTrue(enc.getArtigos().containsValue(mala));
        enc.removeArtigo(mala.getCodBarras());
        assertFalse(enc.getArtigos().containsValue(mala));
    }

    @Test 
    public void testValorEncomenda() {
        Artigo mala = new MalaNU(Artigo.St.EXCELENTE, 0, new TransportadoraNormal(), "Mala Preta", "Chanel", 100, 0.23, Mala.Dim.MEDIA, "Pele de cobra", 2023);
        Encomenda enc = new Encomenda();
        enc.addArtigoEncomenda(mala);
        enc.valorEncomenda(2023, 0, 0, 0, 0);
        assertEquals(100, enc.getPrecoFinal());
    }
}
