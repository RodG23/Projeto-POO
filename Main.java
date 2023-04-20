public class Main {

    public static void main(String[] args)
    {
        Vintage vinted = new Vintage();
        Mala a1 = new Mala();
        Sapatilha a2 = new Sapatilha();
        Tshirt a3 = new Tshirt();
        Utilizador u1 = new Utilizador();
        Utilizador u2 = new Utilizador();

        //perfil do utilizador u1 tem um mala e umas sapatilhas
        u1.compra_Artigo(vinted, u1, a1, 2);
        u1.compra_Artigo(vinted, u1, a2, 2);

        //perfil do utilizador u2 tem um tshirt
        u2.compra_Artigo(vinted, u2, a3, 2);

        //utilizador u1 põe o artigo a1 à venda
        u1.aVenda_Artigo(vinted, a1);

        //artigo a1 fica em stock na vinted
        System.out.println(vinted.toString());

        //utilizador u2 compra artigo a1 ao utilizador u1
        u2.compra_Artigo(vinted, u1, a1, 1);

        System.out.println(u2.toString());
        System.out.println(u1.toString());

        //neste momento o artigo a1 deve sair do stock visto que foi comprado pelo utilizador u2
        System.out.println(vinted.toString());
    }
}
