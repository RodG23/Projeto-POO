import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Vintage {
    private Map<Integer, Artigo> stock; //Guardado o stock de artigos para venda (CodBarras,Artigo).
    private Map<Integer, Set<Encomenda>> encomendas; //Guardadas encomendas feitas por um user (Id,conjunto de encomendas).
    private Map<Integer, Set<Artigo>> vendas; //Guardadas as vendas feitas por user (Id, conjunto de artigos vendidos). 
    private Map<String, String> creds; //Guardadas as credenciais de acesso à Vintage por users (email,pass).
    private double totalAuferido; //Guardado o total auferido pela Vintage no seu funcionamento.
    private double taxaGSNovo; //Guardada a taxa de serviço por artigo novo.
    private double taxaGSUsado; //Guardada a taxa de serviço por artigo usado.
    private Tempo dataAtual;//Data atual do sistema.


    //Getters
    public HashMap<Integer, Artigo> get_Stock(){
        HashMap<Integer, Artigo> aux = new HashMap<>();
        aux.putAll(this.stock);
        return aux;
    }

    public HashMap<Integer, Set<Encomenda>> get_Encomendas(){
        HashMap<Integer, Set<Encomenda>> aux = new HashMap<>();
        aux.putAll(this.encomendas);
        return aux;
    }

    public HashMap<Integer, Set<Artigo>> get_Vendas(){
        HashMap<Integer, Set<Artigo>> aux = new HashMap<>();
        aux.putAll(this.vendas);
        return aux;
    }

    public HashMap<String, String> get_Creds(){
        HashMap<String, String> aux = new HashMap<>();
        aux.putAll(this.creds);
        return aux;
    }

    public double get_TotalAuferido(){
        return this.totalAuferido;
    }
    public double get_TaxaGSnovo(){
        return this.taxaGSNovo;
    }
    public double get_TaxaGSusado(){
        return this.taxaGSUsado;
    }

    public Tempo getDataAtual(){
        return this.dataAtual;
    }

    //Setters
    public void set_Stock(HashMap<Integer, Artigo> novoStock){
        this.stock.clear();
        this.stock.putAll(novoStock); 
    
    }

    public void set_Encomendas(HashMap<Integer, Set<Encomenda>> novoValor){
        this.encomendas.clear();
        this.encomendas.putAll(novoValor);
    }
    
    public void set_Vendas(HashMap<Integer, Set<Artigo>> novoValor){
        this.vendas.clear();
        this.vendas.putAll(novoValor);
    }
    
    public void set_Creds(HashMap<String, String> novoValor){
        this.creds.clear();
        this.creds.putAll(novoValor);
    }
    

    public void set_TaxaAuferido(double novaTaxa){
        this.totalAuferido = novaTaxa;
    }
    
    public void set_TaxaGSnovo(double novaTaxa){
        this.taxaGSNovo = novaTaxa;
    }
    
    public void set_TaxaGSusado(double novaTaxa){
        this.taxaGSUsado = novaTaxa;
    }

    public void setDataAtual(Tempo dataAtual){
        this.dataAtual = dataAtual;
    }
    
    

    public Vintage(){
        this.stock = new HashMap<>();
        this.encomendas = new HashMap<>();
        this.vendas = new HashMap<>();
        this.totalAuferido = -1;
        this.taxaGSNovo = -1;
        this.taxaGSUsado = -1;
        this.dataAtual = null;
    }

    public Vintage(Map<Integer, Artigo> stock, Map<Integer, Set<Encomenda>> encomendas, Map<Integer, Set<Artigo>> vendas, Map<String, String> creds, double totalAuferido, double taxaGSNovo, double taxaGSUsado, Tempo dataAtual) {
        this.stock = new HashMap<>(stock);
        this.encomendas = new HashMap<>(encomendas);
        this.vendas = new HashMap<>(vendas);
        this.creds = new HashMap<>(creds);
        this.totalAuferido = totalAuferido;
        this.taxaGSNovo = taxaGSNovo;
        this.taxaGSUsado = taxaGSUsado;
        this.dataAtual = dataAtual;
    }


    public Vintage(Vintage v) {
        this.stock = v.get_Stock();
        this.encomendas = v.get_Encomendas();
        this.vendas = v.get_Vendas();
        this.creds = v.get_Creds();
        this.totalAuferido = v.get_TotalAuferido();
        this.taxaGSNovo = v.get_TaxaGSnovo();
        this.taxaGSUsado = v.get_TaxaGSusado();
        this.dataAtual = v.getDataAtual();
    }
    

    @Override
    public Vintage clone() {
        return new Vintage(this) ;
        }

        @Override
        public String toString() {
            String stockStr = "{";
            for (int i : this.stock.keySet()) {
                stockStr += i + " =" + stock.get(i) + ", ";
            }
            if (!this.stock.isEmpty()) {
                stockStr = stockStr.substring(0, stockStr.length()-2);
            }
            stockStr += "}";
            
            String encomendasStr = "{";
            for (int i : this.encomendas.keySet()) {
                encomendasStr += i + "=" + encomendas.get(i) + ", ";
            }
            if (!this.encomendas.isEmpty()) {
                encomendasStr = encomendasStr.substring(0, encomendasStr.length()-2);
            }
            encomendasStr += "}";
            
            String vendasStr = "{";
            for (int i : this.vendas.keySet()) {
                vendasStr += i + "=" + vendas.get(i) + ", ";
            }
            if (!this.vendas.isEmpty()) {
                vendasStr = vendasStr.substring(0, vendasStr.length()-2);
            }
            vendasStr += "}";
            
            return "VINTAGE [stock=" + stockStr + ", encomendas=" + encomendasStr + ", vendas=" + vendasStr + ", creds=" + creds + ", totalAuferido=" + totalAuferido + ", taxaGSNovo=" + taxaGSNovo + ", taxaGSUsado=" + taxaGSUsado + "]";
        }
        

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;

        Vintage v = (Vintage) o ;
        return  this.stock.equals(v.get_Stock()) && this.encomendas.equals(v.get_Encomendas()) &&
                this.vendas.equals(v.get_Vendas())&& this.totalAuferido == v.get_TotalAuferido() && 
                this.taxaGSNovo == v.get_TaxaGSnovo() && this.taxaGSUsado == v.get_TaxaGSusado() &&
                this.dataAtual.equals(v.getDataAtual());
    }

    public Encomenda encontrarEncomendaFinalizada(Utilizador utilizador) {
        for (Encomenda enc : utilizador.get_Encomendas()) {
            if (enc.getEstado() == Encomenda.St.FINALIZADA) {
                return enc;
            }
        }
        return null;
    }

    public void enviarEncomenda(Utilizador utilizador){
        Encomenda enc = encontrarEncomendaFinalizada(utilizador);
        enc.setEstado(Encomenda.St.EXPEDIDA);
        int i = 0;
        Tempo aux = this.getDataAtual();
        while(i<enc.getTempEntrega()){ // definir o dia de entrega da encomenda (passado DOIS dias da sua finalização)
            aux = aux.avancaDia();
            i++;
        }
        enc.setDataEntrega(aux); // avança os dias necessários
    }

    // Entrega a encomenda passado o tempo necessário
    public void entregaEncomenda(Utilizador comprador, Utilizador vendedor){
        for (Encomenda enc : comprador.get_Encomendas()) {
            if (enc.getDataEntrega().equals(this.getDataAtual())) {
                // Obter a lista de artigos da encomenda
                Set<Artigo> artigosEncomenda = enc.getArtigos();
                // Adicionar cada artigo ao histórico de compras do utilizador
                artigosEncomenda.forEach(artigo -> { 
                    comprador.get_Comprados().put(artigo.getCodBarras(), artigo);
                    vendedor.get_Vendidos().put(artigo.getCodBarras(), artigo);
                });
                comprador.get_Encomendas().remove(enc);
                // Não é necessário iterar mais se a encomenda já foi encontrada e removida
                break;
            }
        }
        this.encomendas.put(comprador.get_Id(), comprador.get_Encomendas());
    }
        
    public void avancaTempo(Utilizador comprador, Utilizador vendedor, Tempo dataAtual){
        this.setDataAtual(dataAtual.avancaDia());

        this.entregaEncomenda(comprador, vendedor);
    }

    //atualizar a HashMap vendas do utilizador
    public void atualiza_UtilizadorVendas(Utilizador u) {
        // Atualiza a lista de artigos vendidos do utilizador
       
        Map<Integer, Artigo> aux = new HashMap<>(u.get_Vendidos());//vai buscar a HashMap das vendas do utilizador dado como parãmetro
        Set<Artigo> set = new HashSet<>(aux.values());//passa todos os artigos dessa hashMap para um Set
        this.vendas.put(u.get_Id(), set);//troca o Set através do id unico do utilizador
    }

    /* 
    //atualizar a HashMap encomendas do utilizador
    public void atualiza_UtilizadorEncomendas(Utilizador utilizador) {
        // Atualiza a lista de artigos encomendados do utilizador
        this.encomendas.replace(utilizador, utilizador.get_Encomendas());
    }
*/
    public void addStock(Artigo a){
        this.stock.put(a.getCodBarras(), a);
    }

    public void remStock(Artigo a){
        this.stock.remove(a.getCodBarras());
    }
} 
