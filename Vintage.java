import java.util.HashMap;
import java.util.Map;

public class Vintage {
    private static double taxaGSNovo = 0.5; //Guardada a taxa de serviço por artigo novo.
    private static double taxaGSUsado = 0.25; //Guardada a taxa de serviço por artigo usado.

    private Map<Integer, Artigo> stock; //Guardado o stock de artigos para venda (CodBarras,Artigo).
    private Map<Integer, Map<Integer,Encomenda>> encomendas; //Guardadas encomendas feitas por um user (Id,conjunto de encomendas).
    private Map<Integer, Map<Integer,Artigo>> vendas; //Guardadas as vendas feitas por user (Id, conjunto de artigos vendidos). 
    private Map<String, String> creds; //Guardadas as credenciais de acesso à Vintage por users (email,pass).
    private double totalAuferido; //Guardado o total auferido pela Vintage no seu funcionamento.
    private Tempo dataAtual;//Data atual do sistema.

    /**
     * Construtores
     */
    public Vintage(){
        this.stock = new HashMap<Integer,Artigo>();
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>();
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>();
        this.creds = new HashMap<String,String>();
        this.totalAuferido = 0;
        this.dataAtual = null;
    }

    public Vintage(Map<Integer, Artigo> stock, Map<Integer, Map<Integer,Encomenda>> encomendas, Map<Integer, Map<Integer,Artigo>> vendas, Map<String, String> creds, double totalAuferido, Tempo dataAtual) {
        this.stock = new HashMap<Integer,Artigo>(stock);
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>(encomendas);
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>(vendas);
        this.creds = new HashMap<String,String>(creds);
        this.totalAuferido = totalAuferido;
        this.dataAtual = dataAtual;
    }


    public Vintage(Vintage v) {
        this.stock = v.getStock();
        this.encomendas = v.getEncomendas();
        this.vendas = v.getVendas();
        this.creds = v.getCreds();
        this.totalAuferido = v.getTotalAuferido();
        this.dataAtual = v.getDataAtual();
    }
    
    /**
     * Getters
     */
    public Map<Integer, Artigo> getStock(){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.stock.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public Map<Integer,Map<Integer,Encomenda>> getEncomendas(){
        Map<Integer,Map<Integer,Encomenda>> map = new HashMap<Integer,Map<Integer,Encomenda>>();
        for (Map.Entry<Integer,Map<Integer,Encomenda>> e : this.encomendas.entrySet())
        {
            Map<Integer,Encomenda> map2 = new HashMap<Integer,Encomenda>();
            for(Map.Entry<Integer,Encomenda> e2 : e.getValue().entrySet())
            {
                map2.put(e2.getKey(), e2.getValue().clone());
            }
            map.put(e.getKey(), map2);
        }
        return map;
    }

    public Map<Integer, Map<Integer,Artigo>> getVendas(){
        Map<Integer, Map<Integer,Artigo>> map = new HashMap<Integer,Map<Integer,Artigo>>();
        for (Map.Entry<Integer,Map<Integer,Artigo>> e : this.vendas.entrySet())
        {
            Map<Integer,Artigo> map2 = new HashMap<Integer,Artigo>();
            for(Map.Entry<Integer,Artigo> e2 : e.getValue().entrySet())
            {
                map2.put(e2.getKey(), e2.getValue().clone());
            }
            map.put(e.getKey(), map2);
        }
        return map;
    }

    public Map<String, String> getCreds(){
        Map<String, String> map = new HashMap<String,String>();
        for (Map.Entry<String,String> e : this.creds.entrySet())
        {
            map.put(e.getKey(),e.getValue());
        }
        return map;
    }

    public double getTotalAuferido(){
        return this.totalAuferido;
    }

    public double getTaxaGSNovo(){
        return Vintage.taxaGSNovo;
    }

    public double getTaxaGSUsado(){
        return Vintage.taxaGSUsado;
    }

    public Tempo getDataAtual(){
        return this.dataAtual.clone();
    }

    /**
     * Setters.
     */
    public void setStock(Map<Integer, Artigo> novoStock){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : novoStock.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.stock = map;
    }

    public void setEncomendas(Map<Integer, Map<Integer,Encomenda>> novasEnc){
        Map<Integer,Map<Integer,Encomenda>> map = new HashMap<Integer,Map<Integer,Encomenda>>();
        for (Map.Entry<Integer,Map<Integer,Encomenda>> e : novasEnc.entrySet())
        {
            Map<Integer,Encomenda> map2 = new HashMap<Integer,Encomenda>();
            for(Map.Entry<Integer,Encomenda> e2 : e.getValue().entrySet())
            {
                map2.put(e2.getKey(), e2.getValue().clone());
            }
            map.put(e.getKey(), map2);
        }
        this.encomendas = map;
    }

    public void setVendas(Map<Integer, Map<Integer,Artigo>> novasVendas){
        Map<Integer, Map<Integer,Artigo>> map = new HashMap<Integer,Map<Integer,Artigo>>();
        for (Map.Entry<Integer,Map<Integer,Artigo>> e : novasVendas.entrySet())
        {
            Map<Integer,Artigo> map2 = new HashMap<Integer,Artigo>();
            for(Map.Entry<Integer,Artigo> e2 : e.getValue().entrySet())
            {
                map2.put(e2.getKey(), e2.getValue().clone());
            }
            map.put(e.getKey(), map2);
        }
        this.vendas = map;
    }

    public void setCreds(HashMap<String, String> novasCreds){
        Map<String, String> map = new HashMap<String,String>();
        for (Map.Entry<String,String> e : novasCreds.entrySet())
        {
            map.put(e.getKey(),e.getValue());
        }
        this.creds = map;
    }


    public void setTotalAuferido(double tot){
        this.totalAuferido = tot;
    }

    public void setTaxaGSNovo(double novaTaxa){
        Vintage.taxaGSNovo = novaTaxa;
    }

    public void setTaxaGSUsado(double novaTaxa){
        Vintage.taxaGSUsado = novaTaxa;
    }

    public void setDataAtual(Tempo dataAtual){
        this.dataAtual = dataAtual;
    }

    /**
     * Método clone.
     */
    @Override
    public Vintage clone() {
        return new Vintage(this);
        }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" ----- STOCK VINTAGE -----\n");
        for(Artigo a : this.getStock().values())
        {
            sb.append(a.toString());
        }
        sb.append(" Encomendas realizadas:\n");
        for(Map.Entry<Integer, Map<Integer,Encomenda>> e : this.getEncomendas().entrySet())
        {
            sb.append("     Utilizador " +e.getKey().toString() + ":\n");
            for (Map.Entry<Integer,Encomenda> e2 : e.getValue().entrySet())
            {
                sb.append(e2.getValue().toString());
            }
        }
        sb.append(" \nVendas efetuadas:\n");
        for(Map.Entry<Integer, Map<Integer,Artigo>> e : this.getVendas().entrySet())
        {
            sb.append("     Utilizador " +e.getKey().toString() + ":");
            for (Map.Entry<Integer,Artigo> e2 : e.getValue().entrySet())
            {
                sb.append(e2.getValue().toString());
            }
        }
        sb.append(" Credênciais de login:\n");
        for(Map.Entry<String,String> a : this.getCreds().entrySet())
        {
            sb.append(" Email - " + a.getKey() + "| Password - " + a.getValue());
        }
        sb.append(" Total Auferido -> " + this.getTotalAuferido() + "\n\n");

