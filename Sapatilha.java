public class Sapatilha extends Artigo{
    private final boolean atacadores; //Indica se a sapatilha tem atacadores.
    private final String cor; //Guarda a cor da sapatilha.
    private final int tamanho; //Guarda o tamanho da sapatilha.
    private final int anoLancamento; //Guarda o ano de lançamento da coleção.

    //exemplo de um preco base : precoBase =− (precoBase/numeroDonos ∗ estadoU tilizacao)
    
    //Getters

    public int get_Tam(){ 
        return this.tamanho;
    }

    public Boolean get_Atacadores(){ 
        return this.atacadores;
    }

    public String get_Cor(){ 
        return this.cor;
    }

    public int get_ALancamento(){ 
        return this.anoLancamento;
    }

    public Sapatilha(){
        super();
        this.tamanho = -1;
        this.atacadores = false;
        this.cor = "";
        this.anoLancamento = -1;
    }

    public Sapatilha(Cond cond, St etd, int donos, Transportadora trans, String desc, String mrc, double pb, double cp, boolean atacadores, String cor, int tamanho, int anoLancamento){
        super(cond, etd, donos,trans,desc,mrc,pb,cp);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
    }

    public Sapatilha(Sapatilha s){
        super(s);
        this.tamanho = s.get_Tam();
        this.atacadores = s.get_Atacadores();
        this.cor = s.get_Cor();
        this.anoLancamento = s.get_ALancamento();
    }

    @Override
    public Sapatilha clone() {
        return new Sapatilha(this) ;
        }

    @Override
    public String toString() {
        return "Sapatilhas {" +
                "atacadores=" + atacadores +
                ", cor='" + cor +
                ", tamanho=" + tamanho +
                ", anoLancamento=" + anoLancamento +
                ", " + super.toString() +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;
        if (!super.equals(o)) return false;

        Sapatilha s = (Sapatilha) o ;
        return this.tamanho == s.get_Tam() && this.atacadores == s.get_Atacadores() &&
               this.cor.equals(s.get_Cor()) && this.anoLancamento == s.get_ALancamento();            
    }

}
