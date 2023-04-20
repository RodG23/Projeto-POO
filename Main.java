public class Main {

    public static void main(String[] args)
    {
        Vintage vinted = new Vintage();
        Mala a1 = new Mala();
        Sapatilha a2 = new Sapatilha();
        Tshirt a3 = new Tshirt();
        Utilizador u1 = new Utilizador();
        Utilizador u2 = new Utilizador();

        //perfil do utilizador u1 tem um mala e umas sapatilhas à venda
        u1.aVenda_Artigo(vinted, a1);
        u1.aVenda_Artigo(vinted, a2);

        //perfil do utilizador u2 tem um tshirt à venda
        u2.aVenda_Artigo(vinted, a3);

        //artigo a1, a2 e a3 estão em stock na vinted
        System.out.println(vinted.toString());

        //utilizador u2 compra artigo a1 ao utilizador u1
        u2.compra_Artigo(vinted, u1, a1);

        System.out.println(u2.toString());
        System.out.println(u1.toString());

        //neste momento o artigo a1 deve sair do stock visto que foi comprado pelo utilizador u2
        System.out.println(vinted.toString());
    }
}
