import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FaturaTest {
    
    public FaturaTest()
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
        Fatura fatura = new Fatura();
        assertTrue(fatura != null);
        assertTrue(fatura.getNumEmissao() > 0);
        assertEquals(null, fatura.getTipo());
        assertEquals(null, fatura.getEncomenda());
        assertEquals(0, fatura.getValorTotal());

        Encomenda enc = new Encomenda(Encomenda.St.PENDENTE, Encomenda.Dim.PEQUENA, new HashMap<Integer,Artigo>(), 0, LocalDate.of(2023,5,17));
        fatura = new Fatura(Fatura.Tp.COMPRA, enc, 0);
        assertTrue(fatura != null);
        assertTrue(fatura.getNumEmissao() > 0);
        assertEquals(Fatura.Tp.COMPRA, fatura.getTipo());
        assertEquals(0, fatura.getValorTotal());
    }

    @Test
    public void testSetters() {
        Fatura fatura = new Fatura();
        fatura.setTipo(Fatura.Tp.COMPRA);
        assertEquals(Fatura.Tp.COMPRA, fatura.getTipo());
        fatura.setValorTotal(100);
        assertEquals(100, fatura.getValorTotal());
    }

    @Test
    public void testCalculaValorFatura() {
        Encomenda enc = new Encomenda();
        enc.setPrecoFinal(20);
        Fatura fatura = new Fatura(Fatura.Tp.VENDA,enc, 0);
        fatura.calculaValorFatura();
        assertEquals(20, fatura.getValorTotal());
    }
}
