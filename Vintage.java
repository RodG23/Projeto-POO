import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.ExpandVetoException;

public class Vintage {
    private static double taxaGSNovo = 0.5; //Guardada a taxa de serviço por artigo novo.
    private static double taxaGSUsado = 0.25; //Guardada a taxa de serviço por artigo usado.

    private Map<Integer, Artigo> stock; //Guardado o stock de artigos para venda (CodBarras,Artigo).
    private Map<Integer, Map<Integer,Encomenda>> encomendas; //Guardadas encomendas feitas por um user (Id do comprador,conjunto de encomendas).
    private Map<Integer, Map<Integer,Artigo>> vendas; //Guardadas as vendas feitas por user (Id do vendedor, conjunto de artigos vendidos). 
    private Map<Credenciais, Utilizador> creds; //Guardadas as credenciais de acesso à Vintage por users (credenciais,utilizador).
    private double totalAuferido; //Guardado o total auferido pela Vintage no seu funcionamento.
    private LocalDate dataAtual;//Data atual do sistema.

    /**
     * Construtores
     */
    public Vintage(){
        this.stock = new HashMap<Integer,Artigo>();
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>();
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>();
        this.creds = new HashMap<Credenciais, Utilizador>();
        this.totalAuferido = 0;
        this.dataAtual = null;
    }

    public Vintage(Map<Integer, Artigo> stock, Map<Integer, Map<Integer,Encomenda>> encomendas, Map<Integer, Map<Integer,Artigo>> vendas, Map<Credenciais, Utilizador> creds, double totalAuferido, LocalDate dataAtual) {
        this.stock = new HashMap<Integer,Artigo>(stock);
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>(encomendas);
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>(vendas);
        this.creds = new HashMap<Credenciais, Utilizador>(creds);
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

    public Map<Credenciais, Utilizador> getCreds(){
        Map<Credenciais, Utilizador> map = new HashMap<Credenciais, Utilizador>();
        for (Map.Entry<Credenciais, Utilizador> e : this.creds.entrySet())
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

    public LocalDate getDataAtual(){
        return this.dataAtual;
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

    public void setCreds(HashMap<Credenciais, Utilizador>novasCreds){
        Map<Credenciais, Utilizador> map = new HashMap<Credenciais, Utilizador>();
        for (Map.Entry<Credenciais, Utilizador> e : novasCreds.entrySet())
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

    public void setDataAtual(LocalDate dataAtual){
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
        sb.append(" Encomendas em processo:\n");
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
            sb.append("     Utilizador " +e.getKey().toString() + ":\n");
            for (Map.Entry<Integer,Artigo> e2 : e.getValue().entrySet())
            {
                sb.append(e2.getValue().toString());
            }
        }
        sb.append(" Credênciais de login:\n");
        for(Map.Entry<Credenciais, Utilizador> a : this.getCreds().entrySet())
        {
            sb.append("     Utilizador " + a.getValue().getId() + "\n" + a.getKey().toString());
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
        try{
            enc.setEstado(Encomenda.St.EXPEDIDA);
            LocalDate aux = this.getDataAtual();

            // definir o dia de entrega da encomenda (passado DOIS dias da sua finalização, enc.getTempEntrega() = 2)
            aux = aux.plusDays(enc.getTempEntrega());
    
            enc.setDataEntrega(aux); // avança os dias necessários
            utilizador.adicionaEncEncomendas(enc);
            this.encomendas.put(utilizador.getId(), utilizador.getEncomendas());
        }
        catch(NullPointerException e){
            System.out.println("A encomenda do Utilizador " + utilizador.getId() + " não existe\n");
        }
    }

    public void atualizaEncomendas() {
        // Percorre todas as encomendas
        for (Map<Integer, Encomenda> encomendasUtilizadores : this.encomendas.values()) {

            Utilizador comprador = null;
            for (Encomenda encomenda : encomendasUtilizadores.values()) {

                //verificar se o dia da entrega da encomenda chegou
                if (encomenda.getDataEntrega().isBefore(this.getDataAtual()) || encomenda.getDataEntrega().isEqual(this.getDataAtual())) {

                    for (Utilizador compradorAUX : this.creds.values()) {
                        if (this.encomendas.containsKey(compradorAUX.getId())){
                            comprador = compradorAUX;
                            break;
                        }
                    }
                    try{
                        // Obter a lista de artigos da encomenda
                        Map<Integer, Artigo> artigosEncomenda = encomenda.getArtigos();

                        for(Artigo artigo : artigosEncomenda.values()){

                            // Encontrar o utilizador vendedor do artigo
                            Utilizador vendedor = null;
                            for (Utilizador vendedorAUX : this.creds.values()) {
                                if (vendedorAUX.getAVenda().containsKey(artigo.getCodBarras())) {
                                    vendedor = vendedorAUX;
                                    break;
                                }
                            }
                            try{
                                // Adicionar cada artigo à hashMap de vendas do vendedor respetivo
                                vendedor.adicionaArtigoVendas(artigo);

                                // Remover o artigo da lista de artigos à venda do vendedor
                                vendedor.removeArtigoAVenda(artigo);

                                // Adicionar cada artigo à hashMap de compras do comprador
                                comprador.adicionaArtigoCompras(artigo);

                                // Adicionar o artigo na lista de vendidos do sistema
                                this.vendas.put(vendedor.getId(), vendedor.getVendeu());

                            } 
                            catch(NullPointerException e)  {
                                System.out.println("Artigo não encontrado\n");
                            }

                            catch(Exception e){
                                System.out.println("Nenhum vendedor foi encontrado para concluir a encomenda\n");
                            } 


                        //Remover a encomenda que foi entregue da hashMap, encomendas, do comprador
                        comprador.removeEncEncomenda(encomenda);
                        }

                    } catch(Exception e){
                        System.out.println("Nenhum comprador encontrado para concluir a encomenda");
                    }
                    
                    // Verificar se o comprador tem mais encomendas na hashMap, se sim atualiza caso contrário remove a chave da mesma
                    if(!comprador.getEncomendas().isEmpty()){
                        this.encomendas.replace(comprador.getId(), comprador.getEncomendas());
                    }
                    else {
                        this.encomendas.remove(comprador.getId());
                    }
                }
            }
        }
    } 

    public void addStock(Artigo a){
        this.stock.put(a.getCodBarras(), a.clone());
    }

    public void remStock(Artigo a){
        this.stock.remove(a.getCodBarras());
    }

    //avança a data atual em x dias
    public void avancaData(String data){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataFutura = LocalDate.parse(data, formatter);
            long dias = ChronoUnit.DAYS.between(this.dataAtual, dataFutura);
            this.setDataAtual(this.getDataAtual().plusDays(dias));
            this.atualizaEncomendas();
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Por favor, informe uma data no formato 'dd/MM/yyyy'.");
        }
    }

    public void registaUtilizador(Credenciais cred, Utilizador utilizador){
        this.creds.put(cred, utilizador);
    }
} 