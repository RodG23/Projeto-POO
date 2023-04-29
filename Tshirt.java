public class Tshirt extends Artigo{
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

    public Tshirt(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Tam tamanho, Pad padrao){
        super(cond, etd, donos, trans, desc, mrc, pb, cp);
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
    @Override
    public Tshirt clone() {
        return new Tshirt(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(" T-Shirt:\n");
        sb.append(" Tamanho -> " + this.getTamanho() + "\n");
        sb.append(" Padrão -> " + this.getPadrao() + "\n\n");

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
        if(!super.equals(obj)) 
            return false;
        Tshirt t = (Tshirt) obj;
        return  this.getTamanho().equals(t.getTamanho()) && 
                this.getPadrao().equals(t.getPadrao());      
    }

    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        if(padrao.ordinal() != 0){
            valorFinal = precoBase * descBase;
        }
        return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }
    
    public double calcularValorArtigoUsado(int anoAtual) {
        return calcularValorArtigo();
    }

    public double calcularValorArtigoNovo(int anoAtual) {
        return calcularValorArtigo();
    }

}
