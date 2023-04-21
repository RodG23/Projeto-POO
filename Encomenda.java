import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private final Tempo dataCriacao; //Guarda a data de criação de uma encomenda.
    private St estado; //Guarda o estado de uma encomenda.
    private Dim dimensao; //Guarda a dimensão de uma encomenda.
    private List<Artigo> artigos; //Guarda o conjunto de artigos que constitui a encomenda.
    private double precoFinal; //Guarda o preço final da encomenda.
    private Tempo dataEntrega; //Data em que o artigo será entregue
    private final int tempoEntrega = 2;

    /**
     * Construtor vazio.
     */
    public Encomenda() {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = null;
        this.estado = St.PENDENTE;
        this.dimensao = null;
        this.artigos = new ArrayList<Artigo>();
        this.precoFinal = 0;
        this.dataEntrega = null;
    }

    /**
     * Construtor parametrizado.
     */

    public Encomenda(St etd, Dim dim, List<Artigo> art, double preco) {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = null;
        this.estado = etd;
        this.dimensao = dim;
        this.artigos = art;
        this.precoFinal = preco; 
        this.dataEntrega = dataEntrega;
    }

    /**
     * Construtor de cópia.
     */
    public Encomenda(Encomenda umaEncomenda) {
        numEncomenda++;
        this.id = numEncomenda;
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

    public Tempo getDataCriacao() {
        return this.dataCriacao;
    }

    public St getEstado() {
        return this.estado;
    }

    public Dim getDimensao() {
        return this.dimensao;
    }

    public List<Artigo> getArtigos() {
        return this.artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }

    public int getTempEntrega() {
        return this.tempoEntrega;
    }

    public Tempo getDataEntrega() {
        return this.dataEntrega;
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

    public void setArtigos(List<Artigo> art) {
        this.artigos = art;
    }

    public void setDataEntrega(Tempo dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    /**
     * Adiciona ao custo da encomenda o valor do artigo adicionado.
     */
    public void addPrecoFinal(double cost) {
        this.precoFinal += cost;
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

        sb.append("| Encomenda |\n");
        sb.append(" Id -> " + this.id + "\n");
        sb.append(" Data de criação -> " + (this.dataCriacao != null ? this.dataCriacao.toString() : "") + "\n");
        sb.append(" Estado -> " + this.estado.toString() + "\n");
        sb.append(" Dimensão -> " + (this.dimensao != null ? this.dimensao.toString() : "") + "\n");
        sb.append(" Artigos:\n");
        this.artigos.forEach(a -> sb.append(a.toString()));
        sb.append(" Preço final -> " + this.precoFinal + "€\n");
        sb.append(" Data de entrega -> " + this.dataEntrega + "%\n");
        sb.append(" Tempo para entrega -> " + this.tempoEntrega + "%\n");

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
               encomenda.dataEntrega.equals(this.getDataEntrega()) &&
               encomenda.tempoEntrega == this.tempoEntrega;
    }

    public void addArtigo(Artigo a){
        this.artigos.add(a);
    }
}

