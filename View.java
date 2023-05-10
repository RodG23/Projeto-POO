import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.util.List;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }


    public void run() {
        LocalDate dataAtual = LocalDate.now();
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
                            }catch(ExceptionUser e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        }
                        case 2:{
                            user(scanner, dataAtual);
                            break;
                        }
                        case 3:{
                            System.out.println("O programa será encerrado.");
                            break;
                        }
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 5);
        scanner.close();
    }

    public void admin(Scanner scanner) throws ExceptionUser{
        int opcao = 0;
        do{
            System.out.println("\n=== ADMINISTRADOR ===");
            System.out.println("1 - Adicionar transportadora");
            System.out.println("2 - Eliminar utilizador");
            System.out.println("3 - Queries");
            System.out.println("4 - Avançar data");
            System.out.println("5 - Terminar sessão e voltar ao menu principal");
            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch(opcao) {
                    case 1:{
                        System.out.print("Nome da transportadora: ");
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
                                System.out.println("Insira uma opção válida (Sim, Nao).");
                                admin(scanner);
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
                            System.out.println("Opção inválida. Insira um número com virgula.");
                            scanner.nextLine(); // consome a entrada inválida
                            admin(scanner); // chama o método novamente para solicitar uma entrada válida
                            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
                        }
                        controller.addTranspAdmin(nome, cp, cm, cg, imposto, custoAdicional);
                        break;
                    }
                    case 2:{
                        List<String> usuariosDisponiveis = controller.utilizadoresDisponiveis();
                        if(usuariosDisponiveis.isEmpty()){
                            throw new ExceptionUser("Não existe utilizadores disponíveis");
                        }
                        System.out.println("Utilizadores disponíveis\n" + controller.utilizadoresDisponiveis());
                        System.out.print("Email do utilizador que pretende eliminar: ");
                        String email = scanner.nextLine();
                        if (!usuariosDisponiveis.contains(email)) {
                            throw new ExceptionUser("O utilizador escolhido não existe no sistema");
                        }
                        controller.eliminaUserAdmin(email);                        
                        break;
                    }
                    case 3:{
                        try{
                            queries(scanner);
                        }catch(ExceptionUser e){
                            System.out.println(e.getMessage());
                        }
                        
                        break;
                    }
                    case 4:{                     
                        avanca(scanner);
                        break;
                    }
                    case 5:{
                        System.out.println("Sessão como administrador terminada.\n");
                        break;
                    }
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 5);
    }

    public void queries(Scanner scanner) throws ExceptionUser{
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
                        List<String> usuariosDisponiveis = controller.utilizadoresDisponiveis();
                        System.out.print("Email do vendedor que pretende analisar as encomendas: ");
                        String emailVendedor = scanner.nextLine();
                        if (!usuariosDisponiveis.contains(emailVendedor)) {
                            throw new ExceptionUser("O vendedor "+ emailVendedor + " nao existe.");
                        }
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
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 6);
    }

    public void avanca(Scanner scanner){
        System.out.println("Insira a nova data atual do sistema:");
        String dataAtual = scanner.nextLine();
        controller.avancaDataSistema(dataAtual);
    }

    public void user(Scanner scanner, LocalDate dataAtual){
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
                        criarUtilizador(scanner, 5);
                        break;
                    }
                    case 2:{
                        try{
                            loggin(scanner, dataAtual);
                        }catch(ExceptionUser e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Sessão como utilizador terminada.\n");
                        break;
                    }
                }

            }catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // consome a entrada inválida
            }
        }while(opcao != 3);
    }
    
    public void criarUtilizador(Scanner scanner, int tentativas) {
        if (tentativas == 0) {
            System.out.println("Limite de tentativas atingido. Encerrando a criação do utilizador.");
            return;
        }
        System.out.println("\n=== CRIAR UTILIZADOR ===");
        System.out.print("E-mail: ");
        String user = scanner.nextLine();
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
            System.out.println("Opção inválida. Insira um número inteiro.");
            scanner.nextLine(); // consome a entrada inválida
            criarUtilizador(scanner, tentativas - 1); // chama o método novamente com um número menor de tentativas
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }
        controller.registaUser(user, nome, morada, nif);
    }

    public void loggin(Scanner scanner, LocalDate dataAtual) throws ExceptionUser{
        System.out.println("\n=== Iniciar sessão ===");
        List<String> usuariosDisponiveis = controller.utilizadoresDisponiveis();
        if (usuariosDisponiveis == null || usuariosDisponiveis.isEmpty()) {
            throw new ExceptionUser("Não é possível iniciar sessão, não há usuários disponíveis");
        }
        System.out.println("Usuários disponíveis:\n " + usuariosDisponiveis);
        System.out.print("Digite o email do usuário para iniciar sessão: ");
        String email = scanner.nextLine();
        if (!usuariosDisponiveis.contains(email)) {
            throw new ExceptionUser("Não é possível iniciar sessão com o usuário " + email);
        }
        System.out.println("Sessão iniciada com " + email);
        interativo2(scanner, email, dataAtual);
    }

    public void interativo2(Scanner scanner, String email, LocalDate dataAtual){
        Utilizador user = this.controller.retornarLoggedUser(email);
        int opcao = 0;
        do {
            System.out.println("\n=== OPÇÕES ===");
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
                        //só pode vender artigos se houver transportadoras disponíveis
                        if(!controller.transportadorasDisp().isEmpty() || controller.transportadorasDisp() == null){
                            criarArtigo(scanner, user);
                        }
                        else{
                            System.out.println("Opção inválida. Não é possivel colocar à venda um artigo porque não existem transportadoras disponíveis\n");
                        }
                        break;
                    } 
                    case 2:{
                        try{
                            encomendarArtigo(scanner, user, dataAtual);
                        }catch (ExceptionEncomendar e){
                            System.out.println(e.getMessage());
                        }
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
                    System.out.println("Opção inválida. Tente novamente.");
                    scanner.next(); // consome a entrada inválida
            }
        } while (opcao != 8);
    }

    public void criarArtigo(Scanner scanner, Utilizador user){
        System.out.println("\n=== A CRIAR ARTIGO ===");
        System.out.print("Escolha o tipo de artigo (Mala, Sapatilha, Tshirt): ");
        String tipoArtigo = scanner.nextLine();

        tipoArtigo = tipoArtigo.substring(0,1).toUpperCase() + tipoArtigo.substring(1).toLowerCase();
        if(!tipoArtigo.equals("Mala") && !tipoArtigo.equals("Sapatilha") && !tipoArtigo.equals("Tshirt")){
            System.out.println("Opção inválida. Insira uma opção válida (Mala, Sapatilha, Tshirt).");
            return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
        }
        
        System.out.println("\n=== Classe ===");
        String classeArtigo = "Nao";
        if(!tipoArtigo.equals("Tshirt")){
            System.out.print("O artigo é premium? (Sim, Nao): ");
            classeArtigo = scanner.nextLine();
            classeArtigo = classeArtigo.substring(0,1).toUpperCase() + classeArtigo.substring(1).toLowerCase();
            if(!classeArtigo.equals("Sim") && !classeArtigo.equals("Nao")){
                System.out.println("Opção inválida. Insira uma opção válida (Sim, Nao).");
                return; // sai do método para evitar que o restante do código seja executado com a entrada inválida
            }
        }
        
        System.out.println("\n=== ESPECIFICAÇÔES DO ARTIGO ===");

        String transportadora = transportadoraArtigo(scanner);
        
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
            System.out.println("Opção inválida. Insira um número com virgula.");
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
                    System.out.println("Opção inválida");
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
            System.out.println("Opção inválida. Insira um número inteiro.");
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
                            System.out.println("Opção inválida");
                            return;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Opção inválida. Insira um número inteiro.");
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
                    System.out.println("Opção inválida. Insira um número inteiro.");
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
                    System.out.println("Opção inválida. Insira 'true' ou 'false'.");
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
                    System.out.println("Opção inválida. Insira um número inteiro.");
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
                    System.out.println("Opção inválida. Insira um número inteiro.");
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
                        System.out.println("Opção inválida");
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
                    System.out.println("Opção inválida. Insira um número inteiro.");
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

    public String transportadoraArtigo(Scanner scanner){
        System.out.println("\nTransportadoras disponíveis" + "\n" + controller.transportadorasDisp() + "\n");
        System.out.println("Escolha a transportadora mais conveniente para o artigo que está a vender");
        String transportadora = scanner.nextLine();
        transportadora = transportadora.substring(0).toUpperCase();
        return transportadora;
    }

    public void encomendarArtigo(Scanner scanner, Utilizador user, LocalDate dataAtual) throws ExceptionEncomendar{
        if(controller.artigosDisponiveis(user).isEmpty()){
            throw new ExceptionEncomendar("Não há artigos para encomendar");
        }
        else{
            System.out.println("--------- Artigos disponiveis para comprar -----------" + "\n" + controller.artigosDisponiveis(user)+ "\n");
            System.out.println("Seleciona o código de barras do artigo que pretende encomendar:");
            int codBarras = 0;
            try{
                codBarras = scanner.nextInt();
                scanner.nextLine(); // consome o quebra de linha
            } catch(InputMismatchException e){
                System.out.println("Opção inválida. Insira um número inteiro.");
                encomendarArtigo(scanner, user, dataAtual); // chama o método novamente para solicitar uma entrada válida
            }
            controller.encomendarArtigoUser(user, codBarras, dataAtual);
        }
    }

    public void verEncomenda(Scanner scanner, Utilizador user){
        System.out.println("--------- Encomenda -----------" + "\n" + controller.encomendaUser(user) + "\n");
    }

    public void finalizaEncomenda(Scanner scanner, Utilizador user){
        controller.finalizaEncomendaUser(user);
    }

    public void verArtigosComprados(Scanner scanner, Utilizador user){
        System.out.println("--------- Artigos comprados -----------" + "\n" +controller.artigosCompradosUser(user)+ "\n");
    }

    public void verArtigosVendidos(Scanner scanner, Utilizador user){
        System.out.println("--------- Artigos à venda -----------" + "\n" + controller.artigosAvendaUser(user) + "\n");
    }

    public void devolverEncomenda(Scanner scanner, Utilizador user){
        controller.devolverEncomendaUser(user);   
    }
}
