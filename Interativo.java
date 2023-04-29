import java.util.Scanner;

public class Interativo {
    Scanner scanner;

    public Interativo(){
    }

    public void createUser(){
    }

    public void createItem(){
    }

    public void run() {
        int escolha;
        System.out.println("\n  BEM VINDO À VINTAGE\n");
        do {
            this.scanner = new Scanner(System.in);
            System.out.println("     | Menu |\n");
            System.out.println("1. Criar utilizador");
            System.out.println("2. Criar artigo");
            System.out.println("3. Sair");
            System.out.println("Opção : ");
            escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1:
                    criaUtilizador();
                    break;
                case 2:
                    createItem();
                    break;
                case 3:
                    System.out.println("Vemo nos para a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor escolha uma das opções disponíveis.");
                    break;
            }
        } while (escolha != 3);
        scanner.close();
    }

    private void criaUtilizador() {
        System.out.print("\033[H\033[2J");  //limpa o terminal
        System.out.flush();  //poe o apontador em cima na pagina
        
        System.out.println("\n----- A CRIAR UTILIZADOR ----- ");
        System.out.println("Nome");
        String nome = scanner.nextLine();
        System.out.println("Email");
        String email = scanner.nextLine();
        System.out.println("Morada");
        String morada = scanner.nextLine();
        System.out.println("Nif");
        Integer nif = scanner.nextInt();

        this.createUser();
        System.out.print("\033[H\033[2J");  
        System.out.println("Utilizador criado com sucesso!\n");
        System.out.flush();  
    }

    private void criaArtigo() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n----- A CRIAR ARTIGO PARA OS UTILIZADORES VENDEREM ----- ");
        System.out.println("Condicao");
        Artigo.Cond condicao = Artigo.Cond.valueOf(scanner.next().toUpperCase());
        System.out.println("Estado");
        Artigo.St estado = Artigo.St.valueOf(scanner.next().toUpperCase());
        System.out.println("Numero de donos");
        Integer donos = scanner.nextInt();
        System.out.println("Descrição");
        String descricao = scanner.nextLine();
        System.out.println("Marca");
        String marca = scanner.nextLine();
        System.out.println("Preço base");
        Double precoBase = scanner.nextDouble();
        System.out.println("Correção de preco");
        Double correcaoPreco = scanner.nextDouble();

        this.createItem();
        System.out.println("Item criado com sucesso!\n");
    }
}
