import java.util.HashMap;
import java.util.HashSet;
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
    private Set<Encomenda> encomendas;// Guarda as encomendas que o user fez numa list.
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
        return this.comprou;
    }

    public Map<Integer, Artigo> get_Vendidos(){
        return this.vendeu;
    }

    public Map<Integer, Artigo> get_AVenda(){
        return this.aVenda;
    }

    public Set<Encomenda> get_Encomendas(){
        return this.encomendas;
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

    public void set_Encomendas(Set<Encomenda> encomendas){
        this.encomendas = encomendas;
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
        this.encomendas = new HashSet<>();
    }

    public Utilizador(String email, String nome, String morada, int nif, Map<Integer, Artigo> comprou, Map<Integer, Artigo> vendeu, Map<Integer, Artigo> aVenda, Set<Encomenda> encomendas){
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
        this.encomendas= new HashSet<>();
        this.encomendas.addAll(encomendas);
    }

    public Utilizador(Utilizador u){
        numUsers++;
        this.id = numUsers;
        this.email = u.get_Email();
        this.nome = u.get_Nome();
        this.morada = u.get_Morada();
        this.nif = u.get_Nif();
        this.comprou = u.get_Comprados();
        this.vendeu = u.get_Vendidos();
        this.aVenda = u.get_AVenda();
        this.encomendas = u.get_Encomendas();
    }

    @Override
    public Utilizador clone() {
        return new Utilizador(this) ;
        }

    @Override
    public String toString() {
        String comprou_str = "{";
        for (int i : this.comprou.keySet()) {
            comprou_str += i + "=" + this.comprou.get(i) + ", ";
        }
        if (!this.comprou.isEmpty()) {
            comprou_str = comprou_str.substring(0, comprou_str.length()-2);
        }
        comprou_str += "}";
        
        String vendeu_str = "{";
        for (int i : this.vendeu.keySet()) {
            vendeu_str += i + "=" + this.vendeu.get(i) + ", ";
        }
        if (!this.vendeu.isEmpty()) {
            vendeu_str = vendeu_str.substring(0, vendeu_str.length()-2);
        }
        vendeu_str += "}";
        
        String aVenda_str = "{";
        for (int i : this.aVenda.keySet()) {
            aVenda_str += i + "=" + this.aVenda.get(i) + ", ";
        }
        if (!this.aVenda.isEmpty()) {
            aVenda_str = aVenda_str.substring(0, aVenda_str.length()-2);
        }
        aVenda_str += "}";

        String encomendas_Str = "{";
        for (int i =0 ; i < this.encomendas.size(); i++) {
            encomendas_Str += i + "=" + this.encomendas + ", ";
        }
        if (!this.encomendas.isEmpty()) {
            encomendas_Str = encomendas_Str.substring(0, encomendas_Str.length()-2);
        }
        encomendas_Str += "}";
        
        return "UTILIZADOR [id=" + id + ", email=" + email + ", nome=" + nome
                + ", morada=" + morada + ", nif=" + nif + ", comprou=" + comprou_str
                + ", vendeu=" + vendeu_str + ", aVenda=" + aVenda_str + ", encomendou=" + encomendas_Str
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
                this.aVenda.equals(u.get_AVenda()) && this.encomendas.equals(u.get_Encomendas());
    }

    // Adiciona um artigo para venda no sistema
    public void aVenda_Artigo(Vintage vintage, Artigo a, Transportadora transportadora){
            this.aVenda.put(a.getCodBarras(), a);
            a.setTransportadora(transportadora);
            vintage.addStock(a);
    } 

    public Encomenda encontrarEncomendaPendente(Set<Encomenda> encomendas) {
        for (Encomenda enc : encomendas) {
            if (enc.getEstado() == Encomenda.St.PENDENTE) {
                return enc;
            }
        }
        return null;
    }

    // atualiza informações da encomenda do usuário em todas as instâncias relevantes, incluindo a classe Vintage
    public void colocaEncomenda(Vintage vinted, Utilizador vendedor, Artigo artigo) {
        Encomenda aux = encontrarEncomendaPendente(this.encomendas);
        if (aux == null) {
            aux = new Encomenda();
        }
        aux.addArtigo(artigo);
        this.encomendas.add(aux);//poe a encomenda dentro da hashMap das encomendas do utilizador
        vendedor.aVenda.remove(artigo.getCodBarras());
        vinted.remStock(artigo);
    }  

    public void finalizarEncomenda(){
        for (Encomenda enc : this.encomendas) {
            if (enc.getEstado() == Encomenda.St.PENDENTE) {
                enc.setEstado(Encomenda.St.FINALIZADA);;
            }
        }
    }
}
