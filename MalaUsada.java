public class MalaUsada extends Mala implements ArtigoUsado {

    public MalaUsada(){
        super();
    }

    public MalaUsada(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, dimensao, material, anoLancamento);
    }

    public MalaUsada(MalaUsada m){
        super(m);
    }

            /**
     * Método clone.
     */
    @Override
    public MalaUsada clone(){
        return new MalaUsada(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" MalaUsada:\n");
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
        MalaUsada m = (MalaUsada) obj ;
        return  true;       
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        double dimMala = dimensao.ordinal();
        double descontoDimensao = precoBase * (1 + super.getDimensao().ordinal()) * 0.05; // 5% de desconto por cada ponto de dimensão
        double descontoNDonos = precoBase * (super.getNumDonos() * 0.20) // 20% de desconto por cada dono que tenha tido
        double descontoEstado = precoBase * (super.getEstado() * 0.05) // 5% de desconto por cada ponto de estado
        valorFinal -= descontoDimensao - descontoEstado;

        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoUsado(int anoAtual) {
        return calcularValorArtigo();
    }
}
