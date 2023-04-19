public class Mala extends Artigo{
    
    enum Dim { //Enum da dimensão da mala.
        PEQUENA,
        MEDIA,
        GRANDE
    }

    private final Dim dimensao; //Guarda a dimensão da mala.
    private final String material; //Guarda o material da mala.
    private final int anoLancamento; //Guarda o ano de lançamento da coleção.
}
