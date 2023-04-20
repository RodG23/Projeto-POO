public class Mala extends Artigo{
    
    enum Dim { //Enum da dimensão da mala.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private final Dim dimensao; //Guarda a dimensão da mala.
    private final String material; //Guarda o material da mala.
    private final int anoLancamento; //Guarda o ano de lançamento da coleção.

    //Getters

    public Dim get_Dimensao(){ 
        return this.dimensao;
    }

    public String get_Material(){ 
        return this.material;
    }

    public int get_ALancamento(){ 
        return this.anoLancamento;
    }

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
        this.dimensao = m.get_Dimensao();
        this.material = m.get_Material();
        this.anoLancamento = m.get_ALancamento();
    }

    @Override
    public Mala clone(){
        return new Mala(this);
        }

    @Override
    public String toString() {
        return "Malas {" +
                "dimensao=" + dimensao +
                ", material='" + material + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", " + super.toString() +
                '}';
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;
        if (!super.equals(o)) return false;

        Mala m = (Mala) o ;
        return  this.dimensao == m.get_Dimensao() && this.material.equals(m.get_Material()) &&
                this.anoLancamento == m.get_ALancamento();       
    }

}
