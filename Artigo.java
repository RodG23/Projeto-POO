public class Artigo {
    
    private static int numArtigo = 1;

    enum cond {
        NOVO,
        USADO
    }

    enum tp {
        SAPATILHA,
        TSHIRT,
        MALA
    }

    enum st {
        MAU,
        MEDIO,
        BOM,
        MUITO_BOM,
        EXCELENTE
    }

    private final int codBarras;
    private cond condicao;
    private tp tipo;
    private st estado;
    private int numDonos;
    private Transportadora transportadora;
    private String descricao;
    private String marca;
    private double precoBase;
    private double correcaoPreco;



}