        sb.append(" ----- FIM DO STOCK -----\n");

        return sb.toString();
        }
        
    
    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) 
            return true ;
        if(( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        Vintage v = (Vintage) obj ;
        return  this.getStock().equals(v.getStock()) && 
                this.getEncomendas().equals(v.getEncomendas()) &&
                this.getVendas().equals(v.getVendas())&& 
                this.getTotalAuferido() == v.getTotalAuferido() && 
                this.getTaxaGSNovo() == v.getTaxaGSNovo() && 
                this.getTaxaGSUsado() == v.getTaxaGSUsado() &&
                this.getDataAtual().equals(v.getDataAtual());
    }

    public Encomenda encontrarEncomendaFinalizada(Utilizador utilizador) {
        for (Encomenda enc : utilizador.getEncomendas().values()) {
            if (enc.getEstado().equals(Encomenda.St.FINALIZADA)) {
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
        utilizador.getEncomendas().replace(enc.getId(), enc);
        utilizador.setEncomendas(utilizador.getEncomendas());
    }
    

    // Entrega a encomenda passado o tempo necessário
    public void entregaEncomenda(Utilizador comprador, Utilizador vendedor){
        for (Encomenda enc : comprador.getEncomendas().values()) {
            if (enc.getDataEntrega().equals(this.getDataAtual())) {
                // Obter a lista de artigos da encomenda
                Map<Integer,Artigo> artigosEncomenda = enc.getArtigos();
                // Adicionar cada artigo ao histórico de compras do utilizador
                artigosEncomenda.values().forEach(artigo -> { 
                    comprador.getComprou().put(artigo.getCodBarras(), artigo.clone());
                    comprador.setComprou(comprador.getComprou());
                    vendedor.getVendeu().put(artigo.getCodBarras(), artigo.clone());
                    vendedor.setVendeu(vendedor.getVendeu());
                });
                this.encomendas.put(comprador.getId(), comprador.getEncomendas());
                comprador.getEncomendas().remove(enc.getId());
                comprador.setEncomendas(comprador.getEncomendas());
                // Não é necessário iterar mais se a encomenda já foi encontrada e removida
                break;
            }
        }
    }

    //atualizar a HashMap vendas do utilizador
    public void atualiza_UtilizadorVendas(Utilizador u) {
        // Atualiza a lista de artigos vendidos do utilizador
        Map<Integer, Artigo> aux = new HashMap<>(u.getVendeu());//vai buscar a HashMap das vendas do utilizador dado como parãmetro
        this.vendas.put(u.getId(), aux);//troca o Set através do id unico do utilizador
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