import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VintageTest {
    
    public VintageTest()
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
        Vintage vinted = new Vintage();
        assertEquals(new HashMap<Integer,Artigo>(),vinted.getStock());
        assertEquals(new HashMap<Integer,Map<Integer,Encomenda>>(),vinted.getEncomendas());
        assertEquals(new HashMap<Integer,Map<Integer,Artigo>>(),vinted.getVendas());
        assertEquals(new HashMap<String, Utilizador>(),vinted.getCreds());
        assertEquals(new HashMap<String, Transportadora>(),vinted.getTranspDisponiveis());
        assertEquals(0,vinted.getTotalAuferido());
        assertNull(vinted.getDataAtual());
        assertEquals(0.5, vinted.getTaxaGSNovo());
        assertEquals(0.25,vinted.getTaxaGSUsado());
    
        vinted = new Vintage(new HashMap<Integer,Artigo>(), new HashMap<Integer,Map<Integer,Encomenda>>(), new HashMap<Integer,Map<Integer,Artigo>>(), new HashMap<String, Utilizador>(), new HashMap<String, Transportadora>(), 23000, LocalDate.now());
        assertEquals(new HashMap<Integer,Artigo>(),vinted.getStock());
        assertEquals(new HashMap<Integer,Map<Integer,Encomenda>>(),vinted.getEncomendas());
        assertEquals(new HashMap<Integer,Map<Integer,Artigo>>(),vinted.getVendas());
        assertEquals(new HashMap<String, Utilizador>(),vinted.getCreds());
        assertEquals(new HashMap<String, Transportadora>(),vinted.getTranspDisponiveis());
        assertEquals(23000,vinted.getTotalAuferido());
        assertEquals(LocalDate.now(),vinted.getDataAtual());
        assertEquals(0.5, vinted.getTaxaGSNovo());
        assertEquals(0.25,vinted.getTaxaGSUsado());
    }

    @Test
    public void testInitDataInterativo() {
        Vintage vinted = new Vintage();
        vinted.initDataInterativo();
        assertEquals(LocalDate.now(),vinted.getDataAtual());
    }

    @Test 
    public void testAddTransportadoraInterativo() {
        Vintage vinted = new Vintage();
        TransportadoraNormal trans = new TransportadoraNormal("Trans", 2, 3, 4, 0.5);
        assertFalse(vinted.getTranspDisponiveis().values().contains(trans));
        vinted.addTransportadoraInterativo("Trans", 2, 3, 4, 0.5, 0);
        assertTrue(vinted.getTranspDisponiveis().values().contains(trans));
    }

    @Test
    public void testRegistaAndRemoveUtilizadorInterativo() {
        Vintage vinted = new Vintage();
        assertFalse(vinted.getCreds().keySet().contains("123@gmail.com"));
        vinted.registaUtilizadorInterativo("123@gmail.com", "Rod", "Rua das 13 casas, nº 14", 234567890);
        assertTrue(vinted.getCreds().keySet().contains("123@gmail.com"));
        try {
            vinted.removeUtilizadorInterativo("123@gmail.com");
        } catch (ExceptionUser e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
        assertFalse(vinted.getCreds().keySet().contains("123@gmail.com"));
    }

    @Test
    public void testAddTransportadora() {
        Vintage vinted = new Vintage();
        TransportadoraNormal trans = new TransportadoraNormal("Trans", 2, 3, 4, 0.5);
        assertFalse(vinted.getTranspDisponiveis().values().contains(trans));
        vinted.addTransportadora(trans);
        assertTrue(vinted.getTranspDisponiveis().values().contains(trans));
    }

    @Test
    public void testAddStockAndRemStock() {
        Vintage vinted = new Vintage();
        MalaNU mala = new MalaNU(Artigo.St.EXCELENTE, 0, new TransportadoraNormal(), "Top", "Fila", 100, 0.2, Mala.Dim.PEQUENA, "Algodão", 2019);
        assertFalse(vinted.getStock().values().contains(mala));
        vinted.addStock(mala);
        assertTrue(vinted.getStock().values().contains(mala));
        vinted.remStock(mala);
        assertFalse(vinted.getStock().values().contains(mala));
    }

    @Test
    public void testRegistaUtilizador() {
        Vintage vinted = new Vintage();
        Utilizador user = new Utilizador("a@b.com", "João", "Aquela rua das Taipas", 234567891, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>());
        assertFalse(vinted.getCreds().keySet().contains(user.getEmail()));
        vinted.registaUtilizador(user.getEmail(), user);
        assertTrue(vinted.getCreds().keySet().contains(user.getEmail()));
    }

    
}