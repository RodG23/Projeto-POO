public class Tshirt extends Artigo {

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

    private Tam tamanho; //Guarda o tamanho da tshirt.
    private Pad padrao; //Guarda o padrão da tshirt.
}
