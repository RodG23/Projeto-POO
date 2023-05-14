import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Vintage implements Serializable {
    private static double taxaGSNovo = 0.5; //Guardada a taxa de serviço por artigo novo(centimos).
    private static double taxaGSUsado = 0.25; //Guardada a taxa de serviço por artigo usado(centimos).
    private static double taxaServiço = 0.05; //Guardada a taxa de serviço por artigo usado(percentagem).

    private Map<Integer, Artigo> stock; //Guardado o stock de artigos para venda (CodBarras,Artigo).
    private Map<Integer, Map<Integer,Encomenda>> encomendas; //Guardadas encomendas feitas por um user (Id do comprador,conjunto de encomendas).
    private Map<Integer, Map<Integer,Artigo>> vendas; //Guardadas as vendas feitas por user (Id do vendedor, conjunto de artigos vendidos). 
    private Map<String, Utilizador> creds; //Guardadas o email de acesso à Vintage por users (String,utilizador).
    private Map<String, Transportadora> transpDisponiveis; //transportadoras disponíveis no sistema.
    private double totalAuferido; //Guardado o total auferido pela Vintage no seu funcionamento.
    private LocalDate dataAtual;//Data atual do sistema.

    /**
     * Construtores
     */
    public Vintage(){
        this.stock = new HashMap<Integer,Artigo>();
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>();
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>();
        this.creds = new HashMap<String, Utilizador>();
        this.transpDisponiveis = new HashMap<String, Transportadora>();
        this.totalAuferido = 0;
        this.dataAtual = null;
    }

    public Vintage(Map<Integer, Artigo> stock, Map<Integer, Map<Integer,Encomenda>> encomendas, Map<Integer, Map<Integer,Artigo>> vendas, Map<String, Utilizador> creds,HashMap<String, Transportadora> transpDisponiveis, double totalAuferido, LocalDate dataAtual) {
        this.stock = new HashMap<Integer,Artigo>(stock);
        this.encomendas = new HashMap<Integer,Map<Integer,Encomenda>>(encomendas);
        this.vendas = new HashMap<Integer,Map<Integer,Artigo>>(vendas);
        this.creds = new HashMap<String, Utilizador>(creds);
        this.transpDisponiveis = new HashMap<String, Transportadora>(transpDisponiveis);
        this.totalAuferido = totalAuferido;
        this.dataAtual = dataAtual;
    }


    public Vintage(Vintage v) {
        this.stock = v.getStock();
        this.encomendas = v.getEncomendas();
        this.vendas = v.getVendas();
        this.creds = v.getCreds();
        this.transpDisponiveis = v.getTranspDisponiveis();
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

    public Map<String, Utilizador> getCreds(){
        Map<String, Utilizador> map = new HashMap<String, Utilizador>();
        for (Map.Entry<String, Utilizador> e : this.creds.entrySet())
        {
            map.put(e.getKey(),e.getValue());
        }
        return map;
    }

    public Map<String, Transportadora> getTranspDisponiveis(){
        Map<String, Transportadora> map = new HashMap<String, Transportadora>();
        for (Map.Entry<String, Transportadora> e : this.transpDisponiveis.entrySet())
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

    public double getTaxaSeriço(){
        return Vintage.taxaServiço;
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

    public void setCreds(HashMap<String, Utilizador>novasCreds){
        Map<String, Utilizador> map = new HashMap<String, Utilizador>();
        for (Map.Entry<String, Utilizador> e : novasCreds.entrySet())
        {
            map.put(e.getKey(),e.getValue());
        }
        this.creds = map;
    }

    public void setTransp(HashMap<String, Transportadora> novasTransp){
        Map<String, Transportadora> map = new HashMap<String, Transportadora>();
        for (Map.Entry<String, Transportadora> e : novasTransp.entrySet())
        {
            map.put(e.getKey(),e.getValue());
        }
        this.transpDisponiveis = map;
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

        sb.append(" ----- STOCK VINTAGE -----\n\n");
        for(Artigo a : this.getStock().values())
        {
            sb.append(a.toString()+ "\n");
        }
        sb.append("│ Encomendas em processo:\n\n");
        for(Map.Entry<Integer, Map<Integer,Encomenda>> e : this.getEncomendas().entrySet())
        {
            sb.append("    > Utilizador " +e.getKey().toString() + "\n");
            for (Map.Entry<Integer,Encomenda> e2 : e.getValue().entrySet())
            {
                sb.append(e2.getValue().toString() + "\n");
            }
        }
        sb.append("│ Vendas efetuadas:\n\n");
        for(Map.Entry<Integer, Map<Integer,Artigo>> e : this.getVendas().entrySet())
        {
            sb.append("    > Utilizador " +e.getKey().toString() + "\n");
            for (Map.Entry<Integer,Artigo> e2 : e.getValue().entrySet())
            {
                sb.append(e2.getValue().toString()+ "\n");
            }
        }
        sb.append("│ Credênciais de login:\n");
        for(Map.Entry<String, Utilizador> a : this.getCreds().entrySet())
        {
            sb.append("   > Utilizador " + a.getValue().getId() + " : " + a.getKey().toString() + "\n");
        }
        sb.append("│ Total Auferido -> " + Math.round(this.getTotalAuferido()*100)/100 + "\n\n");

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
                this.getCreds().equals(v.getCreds()) &&
                this.getTranspDisponiveis().equals(v.getTranspDisponiveis()) &&
                this.getTotalAuferido() == v.getTotalAuferido() && 
                this.getTaxaGSNovo() == v.getTaxaGSNovo() && 
                this.getTaxaGSUsado() == v.getTaxaGSUsado() &&
                this.getDataAtual().equals(v.getDataAtual());
    }

    // ------------------------- FUNÇÔES PARA O MODO INTERATIVO ----------------------------------

    public void initDataInterativo(){
        this.setDataAtual(LocalDate.now());
    }

    public void addTransportadoraInterativo(String nome, double cp, double cm, double cg, double imposto, double custoAdicional){
        if(custoAdicional>0){
            TransportadoraPremium tp = new TransportadoraPremium(
                nome,
                cp, //custo de uma encomenda pequena
                cm, //custo de uma encomenda media
                cg, //custo de uma encomenda grande
                imposto, //imposto
                custoAdicional // custo adicional
            );
            if(!this.transpDisponiveis.containsKey(nome)){
                this.addTransportadora(tp);
            }
            
        }
        else{
            TransportadoraNormal tn = new TransportadoraNormal(
                nome,
                cp, //custo de uma encomenda pequena
                cm, //custo de uma encomenda media
                cg, //custo de uma encomenda grande
                imposto //imposto
            );
            if(!this.transpDisponiveis.containsKey(nome)){
                this.addTransportadora(tn);
            }

        }
        System.out.println("Transportadora criada com sucesso\n");
    }

    public void registaUtilizadorInterativo(String email, String nome, String morada, int nif){
        Map<Integer, Artigo> aVenda = new HashMap<Integer,Artigo>();
        Map<Integer, Artigo> vendeu = new HashMap<Integer,Artigo>();
        Map<Integer, Artigo> comprou = new HashMap<Integer,Artigo>();
        Map<Integer, Encomenda> encomendas = new HashMap<Integer,Encomenda>();
        Map<Integer, Fatura> faturas = new HashMap<Integer,Fatura>();
        Utilizador user = new Utilizador(email, nome, morada, nif, aVenda, vendeu, comprou, encomendas, faturas);
        this.creds.put(email, user);
    }

    public void removeUtilizadorInterativo(String email) throws ExceptionUser{
        if(this.creds.get(email) == null){
            throw new ExceptionUser("Não existe utilizadores disponíveis");
        }
        this.creds.remove(email);
    }

    public Utilizador encontraUserInterativo(String email){
        return this.creds.get(email);
    }

    public void colocaAvendaUserInterativo(Utilizador user, String tipoArtigo, String classeArtigo, Artigo.St estado, int numDonos, String nomeTransp, String descricao, String marca, double precoBase, double correcaoPreco, Mala.Dim dimensao, String material, int anoLancamento, Boolean atacadores, String cor, int tamanho, Tshirt.Tam tamTshirt, Tshirt.Pad padTshirt){
        Transportadora transportadora = this.getTranspDisponiveis().get(nomeTransp);
        if(tipoArtigo.equals("Mala")){
            if(classeArtigo.equals("Nao")){
                MalaNU malaNU = new MalaNU(estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, dimensao, material, anoLancamento);
                user.aVendaArtigo(this, malaNU, transportadora);
            }
            else{
                MalaPremium malaPremium = new MalaPremium(estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, dimensao, material, anoLancamento);
                user.aVendaArtigo(this, malaPremium, transportadora);
            }
        }
        else if(tipoArtigo.equals("Sapatilha")){
            if(classeArtigo.equals("Nao")){
                Sapatilha sapatilhaNU = new SapatilhaNU(estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, tamanho, atacadores, cor, anoLancamento);
                user.aVendaArtigo(this, sapatilhaNU, transportadora);
            }
            else{
                SapatilhaPremium sapatilhaPremium = new SapatilhaPremium(estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, tamanho, atacadores, cor, anoLancamento);
                user.aVendaArtigo(this, sapatilhaPremium, transportadora);
            }
        }
        else {
            TshirtNU tshirt = new TshirtNU(estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, tamTshirt, padTshirt);
            user.aVendaArtigo(this, tshirt, transportadora); 
        }
    }

    public void encomendarArtigoUserInterativo(Utilizador user, int codBarras){
        //Criar uma lista de vendedores que já apareceram(Para quando um comprador compra mais que um artigo a um vendedor)
        Map<Integer, Utilizador> vendedoresProcessados = new HashMap<>();
        Utilizador vendedor = obterVendedorDoArtigo(vendedoresProcessados);
        if (vendedor == null) {
            for (Utilizador vendedorAUX : this.getCreds().values()) {
                if(codBarras == 0){
                    break;
                }
                else{
                    if (vendedorAUX.getAVenda().containsKey(codBarras)) {
                        vendedor = vendedorAUX;
                        vendedoresProcessados.put(vendedor.getId(), vendedor.clone());
                        break;
                    }
                }

            }
        }
        try{
            user.colocaEncomenda(this, vendedor.getAVenda().get(codBarras));
        }catch(NullPointerException e){
            System.out.println("O utilizado nao está a vender o artigo escolhido.\n");
        }
        System.out.print("Artigo encomendado com sucesso.\n\n");
    }

    public Map<Integer, Encomenda> encomendaUserInterativo(Utilizador user){
        return user.getEncomendas();
    }

    public void finalizaEncomendaUserInterativo(Utilizador user){
        user.finalizarEncomenda(this);
        System.out.print("Encomenda finalizada com sucesso.\n\n");
    }

    public Map<Integer, Artigo> artigosAVendaUserInterativo(Utilizador user){
        return user.getAVenda();
    }

    public Map<Integer, Artigo> artigosVendidosUserInterativo(Utilizador user){
        return user.getVendeu();
    }

    public void devolveEncomendaInterativo(Utilizador user){
        try{
            this.devolveEncomenda(user);
            System.out.print("Encomenda devolvida com sucesso.\n\n");  
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch(ExceptionDevolver e){
            System.out.println(e.getMessage());
        }
    }
    

    public Map<Integer, Artigo> getStockInterativo(Utilizador user){
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.getStock().entrySet())
        {
            //só guarda os artigos que não estiverem a ser vendidos pelo utilizador
            if(!user.getAVenda().containsKey(e.getKey())){
                map.put(e.getKey(),e.getValue().clone());
            }    
        }
        return map;
    }

    public void maiorVendedorInterativo(String inferior, String superior) throws ExceptionData{
        LocalDate inf = formataData(inferior);
        LocalDate sup = formataData(superior);
        
        maiorVendedor(inf, sup);
    }

    public void encomendasVendedorInterativo(String emailVendedor) throws ExceptionUser{
        Utilizador vendedor = this.getCreds().get(emailVendedor);
        if(vendedor == null){
            throw new ExceptionUser("O vendedor "+ emailVendedor + " nao existe.\n");
        }
        for (Fatura fatura : vendedor.getFaturas().values()) {
            if(fatura.getTipo().equals(Fatura.Tp.VENDA)){
                try{
                    System.out.println("As encomendas emitidas pelo vendedor " + vendedor.getId() + " são as seguintes \n" + fatura.getEncomenda().toString());
                }
                catch(NullPointerException e){
                    System.out.println("O utilizador não apresenta encomenda emitidas.\n");
                }
            }
        }
    }

    public void ordenarUtilizadoresPorFaturamentoInterativo(String inferior, String superior) throws ExceptionData{
        LocalDate inf = formataData(inferior);
        LocalDate sup = formataData(superior);
            
        ordenarUtilizadoresPorFaturamento(inf, sup);
    }

 // ---------------------------------------------------------------------------------------------


    public void addTransportadora(Transportadora t){
        if(!this.transpDisponiveis.containsKey(t.getNome())){
            this.transpDisponiveis.put(t.getNome(), t);
        }

    }

    public void enviarEncomenda(Utilizador utilizador){
        Encomenda enc = encontrarEncomendaFinalizada(utilizador);

        try{
            enc.setEstado(Encomenda.St.EXPEDIDA);
            LocalDate aux = this.getDataAtual();

            // definir o dia de entrega da encomenda (passado DOIS dias da sua finalização, enc.getTempEntrega() = 2)
            aux = aux.plusDays(enc.getTempEntrega());
            enc.setDataEntrega(aux); // avança os dias necessários

            //atualiza a encomenda
            utilizador.addEncEncomendas(enc.clone());

            //coloca a encomenda na lista das encomendas do sistema
            this.encomendas.put(utilizador.getId(), utilizador.getEncomendas());    
        }
        catch(NullPointerException e){
            System.out.println("A encomenda do Utilizador " + utilizador.getId() + " não existe\n");
        }
    }

    public Encomenda encontrarEncomendaFinalizada(Utilizador utilizador) {
        for (Encomenda enc : utilizador.getEncomendas().values()) {
            if (enc.getEstado().equals(Encomenda.St.FINALIZADA)) {
                return enc;
            }
        }
        return null;
    }

    public double precoTransportadora(Map<Integer, Artigo> encomenda) {
        Map<Transportadora, Long> transportadoras = encomenda.values().stream()
            .collect(Collectors.groupingBy(Artigo::getTransportadora, Collectors.counting()));
        int sizeEncomenda = encomenda.values().size();
    
        double valorFinal = transportadoras.entrySet().stream().mapToDouble(transportadora -> {
            if (transportadora.getKey() instanceof TPremium) {
                TransportadoraPremium tp = (TransportadoraPremium) transportadora.getKey();
                System.out.println(sizeEncomenda);
                double custoB = this.precoBaseT(tp.clone(), sizeEncomenda);
                tp.setTotalAuferido(tp.calculaExpedicaoPremium(transportadora.getValue()) + custoB + tp.getTotalAuferido());
                this.transpDisponiveis.put(tp.getNome(), tp.clone());
                return tp.getTotalAuferido();

            } else if (transportadora.getKey() instanceof TNormal) {
                TransportadoraNormal tn = (TransportadoraNormal) transportadora.getKey();
                double custoB = this.precoBaseT(tn.clone(), sizeEncomenda);
                tn.setTotalAuferido(tn.calculaExpedicaoNormal(transportadora.getValue()) + custoB + tn.getTotalAuferido());
                this.transpDisponiveis.put(tn.getNome(), tn.clone());
                return tn.getTotalAuferido();

            } else {
                return 0.0; // ou um valor padrão, caso seja apropriado
            }

        }).sum();
        return Math.round(valorFinal * 100) / 100.0;
    }

    public double precoBaseT(Transportadora t, int sizeEncomenda){
        double custoB = 0.0;
        if(sizeEncomenda == 1){
            custoB = t.getCustoPequena();
        }
        else if(sizeEncomenda >1 && sizeEncomenda <=5){
            custoB = t.getCustoMedia();
        }
        else{
            custoB = t.getCustoGrande();
        }
        return custoB;
    }
    

    public void atualizaVendedor(Encomenda encomenda, Fatura vendfat, Utilizador vendedor, Artigo artigo) {

        Map<Integer, Map<Integer,Artigo>> vendasSistemaCopia = this.getVendas();
        Encomenda encVendedor;
        
        // Verifica se a fatura já tem uma encomenda
        if (vendfat.getEncomenda() == null) {
            // Se não tiver, cria uma nova encomenda com as informações da encomenda original
            encVendedor = new Encomenda();
            encVendedor.setEstado(Encomenda.St.ENTREGUE);
            encVendedor.setDataCriacao(encomenda.getDataCriacao());
            encVendedor.setDataEntrega(encomenda.getDataEntrega());
            
        } else {
            // Se já tiver uma encomenda
            encVendedor = vendfat.getEncomenda();
        }

        // Adiciona o artigo à nova encomenda
        encVendedor.addArtigoEncomenda(artigo.clone()); 

        // Atualiza a dimensão da encomenda 
        encVendedor.alteraDimensao();
        encVendedor.valorEncomenda(this.dataAtual.getYear(), 0, 0, 0, taxaServiço);

        vendfat.addEncFatura(encVendedor);
        vendfat.calculaValorFatura();

        vendedor.addFatura(vendfat);
        
        // Vende o artigo
        vendedor.addArtigoVendas(artigo.clone());
        vendedor.removeArtigoAVenda(artigo.clone());
        
        // Adiciona o artigo aos artigos vendidos do sistema
        vendasSistemaCopia.put(vendedor.getId(), vendedor.getVendeu());
        this.vendas.clear();
        this.vendas.putAll(vendasSistemaCopia);      
    }

    public void atualizaComprador(int numArtigos, Encomenda encomenda, Fatura compfat, Utilizador comprador){
        
        Map<Integer, Map<Integer, Encomenda>> encomendasSistemaCopia = this.getEncomendas();
        // Atualiza a dimensão da encomenda 
        encomenda.alteraDimensao();

        //adiciona a fatura ao comprador
        encomenda.setEstado(Encomenda.St.ENTREGUE);

        compfat.addEncFatura(encomenda);
        compfat.calculaValorFatura();
        comprador.addFatura(compfat);

        //Remover a encomenda que foi entregue da hashMap encomendas, do comprador
        comprador.removeEncEncomenda(encomenda);

        // Verificar se o comprador tem mais encomendas na hashMap da Vintage, se sim atualiza caso contrário remove a chave da mesma
        if(!comprador.getEncomendas().isEmpty()){
            encomendasSistemaCopia.put(comprador.getId(), comprador.getEncomendas());
        }
        else {
            encomendasSistemaCopia.remove(comprador.getId());
        }
        this.encomendas.clear();
        this.encomendas.putAll(encomendasSistemaCopia);
    }

    private Utilizador obterVendedorDoArtigo(Map<Integer, Utilizador> vendedoresProcessados) {
        for (Utilizador vendedor : this.getCreds().values()) {
            if (vendedoresProcessados.containsValue(vendedor)) {
                return vendedor;
            }
        }
        return null;
    }

    public void atualiza() {
        // Percorre todas as encomendas
        for (Map<Integer, Encomenda> encomendasUtilizadores : this.getEncomendas().values()) {
            Fatura compfat = new Fatura();
            compfat.setTipo(Fatura.Tp.COMPRA);
            Fatura vendfat = new Fatura();
            vendfat.setTipo(Fatura.Tp.VENDA);

            Utilizador comprador = null;
            for (Encomenda encomenda : encomendasUtilizadores.values()) {
    
                //verificar se o dia da entrega da encomenda chegou
                if (encomenda.getDataEntrega().isEqual(this.getDataAtual())) {

                    for (Utilizador compradorAUX : this.getCreds().values()) {
                        if (this.getEncomendas().containsKey(compradorAUX.getId())){
                            comprador = compradorAUX;
                            break;
                        }
                    }
                    int numArtigos = 0;
                    try{
                        // Obter a lista de artigos da encomenda
                        Map<Integer, Artigo> artigosEncomenda = encomenda.getArtigos();

                        //Criar uma lista de vendedores que já apareceram(Para quando um comprador compra mais que um artigo a um vendedor)
                        Map<Integer, Utilizador> vendedoresProcessados = new HashMap<>();

                        //numero de artigos para saber o tamanho da encomenda;
                        for(Artigo artigo : artigosEncomenda.values()){
                           
                            Utilizador vendedor = obterVendedorDoArtigo(vendedoresProcessados);
                            if (vendedor == null) {
                                for (Utilizador vendedorAUX : this.getCreds().values()) {
                                    if (vendedorAUX.getAVenda().containsKey(artigo.getCodBarras())) {
                                        vendedor = vendedorAUX;
                                        vendedoresProcessados.put(vendedor.getId(), vendedor.clone());
                                        break;
                                    }
                                }
                            }
                            try {
                                this.atualizaVendedor(encomenda, vendfat, vendedor, artigo);
                                // Compra o artigo
                                comprador.addArtigoCompras(artigo.clone());

                            } catch (NullPointerException e) {
                                System.out.println("Artigo não encontrado\n");
                            }
                            numArtigos++;
                        }
                    } 
                    catch(NullPointerException e){
                        System.out.println("Nenhum comprador encontrado para concluir a encomenda");
                    }
                    this.atualizaComprador(numArtigos, encomenda, compfat, comprador);

                    //lucro da vintage
                    this.totalAuferido += encomenda.getPrecoFinal()*taxaServiço;
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

    public LocalDate formataData(String data) throws ExceptionData{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
        try{
            return LocalDate.parse(data, formatter);
        }catch(DateTimeParseException e){
            throw new ExceptionData("Formato da data inválida.\n" + data);
        }
    }

    //avança a data atual
    public void avancaData(String data)throws ExceptionData{
        LocalDate dataFutura = formataData(data);
        long dias = 0;
        try{
            dias = ChronoUnit.DAYS.between(this.dataAtual, dataFutura);
        }catch(NullPointerException e){
            System.out.println("Selecione uma data superior à atual ( "+ this.getDataAtual()+" )");
        }
        int i = 0;
        while(i < dias){
            this.setDataAtual(this.getDataAtual().plusDays(1));
            this.atualiza();
            i++;
        }
    }

    //não leva clone que é para poder ser alterado diretamente
    public void registaUtilizador(String email, Utilizador utilizador){
        this.creds.put(email, utilizador);
        utilizador.setEmail(email);
    }

    //nao se adiciona aos vendidos do utilizador porque nunca se chegou a remover até que a entrega seja concluida
    public void devolveEncomenda(Utilizador utilizador) throws NullPointerException, ExceptionDevolver{
        if(utilizador.getEncomendas().values().size()==0){
            throw new ExceptionDevolver("O utilizador não tem encomendas para devoler.\n");
        }
        for (Encomenda encomenda : utilizador.getEncomendas().values()) {
            //verificar se o dia da entrega da encomenda ainda não chegou
            if (!this.getDataAtual().isBefore(encomenda.getDataEntrega())) {
                throw new ExceptionDevolver("A data limite para a devolução do artigo foi ultrapassada.\n");
            }
            try{
                // Obter a lista de artigos da encomenda
                Map<Integer, Artigo> artigosEncomenda = encomenda.getArtigos();

                //numero de artigos para saber o tamanho da encomenda;
                for(Artigo artigo : artigosEncomenda.values()){

                    // adiciona cada artigo de novo ao stock da vintage ficando assim disponivel para venda no utilizador que o estava a vender(nunca se chegou a remover da hashMap AVenda do vendedor)
                    try{
                        this.addStock(artigo.clone()); 
                    } 
                    catch(NullPointerException e)  {
                        System.out.println("Artigo não encontrado\n");
                    }
                }
                //Remover a encomenda que foi entregue da hashMap encomendas, do comprador
                utilizador.removeEncEncomenda(encomenda.clone());
            } 
            catch(Exception e){
                System.out.println("Nenhum encomenda encontrado para devolver do utilizador");
            }

            // Verificar se o comprador tem mais encomendas na hashMap da Vintage, se sim atualiza caso contrário remove a chave da mesma
            if(!utilizador.getEncomendas().isEmpty()){
                this.encomendas.replace(utilizador.getId(), utilizador.getEncomendas());
            }
            else {
                this.encomendas.remove(utilizador.getId());
            }
        }
    }

    public void maiorVendedor(LocalDate inferior, LocalDate superior) throws ExceptionData{
        double maiorFaturamento = 0.0;
        Utilizador utilizadorMaiorFaturamento = null;

        if(inferior.compareTo(superior)>0){
            throw new ExceptionData("A data inferior é maior que a data superior.");
        }
        
        for (Utilizador utilizador : this.creds.values()) {
            double faturamento = 0.0;
            
            for (Fatura fatura : utilizador.getFaturas().values()) {
                if(fatura.getTipo().equals(Fatura.Tp.VENDA)){
                    if(fatura.getEncomenda().getDataEntrega().isAfter(inferior) && fatura.getEncomenda().getDataEntrega().isBefore(superior)){
                        faturamento += fatura.getValorTotal();
                    }
                }
            }
            if (faturamento >= maiorFaturamento) {
                maiorFaturamento = faturamento;
                utilizadorMaiorFaturamento = utilizador;
            }
        }
        try{
            double valorFatura = Math.round(maiorFaturamento*100)/100 ;
            System.out.println("O utilizador que mais fatorou é o " + utilizadorMaiorFaturamento.getId()+  " com um total de " + valorFatura+ " euros\n");
        }catch(NullPointerException e){
            System.out.println("Não há utilizadores registados no sistema");
        }

    }

    public void maiorTransportadora(){
        double maiorFaturamento = 0.0;
        Transportadora transportadoraMaiorFaturamento = null;
        for (Transportadora transportadora : this.getTranspDisponiveis().values()) {
            double faturamento = transportadora.getTotalAuferido();
            if(faturamento>=maiorFaturamento){
                transportadoraMaiorFaturamento = transportadora;
                maiorFaturamento = faturamento;
            } 
        }
        try{
        System.out.println("A transportadora que mais fatorou foi a " + transportadoraMaiorFaturamento.getNome() + " com um total de " + Math.round(transportadoraMaiorFaturamento.getTotalAuferido()));
        }catch(NullPointerException e){
        System.out.println("Não há transportadoras registadas no sistema");
        }
    }

    public void encomendasVendedor(Utilizador vendedor){
        for (Fatura fatura : vendedor.getFaturas().values()) {
            if(fatura.getTipo().equals(Fatura.Tp.VENDA))
                try{
                    System.out.println("As encomendas emitidas pelo vendedor " + vendedor.getEmail() + " são as seguintes \n" + fatura.getEncomenda().toString());
                }
                catch(NullPointerException e){
                    System.out.println("O utilizador não apresenta faturas emitidas\n");
                }
        }
    }

    public void ordenarUtilizadoresPorFaturamento(LocalDate inferior, LocalDate superior) throws ExceptionData{
        List<Utilizador> utilizadores = new ArrayList<>(this.creds.values());

        if(inferior.compareTo(superior)>0){
            throw new ExceptionData("A data inferior é maior que a data superior.");
        }
        //compara os fatoramentos
        Comparator<Utilizador> comp = Comparator.comparingDouble(utilizador -> {
            double faturamento = 0.0;
                for (Fatura fatura : utilizador.getFaturas().values()) {
                    //só por na lista quem tem faturas;
                    if (fatura.getEncomenda().getDataEntrega().isAfter(inferior) && (fatura.getEncomenda().getDataEntrega().isBefore(superior) || fatura.getEncomenda().getDataEntrega().isEqual(superior))) {
                        faturamento += fatura.getValorTotal();
                    }
                }
            return faturamento;
        });
        try{
            System.out.println("Lista dos id's dos utilizadores que mais faturaram por ordem crescente: " + utilizadores.stream().sorted(comp).map(Utilizador::getEmail).collect(Collectors.toList()));
        }catch(NullPointerException e){
            System.out.println("Não existe utilizadores registados no sistema");
        }
    }
    
    public void acoesRapidas(String filename) throws ExceptionData, ExceptionUser{
       loadficheiro(filename);
    }    

    public List<String> lerFicheiro(String nomeFich){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return lines;
    }

    public boolean isDate(String dateStr) {
        try {
            formataData(dateStr);
            return true;
        } catch (ExceptionData e) {
            return false;
        }
    }

    public void loadficheiro(String filename) throws ExceptionData{
        List<String> linhas = lerFicheiro(filename);
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Data":{
                    String[] aux = linhaPartida[1].split(":", 2);
                    parseData(aux[0]);
                    parsingGeral(aux[1]);
                    break;
                }
                default : {
                    parsingGeral(linha);
                }
            }
        }
    }

    public void parsingGeral(String str){
        String[] linhaPartida = str.split(":", 2);
        switch(linhaPartida[0]){
            case "Transportadora": {
                parseTransportadora(linhaPartida[1]);
                break;
            }
            case "Utilizador":{
                parseUtilizador(linhaPartida[1]);
                break;
            }
            case "Artigo":{
                String tipo = tipo(linhaPartida[1]);
                switch(tipo){
                    case "Mala":{
                        parseMala(linhaPartida[1]);                          
                        break;
                    }
                    case "Sapatilha":{
                        parseSapatilha(linhaPartida[1]);             
                        break;
                    }
                    case "Tshirt":{
                        parseTshirt(linhaPartida[1]);
                        break;
                    }
                } 
                break;
            }
            case "Vender":{
                try{
                    parseVendas(linhaPartida[1]);
                }catch(ExceptionUser e){
                    System.out.println(e.getMessage());
                }catch(ExceptionTransportadora e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "Encomendar":{
                try{
                parseEncomendas(linhaPartida[1]);
                }catch(ExceptionUser e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "Finalizar":{
                try{
                    parseFinaliar(linhaPartida[1]);
                }catch(ExceptionUser e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "Alterar preco artigo":{
                try{
                    parsePreco(linhaPartida[1]);
                }catch(ExceptionArtigo e){
                    System.out.println(e.getMessage());
                }catch(ExceptionUser e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "Alterar transportadora":{
                try{
                    parseT(linhaPartida[1]);
                }catch(ExceptionTransportadora e){
                    System.out.println(e.getMessage());
                }catch(ExceptionArtigo e){
                    System.out.println(e.getMessage());
                }catch(ExceptionUser e){
                    System.out.println(e.getMessage());
                }
                break;

            }
            case "Alterar custo transportadora":{
                try{
                    parseCustoT(linhaPartida[1]);
                }catch(ExceptionTransportadora e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public void parseData(String strData) throws ExceptionData{
        String[] aux = strData.split(":", 2);
        LocalDate datacomando = null;
        if(isDate(aux[0])){
            datacomando = formataData(aux[0]);

            if (datacomando.isBefore(this.dataAtual)) {
                throw new ExceptionData("Data do comando ultrapassada");
            } 
            else if(datacomando.isAfter(this.dataAtual)){
                this.avancaData(aux[0]);
            }
            else{;}
        }
        this.setDataAtual(datacomando);
    }
    
    public void parseCustoT(String srtPreco) throws ExceptionTransportadora{
        String[] aux = srtPreco.split(";");
        Transportadora transportadora = this.transpDisponiveis.get(aux[0]);
        if(transportadora == null){
            throw new ExceptionTransportadora("A transportadora nao existe no sistema.\n\n");
        }
        String tamanho = aux[1];
        switch(tamanho){
            case "P":{
                transportadora.setCustoPequena(Double.parseDouble(aux[2]));
            }
            case "M":{
                transportadora.setCustoMedia(Double.parseDouble(aux[2]));
            }
            case "G":{
                transportadora.setCustoGrande(Double.parseDouble(aux[2]));
            }
            case "A":{
                if( transportadora instanceof TransportadoraPremium){
                    ((TransportadoraPremium) transportadora).setCustoAdicional(Double.parseDouble(aux[2]));
                }
            }
        }
        this.transpDisponiveis.replace(transportadora.getNome(), transportadora.clone());
    }

    public void parseT(String srtPreco) throws ExceptionTransportadora, ExceptionArtigo, ExceptionUser{
        String[] aux = srtPreco.split(";");
        String email = aux[0];
        Utilizador user = this.creds.get(email);
        if(user==null){
            throw new ExceptionUser("O utilizador nao existe no sistema.\n");
        }
        int indice = Integer.parseInt(aux[1]);
        Artigo artigo = this.stock.get(indice);
        if(artigo==null){
            throw new ExceptionArtigo("O artigo já não se encontra à venda para ser alterado o seu transportador.\n");
        }
        Transportadora transportadora = this.transpDisponiveis.get(aux[2]);
        if(transportadora == null){
            throw new ExceptionTransportadora("A transportadora nao existe no sistema.\n");
        }
        artigo.setTransportadora(transportadora.clone());
        this.addStock(artigo.clone());
    }

    public void parsePreco(String srtPreco) throws ExceptionArtigo, ExceptionUser{
        String[] aux = srtPreco.split(";");
        String email = aux[0];
        Utilizador user = this.creds.get(email);
        if(user==null){
            throw new ExceptionUser("O utilizador nao existe no sistema.\n");
        }
        int indice = Integer.parseInt(aux[1]);
        Artigo artigo = this.stock.get(indice);
        if(artigo==null){
            throw new ExceptionArtigo("O artigo já não se encontra à venda para ser alterado o seu valor.\n");
        }
        artigo.setPrecoBase(Double.parseDouble(aux[2]));
        user.addArtigoAVenda(artigo.clone());
        this.creds.replace(email, user.clone());
    }

    public void parseFinaliar(String srtFinalizar) throws ExceptionUser{
        String[] aux = srtFinalizar.split(";");
        String email = aux[0];
        Utilizador user = this.creds.get(email);
        if(user==null){
            throw new ExceptionUser("O utilizador nao existe no sistema.\n");
        }
        user.finalizarEncomenda(this);
        this.creds.replace(email, user.clone());
    }

    public void parseEncomendas(String srtEncomendas) throws ExceptionUser{
        String[] aux = srtEncomendas.split(";");
        String email = aux[0];
        Utilizador user = this.creds.get(email);
        if(user==null){
            throw new ExceptionUser("O utilizador nao existe no sistema.\n");
        }
        int indice = Integer.parseInt(aux[1]);
        Utilizador vendedor = null;
            for (Utilizador vendedorAUX : this.getCreds().values()) {
                if (vendedorAUX.getAVenda().containsKey(indice)) {
                    vendedor = vendedorAUX;
                    break;
                }
            }
        Artigo artigo = vendedor.getAVenda().get(indice);
        if(artigo == null){
            throw new ExceptionUser("O artigo nao está à venda no sistema para ser encomendado.\n");
        }
        user.colocaEncomenda(this, artigo.clone());
        this.creds.replace(email, user.clone());
    }

    public void parseVendas(String srtVendas) throws ExceptionUser, ExceptionTransportadora{
        String[] aux = srtVendas.split(";");
        String email = aux[0];
        Utilizador user = this.creds.get(email);

        if(user==null){
            throw new ExceptionUser("O utilizador nao existe no sistema.\n");
        }

        int indice = Integer.parseInt(aux[1]);
        Artigo artigo =  this.stock.get(indice);
        if(artigo == null){
            throw new ExceptionUser("O artigo não foi criada para poder ser colocado à venda.\n");
        }
        String transportadora = aux[2];
        Transportadora transp = null;

        if (this.transpDisponiveis.containsKey(transportadora)) {
             transp = this.transpDisponiveis.get(transportadora);
        } else {
            throw new ExceptionTransportadora("A transportadora não está registada no sistema.\n");
        }
        user.aVendaArtigo(this, artigo.clone(), transp);
        this.creds.replace(email, user.clone());
    }

    public void parseUtilizador(String strUtilizador) {
        String[] campos = strUtilizador.split(";");

        String email = campos[0];
        String nome = campos[1];
        String morada = campos[2];
        int nif = Integer.parseInt(campos[3]);

        this.registaUtilizador(email, new Utilizador(email, nome, morada, nif, new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Artigo>(), new HashMap<Integer,Encomenda>(), new HashMap<Integer,Fatura>()));
    }

    public void parseTransportadora(String dadosTransportadora) {
        String[] dados = dadosTransportadora.split(";");
        String nome = dados[0];
        String isPremium = dados[1];
        double custop = Double.parseDouble(dados[2]);
        double custom = Double.parseDouble(dados[3]);
        double custog = Double.parseDouble(dados[4]);
        double imposto = Double.parseDouble(dados[5]);
        double custoadicional = 0.0;
        if(isPremium.equals("Sim")){
            custoadicional = Double.parseDouble(dados[6]);
        }

        if(isPremium.equals("Sim")){
           this.addTransportadora(new TransportadoraPremium(nome, custop, custom, custog, imposto, custoadicional));
        }
        else{
           this.addTransportadora(new TransportadoraNormal(nome, custop, custom, custog, imposto));
        }
    }

    public String tipo(String string){
        String[] dados = string.split(";");
        return dados[0];
    }

    public void parseMala(String dadosMala){
        String[] dados = dadosMala.split(";");
        String isPremium = dados[1];
        String estado = dados[2];
        Artigo.St st = null;
        int donos = Integer.parseInt(dados[3]);
        String desc = dados[5];
        String mrc = dados[6];
        double pb = Double.parseDouble(dados[7]);
        double cp = Double.parseDouble(dados[8]);
        String dimensao = dados[9];
        Mala.Dim dim = null;
        String material = dados[10];
        int anoLancamento = Integer.parseInt(dados[11]);

        switch (dimensao) {
            case "Pequena":
                dim = Mala.Dim.PEQUENA;
                break;
            case "Média":
                dim = Mala.Dim.MEDIA;
                break;
            case "Grande":
                dim = Mala.Dim.GRANDE;
                break;
        }
        switch (estado) {
            case "Mau":
                st = Artigo.St.MAU;
                break;
            case "Médio":
                st = Artigo.St.MEDIO;
                break;
            case "Bom":
                st = Artigo.St.BOM;
                break;
            case "Muito bom":
                st = Artigo.St.MUITO_BOM;
                break;
            case "Execlente":
                st = Artigo.St.EXCELENTE;
                break;
        }

        if(isPremium.equals("Sim")){
            this.addStock(new MalaPremium(st, donos, null, desc, mrc, pb, cp, dim, material, anoLancamento));
         }
         else{
            this.addStock(new MalaNU(st, donos, null, desc, mrc, pb, cp, dim, material, anoLancamento));
         }
    }

    public void parseTshirt(String dadosMala){
        String[] dados = dadosMala.split(";");
        String estado = dados[2];
        Artigo.St st = null;
        int donos = Integer.parseInt(dados[3]);
        String desc = dados[5];
        String mrc = dados[6];
        double pb = Double.parseDouble(dados[7]);
        double cp = Double.parseDouble(dados[8]);
        String tamanho = dados[9];
        Tshirt.Tam tam = null;
        String padrao = dados[10];
        Tshirt.Pad pad = null;

        switch (estado) {
            case "Mau":
                st = Artigo.St.MAU;
                break;
            case "Médio":
                st = Artigo.St.MEDIO;
                break;
            case "Bom":
                st = Artigo.St.BOM;
                break;
            case "Muito bom":
                st = Artigo.St.MUITO_BOM;
                break;
            case "Execlente":
                st = Artigo.St.EXCELENTE;
                break;
        }

        switch (tamanho) {
            case "S":
                tam = Tshirt.Tam.S;
                break;
            case "M":
                tam = Tshirt.Tam.M;
                break;
            case "L":
                tam = Tshirt.Tam.L;
                break;
            case "XL":
                tam = Tshirt.Tam.XL;
                break;
        }
        switch (padrao) {
            case "Liso":
                pad = Tshirt.Pad.LISO;
                break;
            case "Riscas":
                pad = Tshirt.Pad.RISCAS;
                break;
            case "Palmeiras":
                pad = Tshirt.Pad.PALMEIRAS;
                break;
        }

        this.addStock(new TshirtNU(st, donos, null, desc, mrc, pb, cp, tam, pad));
    }

    public void parseSapatilha(String dadosMala){
        String[] dados = dadosMala.split(";");
        String isPremium = dados[1];
        String estado = dados[2];
        Artigo.St st = null;
        int donos = Integer.parseInt(dados[3]);
        String desc = dados[5];
        String mrc = dados[6];
        double pb = Double.parseDouble(dados[7]);
        double cp = Double.parseDouble(dados[8]);
        int tamanho = Integer.parseInt(dados[9]);
        boolean atacadores = Boolean.parseBoolean(dados[10]);
        String cor = dados[11];
        int anoLancamento = Integer.parseInt(dados[12]);

        switch (estado) {
            case "Mau":
                st = Artigo.St.MAU;
                break;
            case "Médio":
                st = Artigo.St.MEDIO;
                break;
            case "Bom":
                st = Artigo.St.BOM;
                break;
            case "Muito bom":
                st = Artigo.St.MUITO_BOM;
                break;
            case "Excelente":
                st = Artigo.St.EXCELENTE;
                break;
        }

        if(isPremium.equals("Sim")){
            this.addStock(new SapatilhaPremium(st, donos, null, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento));
         }
         else{
            this.addStock(new SapatilhaNU(st, donos, null, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento));

        }
    }

        //carregar de ficheiro objeto
        public void loadEstadoObj(String file) throws IOException, ClassNotFoundException {
            Vintage e = loadAux(file);
            this.stock = e.stock;
            this.encomendas = e.encomendas;
            this.vendas = e.vendas;
            this.creds = e.creds;
            this.transpDisponiveis = e.transpDisponiveis;
            this.totalAuferido = e.totalAuferido;
            this.dataAtual = e.dataAtual;
        }
    
        public Vintage loadAux(String file) throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Vintage e = (Vintage) ois.readObject();
            ois.close();
            return e;
        }
    
        public void saveEstado() throws IOException {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.obj"));
            oos.writeObject(this);
            oos.flush();
            oos.close();
        }

        public void salvaEstadoObj() throws IOException {
            this.saveEstado();
        }
    
        public Vintage reiniciarSistemaInterativo(){
            return new Vintage();
        }
}