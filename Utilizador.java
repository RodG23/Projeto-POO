import java.util.Map;
import java.util.Set;

public class Utilizador {
    
    private static int numUsers = 0; //Mantém contagem de users e é utilizada para o id de um user.

    private final int id; //Guarda a identificação única de um user, inalterável.
    private String email; //Guarda o email de um user.
    private String nome; //Guarda o nome de um user.
    private String morada; //Guarda a morada de um user.
    private final int nif; //Guarda o nif de um user, inalterável.
    private Map<Integer,Artigo> aVenda; //Guarda os artigos que o user tem à venda (codBarras,Artigo).
    private Map<Integer,Artigo> vendeu; //Guarda os artigos que o user já vendeu (codBarras,Artigo).
    private Map<Integer,Artigo> comprou; //Guarda os artigos que o user já comprou (codBarras,Artigo).
    private Set<Fatura> faturas; //Guarda o conjunto das faturas de compra e venda de um user.
}
