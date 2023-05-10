import java.util.Scanner;
import java.util.InputMismatchException;
//import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class View{
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }


    public void run(){ //throws InterruptedException, IOException {
        LocalDate dataAtual = LocalDate.now();
        controller.setDataSistema(dataAtual);
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("=== MENU INTERATIVO ===");
            System.out.println("1 - Admin");
            System.out.println("2 - User");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");
            try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    switch(opcao) {
                        case 1:{
                            try{
                                admin(scanner);
                            }catch(ExceptionData e){
                                System.out.println(e.getMessage());
                            }catch(ExceptionUser e){
                                System.out.println(e.getMessage());;
                            }
                            //new ProcessBuilder("clear").inheritIO().start().waitFor();
                            break;
                        }
                        case 2:{
                            try{
                                user(scanner); 
                            }catch (ExceptionEncomenda e){
                                System.out.println(e.getMessage());
                            }
                            catch (ExceptionTransportadora e){
                                System.out.println(e.getMessage());
                            }
                            catch(ExceptionUser e){
                                System.out.println(e.getMessage());
                            }
                            catch(ExceptionArtigo e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        }
                        case 3:{
                            System.out.println("O programa será encerrado.\n");
                            break;
                        }
                        default:
                            System.out.println("Opção inválida. Escolha um numero de 1 a 3.\n");
                            break;
                    }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.\n");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 5);
        scanner.close();
    }


    public void admin(Scanner scanner) throws ExceptionUser, ExceptionData{
        int opcao = 0;
        do{
            System.out.println("\n=== ADMINISTRADOR ===");
            System.out.println("1 - Adicionar transportadora");
            System.out.println("2 - Eliminar utilizador");
            System.out.println("3 - Queries");
            System.out.println("4 - Ler de um ficheiro");
            System.out.println("5 - Avançar data");
            System.out.println("6 - Terminar sessão e voltar ao menu principal");
            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch(opcao) {
                    case 1:{
                        addT(scanner);
                        break;
                    }
                    case 2:{
                        eliminaUser(scanner);
                        break;
                    }
                    case 3:{
                        queries(scanner);
                        break;
                    }
                    case 4:{                 
                        ler(scanner);
                        break;
                    }
                    case 5:{                     
                        avanca(scanner);
                        break;
                    }
                    case 6:{
                        System.out.println("Sessão como administrador terminada.\n");
                        break;
                    }
                    default:
                        System.out.println("Opção inválida. Escolha um numero de 1 a 6.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.\n");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 6);
    }

    public void addT(Scanner scanner){
        System.out.print("\nNome da transportadora: ");
        String nome = scanner.nextLine();
        nome = nome.substring(0).toUpperCase();
        System.out.print("O artigo é premium? (Sim, Nao): ");
        String isPremium;
        double cp = 0.0;
        double cm = 0.0;
        double cg = 0.0;
        double imposto = 0.0;
        double custoAdicional = 0.0;
        try {
            isPremium = scanner.nextLine();
            isPremium = isPremium.substring(0,1).toUpperCase()+ isPremium.substring(1).toLowerCase();
            if(!isPremium.equals("Sim") && !isPremium.equals("Nao")){
                System.out.println("Insira uma opção válida (Sim, Nao).\n");
                return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
            }
            System.out.print("Custo de ume encomenda pequena: ");
            cp = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Custo de ume encomenda média: ");
            cm = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Custo de ume encomenda grande: ");
            cg = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Imposto da transportadora: ");
            imposto = scanner.nextDouble();
            scanner.nextLine();
            if(isPremium.equals("Sim")){
                System.out.print("Custo adicional: ");
                custoAdicional = scanner.nextDouble();
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida. Insira um número.\n");
            scanner.nextLine(); // consome a entrada inválida
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }
        controller.addTranspAdmin(nome, cp, cm, cg, imposto, custoAdicional);
    }

    public void eliminaUser(Scanner scanner) throws ExceptionUser{
        if(controller.utilizadoresDisponiveis().isEmpty()){
            throw new ExceptionUser("Não há utilizadores disponíveis para remover.\n");
        }
        System.out.println("Utilizadores disponíveis\n" + controller.utilizadoresDisponiveis());
        System.out.print("Email do utilizador que pretende eliminar: ");
        String email = scanner.nextLine();
        controller.removeUser(email);    
    }

    public void queries(Scanner scanner) throws ExceptionUser, ExceptionData{
        int opcao = 0;
        do{
            System.out.println("\n=== QUERIES ===");
            System.out.println("1 - Qual é o vendedor que mais facturou num período ou desde sempre.");
            System.out.println("2 - Qual o transportador com maior volume de facturação.");
            System.out.println("3 - Listar as encomendas emitidas por um vendedor.");
            System.out.println("4 - Fornecer uma ordenação dos maiores compradores/vendedores do sistema durante um período a determinar.");
            System.out.println("5 - Determinar quanto dinheiro ganhou o Vintage no seu funcionamento.");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch(opcao) {
                    case 1:{
                        System.out.println("Defina o intrevalo de tempo a aplicar:");
                        System.out.print("Limite inferir da data: ");
                        String dateInferior = scanner.nextLine();
                        System.out.print("Limite superir da data: ");
                        String dateSuperior = scanner.nextLine();   
                                        
                        controller.maiorVendedorAdmin(dateInferior, dateSuperior);   
                        break;
                    }
                    case 2:{
                        controller.maiorTransportadoraAdmin();
                        break;
                    }
                    case 3:{
                        System.out.print("Email do vendedor que pretende analisar as encomendas: ");
                        String emailVendedor = scanner.nextLine();

                        controller.encomendasVendedorAdmin(emailVendedor);
                        break;
                    }
                    case 4:{
                        System.out.println("Defina o intrevalo de tempo a aplicar:");
                        System.out.print("Limite inferir da data: ");
                        String dateInferior = scanner.nextLine();
                        System.out.print("Limite superir da data: ");
                        String dateSuperior = scanner.nextLine();

                        controller.ordenarUtilizadoresPorFaturamento(dateInferior, dateSuperior);
                        break;
                    }
                    case 5:{
                        System.out.println("O total auferido pelo sistema é de "+ controller.faturamentoSistema() + " euros\n");
                        break;
                    }
                    default:
                        System.out.println("Opção inválida. Escolha um numero de 1 a 6.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.\n");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 6);
    }

    public void ler(Scanner scanner) throws ExceptionData, ExceptionUser{
        System.out.println("Insira o caminho correto para o ficheiro que pretende que seja lido:");
        String caminho = scanner.nextLine();
        controller.lerSistema(caminho);
    }

    public void avanca(Scanner scanner) throws ExceptionData{
        System.out.println("Insira a nova data atual do sistema:");
        String dataAtual = scanner.nextLine();
        controller.avancaDataSistema(dataAtual);
    }

    public void user(Scanner scanner) throws ExceptionEncomenda, ExceptionTransportadora, ExceptionUser, ExceptionArtigo{
        int opcao = 0;
        do{
            System.out.println("\n=== UTILIZADOR ===");
            System.out.println("1 - Criar utilizador");
            System.out.println("2 - Iniciar sessão");
            System.out.println("3 - Voltar ao menu principal");
            System.out.print("Opção: ");
            try{
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch(opcao){
                    case 1:{
                        criarUtilizador(scanner);
                        break;
                    }
                    case 2:{
                        loggin(scanner);
                        break;
                    }
                    case 3:{
                        System.out.println("Sessão como utilizador terminada.\n");
                        break;
                    }
                }

            }catch (InputMismatchException e) {
                System.out.println("Opção inválida. Escolha um numero de 1 a 3.\n");
                scanner.next(); // consome a entrada inválida
            }
        }while(opcao != 3);
    }
    
    public void criarUtilizador(Scanner scanner) throws ExceptionEncomenda, ExceptionUser{
        List<String> usuariosDisponiveis = controller.utilizadoresDisponiveis();
        System.out.println("\n=== CRIAR UTILIZADOR ===");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Morada: ");
        String morada = scanner.nextLine();
        System.out.print("NIF: ");
        int nif;
        try {
            nif = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida. Insira um número inteiro.\n");
            scanner.nextLine(); // consome a entrada inválida
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }
        if(usuariosDisponiveis.contains(email)){
            throw new ExceptionUser("O email do utilizador está indisponível.\n");
        }
        controller.registaUser(email, nome, morada, nif);
    }

    public void loggin(Scanner scanner) throws ExceptionUser, ExceptionEncomenda, ExceptionTransportadora, ExceptionArtigo{
        System.out.println("\n=== Iniciar sessão ===");
        List<String> usuariosDisponiveis = controller.utilizadoresDisponiveis();
        if (usuariosDisponiveis == null || usuariosDisponiveis.isEmpty()) {
            throw new ExceptionUser("Não há usuários disponíveis.\n");
        }
        System.out.println("Usuários disponíveis:\n " + usuariosDisponiveis);
        System.out.print("Digite o email do usuário para iniciar sessão: ");
        String email = scanner.nextLine();
        if (!usuariosDisponiveis.contains(email)) {
            throw new ExceptionUser("Não é possível iniciar sessão com o usuário " + email + "\n");
        }
        System.out.println("Sessão iniciada com " + email + "\n");
        interativo2(scanner, email);
    }

    public void interativo2(Scanner scanner, String email) throws ExceptionEncomenda, ExceptionTransportadora, ExceptionArtigo{
        Utilizador user = this.controller.retornarLoggedUser(email);
        int opcao = 0;
        do {
            System.out.println("=== OPÇÕES ===");
            System.out.println("1 - Colocar à venda um artigo");
            System.out.println("2 - Encomendar um artigo");
            System.out.println("3 - Finalizar encomenda");
            System.out.println("4 - Visualizar encomenda");
            System.out.println("5 - Visualizar artigos comprados");
            System.out.println("6 - Visualizar artigos à venda");
            System.out.println("7 - Devolver encomenda");
            System.out.println("8 - Terminar sessão");
            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch(opcao) {
                    case 1:{
                        criarArtigo(scanner, user);
                        break;
                    } 
                    case 2:{
                        encomendarArtigo(scanner, user);
                        break;
                    } 
                    case 3:{
                        finalizaEncomenda(scanner, user);
                        break;
                    } 
                    case 4:{
                        verEncomenda(scanner, user);                     
                        break;
                    } 
                    case 5:{
                        verArtigosComprados(scanner, user);
                        break;
                    }
                    case 6:{
                        verArtigosVendidos(scanner, user);
                        break;
                    }
                    case 7:{
                        devolverEncomenda(scanner, user); 
                        break;
                    }
                    case 8:{
                        System.out.println("Sessão encerrada com o utilizador " + user.getNome());
                    }
                }
            } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Escolha um numero de 1 a 8.");
                    scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 8);
    }

    public void criarArtigo(Scanner scanner, Utilizador user) throws ExceptionTransportadora, ExceptionArtigo{
        System.out.println("\n=== A CRIAR ARTIGO ===");
        System.out.print("Escolha o tipo de artigo (Mala, Sapatilha, Tshirt): ");
        String tipoArtigo = scanner.nextLine();

        tipoArtigo = tipoArtigo.substring(0,1).toUpperCase() + tipoArtigo.substring(1).toLowerCase();
        if(!tipoArtigo.equals("Mala") && !tipoArtigo.equals("Sapatilha") && !tipoArtigo.equals("Tshirt")){
            throw new ExceptionArtigo("Opção inválida. Insira uma opção válida (Mala, Sapatilha, Tshirt).\n");
        }
        
        System.out.println("\n=== Classe ===");
        String classeArtigo = "Nao";
        if(!tipoArtigo.equals("Tshirt")){
            System.out.print("O artigo é premium? (Sim, Nao): ");
            classeArtigo = scanner.nextLine();
            classeArtigo = classeArtigo.substring(0,1).toUpperCase() + classeArtigo.substring(1).toLowerCase();
            if(!classeArtigo.equals("Sim") && !classeArtigo.equals("Nao")){
                throw new ExceptionArtigo("Opção inválida. Insira uma opção válida (Sim, Nao).\n");
            }
        }
        
        System.out.println("\n=== ESPECIFICAÇÔES DO ARTIGO ===\n");

        String transportadora = transportadoraArtigo(scanner, classeArtigo);

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Preço base: ");
        double precoBase = 0.0;
        double correcaoPreco= 0.0;
        try{
            precoBase = scanner.nextDouble();
            scanner.nextLine(); // consome a quebra de linha

            System.out.print("Correção de preço (%): ");
            correcaoPreco = scanner.nextDouble();
            scanner.nextLine(); // consome a quebra de linha

        }catch(InputMismatchException e){
            System.out.println("Opção inválida. Insira um número.\n");
            scanner.nextLine(); // consome a entrada inválida
            return;
        }
        
        System.out.println("Escolha o estado do artigo:");
        System.out.println("1 - Mau");
        System.out.println("2 - Médio");
        System.out.println("3 - Bom");
        System.out.println("4 - Muito bom");
        System.out.println("5 - Excelente");
        System.out.print("Opção: ");
        int estadoArtigo;
        Artigo.St estado;
        try{
            estadoArtigo = scanner.nextInt();
            switch (estadoArtigo) {
                case 1:
                    estado = Artigo.St.MAU;
                    break;
                case 2:
                    estado = Artigo.St.MEDIO;
                    break;
                case 3:
                    estado = Artigo.St.BOM;
                    break;
                case 4:
                    estado = Artigo.St.MUITO_BOM;
                    break;
                case 5:
                    estado = Artigo.St.EXCELENTE;
                    break;
                default:
                    System.out.println("Opção inválida. Escolha um numero de 1 a 5.");
                    return;
            }
        }catch(InputMismatchException e){
            System.out.println("Opção inválida. Insira um número inteiro.");
            scanner.nextLine(); // descarta a entrada inválida
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }
        scanner.nextLine(); // consome a quebra de linha
        
        int numDonos = 0;
        try{
            System.out.print("Número de donos anteriores: ");
            numDonos = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha
        }catch(InputMismatchException e){
            System.out.println("Opção inválida. Insira um número inteiro.\n");
            scanner.nextLine(); // descarta a entrada inválida
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }

        int dim = 0;
        String material = null;
        int anoLancamento = 0;
        Mala.Dim dimensao = null;
        Boolean atacadores = null;
        String cor = null;
        int tamanho = 0;
        Tshirt.Tam tamTshirt = null;
        int padrao = 0;
        Tshirt.Pad padTshirt = null;

        if(tipoArtigo.equals("Mala")){
                System.out.println("Dimensão: ");
                System.out.println("1 - Pequena");
                System.out.println("2 - Média");
                System.out.println("3 - Grande");
                System.out.print("Opção: ");
                try{
                    dim = scanner.nextInt();
                    switch (dim) {
                        case 1:
                            dimensao = Mala.Dim.PEQUENA;
                            break;
                        case 2:
                            dimensao = Mala.Dim.MEDIA;
                            break;
                        case 3:
                            dimensao = Mala.Dim.GRANDE;
                            break;
                        default:
                            System.out.println("Opção inválida. Escolha um numero de 1 a 3.");
                            return;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira um número inteiro.\n");
                    scanner.nextLine(); // descarta a entrada inválida
                    return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                }
                scanner.nextLine(); // consome a quebra de linha
                System.out.print("Material: ");
                material = scanner.nextLine();
                System.out.print("Ano de lancamento: ");
                try{
                    anoLancamento = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira um número inteiro.\n");
                    scanner.nextLine(); // descarta a entrada inválida
                    return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                }
            }

            else if(tipoArtigo.equals("Sapatilha")){
                System.out.print("Atacadores(true, false): ");
                try{
                    atacadores = scanner.nextBoolean();
                    scanner.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira 'true' ou 'false'.\n");
                    scanner.nextLine();
                    return;
                }
                
                System.out.print("Cor: ");
                cor = scanner.nextLine();
                System.out.print("Material: ");
                material = scanner.nextLine();
                System.out.print("Tamanho: ");
                try{
                    tamanho = scanner.nextInt();
                    scanner.nextLine(); // consome a quebra de linha
                    System.out.print("Ano de lancamento: ");
                    anoLancamento = scanner.nextInt();
                    scanner.nextLine(); // consome a quebra de linha
        
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira um número inteiro.\n");
                    scanner.nextLine(); // descarta a entrada inválida
                    return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                }
            }

            else{
                System.out.println("Escolha o tamanho do artigo:");
                System.out.println("1 - S");
                System.out.println("2 - M");
                System.out.println("3 - L");
                System.out.println("4 - XL");
                System.out.print("Opção: ");
                try{
                    tamanho = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira um número inteiro.\n");
                    scanner.nextLine(); // descarta a entrada inválida
                    return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                }
                switch (tamanho) {
                    case 1:
                        tamTshirt = Tshirt.Tam.S;
                        break;
                    case 2:
                        tamTshirt = Tshirt.Tam.M;
                        break;
                    case 3:
                        tamTshirt = Tshirt.Tam.L;
                        break;
                    case 4:
                        tamTshirt = Tshirt.Tam.XL;
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha um numero de 1 a 4.");
                        scanner.nextLine(); // consome a entrada inválida
                        return;
                }
                System.out.println("Escolha o padrao do artigo:");
                System.out.println("1 - Liso");
                System.out.println("2 - Riscas");
                System.out.println("3 - Palmeiras");
                System.out.print("Opção: ");
                try{
                    padrao = scanner.nextInt();
                    scanner.nextLine(); // consome o quebra de linha
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Escolha um numero de 1 a 3.\n");
                    scanner.nextLine(); // descarta a entrada inválida
                    return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                }
                switch (padrao) {
                    case 1:
                        padTshirt = Tshirt.Pad.LISO;
                        break;
                    case 2:
                        padTshirt = Tshirt.Pad.RISCAS;
                        break;
                    case 3:
                        padTshirt = Tshirt.Pad.PALMEIRAS;
                        break;
                    default:
                        System.out.println("Opção inválida");
                        scanner.nextLine(); // consome a entrada inválida
                        return;
                }
            }
        
        System.out.println("Artigo criado com sucesso!\n");

        controller.colocaAVendaUser(user, tipoArtigo, classeArtigo, estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, dimensao, material, anoLancamento,atacadores, cor, tamanho, tamTshirt, padTshirt);          
    }

    public String transportadoraArtigo(Scanner scanner, String classeArtigo) throws ExceptionTransportadora{
        if(controller.transportadorasDisp().isEmpty()){
            throw new ExceptionTransportadora("Não há transportadoras disponíveis.\n");
        }
        System.out.println("\nTransportadoras disponíveis" + "\n" + controller.transportadorasDisp().toString() + "\n");
        System.out.print("Escolha a transportadora mais conveniente para o artigo que está a vender: ");
        String transportadora = scanner.nextLine();
        if(!controller.transportadorasDisp().containsKey(transportadora)){
            throw new ExceptionTransportadora("A transportadora escolida não está registada no sistema.\n");
        }
        transportadora = transportadora.substring(0).toUpperCase();
        return transportadora;
    }

    public void encomendarArtigo(Scanner scanner, Utilizador user) throws ExceptionEncomenda{
        if(controller.artigosDisponiveis(user).isEmpty()){
            throw new ExceptionEncomenda("Não há artigos para encomendar.\n");
        }
        else{
            System.out.println("--------- Artigos disponiveis para comprar -----------" + "\n" + controller.artigosDisponiveis(user)+ "\n");
            System.out.print("Seleciona o código de barras do artigo que pretende encomendar: ");
            int codBarras = 0;
            try{
                codBarras = scanner.nextInt();
                scanner.nextLine(); // consome o quebra de linha
            } catch(InputMismatchException e){
                System.out.println("Opção inválida. Insira um número inteiro.\n");
                encomendarArtigo(scanner, user); // chama o método novamente para solicitar uma entrada válida
            }
            controller.encomendarArtigoUser(user, codBarras);
        }
    }

    public void verEncomenda(Scanner scanner, Utilizador user){
        System.out.println("--------- Encomenda -----------" + "\n" + controller.encomendaUser(user).toString() + "\n");
    }

    public void finalizaEncomenda(Scanner scanner, Utilizador user){
        controller.finalizaEncomendaUser(user);
    }

    public void verArtigosComprados(Scanner scanner, Utilizador user){
        System.out.println("--------- Artigos comprados -----------" + "\n" +controller.artigosCompradosUser(user).toString() + "\n");
    }

    public void verArtigosVendidos(Scanner scanner, Utilizador user){
        System.out.println("--------- Artigos à venda -----------" + "\n" + controller.artigosAvendaUser(user).toString() + "\n");
    }

    public void devolverEncomenda(Scanner scanner, Utilizador user){
        controller.devolverEncomendaUser(user);   
    }
}
