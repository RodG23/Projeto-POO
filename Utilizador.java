import java.util.HashMap;
import java.util.Map;

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
    //private Set<Fatura> faturas; //Guarda o conjunto das faturas de compra e venda de um user.

    //Getters

    public int getId(){ 
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getMorada(){
        return this.morada;
    }

    public int getNif(){
        return this.nif;
    }

    public Map<Integer, Artigo> getComprados(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.comprou);
        return aux;
    }

    public Map<Integer, Artigo> getVendidos(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.vendeu);
        return aux;
    }

    public Map<Integer, Artigo> getAVenda(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.aVenda);
        return aux;
    }

    //Setters

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setComprados(Map<Integer, Artigo> comprou){
        this.comprou = comprou;
    }

    public void setVendidos(Map<Integer, Artigo> vendeu){
        this.vendeu = vendeu;
    }

    public void setAVenda(Map<Integer, Artigo> aVenda){
        this.aVenda = aVenda;
    }

    public Utilizador(){
        numUsers++;
        this.id = numUsers;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = -1;
        this.comprou = new HashMap<>();
        this.vendeu = new HashMap<>();
        this.aVenda = new HashMap<>();
    }

    public Utilizador(String email, String nome, String morada, int nif, Map<Integer, Artigo> comprou, Map<Integer, Artigo> vendeu, Map<Integer, Artigo> aVenda){
        numUsers++;
        this.id = numUsers;
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
        numUsers++;
        this.id = numUsers;
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.morada = u.getMorada();
        this.nif = u.getNif();
        this.comprou = u.getComprados();
        this.vendeu = u.getVendidos();
        this.aVenda = u.getAVenda();
    }

    @Override
    public Utilizador clone() {
        return new Utilizador(this) ;
        }

    @Override
    public String toString() {
        String comproustr = "{";
        for (int i : comprou.keySet()) {
            comproustr += i + "=" + comprou.get(i) + ", ";
        }
        if (!comprou.isEmpty()) {
            comproustr = comproustr.substring(0, comproustr.length()-2);
        }
        comproustr += "}";
        
        String vendeustr = "{";
        for (int i : vendeu.keySet()) {
            vendeustr += i + "=" + vendeu.get(i) + ", ";
        }
        if (!vendeu.isEmpty()) {
            vendeustr = vendeustr.substring(0, vendeustr.length()-2);
        }
        vendeustr += "}";
        
        String aVendastr = "{";
        for (int i : aVenda.keySet()) {
            aVendastr += i + "=" + aVenda.get(i) + ", ";
        }
        if (!aVenda.isEmpty()) {
            aVendastr = aVendastr.substring(0, aVendastr.length()-2);
        }
        aVendastr += "}";
        
        return "UTILIZADOR [id=" + id + ", email=" + email + ", nome=" + nome
                + ", morada=" + morada + ", nif=" + nif + ", comprou=" + comproustr
                + ", vendeu=" + vendeustr + ", aVenda=" + aVendastr
                + "]";
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;

        Utilizador u = (Utilizador) o ;
        return  this.email.equals(u.getEmail()) &&
                this.nome.equals(u.getNome()) && this.morada.equals(u.getMorada()) && this.nif == u.getNif() &&
                this.comprou.equals(u.getComprados()) && this.vendeu.equals(u.getVendidos()) &&
                this.aVenda.equals(u.getAVenda());

    }

    // Adiciona um artigo para venda
    public void aVendaArtigo(Vintage vintage, Artigo a){
            this.aVenda.put(a.getCodBarras(), a);
            vintage.addStock(a);
    }

    // atualiza informações das vendas dos usuário em todas as instâncias relevantes, incluindo a classe Vintage
    public void vendeArtigo(Artigo artigo) {
        this.vendeu.put(artigo.getCodBarras(), artigo);//poe o novo artigo dentro da hashMap dos vendidos
        this.aVenda.remove(artigo.getCodBarras());
    }   

    // Adiciona um artigo aos comprados e remove do vendedor
    public void compraArtigo(Vintage vinted, Utilizador utilizador, Artigo a){
        if(utilizador.getAVenda().containsKey(a.getCodBarras())){//comprar a um utilizador
            //comprador
            this.comprou.put(a.getCodBarras(), a);
            //vender
            utilizador.vendeArtigo(a);
            //sistema
            vinted.remStock(a);
        }
    }
}
