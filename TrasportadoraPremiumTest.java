import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrasportadoraPremiumTest {
        
    public TrasportadoraPremiumTest()
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
        TransportadoraPremium transportadoraPremium = new TransportadoraPremium();
        assertTrue(transportadoraPremium != null);
        assertNull(transportadoraPremium.getNome());
        assertEquals(0,transportadoraPremium.getCustoPequena());
        assertEquals(0,transportadoraPremium.getCustoMedia());
        assertEquals(0,transportadoraPremium.getCustoGrande());
        assertEquals(0,transportadoraPremium.getImposto());
        assertEquals(0,transportadoraPremium.getTotalAuferido());
        assertEquals(0,transportadoraPremium.getCustoAdicional());

        transportadoraPremium = new TransportadoraPremium("TransP",10,15,20,0.4,10);
        assertTrue(transportadoraPremium != null);
        assertEquals("TransP", transportadoraPremium.getNome());
        assertEquals(10,transportadoraPremium.getCustoPequena());
        assertEquals(15,transportadoraPremium.getCustoMedia());
        assertEquals(20,transportadoraPremium.getCustoGrande());
        assertEquals(0.4,transportadoraPremium.getImposto());
        assertEquals(0,transportadoraPremium.getTotalAuferido());
        assertEquals(10,transportadoraPremium.getCustoAdicional());
    }

    @Test
    public void testSetters() {
        TransportadoraPremium transportadoraPremium = new TransportadoraPremium();
        transportadoraPremium.setNome("TransGuimarães");
        assertEquals("TransGuimarães",transportadoraPremium.getNome());
        transportadoraPremium.setCustoPequena(10);
        assertEquals(10,transportadoraPremium.getCustoPequena());
        transportadoraPremium.setCustoMedia(15);
        assertEquals(15,transportadoraPremium.getCustoMedia());
        transportadoraPremium.setCustoGrande(20);
        assertEquals(20,transportadoraPremium.getCustoGrande());
        transportadoraPremium.setImposto(0.5);
        assertEquals(0.5,transportadoraPremium.getImposto());
        transportadoraPremium.setTotalAuferido(450);
        assertEquals(450,transportadoraPremium.getTotalAuferido());
        transportadoraPremium.setCustoAdicional(15);
        assertEquals(15,transportadoraPremium.getCustoAdicional());
    }

    @Test
    public void testCalculaExpedicaoNormal() {
        TransportadoraPremium transportadoraPremium = new TransportadoraPremium("TransP",10,15,20,0.6,15);
        assertEquals(19.8,transportadoraPremium.calculaExpedicaoPremium(1));
        assertEquals(26.64,transportadoraPremium.calculaExpedicaoPremium(3));
        assertEquals(39.78,transportadoraPremium.calculaExpedicaoPremium(7));
    }
}
