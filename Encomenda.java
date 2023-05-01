import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {
    
    private static int numEncomenda = 0; ////Mantém contagem de encomendas e é utilizada para o id de uma encomenda.

    enum St { //Enum dos diferentes estados de processamento de uma encomenda,
        PENDENTE,
        FINALIZADA,
        EXPEDIDA,
        ENTREGUE
    }

    enum Dim { //Enum dos diferentes tamanhos de uma encomenda.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private final int id; //Guarda o número de identificação de uma encomenda.
    private LocalDate dataCriacao; //Guarda a data de criação de uma encomenda.
    private St estado; //Guarda o estado de uma encomenda.
    private Dim dimensao; //Guarda a dimensão de uma encomenda.
    private Map<Integer,Artigo> artigos; //Guarda o conjunto de artigos que constitui a encomenda.
    private double precoFinal; //Guarda o preço final da encomenda.
    private LocalDate dataEntrega; //Data em que o artigo será entregue
    private final int tempoEntrega = 2; // Tempo que demora à encomenda a ser entregue.

    /**
     * Construtor vazio.
     */
    public Encomenda() {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = null;
        this.estado = St.PENDENTE;
        this.dimensao = null;
        this.artigos = new HashMap<Integer,Artigo>();
        this.precoFinal = 0;
        this.dataEntrega = null;
    }

    /**
     * Construtor parametrizado.
     */

    public Encomenda(St etd, Dim dim, Map<Integer,Artigo> art, double preco, LocalDate dataEntrega) {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = null;
        this.estado = etd;
        this.dimensao = dim;
        this.artigos = new HashMap<Integer,Artigo>();
        for(Map.Entry<Integer,Artigo> e : art.entrySet())
        {
            this.artigos.put(e.getKey(), e.getValue().clone());
        }
        this.precoFinal = preco; 
        this.dataEntrega = dataEntrega;
    }

    /**
     * Construtor de cópia.
     */
    public Encomenda(Encomenda umaEncomenda) {
        this.id = umaEncomenda.getId();
        this.dataCriacao = umaEncomenda.getDataCriacao();
        this.estado =umaEncomenda.getEstado();
        this.dimensao = umaEncomenda.getDimensao();
        this.artigos = umaEncomenda.getArtigos();
        this.precoFinal = umaEncomenda.getPrecoFinal();
        this.dataEntrega = umaEncomenda.getDataEntrega();
    }

    /**
     * Getters das variáveis de instância de uma encomenda.
     */
    public int getId() {
        return this.id;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public St getEstado() {
        return this.estado;
    }

    public Dim getDimensao() {
        return this.dimensao;
    }

    public Map<Integer,Artigo> getArtigos() {
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.artigos.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }

    public LocalDate getDataEntrega() {
        return this.dataEntrega;
    }

    public int getTempEntrega() {
        return this.tempoEntrega;
    }

    /**
     * Setters das variáveis de instância de uma encomenda.
     */
    public void setEstado(St etd) {
        this.estado = etd;
    }

    public void setDimesao(Dim dim) {
        this.dimensao = dim;
    }

    public void setArtigos(Map<Integer,Artigo> art) {
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : art.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.artigos = map;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    /**
     * Método clone.
     */
    @Override
    public Encomenda clone() {
        return new Encomenda(this);
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" ----- ENCOMENDA ----- \n");
        sb.append(" Id -> " + this.getId() + "\n");
        sb.append(" Data de criação -> " + (this.getDataCriacao() != null ? this.getDataCriacao().toString() : "Não associada") + "\n");
        sb.append(" Estado -> " + this.getEstado().toString() + "\n");
        sb.append(" Dimensão -> " + (this.getDimensao() != null ? this.getDimensao().toString() : "Não associada") + "\n");
        sb.append("\n Artigos na encomenda:\n");
        this.getArtigos().forEach((chave,valor) -> sb.append( " Chave: " + chave.toString() + "\n"));
        sb.append(" Preço final -> " + Math.round(this.getPrecoFinal()*100)/100 + "€\n");
        sb.append(" Data de entrega -> " + this.getDataEntrega() + "\n");
        sb.append(" Tempo para entrega -> " + this.getTempEntrega() + "\n\n");
        sb.append(" ----- FIM DA ENCOMENDA ----- \n");
        return sb.toString();
    }

    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
            return true;
        if(obj==null || obj.getClass() != this.getClass()) 
            return false;
        Encomenda encomenda = (Encomenda) obj;
        return encomenda.getId() == this.getId() &&
               encomenda.getDataCriacao().equals(this.getDataCriacao()) &&
               encomenda.getEstado().equals(this.getEstado()) &&
               encomenda.getDimensao().equals(this.getDimensao()) &&
               encomenda.getArtigos().equals(this.getArtigos()) &&
               encomenda.getPrecoFinal() == this.getPrecoFinal() &&
               encomenda.dataEntrega.equals(this.getDataEntrega());
    }

    /**
     * Adiciona um artigo a uma encomenda.
     */
    public void addArtigoEncomenda(Artigo a) {
        this.artigos.put(a.getCodBarras(), a.clone());
    }

    /**
     * Remove um artigo de uma encomenda, dado o seu código de barras.
     * @param id
     */
    public void removeArtigo(Integer codBarras) {
        this.artigos.remove(codBarras);
    }

    public double precoTransportadora(Map<Integer, Artigo> artigos){
        double valorFinal = 0.0;
        Map<Transportadora, Long> transportadoras = artigos.values().stream()
        .collect(Collectors.groupingBy(Artigo::getTransportadora, Collectors.counting()));

        for (Map.Entry<Transportadora, Long> transportadora : transportadoras.entrySet()) {
            if(transportadora.getValue() == 1){
                valorFinal += transportadora.getKey().calcularValorExpedicaoPequeno();
            }
            else if(transportadora.getValue() > 1 && transportadora.getValue()<=5){
                valorFinal += transportadora.getKey().calcularValorExpedicaoMedio();
            }
            else if(transportadora.getValue() > 5){
                valorFinal += transportadora.getKey().calcularValorExpedicaoGrande();
            } 
        }
        return valorFinal;

    }

    //Calculo do valor da encomenda do COMPRADOR
    public double valorEncomenda(int anoAtual, double taxaGSNovo, double taxaGSUsado){
        double x = this.artigos.values().stream().mapToDouble(artigo -> {
            if (artigo instanceof ArtigoNovo) {
                return ((ArtigoNovo) artigo).calcularValorArtigoNovo(anoAtual) - taxaGSNovo; // chama o método correto para ArtigoNovo

            } else if (artigo instanceof ArtigoUsado) {
                return ((ArtigoUsado) artigo).calcularValorArtigoUsado(anoAtual) - taxaGSUsado; // chama o método correto para ArtigoUsado

            } else if(artigo instanceof ArtigoPremium){
                return ((ArtigoPremium) artigo).calcularValorArtigoPremium(anoAtual); // chama o método correto para ArtigoPremium
            } else{
                return 0;
            }
        }).sum();
        double precoTotalTransportadoras = this.precoTransportadora(this.artigos);
        this.precoFinal = x + precoTotalTransportadoras;
        return precoFinal;
    }
}

