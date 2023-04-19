import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Encomenda {
    
    private static int numEncomenda = 0; ////Mantém contagem de encomendas e é utilizada para o id de uma encomenda.

    enum St { //Enum dos diferentes estados de processamento de uma encomenda,
        PENDENTE,
        FINALIZADA,
        EXPEDIDA,
        ENTRGUE
    }

    enum Dim { //Enum dos diferentes tamanhos de uma encomenda.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private final int id; //Guarda o número de identificação de uma encomenda.
    private final LocalDate dataCriacao; //Guarda a data de criação de uma encomenda.
    private St estado; //Guarda o estado de uma encomenda.
    private Dim dimensao; //Guarda a dimensão de uma encomenda.
    private Set<Artigo> artigos; //Guarda o conjunto de artigos que constitui a encomenda.
    private double precoFinal; //Guarda o preço final da encomenda.

    /**
     * Construtor vazio.
     */
    public Encomenda() {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = LocalDate.now();
        this.estado = St.PENDENTE;
        this.dimensao = null;
        this.artigos = new HashSet<Artigo>();
        this.precoFinal = 0;
    }

    /**
     * Construtor parametrizado.
     */
    public Encomenda(St etd, Dim dim, Set<Artigo> art, double preco) {
        numEncomenda++;
        this.id = numEncomenda;
        this.dataCriacao = LocalDate.now();
        this.estado = etd;
        this.dimensao = dim;
        this.artigos = art;
        this.precoFinal = preco; 
    }

    /**
     * Construtor de cópia.
     */
    public Encomenda(Encomenda umaEncomenda) {
        this.id = umaEncomenda.getId();
        this.dataCriacao = umaEncomenda.getDataCriacao();
        this.estado =umaEncomenda.getEstado();
        this.dimensao = umaEncomenda.getDimesao();
        this.artigos = umaEncomenda.getArtigos();
        this.precoFinal = umaEncomenda.getPrecoFinal();
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

    public Dim getDimesao() {
        return this.dimensao;
    }

    public Set<Artigo> getArtigos() {
        return new HashSet<Artigo>(this.artigos);
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }

    /**
     * Setters das variáveis de insância de uma encomenda.
     */
    public void setEstado(St etd) {
        this.estado = etd;
    }

    public void setDimesao(Dim dim) {
        this.dimensao = dim;
    }

    public void setArtigos(Set<Artigo> art) {
        this.artigos = art;
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
    public Encomenda clone() {
        return new Encomenda(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("| Encomenda |\n");
        sb.append(" Id -> " + this.id + "\n");
        sb.append(" Data de criação -> " + this.dataCriacao + "\n");
        sb.append(" Estado -> " + this.estado.toString() + "\n");
        sb.append(" Dimensão -> " + this.dimensao.toString() + "\n");
        sb.append(" Artigos:\n");
        this.artigos.forEach(a -> sb.append(a.toString()));
        sb.append(" Preço final -> " + this.precoFinal + "€\n");

        return sb.toString();
    }

    /**
     * Método equals.
     */
    public boolean equals(Object obj) {
        if(obj==this) 
            return true;
        if(obj==null || obj.getClass() != this.getClass()) 
            return false;
        Encomenda encomenda = (Encomenda) obj;
        return encomenda.id == this.id &&
               encomenda.dataCriacao.equals(this.dataCriacao) &&
               encomenda.estado.equals(this.estado) &&
               encomenda.dimensao.equals(this.dimensao) &&
               encomenda.artigos.equals(this.artigos) &&
               encomenda.precoFinal == this.precoFinal;
    }
}
