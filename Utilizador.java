import java.util.HashMap;
import java.util.Map;

public class Utilizador {
    private static int numUsers = 1; //Mantém contagem de users e é utilizada para o id de um user.

    private final int id; //Guarda a identificação única de um user, inalterável.
    private String email; //Guarda o email de um user.
    private String nome; //Guarda o nome de um user.
    private String morada; //Guarda a morada de um user.
    private final int nif; //Guarda o nif de um user, inalterável.
    private Map<Integer,Artigo> aVenda; //Guarda os artigos que o user tem à venda (codBarras,Artigo).
    private Map<Integer,Artigo> vendeu; //Guarda os artigos que o user já vendeu (codBarras,Artigo).
    private Map<Integer,Artigo> comprou; //Guarda os artigos que o user já comprou (codBarras,Artigo).
    //private Set<Fatura> faturas; //Guarda o conjunto das faturas de compra e venda de um user.

    //Getters

    public int get_Id(){ 
        return this.id;
    }

    public String get_Email(){
        return this.email;
    }

    public String get_Nome(){
        return this.nome;
    }

    public String get_Morada(){
        return this.morada;
    }

    public int get_Nif(){
        return this.nif;
    }

    public Map<Integer, Artigo> get_Comprados(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.comprou);
        return aux;
    }

    public Map<Integer, Artigo> get_Vendidos(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.vendeu);
        return aux;
    }

    public Map<Integer, Artigo> get_AVenda(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.aVenda);
        return aux;
    }

    //Setters

    public void set_Email(String email){
        this.email = email;
    }

    public void set_Nome(String nome){
        this.nome = nome;
    }

    public void set_Morada(String morada){
        this.morada = morada;
    }

    public void set_Comprados(Map<Integer, Artigo> comprou){
        this.comprou = comprou;
    }

    public void set_Vendidos(Map<Integer, Artigo> vendeu){
        this.vendeu = vendeu;
    }

    public void set_AVenda(Map<Integer, Artigo> aVenda){
        this.aVenda = aVenda;
    }

    public Utilizador(){
        this.id = numUsers;
        numUsers++;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = -1;
        this.comprou = new HashMap<>();
        this.vendeu = new HashMap<>();
        this.aVenda = new HashMap<>();
    }

    public Utilizador(String email, String nome, String morada, int nif, Map<Integer, Artigo> comprou, Map<Integer, Artigo> vendeu, Map<Integer, Artigo> aVenda){
        this.id = numUsers;
        numUsers++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.comprou = new HashMap<>();
        this.comprou.putAll(comprou);
        this.vendeu = new HashMap<>();
        this.vendeu.putAll(vendeu);
        this.aVenda= new HashMap<>();
        this.aVenda.putAll(aVenda);
    }

    public Utilizador(Utilizador u){
        this.id = numUsers;
        numUsers++;
        this.email = u.get_Email();
        this.nome = u.get_Nome();
        this.morada = u.get_Morada();
        this.nif = u.get_Nif();
        this.comprou = u.get_Comprados();
        this.vendeu = u.get_Vendidos();
        this.aVenda = u.get_AVenda();
    }

    @Override
    public Utilizador clone() {
        return new Utilizador(this) ;
        }

    @Override
    public String toString() {
        String comprou_str = "{";
        for (int i : comprou.keySet()) {
            comprou_str += i + "=" + comprou.get(i) + ", ";
        }
        if (!comprou.isEmpty()) {
            comprou_str = comprou_str.substring(0, comprou_str.length()-2);
        }
        comprou_str += "}";
        
        String vendeu_str = "{";
        for (int i : vendeu.keySet()) {
            vendeu_str += i + "=" + vendeu.get(i) + ", ";
        }
        if (!vendeu.isEmpty()) {
            vendeu_str = vendeu_str.substring(0, vendeu_str.length()-2);
        }
        vendeu_str += "}";
        
        String aVenda_str = "{";
        for (int i : aVenda.keySet()) {
            aVenda_str += i + "=" + aVenda.get(i) + ", ";
        }
        if (!aVenda.isEmpty()) {
            aVenda_str = aVenda_str.substring(0, aVenda_str.length()-2);
        }
        aVenda_str += "}";
        
        return "UTILIZADOR [id=" + id + ", email=" + email + ", nome=" + nome
                + ", morada=" + morada + ", nif=" + nif + ", comprou=" + comprou_str
                + ", vendeu=" + vendeu_str + ", aVenda=" + aVenda_str
                + "]";
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;

        Utilizador u = (Utilizador) o ;
        return  this.email.equals(u.get_Email()) &&
                this.nome.equals(u.get_Nome()) && this.morada.equals(u.get_Morada()) && this.nif == u.get_Nif() &&
                this.comprou.equals(u.get_Comprados()) && this.vendeu.equals(u.get_Vendidos()) &&
                this.aVenda.equals(u.get_AVenda());

    }

    // Adiciona um artigo para venda
    public void aVenda_Artigo(Vintage vintage, Artigo a){
            this.aVenda.put(a.getCodBarras(), a);
            vintage.addStock(a);
    }

    // atualiza informações das vendas dos usuário em todas as instâncias relevantes, incluindo a classe Vintage
    public void vende_Artigo(Artigo artigo) {
        this.vendeu.put(artigo.getCodBarras(), artigo);//poe o novo artigo dentro da hashMap dos vendidos
        this.aVenda.remove(artigo.getCodBarras());
    }   

    // Adiciona um artigo aos comprados e remove do vendedor
    public void compra_Artigo(Vintage vinted, Utilizador utilizador, Artigo a){
        if(utilizador.get_AVenda().containsKey(a.getCodBarras())){//comprar a um utilizador
            //comprador
            this.comprou.put(a.getCodBarras(), a);
            //vender
            utilizador.vende_Artigo(a);
            //sistema
            vinted.remStock(a);
        }
    }
}
