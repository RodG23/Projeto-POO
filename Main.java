import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        Transportadora trans = new Transportadora(1, 2, 3, 23);
        Artigo art1 = new Artigo(Artigo.Cond.USADO, Artigo.St.BOM, 1, trans, "top", "nike", 20, 23);
        List<Artigo> set = new ArrayList<>();
        set.add(art1);
        Encomenda enc1 = new Encomenda(Encomenda.St.PENDENTE, Encomenda.Dim.PEQUENA, set, 0);
        Encomenda enc2 = enc1.clone();
        art1.setMarca("adidas");
        System.out.println(enc1.equals(enc2));
        System.out.println(enc1.toString());
        System.out.println(enc2.toString());

        // Transportadora trans = new Transportadora();
        // Artigo art1 = new Artigo(Artigo.Cond.USADO, Artigo.St.EXCELENTE, 0, trans, "", "", 0, 0);
        // Artigo art2 = art1.clone();
        // art1.setCondicao(Artigo.Cond.NOVO);
        
        // System.out.println(art1.toString());        
        // System.out.println(art2.toString());
        // Vintage vinted = new Vintage();
        // Mala a1 = new Mala();
        // Sapatilha a2 = new Sapatilha();
        // Tshirt a3 = new Tshirt();
        // Utilizador u1 = new Utilizador();
        // Utilizador u2 = new Utilizador();

        // //perfil do utilizador u1 tem um mala e umas sapatilhas à venda
        // u1.aVenda_Artigo(vinted, a1);
        // u1.aVenda_Artigo(vinted, a2);

        // //perfil do utilizador u2 tem um tshirt à venda
        // u2.aVenda_Artigo(vinted, a3);

        // //artigo a1, a2 e a3 estão em stock na vinted
        // System.out.println(vinted.toString());

        // //utilizador u2 compra artigo a1 ao utilizador u1
        // u2.compra_Artigo(vinted, u1, a1);

        // System.out.println(u2.toString());
        // System.out.println(u1.toString());

        // //neste momento o artigo a1 deve sair do stock visto que foi comprado pelo utilizador u2
        // System.out.println(vinted.toString());
    }
}
