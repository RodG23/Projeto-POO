import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilizadorTest {
    
    public UtilizadorTest()
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
        Utilizador user = new Utilizador();
        assertTrue(user.getId() > 0);
        assertEquals("",user.getEmail());
        assertEquals("",user.getNome());
        assertEquals("",user.getMorada());
        assertEquals(-1,user.getNif());
        assertEquals(new HashMap<Integer,Artigo>(),user.getAVenda());
        assertEquals(new HashMap<Integer,Artigo>(),user.getVendeu());
        assertEquals(new HashMap<Integer,Artigo>(),user.getComprou());
        assertEquals(new HashMap<Integer,Encomenda>(),user.getEncomendas());
        assertEquals(new HashMap<Integer,Fatura>(),user.getFaturas());

        user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertTrue(user.getId() > 0);
        assertEquals("123@gmail.com",user.getEmail());
        assertEquals("Rui Unas",user.getNome());
        assertEquals("Rua das 12 Casas, nº13",user.getMorada());
        assertEquals(225267374,user.getNif());
        assertEquals(new HashMap<Integer,Artigo>(),user.getAVenda());
        assertEquals(new HashMap<Integer,Artigo>(),user.getVendeu());
        assertEquals(new HashMap<Integer,Artigo>(),user.getComprou());
        assertEquals(new HashMap<Integer,Encomenda>(),user.getEncomendas());
        assertEquals(new HashMap<Integer,Fatura>(),user.getFaturas());
    }

    @Test
    public void testSetters() {
        Utilizador user = new Utilizador();
        user.setEmail("321@gmail.com");
        assertEquals("321@gmail.com",user.getEmail());
        user.setNome("Marco Horácio");
        assertEquals("Marco Horácio",user.getNome());
        user.setMorada("Rua das 12 casas, nº14");
        assertEquals("Rua das 12 casas, nº14",user.getMorada());
        Map<Integer,Artigo> avenda = new HashMap<>();
        user.setAVenda(avenda);
        assertEquals(avenda,user.getAVenda());
        Map<Integer,Artigo> vendeu = new HashMap<>();
        user.setVendeu(vendeu);
        assertEquals(vendeu,user.getVendeu());
        Map<Integer,Artigo> comprou = new HashMap<>();
        user.setComprou(comprou);
        assertEquals(comprou,user.getComprou());
        Map<Integer,Encomenda> encs = new HashMap<>();
        user.setEncomendas(encs);
        assertEquals(encs,user.getEncomendas());
        Map<Integer,Fatura> faturas = new HashMap<>();
        user.setFaturas(faturas);
        assertEquals(faturas,user.getFaturas());
    }

    @Test
    public void testAddAndRemoveVendas() {
        MalaNU mala = new MalaNU(Artigo.St.BOM, 0, new TransportadoraNormal(), "Mala", "Samsonite", 100, 0.2, Mala.Dim.MEDIA, "Cabedal", 2000);
        Utilizador user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertFalse(user.getVendeu().containsValue(mala));
        user.addArtigoVendas(mala);
        assertTrue(user.getVendeu().containsValue(mala));
        user.removeArtigoVendas(mala);
        assertFalse(user.getVendeu().containsValue(mala));
    }

    @Test
    public void testAddAndRemoveAVenda() {
        MalaNU mala = new MalaNU(Artigo.St.BOM, 0, new TransportadoraNormal(), "Mala", "Samsonite", 100, 0.2, Mala.Dim.MEDIA, "Cabedal", 2000);
        Utilizador user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertFalse(user.getAVenda().containsValue(mala));
        user.addArtigoAVenda(mala);
        assertTrue(user.getAVenda().containsValue(mala));
        user.removeArtigoAVenda(mala);
        assertFalse(user.getAVenda().containsValue(mala));
    }

    @Test
    public void testAddAndRemoveComprou() {
        MalaNU mala = new MalaNU(Artigo.St.BOM, 0, new TransportadoraNormal(), "Mala", "Samsonite", 100, 0.2, Mala.Dim.MEDIA, "Cabedal", 2000);
        Utilizador user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertFalse(user.getComprou().containsValue(mala));
        user.addArtigoCompras(mala);
        assertTrue(user.getComprou().containsValue(mala));
        user.removeArtigoCompras(mala);
        assertFalse(user.getComprou().containsValue(mala));
    }

    @Test
    public void testAddAndRemoveEnc() {
        MalaNU mala = new MalaNU(Artigo.St.BOM, 0, new TransportadoraNormal(), "Mala", "Samsonite", 100, 0.2, Mala.Dim.MEDIA, "Cabedal", 2000);
        Encomenda enc = new Encomenda(Encomenda.St.PENDENTE, Encomenda.Dim.PEQUENA, new HashMap<Integer,Artigo>(), 0, LocalDate.of(2023,5,17));
        enc.setDataCriacao(LocalDate.now());
        enc.addArtigoEncomenda(mala);
        Utilizador user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertFalse(user.getEncomendas().containsValue(enc));
        user.addEncEncomendas(enc);
        assertTrue(user.getEncomendas().containsValue(enc));
        user.removeEncEncomenda(enc);
        assertFalse(user.getEncomendas().containsValue(enc));
    }

    @Test
    public void testAddAndRemoveFatura() {
        MalaNU mala = new MalaNU(Artigo.St.BOM, 0, new TransportadoraNormal(), "Mala", "Samsonite", 100, 0.2, Mala.Dim.MEDIA, "Cabedal", 2000);
        Encomenda enc = new Encomenda(Encomenda.St.PENDENTE, Encomenda.Dim.PEQUENA, new HashMap<Integer,Artigo>(), 0, LocalDate.of(2023,5,17));
        enc.setDataCriacao(LocalDate.now());
        enc.addArtigoEncomenda(mala);        
        Utilizador user = new Utilizador("123@gmail.com", "Rui Unas", "Rua das 12 Casas, nº13", 225267374, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        Fatura fatura = new Fatura(Fatura.Tp.VENDA, enc, 0);
        assertFalse(user.getFaturas().containsValue(fatura));
        user.addFatura(fatura);
        assertTrue(user.getFaturas().containsValue(fatura));
        user.removeFatura(fatura);
        assertFalse(user.getFaturas().containsValue(fatura));
    }
}
