public class SapatilhaNova extends Sapatilha implements ArtigoNovo {

    public SapatilhaNova(){
        super();
    }

    public SapatilhaNova(St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, int tamanho, boolean atacadores, String cor, int anoLancamento){
        super(etd, donos, trans, desc, mrc, pb, cp, tamanho, atacadores, cor, anoLancamento);
    }

    public SapatilhaNova(SapatilhaNova s){
        super(s);
    }

        /**
     * Método clone.
     */
    @Override
    public SapatilhaNova clone() {
        return new SapatilhaNova(this) ;
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" SapatilhaNova:\n");
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
        SapatilhaNova s = (SapatilhaNova) obj ;
        return  true;        
    }

    @Override
    public double calcularValorArtigo(){

        double precoBase = super.getPrecoBase();
        double valorFinal = precoBase - precoBase * super.getCorrecaoPreco();
        double descontoEstado = precoBase * super.getEstado().ordinal();
        if(super.getTamanho() > 45){
            valorFinal -= descontoEstado;
        }
        return Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calcularValorArtigoNovo(int anoAtual) {
        return calcularValorArtigo();
    }
}
