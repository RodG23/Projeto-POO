//import java.io.IOException;

public class Main{

    public static void main(String[] args) throws ExceptionData{// throws IOException, InterruptedException{

    Vintage model = new Vintage();
    Controller controller = new Controller(model);
    View view = new View(controller);

    view.run();
    /*
    Vintage vinted = new Vintage();
    MalaPremium malaPremium = new MalaPremium(
        null, // estado: excelente
        0, // número de donos anteriores
        null, // transportadora: FedEx
        "Mala de viagem resistente, com fecho TSA, quatro rodas giratórias e feita em polipropileno.", // descrição
        "Samsonite", // marca
        100, // preço base
        0.0, // correção de preço: 0%
        Mala.Dim.GRANDE, // dimensão: grande
        "Polipropileno", // material
        2023 // ano de lançamento
    );
    
    SapatilhaNU sapatilhaNova = new SapatilhaNU(
        null, // estado: excelente
        0, // número de donos anteriores
        null, // transportadora: DHL
        "Sapatilhas desportivas com tecnologia de absorção de impacto, perfeitas para treino intenso.", // descrição
        "Nike", // marca
        80, // preço base
        0.0, // correção de preço: 0%
        42, // tamanho
        true, // tem atacadores
        "Preto", // cor
        2021 // ano de lançamento
        );

    TshirtNU tshirtUsada = new TshirtNU(
        Artigo.St.MUITO_BOM, // estado: excelente
        1, // número de donos anteriores
        null, // transportadora: DHL
        "Tshirt desportiva em algodão orgânico, com corte ajustado e logótipo Adidas estampado no peito.", // descrição
        "Adidas", // marca
        40, // preço base
        0.0, // correção de preço: 0%
        Tshirt.Tam.L, // tamanho: grande
        Tshirt.Pad.RISCAS // padrão: riscas
        );
    
    Utilizador u1 = new Utilizador();
    Utilizador u2 = new Utilizador();
    TransportadoraPremium tp = new TransportadoraPremium(
        "CTT",
        5, //custo de uma encomenda pequena
        7.5, //custo de uma encomenda media
        10, //custo de uma encomenda grande
        0.1, //imposto
        10 // custo adicional
    );
    TransportadoraNormal tn = new TransportadoraNormal(
        "BOLT",
        4.5, //custo de uma encomenda pequena
        8, //custo de uma encomenda media
        12, //custo de uma encomenda grande
        0.11 //imposto
    );
    
    vinted.setDataAtual(LocalDate.now());

    //regista utilizadores
    vinted.registaUtilizador("guga.com", u1);
    vinted.registaUtilizador("jonas.com", u2);
    

    // perfil do utilizador u1 tem um mala e umas sapatilhas à venda
    u1.aVendaArtigo(vinted, malaPremium, tp);
    u1.aVendaArtigo(vinted, sapatilhaNova, tn);

    // perfil do utilizador u2 tem um tshirt à venda
    u2.aVendaArtigo(vinted, tshirtUsada, tp);

    //vinted.leitura("projeto.txt");

    // utilizador u2 encomenda o artigo malaPremium e a sapatilhaNova ao utilizador u1
    u2.colocaEncomenda(vinted, malaPremium);
    u2.colocaEncomenda(vinted, sapatilhaNova);

    // utilizador u2 finaliza a sua encomenda
    u2.finalizarEncomenda(vinted);

    //o sistema é atualizado com o avançar dos dias
    vinted.avancaData("30/05/2023");
    String inferior = "10/05/2023";
    LocalDate inf = vinted.formataData(inferior);

    vinted.maiorTransportadora();
    vinted.maiorVendedor(inf, vinted.getDataAtual());
    vinted.encomendasVendedor(u1);
    vinted.ordenarUtilizadoresPorFaturamento(inf, vinted.getDataAtual());
    System.out.println(u1.toString());
    System.out.println(u2.toString());
    System.out.println(vinted.toString());
*/
    }
}
