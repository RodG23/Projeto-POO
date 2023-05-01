public class SapatilhaUsada extends Sapatilha implements ArtigoUsado{

    public SapatilhaUsada(){
        super();
    }

    public SapatilhaUsada(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento);
    }

    public SapatilhaUsada(SapatilhaUsada s){
        super(s);
    }

            /**
     * Método clone.
     */
    @Override
    public SapatilhaUsada clone() {
        return new SapatilhaUsada(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" SapatilhaUsada:\n");
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
        SapatilhaUsada s = (SapatilhaUsada) obj ;
        return  true          
    }

    public double calcularValorArtigo(){

        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
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
