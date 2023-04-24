public class Main {
    public static void main(String[] args){
    Vintage vinted = new Vintage();
    Mala a1 = new Mala();
    Sapatilha a2 = new Sapatilha();
    Tshirt a3 = new Tshirt();
    Utilizador u1 = new Utilizador();
    Utilizador u2 = new Utilizador();
    Transportadora t = new Transportadora();
    Transportadora t2 = new Transportadora();
    Tempo tempo = new Tempo();
    vinted.setDataAtual(tempo);

    // perfil do utilizador u1 tem um mala e umas sapatilhas à venda
    u1.aVendaArtigo(vinted, a1, t);
    u1.aVendaArtigo(vinted, a2, t2);

    // perfil do utilizador u2 tem um tshirt à venda
    u2.aVendaArtigo(vinted, a3, t);

    // utilizador u2 encomenda o artigo a1 ao utilizador u1
    u2.colocaEncomenda(vinted, u1, a1);

    // utilizador u2 finaliza a sua encomenda
    u2.finalizarEncomenda();

     // a encomenda de u2 é expedida
    vinted.enviarEncomenda(u2);

    // avançar dois dias
    vinted.setDataAtual(tempo.avancaDia());
    vinted.setDataAtual(tempo.avancaDia());

    vinted.entregaEncomenda(u2, u1);

    System.out.println(u2.toString());
    System.out.println(u1.toString());

    //neste momento o artigo a1 deve sair do stock visto que foi comprado pelo utilizador u2
    System.out.println(vinted.toString());
    }
}
