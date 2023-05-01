public class SapatilhaPremium extends Mala implements ArtigoPremium  {
    public SapatilhaPremium(){
        super();
    }

    public SapatilhaPremium(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, dimensao, material, anoLancamento);
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
        
        sb.append(" SapatilhaPremium:\n");
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
        if(!super.equals(obj)) 
            return false;
        SapatilhaPremium m = (SapatilhaPremium) obj ;
        return  true;       
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoPremium(int anoAtual) {
        return calcularValorArtigo() * (anoAtual - super.getAnoLancamento() + 0.10); //valoriza 10% a cada ano que passa
    }
}