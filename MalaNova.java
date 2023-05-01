public class MalaNova extends Mala implements ArtigoNovo {

    public MalaNova(){
        super();
    }

    public MalaNova(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, dimensao, material, anoLancamento);
    }

    public MalaNova(MalaNova m){
        super(m);
    }

        /**
     * Método clone.
     */
    @Override
    public MalaNova clone(){
        return new MalaNova(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(" MalaNova:\n");
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
        MalaNova m = (MalaNova) obj ;
        return  true;       
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        double dimMala = super.getDimensao().ordinal();
        double descontoDimensao = precoBase * (1 + super.getDimensao().ordinal()) * 0.05; // 5% de desconto por cada ponto de dimensão
        valorFinal -= descontoDimensao;
        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoNovo(int anoAtual) {
        return calcularValorArtigo();
    }
}
