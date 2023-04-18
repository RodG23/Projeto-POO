public class Mala extends Artigo{
    
    enum Dim { //Enum da dimensão da mala.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private Boolean premium; //Indica se a mala é Premium.
    private Dim dimensao; //Guarda a dimensão da mala.
    private String material; //Guarda o material da mala.
    private int anoLancamento; //Guarda o ano de lançamento da coleção.
}
