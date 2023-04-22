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
    private int nif; //Guarda o nif de um user, inalterável.
    private Map<Integer,Artigo> aVenda; //Guarda os artigos que o user tem à venda (codBarras,Artigo).
    private Map<Integer,Artigo> vendeu; //Guarda os artigos que o user já vendeu (codBarras,Artigo).
    private Map<Integer,Artigo> comprou; //Guarda os artigos que o user já comprou (codBarras,Artigo).
    private Map<Integer,Encomenda> encomendas;// Guarda as encomendas que o user fez numa list.
    private Map<Integer, Fatura> faturas; //Guarda as faturas de compra e venda de um user(numEmissao,Fatura).

    /**
     * Construtores
     */
    public Utilizador(){
        numUsers++;
        this.id = numUsers;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = -1;
        this.aVenda = new HashMap<Integer,Artigo>();
        this.vendeu = new HashMap<Integer,Artigo>();
        this.comprou = new HashMap<Integer,Artigo>();
        this.encomendas = new HashMap<Integer,Encomenda>();
        this.faturas = new HashMap<Integer,Fatura>();
    }

    public Utilizador(String email, String nome, String morada, int nif, Map<Integer, Artigo> aVenda, Map<Integer, Artigo> vendeu, Map<Integer, Artigo> comprou, Map<Integer,Encomenda> encomendas, Map<Integer,Fatura> fat){
        numUsers++;
        this.id = numUsers;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;

        this.aVenda= new HashMap<Integer,Artigo>();
        for(Map.Entry<Integer,Artigo> e : aVenda.entrySet())
        {
            this.aVenda.put(e.getKey(), e.getValue().clone());
        }

        this.vendeu = new HashMap<>();
        for(Map.Entry<Integer,Artigo> e : vendeu.entrySet())
        {
            this.vendeu.put(e.getKey(), e.getValue().clone());
        }

        this.comprou = new HashMap<Integer,Artigo>();
        for(Map.Entry<Integer,Artigo> e : comprou.entrySet())
        {
            this.comprou.put(e.getKey(), e.getValue().clone());
        }

        this.encomendas= new HashMap<>();
        for(Map.Entry<Integer,Encomenda> e : encomendas.entrySet())
        {
            this.encomendas.put(e.getKey(), e.getValue().clone());
        }

        this.faturas = new HashMap<Integer,Fatura>();
        for(Map.Entry<Integer,Fatura> e : fat.entrySet())
        {
            this.faturas.put(e.getKey(), e.getValue().clone());
        }
    }

    public Utilizador(Utilizador umUser){
        this.id = numUsers;
        this.email = umUser.getEmail();
        this.nome = umUser.getNome();
        this.morada = umUser.getMorada();
        this.nif = umUser.getNif();
        this.aVenda = umUser.getAVenda();
        this.vendeu = umUser.getVendeu();
        this.comprou = umUser.getComprou();
        this.encomendas = umUser.getEncomendas();
        this.faturas = umUser.getFaturas();
    }

    /**
     * Getters
     */
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

    public Map<Integer, Artigo> getComprou(){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.comprou.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public Map<Integer, Artigo> getVendeu(){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.vendeu.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public Map<Integer, Artigo> getAVenda(){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.aVenda.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public Map<Integer,Encomenda> getEncomendas(){
        Map<Integer,Encomenda> map = new HashMap<Integer,Encomenda>();
        for (Map.Entry<Integer,Encomenda> e : this.encomendas.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public Map<Integer,Fatura> getFaturas(){
        Map<Integer,Fatura> map = new HashMap<Integer,Fatura>();
        for (Map.Entry<Integer,Fatura> e : this.faturas.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    /**
     * Setters
     */
    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setComprou(Map<Integer, Artigo> comp){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : comp.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.comprou = map;
    }

    public void setVendeu(Map<Integer, Artigo> vend){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : vend.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.vendeu = map;
    }

    public void setAVenda(Map<Integer, Artigo> aVen){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : aVen.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.aVenda = map;
    }

    public void setEncomendas(Map<Integer,Encomenda> enc){
        Map<Integer,Encomenda> map = new HashMap<Integer,Encomenda>();
        for (Map.Entry<Integer,Encomenda> e : enc.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.encomendas = map;
    }

    public void setFaturas(Map<Integer,Fatura> fat){
        Map<Integer,Fatura> map = new HashMap<Integer,Fatura>();
        for (Map.Entry<Integer,Fatura> e : fat.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.faturas = map;
    }

    /**
     * Método clone.
     */
    @Override
    public Utilizador clone() {
        return new Utilizador(this);
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("| Utilizador |\n");
        sb.append(" Id -> " + this.getId() + "\n");
        sb.append(" Email -> " + this.getEmail() + "\n");
        sb.append(" Nome -> " + this.getNome() + "\n");
        sb.append(" Morada -> " + this.getMorada() + "\n");
        sb.append(" NIF -> " + this.getNif() + "\n");
        sb.append(" A Venda:\n");
        for(Artigo a : this.getAVenda().values())
        {
            sb.append(a.toString());
        }
        sb.append(" Vendeu:\n");
        for(Artigo a : this.getVendeu().values())
        {
            sb.append(a.toString());
        }
        sb.append(" Comprou:\n");
        for(Artigo a : this.getComprou().values())
        {
            sb.append(a.toString());
        }
        sb.append(" Encomendas:\n");
        for(Encomenda e : this.getEncomendas().values())
        {
            sb.append(e.toString());
        }
        sb.append(" Faturas:\n");
        for(Fatura e : this.getFaturas().values())
        {
            sb.append(e.toString());
        }

        return sb.toString();
    }

    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true ;
        if (( obj == null ) || ( this.getClass () != obj.getClass ()))
            return false ;
        Utilizador user = (Utilizador) obj ;
        return  this.getEmail().equals(user.getEmail()) &&
                this.getNome().equals(user.getNome()) && 
                this.getMorada().equals(user.getMorada()) && 
                this.getNif() == user.getNif() &&
                this.getAVenda().equals(user.getAVenda()) && 
                this.getVendeu().equals(user.getVendeu()) &&
                this.getComprou().equals(user.getComprou()) && 
                this.getEncomendas().equals(user.getEncomendas()) &&
                this.getFaturas().equals(user.getFaturas());
    }

    ////////////////////Daqui para baixo falta mudar as coisas relativas à encomenda ser um map, deixem comigo//////////////////

    // Adiciona um artigo para venda no sistema
    public void aVenda_Artigo(Vintage vintage, Artigo a, Transportadora transportadora){

            this.aVenda.put(a.getCodBarras(), a);
            a.setTransportadora(transportadora);
            vintage.addStock(a);
    } 

    public Encomenda encontrarEncomendaPendente(Set<Encomenda> encomendas) {
        for (Encomenda enc : encomendas) {
            if (enc.getEstado().equals(Encomenda.St.PENDENTE)) {
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
