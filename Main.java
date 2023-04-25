public class Main {
    public static void main(String[] args){
    Vintage vinted = new Vintage();
    Mala a1 = new Mala();
    Sapatilha a2 = new Sapatilha();
    Tshirt a3 = new Tshirt();
    Utilizador u1 = new Utilizador();
    Credenciais cr1 = new Credenciais("jonasfmagalhaes@gmail.com", "12345");
    Utilizador u2 = new Utilizador();
    Credenciais cr2 = new Credenciais("gugaefmagalhaes@gmail.com", "67890");
    Transportadora t = new Transportadora();
    Transportadora t2 = new Transportadora();
    Tempo tempo = new Tempo();
    vinted.setDataAtual(tempo);

    //regista utilizadores
    vinted.registaUtilizador(cr1, u1);
    vinted.registaUtilizador(cr2, u2);

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

    //o sistema é atualizado com o avançar dos dias
    vinted.avancaData(2);

    System.out.println(u2.toString());
    System.out.println(u1.toString());

    //neste momento o artigo a1 deve sair do stock visto que foi comprado pelo utilizador u2
    System.out.println(vinted.toString());
    }
}
