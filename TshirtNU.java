public class TshirtNU extends Tshirt implements ArtigoNU {

        /**
     * Construtores
     */
    public TshirtNU(){
        super();
    }

    public TshirtNU(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Tam tamanho, Pad padrao){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, padrao);
    }

    public TshirtNU(TshirtNU t){
        super(t);
    }

        /**
     * Método clone.
     */
    @Override
    public TshirtNU clone() {
        return new TshirtNU(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" TShirt ──┐\n");
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
        if(( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        TshirtNU t = (TshirtNU) obj;
        return  super.equals(t); 
    }

    @Override
    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        if(!super.getPadrao().equals(Tshirt.Pad.LISO)){
            valorFinal = precoBase * super.getDescBase();
        }
        return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoNU(int anoAtual) {
        return calcularValorArtigo();
    }
}
