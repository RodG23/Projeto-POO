import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


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
    }

    /**
     * Construtor parametrizado.
     */

    public Encomenda(St etd, Dim dim, Map<Integer,Artigo> art, double preco) {
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

    /**
     * Setters das variáveis de instância de uma encomenda.
     */
    public void setEstado(St etd) {
        this.estado = etd;
    }

    public void setDimesao(Dim dim) {
        this.dimensao = dim;
    }

    public void setDataCriacao(LocalDate data) {
        this.dataCriacao = data;
    }

    public void setArtigos(Map<Integer,Artigo> art) {
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : art.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.artigos = map;
    }

    /**
     * Adiciona ao custo da encomenda o valor do artigo adicionado.
     */
    public void addPrecoFinal(double cost) {
        this.precoFinal += cost;
    }

    /**
     * Adiciona um artigo a uma encomenda.
     */
    public void addArtigo(Artigo a) {
        this.artigos.put(a.getCodBarras(), a.clone());
    }

    /**
     * Remove um artigo de uma encomenda, dado o seu código de barras.
     * @param id
     */
    public void removeArtigo(Integer codBarras) {
        this.artigos.remove(codBarras);
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
        sb.append(" Id -> " + this.getId() + "\n");
        sb.append(" Data de criação -> " + (this.getDataCriacao() != null ? this.getDataCriacao().toString() : "") + "\n");
        sb.append(" Estado -> " + this.getEstado().toString() + "\n");
        sb.append(" Dimensão -> " + (this.getDimensao() != null ? this.getDimensao().toString() : "") + "\n");
        sb.append(" Artigos:\n");
        for(Artigo a : this.getArtigos().values())
        {
            sb.append(a.toString());
        }
        sb.append(" Preço final -> " + this.getPrecoFinal() + "€\n");

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
               encomenda.getPrecoFinal() == this.getPrecoFinal();
    }
}

