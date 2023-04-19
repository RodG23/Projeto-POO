import java.util.Set;

public class Fatura {
    
    private static int numFaturas;

    enum Tp {
        COMPRA,
        VENDA
    }

    private final int numEmissao;
    private final Tp tipo;
    private Set<Artigo> artigos;
    private double valorTotal;
}
