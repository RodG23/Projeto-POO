public abstract class Sapatilha extends Artigo{

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
                                             
    public Sapatilha(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp);
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
    public abstract Sapatilha clone();

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());

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
    
    public abstract double calcularValorArtigo();

    public double calcularValorArtigoNU(int anoAtual){
        return 0.0;
    }

}