public abstract class Tshirt extends Artigo{
    private static final double descBase = 0.5; //Guarda o desconto que se aplica às tshirts.
    
    enum Tam { //Enum do tamanho da tshirt;
        S,
        M,
        L,
        XL
    }

    enum Pad { //Enum do padrão da tshirt
        LISO,
        RISCAS,
        PALMEIRAS
    }

    private Tam tamanho; //Guarda o tamanho da tshirt.
    private Pad padrao; //Guarda o padrão da tshirt.

    /**
     * Construtores
     */
    public Tshirt(){
        super();
        this.tamanho = null;
        this.padrao = null;

    }

    public Tshirt(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Tam tamanho, Pad padrao){
        super(etd, donos, trans, desc, mrc, pb, cp);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt t){
        super(t);
        this.tamanho = t.getTamanho();
        this.padrao = t.getPadrao();
    }

    /**
     * Getters
     */ 

    public double getDescBase(){
        return this.descBase;
    }

    public Tam getTamanho(){
        return this.tamanho;
    }

    public Pad getPadrao(){
        return this.padrao;
    }

    /**
     * Setters
     */
    public void setTamanho(Tam tam) {
        this.tamanho = tam;
    }

    public void setPadrao(Pad pad) {
        this.padrao = pad;
    }

    /**
     * Método clone.
     */
    public abstract Tshirt clone();

    /**
     * Método toString.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) 
            return true ;
        if(( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        if(!super.equals(obj)) 
            return false;
        Tshirt t = (Tshirt) obj;
        return  this.getTamanho().equals(t.getTamanho()) && 
                this.getPadrao().equals(t.getPadrao());      
    }

    public abstract double calcularValorArtigo();

    public double calcularValorArtigoNU(int anoAtual){
        return 0.0;
    }

}
