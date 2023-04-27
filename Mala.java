public class Mala extends Artigo{
    
    enum Dim { //Enum da dimensão da mala.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private Dim dimensao; //Guarda a dimensão da mala.
    private String material; //Guarda o material da mala.
    private int anoLancamento; //Guarda o ano de lançamento da coleção.

    /**
     * Construtores
     */
    public Mala(){
        super();
        this.dimensao = null;
        this.material = "";
        this.anoLancamento = -1;
    }

    public Mala(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(cond, etd, donos, trans, desc, mrc, pb, cp);
        this.dimensao = dimensao;
        this.material = material;
        this.anoLancamento = anoLancamento;
    }

    public Mala(Mala m){
        super(m);
        this.dimensao = m.getDimensao();
        this.material = m.getMaterial();
        this.anoLancamento = m.getAnoLancamento();
    }

    /**
     * Getters
     */
    public Dim getDimensao(){ 
        return this.dimensao;
    }

    public String getMaterial(){ 
        return this.material;
    }

    public int getAnoLancamento(){ 
        return this.anoLancamento;
    }

    /**
     * Setters
     */
    public void setDimensao(Dim dim) {
        this.dimensao = dim;
    }

    public void setMaterial(String mat) {
        this.material = mat;
    }

    public void setAnoLancamento(int ano) {
        this.anoLancamento = ano;
    }

    /**
     * Método clone.
     */
    @Override
    public Mala clone(){
        return new Mala(this);
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(" Mala:\n");
        sb.append(" Dimensão -> " + (this.getDimensao() != null ? this.getDimensao().toString() : "") + "\n");
        sb.append(" Tamanho -> " + this.getMaterial() + "\n");
        sb.append(" Ano de lançamento -> " + this.getAnoLancamento() + "\n\n");

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
        Mala m = (Mala) obj ;
        return  this.getDimensao().equals(m.getDimensao()) &&
                this.getMaterial().equals(m.getMaterial()) &&
                this.getAnoLancamento() == m.getAnoLancamento();       
    }
}