import java.io.Serializable;

public abstract class Artigo implements Serializable{
    
    private static int numArtigos = 0; //Mantém contagem de artigos e é utilizada para o codBarras de um artigo.

    enum St { //Enum dos estados possíveis de um artigo.
        EXCELENTE,
        MUITO_BOM,
        BOM,
        MEDIO,
        MAU  
    }

    private final int codBarras; //Guarda a identificação de um artigo.
    private St estado; //Guarda o estado de um artigo.
    private int numDonos; //Guarda o número de donos anteriores de um artigo.
    private Transportadora transportadora; //Guarda a transportadora pre-definida de um artigo (alteravel).
    private String descricao; //Guarda a descrição de um artigo.
    private String marca; //Guarda a marca de um artigo.
    private double precoBase; //Guarda o preço base de um artigo.
    private double correcaoPreco; //Guarda a percentagem (relativa ao preço base) de correção de preço.

    /**
     * Construtor vazio.
     */
    public Artigo() {
        numArtigos++;
        this.codBarras = numArtigos;
        this.estado = null;
        this.numDonos = 0;
        this.transportadora = null;
        this.descricao = "";
        this.marca = "";
        this.precoBase = 0;
        this.correcaoPreco = 0;
    }

    /**
     * Construtor parametrizado.
     */
    public Artigo(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp) {
        numArtigos++;
        this.codBarras = numArtigos;
        this.estado = etd;
        this.numDonos = donos;
        this.transportadora = trans;
        this.descricao = desc;
        this.marca = mrc;
        this.precoBase = pb;
        this.correcaoPreco = cp;
    }

    /**
     * Construtor de cópia.
     */
    public Artigo(Artigo umArtigo) {
        this.codBarras = umArtigo.getCodBarras();
        this.estado = umArtigo.getEstado();
        this.numDonos = umArtigo.getNumDonos();
        this.transportadora = umArtigo.getTransportadora();
        this.descricao = umArtigo.getDescricao();
        this.marca = umArtigo.getMarca();
        this.precoBase = umArtigo.getPrecoBase();
        this.correcaoPreco = umArtigo.getCorrecaoPreco();
    }

    /**
     * Getters das variáveis de intância de um artigo.
     */
    public int getCodBarras() {
        return this.codBarras;
    }

    public St getEstado() {
        return this.estado;
    }

    public int getNumDonos() {
        return this.numDonos;
    }

    //Não se faz clone pelo mesmo motivo do set, suponho.
    public Transportadora getTransportadora() {
        return this.transportadora;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getMarca() {
        return this.marca;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }

    public double getCorrecaoPreco() {
        return this.correcaoPreco;
    }

    /**
     * Setters das variáveis de intância de um artigo.
     */

    public void setEstado(St etd) {
        this.estado = etd;
    }

    public void setNumDonos(int donos) {
        this.numDonos = donos;
    }

    //Não faz clone para quando se alterar o valor de uma transportadora alterar em todos os seus artigos, suponho.
    public void setTransportadora(Transportadora umaTransportadora) {
        this.transportadora = umaTransportadora;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public void setMarca(String mrc) {
        this.marca = mrc;
    }

    public void setPrecoBase(double pb) {
        this.precoBase = pb;
    }

    public void setCorrecaoPreco(double cp) {
        this.correcaoPreco = cp;
    }
    
    /**
     * Método clone.
     */
    public abstract Artigo clone();


    /**
     * Método toString.
     */
    @Override
    public String toString() {
        String header = "┌────────────────── Artigo ──────────────────\n";
        String footer = "└─────────────────────────────────────────────\n";
    
        String codigoBarras = String.format("│ Código de barras:   %d \n", this.getCodBarras());
        String estado = String.format("│ Estado:   %s \n", (this.getEstado() != null ? this.getEstado().toString() : "Não associado"));
        String numDonos = String.format("│ Número de Donos:   %d \n", this.getNumDonos());
        String descricao = String.format("│ Descrição:   %s\n", this.getDescricao());
        String marca = String.format("│ Marca:   %s\n", this.getMarca());
        String precoBase = String.format("│ Preço Base:   %.2f€\n", this.getPrecoBase());
        String correcaoPreco = String.format("│ Correção de preço:   %.2f%%\n", this.getCorrecaoPreco());
        String transportadora = "";
        if (this.getTransportadora() != null) {
            transportadora = String.format("│ Transportadora: \n", this.getTransportadora().toString());
        } else {
            transportadora = "│ Transportadora: Não associada\n";
        }
    
    return header + codigoBarras + estado + numDonos + descricao + marca + precoBase + correcaoPreco + transportadora + footer;
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
        Artigo artigo = (Artigo) obj;
        return artigo.getCodBarras() == this.getCodBarras() &&
               artigo.getEstado().equals(this.getEstado()) &&
               artigo.getNumDonos() == this.getNumDonos() &&
               artigo.getTransportadora().equals(this.getTransportadora()) &&
               artigo.getDescricao().equals(this.getDescricao()) &&
               artigo.getMarca().equals(this.getMarca()) &&
               artigo.getPrecoBase() == this.getPrecoBase() &&
               artigo.getCorrecaoPreco() == this.getCorrecaoPreco();
    }

    public abstract double calcularValorArtigo();
}
