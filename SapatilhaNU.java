public class SapatilhaNU extends Sapatilha implements ArtigoNU {

    public SapatilhaNU(){
        super();
    }

    public SapatilhaNU(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento);
    }

    public SapatilhaNU(SapatilhaNU s){
        super(s);
    }

        /**
     * Método clone.
     */
    @Override
    public SapatilhaNU clone() {
        return new SapatilhaNU(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" Sapatilha ──┐\n");
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
        SapatilhaNU s = (SapatilhaNU) obj ;
        return  super.equals(s);        
    }

    @Override
    public double calcularValorArtigo() {
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase;
        
        if (super.getTamanho() > 45 || super.getNumDonos() > 0) {
            valorFinal = precoBase * (1 - super.getCorrecaoPreco());
            double descontoNDonos = precoBase * (super.getNumDonos() * 0.20); // 20% de desconto por cada dono que tenha tido
            double estado = (super.getEstado() != null) ? 1 + super.getEstado().ordinal() : 0.0; // 5% de desconto por cada ponto de estado, se houver estado
            double descontoEstado = precoBase * estado * 0.05;
            valorFinal -= descontoNDonos + descontoEstado;
        }
    
        return Math.round(valorFinal * 100.0) / 100.0;
    }

    @Override
    public double calcularValorArtigoNU(int anoAtual) {
        return calcularValorArtigo() * (1 - (anoAtual - super.getAnoLancamento()) * 0.05); //desvaloriza 5% a cada ano que passa;
    }
}
