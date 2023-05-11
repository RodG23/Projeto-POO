public class MalaNU extends Mala implements ArtigoNU{

    public MalaNU(){
        super();
    }

    public MalaNU(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, dimensao, material, anoLancamento);
    }

    public MalaNU(MalaNU m){
        super(m);
    }

        /**
     * Método clone.
     */
    @Override
    public MalaNU clone(){
        return new MalaNU(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(" Mala ──┐\n");
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
        MalaNU m = (MalaNU) obj ;
        return  super.equals(m);       
    }

    @Override
    public double calcularValorArtigo() {

        double precoBase = super.getPrecoBase();

        double valorComCorrecao = precoBase * (1 - super.getCorrecaoPreco());
        
        double descontoNDonos = precoBase * (super.getNumDonos() * 0.20); // 20% de desconto por cada dono que tenha tido
        
        int dimMala = super.getDimensao().ordinal() + 1;
        double descontoDimensao = precoBase * dimMala * 0.05; // 5% de desconto por cada ponto de dimensão
        
        int estado = super.getEstado() != null ? super.getEstado().ordinal() + 1 : 0;
        double descontoEstado = precoBase * estado * 0.05; // 5% de desconto por cada ponto de estado

        double valorFinal = valorComCorrecao - descontoNDonos - descontoDimensao - descontoEstado;
        
        // Arredondar o valor final para 2 casas decimais
        valorFinal = Math.round(valorFinal * 100.0) / 100.0;
        
        return valorFinal;
    }
    

    @Override
    public double calcularValorArtigoNU(int anoAtual) {
        return calcularValorArtigo() * (1 - (anoAtual - super.getAnoLancamento()) * 0.05);//desvaloriza 5% ao ano
    }
}
