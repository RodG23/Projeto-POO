public class Mala extends Artigo{
    
    enum Dim { //Enum da dimensão da mala.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private final Dim dimensao; //Guarda a dimensão da mala.
    private final String material; //Guarda o material da mala.
    private final int anoLancamento; //Guarda o ano de lançamento da coleção.


    public Mala(){
        super();
        this.dimensao = Dim.PEQUENA;
        this.material = "";
        this.anoLancamento = -1;
    }

    public Mala(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Dim dimensao, String material, int anoLancamento){
        super(cond, etd, donos,trans,desc,mrc,pb,cp);
        this.dimensao = dimensao;
        this.material = material;
        this.anoLancamento = anoLancamento;
    }

    public Mala(Mala m){
        super(m);
        this.dimensao = m.getDimensao();
        this.material = m.getMaterial();
        this.anoLancamento = m.getALancamento();
    }

    //Getters

    public Dim getDimensao(){ 
        return this.dimensao;
    }

    public String getMaterial(){ 
        return this.material;
    }

    public int getALancamento(){ 
        return this.anoLancamento;
    }

    public Mala clone(){
        return new Mala(this);
        }

    public String toString() {
        return "Malas {" +
                "dimensao=" + dimensao +
                ", material='" + material + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", " + super.toString() +
                '}';
    }
    
    public boolean equals(Object obj) {
        if (this == obj) 
            return true ;
        if (( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false ;
        if (!super.equals(obj)) 
            return false;
        Mala m = (Mala) obj ;
        return  this.dimensao.equals(m.getDimensao()) && this.material.equals(m.getMaterial()) &&
                this.anoLancamento == m.getALancamento();       
    }

}
