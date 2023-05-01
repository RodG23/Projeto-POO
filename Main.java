import java.time.LocalDate;

public class Main {

    public static void main(String[] args){

    Vintage vinted = new Vintage();

    MalaNova malaNova = new MalaNova(
        Artigo.St.MAU, // estado: excelente
        0, // número de donos anteriores
        null, // transportadora: FedEx
        "Mala de viagem resistente, com fecho TSA, quatro rodas giratórias e feita em polipropileno.", // descrição
        "Samsonite", // marca
        100, // preço base
        0.0, // correção de preço: 10%
        Mala.Dim.GRANDE, // dimensão: grande
        "Polipropileno", // material
        2022 // ano de lançamento
    );
    
    SapatilhaNova sapatilhaNova = new SapatilhaNova(
        Artigo.St.MEDIO, // estado: excelente
        0, // número de donos anteriores
        null, // transportadora: DHL
        "Sapatilhas desportivas com tecnologia de absorção de impacto, perfeitas para treino intenso.", // descrição
        "Nike", // marca
        80, // preço base
        0.05, // correção de preço: 5%
        42, // tamanho
        true, // tem atacadores
        "Preto", // cor
        2021 // ano de lançamento
        );

    TshirtUsada tshirtUsada = new TshirtUsada(
        Artigo.St.MUITO_BOM, // estado: excelente
        0, // número de donos anteriores
        null, // transportadora: DHL
        "Tshirt desportiva em algodão orgânico, com corte ajustado e logótipo Adidas estampado no peito.", // descrição
        "Adidas", // marca
        40, // preço base
        0.1, // correção de preço: 5%
        Tshirt.Tam.L, // tamanho: grande
        Tshirt.Pad.RISCAS // padrão: riscas
        );
    
    Utilizador u1 = new Utilizador();
    Utilizador u2 = new Utilizador();
    Utilizador u3 = new Utilizador();
    Transportadora t = new Transportadora(
        5, //custo de uma encomenda pequena
        7.5, //custo de uma encomenda media
        10, //custo de uma encomenda grande
        0.1 //imposto
    );
    Transportadora t2 = new Transportadora(
        4.5, //custo de uma encomenda pequena
        8, //custo de uma encomenda media
        12, //custo de uma encomenda grande
        0.11 //imposto
    );
    vinted.setDataAtual(LocalDate.now());

    //regista utilizadores
    vinted.registaUtilizador("jonas@gmail.com", u1);
    vinted.registaUtilizador("guga.com", u2);
    vinted.registaUtilizador("jota@gmail.com", u3);


    // perfil do utilizador u1 tem um mala e umas sapatilhas à venda
    u1.aVendaArtigo(vinted, malaNova, t);
    //u1.aVendaArtigo(vinted, sapatilhaNova, t2);

    // perfil do utilizador u2 tem um tshirt à venda
    u2.aVendaArtigo(vinted, tshirtUsada, t);

    // utilizador u2 encomenda o artigo malaPremium ao utilizador u1
    u2.colocaEncomenda(vinted, u1, malaNova);

    // utilizador u2 finaliza a sua encomenda
    u2.finalizarEncomenda();

     // a encomenda de u2 é expedida
    vinted.enviarEncomenda(u2);

    LocalDate inferior = vinted.getDataAtual();
    //o sistema é atualizado com o avançar dos dias
    vinted.avancaData("30/05/2023");

    /*vinted.maiorVendedor(inferior, vinted.getDataAtual());
    vinted.encomendasVendedor(u1);
    vinted.ordenarUtilizadoresPorFaturamento(inferior, vinted.getDataAtual());
    vinted.ganhosVintage();
*/
    System.out.println(u1.toString());
    System.out.println(u2.toString());
    //System.out.println(u3.toString());

    //neste momento o artigo malaPremium deve sair do stock visto que foi comprado pelo utilizador u2
    //System.out.println(vinted.toString());
    //scanner.close();

    }
}
