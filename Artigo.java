public abstract class Artigo implements ArtigoNovo, ArtigoUsado, ArtigoPremium {
    
    private static int numArtigos = 0; //Mantém contagem de artigos e é utilizada para o codBarras de um artigo.

    enum Cond { //Enum das condições possíveis de um artigo.
        NOVO,
        USADO
    }

    enum St { //Enum dos estados possíveis de um artigo.
        MAU,
        MEDIO,
        BOM,
        MUITO_BOM,
        EXCELENTE
    }

    private final int codBarras; //Guarda a identificação de um artigo.
    private Cond condicao; //Guarda a condição de um artigo.
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
        this.condicao = null;
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
    public Artigo(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp) {
        numArtigos++;
        this.codBarras = numArtigos;
        this.condicao = cond;
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
        this.condicao = umArtigo.getCondicao();
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

    public Cond getCondicao() {
        return this.condicao;
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
    public void setCondicao(Cond cond) {
        this.condicao = cond;
    }

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
        StringBuilder sb = new StringBuilder();
    
        sb.append("     | Artigo |\n");
        sb.append(" Código de barras -> " + this.getCodBarras() + "\n");
        sb.append(" Condição -> " + (this.getCondicao() != null ? this.getCondicao().toString() : "Não associada") + "\n");
        sb.append(" Estado -> " + (this.getEstado() != null ? this.getEstado().toString() : "Não associado") + "\n");
        sb.append(" Número de Donos -> " + this.getNumDonos() + "\n");
        sb.append(this.getTransportadora() != null ? this.getTransportadora().toString() : "Não associada" + "\n");
        sb.append(" Descrição -> " + this.getDescricao() + "\n");
        sb.append(" Marca -> " + this.getMarca() + "\n");
        sb.append(" Preço Base -> " + this.getPrecoBase() + "€\n");
        sb.append(" Correção de preço -> " + this.getCorrecaoPreco() + "%\n");
    
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
        Artigo artigo = (Artigo) obj;
        return artigo.getCodBarras() == this.getCodBarras() &&
               this.getCondicao() != null &&
               artigo.getCondicao().equals(this.getCondicao()) &&
               artigo.getEstado().equals(this.getEstado()) &&
               artigo.getNumDonos() == this.getNumDonos() &&
               artigo.getTransportadora().equals(this.getTransportadora()) &&
               artigo.getDescricao().equals(this.getDescricao()) &&
               artigo.getMarca().equals(this.getMarca()) &&
               artigo.getPrecoBase() == this.getPrecoBase() &&
               artigo.getCorrecaoPreco() == this.getCorrecaoPreco();
    }

    @Override
    public void precoBaseNovo() {
        this.setPrecoBase(this.precoBase - (this.precoBase / (this.estado.ordinal() + this.condicao.ordinal())));

    }

    @Override
    public void correcaoPrecoNovo() {
        this.setCorrecaoPreco(this.precoBase + 10);
    }

    @Override
    public void precoBaseUsada() {
        this.setPrecoBase(this.precoBase - (this.precoBase / this.numDonos * (this.estado.ordinal() - this.condicao.ordinal())));
    }


    @Override
    public void correcaoPrecoUsada() {
        this.setCorrecaoPreco(this.precoBase + 5);
    }

    @Override
    public void precoBasePremium() {
        this.setPrecoBase(this.precoBase + (this.precoBase / this.numDonos * (this.estado.ordinal() + this.condicao.ordinal())));
    }

    @Override
    public void correcaoPrecoPremium() {
        this.setCorrecaoPreco(this.precoBase + 15);
    }
}
