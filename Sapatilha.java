public class Sapatilha extends Artigo{

    private boolean atacadores; //Indica se a sapatilha tem atacadores.
    private String cor; //Guarda a cor da sapatilha.
    private int tamanho; //Guarda o tamanho da sapatilha.
    private int anoLancamento; //Guarda o ano de lançamento da coleção.
    //exemplo de um preco base : precoBase =− (precoBase/numeroDonos ∗ estadoU tilizacao)
    
    /**
     * Construtores
     */
    public Sapatilha(){
        super();
        this.tamanho = -1;
        this.atacadores = false;
        this.cor = "";
        this.anoLancamento = -1;
    }

    public Sapatilha(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(cond,etd, donos, trans, desc, mrc, pb, cp);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
    }

    public Sapatilha(Sapatilha s){
        super(s);
        this.tamanho = s.getTamanho();
        this.atacadores = s.getAtacadores();
        this.cor = s.getCor();
        this.anoLancamento = s.getAnoLancamento();
    }

    /**
     * Getters
     */
    public int getTamanho(){ 
        return this.tamanho;
    }

    public Boolean getAtacadores(){ 
        return this.atacadores;
    }

    public String getCor(){ 
        return this.cor;
    }

    public int getAnoLancamento(){ 
        return this.anoLancamento;
    }

    /**
     * Setters
     */
    public void setTamanho(int tam) {
        this.tamanho = tam;
    }

    public void setAtacadores(boolean atac) {
        this.atacadores = atac;
    }

    public void setCor(String c) {
        this.cor = c;
    }

    public void setAnoLancamento(int ano) {
        this.anoLancamento = ano;
    }

    /**
     * Método clone.
     */
    @Override
    public Sapatilha clone() {
        return new Sapatilha(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(" Sapatilhas:\n");
        sb.append(" Atacadores -> " + this.getAtacadores() + "\n");
        sb.append(" Cor -> " + this.getCor() + "\n");
        sb.append(" Tamanho -> " + this.getTamanho() + "\n");
        sb.append(" Ano de lançamento -> " + this.getAnoLancamento() + "\n");

        return sb.toString();
    }
    
    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) 
            return true ;
        if((obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        if(!super.equals(obj)) 
            return false;
        Sapatilha s = (Sapatilha) obj ;
        return  this.getTamanho() == s.getTamanho() && 
                this.getAtacadores().equals(s.getAtacadores()) &&
                this.getCor().equals(s.getCor()) && 
                this.getAnoLancamento() == s.getAnoLancamento();            
    }

    public double calcularValorArtigo(){

        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        if(!super.getCondicao().equals(Artigo.Cond.PREMIUM) && (super.getCondicao().equals(Artigo.Cond.USADO) || this.tamanho > 45)){
            valorFinal = precoBase - (precoBase/super.getNumDonos()* super.getEstado().ordinal());
        }
        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    public double calcularValorArtigoUsado(int anoAtual) {
        return this.calcularValorArtigo() - (anoAtual - this.anoLancamento)*0.10 ; // diminui o valor 10% ao ano
    }

    public double calcularValorArtigoNovo(int anoAtual) {
        return this.calcularValorArtigo() - (anoAtual - this.anoLancamento)*0.05 ; // diminui o valor 10% ao ano
    }

    public double calcularValorArtigoPremium(int anoAtual) {
        return this.calcularValorArtigo() + (anoAtual - this.anoLancamento)*0.10 ; // aumenta o valor 10% ao ano
    }

}