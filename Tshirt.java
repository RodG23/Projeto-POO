public class Tshirt extends Artigo{
        private static final double descBase = 0.5; //Guarda o desconto que se aplica às tshirts.
        
        enum Tam { //Enum do tamanho da tshirt;
            S,
            M,
            L,
            XL
        }
    
        enum Pad { //Enum do padrão da tshirt
            LISO,
            RISCAS,
            PALMEIRAS
        }
    
        private final Tam tamanho; //Guarda o tamanho da tshirt.
        private final Pad padrao; //Guarda o padrão da tshirt.
    
    //Getters
    public Tam get_Tam(){
        return this.tamanho;
    }

    public Pad get_padrao(){
        return this.padrao;
    }

    public Tshirt(){
        super();
        this.tamanho = Tam.S;
        this.padrao = Pad.LISO;

    }

    public Tshirt(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, Tam tamanho, Pad padrao){
        super(cond, etd, donos,trans,desc,mrc,pb,cp);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt t){
        super(t);
        this.tamanho = t.get_Tam();
        this.padrao = t.get_padrao();
    }

    @Override
    public Tshirt clone() {
        return new Tshirt(this) ;
        }

    @Override
    public String toString() {
        return "Tshirts {" + "Tamanho: " + tamanho + " | Padrao: " + padrao + " | " + super.toString() + "}";
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;
        if (!super.equals(o)) return false;

        Tshirt t = (Tshirt) o ;
        return this.tamanho == t.get_Tam() && this.padrao == t.get_padrao();      
    }

}
