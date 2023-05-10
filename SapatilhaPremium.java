public class SapatilhaPremium extends Sapatilha implements ArtigoPremium  {
    public SapatilhaPremium(){
        super();
    }

    public SapatilhaPremium(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento);
    }

    public SapatilhaPremium(SapatilhaPremium m){
        super(m);
    }

        /**
     * Método clone.
     */
    @Override
    public SapatilhaPremium clone(){
        return new SapatilhaPremium(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(" Sapatilha premium ──┐\n");
        sb.append(super.toString());

        return sb.toString();
    }
    
    /**
     * Método equals.
     */
    public boolean equals(Object obj) {
        if(this == obj) 
            return true ;
        if(( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        SapatilhaPremium m = (SapatilhaPremium) obj ;
        return  super.equals(m);       
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoPremium(int anoAtual) {
        return calcularValorArtigo() * (1 + (anoAtual - super.getAnoLancamento()) * 0.10); //valoriza 10% a cada ano que passa
    }
}