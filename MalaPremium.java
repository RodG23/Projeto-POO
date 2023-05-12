public class MalaPremium extends Mala implements ArtigoPremium {
    public MalaPremium(){
        super();
    }

    public MalaPremium(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, dimensao, material, anoLancamento);
    }

    public MalaPremium(MalaPremium m){
        super(m);
    }

        /**
     * Método clone.
     */
    @Override
    public MalaPremium clone(){
        return new MalaPremium(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(" Mala premium ──┐\n");
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
        MalaPremium m = (MalaPremium) obj ;
        return  super.equals(m);       
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        return Math.round(precoBase * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoPremium(int anoAtual) {
        return calcularValorArtigo() * (1 + (anoAtual - super.getAnoLancamento()) * 0.10); //valoriza 10% a cada ano que passa
    }
}
