import java.util.Map;
import java.util.Set;

public class Vintage {

    private double totalAuferido; //Guardado o total auferido pela Vintage no seu funcionamento.
    private double taxaGSNovo; //Guardada a taxa de serviço por artigo novo.
    private double taxaGSUsado; //Guardada a taxa de serviço por artigo usado.
    private Map<String, String> creds; //Guardadas as credenciais de acesso à Vintage por users (email,pass).
    private Map<Integer, Artigo> stock; //Guardado o stock de artigos para venda (CodBarras,Artigo).
    private Map<Integer, Set<Encomenda>> encomendas; //Guardadas encomendas feitas por um user (Id,conjunto de encomendas).
    private Map<Integer, Set<Artigo>> vendas; //Guardadas as vendas feitas por user (Id, conjunto de artigos vendidos). 
}