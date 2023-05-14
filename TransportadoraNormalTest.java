import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransportadoraNormalTest {
    
    public TransportadoraNormalTest()
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
        TransportadoraNormal transportadoraNormal = new TransportadoraNormal();
        assertTrue(transportadoraNormal != null);
        assertNull(transportadoraNormal.getNome());
        assertEquals(0,transportadoraNormal.getCustoPequena());
        assertEquals(0,transportadoraNormal.getCustoMedia());
        assertEquals(0,transportadoraNormal.getCustoGrande());
        assertEquals(0,transportadoraNormal.getImposto());
        assertEquals(0,transportadoraNormal.getTotalAuferido());

        transportadoraNormal = new TransportadoraNormal("TransP",2,3,4,0.2);
        assertTrue(transportadoraNormal != null);
        assertEquals("TransP", transportadoraNormal.getNome());
        assertEquals(2,transportadoraNormal.getCustoPequena());
        assertEquals(3,transportadoraNormal.getCustoMedia());
        assertEquals(4,transportadoraNormal.getCustoGrande());
        assertEquals(0.2,transportadoraNormal.getImposto());
        assertEquals(0,transportadoraNormal.getTotalAuferido());
    }

    @Test
    public void testSetters() {
        TransportadoraNormal transportadoraNormal = new TransportadoraNormal();
        transportadoraNormal.setNome("TransBraga");
        assertEquals("TransBraga",transportadoraNormal.getNome());
        transportadoraNormal.setCustoPequena(3);
        assertEquals(3,transportadoraNormal.getCustoPequena());
        transportadoraNormal.setCustoMedia(5);
        assertEquals(5,transportadoraNormal.getCustoMedia());
        transportadoraNormal.setCustoGrande(7);
        assertEquals(7,transportadoraNormal.getCustoGrande());
        transportadoraNormal.setImposto(0.4);
        assertEquals(0.4,transportadoraNormal.getImposto());
        transportadoraNormal.setTotalAuferido(25);
        assertEquals(25,transportadoraNormal.getTotalAuferido());
    }

    @Test
    public void testCalculaExpedicaoNormal() {
        TransportadoraNormal transportadoraNormal = new TransportadoraNormal("TransP",2,3,4,0.2);
        assertEquals(3.6,transportadoraNormal.calculaExpedicaoNormal(1));
        assertEquals(7.2,transportadoraNormal.calculaExpedicaoNormal(3));
        assertEquals(14.4,transportadoraNormal.calculaExpedicaoNormal(7));
    }
}
