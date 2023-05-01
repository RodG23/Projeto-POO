public class TshirtUsada extends Tshirt implements ArtigoUsado {

            /**
     * Construtores
     */
    public TshirtUsada(){
        super();
    }

    public TshirtUsada(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Tam tamanho, Pad padrao){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, padrao);
    }

    public TshirtUsada(TshirtUsada t){
        super(t);
    }

           /**
     * Método clone.
     */
    @Override
    public TshirtUsada clone() {
        return new TshirtUsada(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" TshirtUsada:\n");
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
        if(!super.equals(obj)) 
            return false;
        TshirtUsada t = (TshirtUsada) obj;
        return  true;     
    }

    public double calcularValorArtigo(){
        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        if(super.getPadrao().ordinal() != 0){
            valorFinal = precoBase * super.getDescBase();
        }
        return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoUsado(int anoAtual) {
        return calcularValorArtigo();
    }

}
