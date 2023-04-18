public class Artigo {
    
    private static int numArtigo = 1; ////Mantém contagem de artigos e é utilizada para o codBarras de um artigo.

    enum Cond { //Enum das condições possíveis de um artigo.
        NOVO,
        USADO
    }

    enum Tp { //Enum dos tipos possíveis de um artigo.
        SAPATILHA,
        TSHIRT,
        MALA
    }

    enum St { //Enum dos estados possíveis de um artigo.
        MAU,
        MEDIO,
        BOM,
        MUITO_BOM,
        EXCELENTE
    }

    private final int codBarras; //Guarda a identificação de um artigo.
    private Cond condicao; //Guarda a condição de um artigo.
    private Tp tipo; //Guarda o tipo de artigo.
    private St estado; //Guarda o estado de um artigo.
    private int numDonos; //Guarda o número de donos anteriores de um artigo.
    private Transportadora transportadora; //Guarda a transportadora pre-definida de um artigo (alteravel).
    private String descricao; //Guarda a descrição de um artigo.
    private String marca; //Guarda a marca de um artigo.
    private double precoBase; //Guarda o preço base de um artigo.
    private double correcaoPreco; //Guarda a percentagem (relativa ao preço base) de correção de preço.
}
