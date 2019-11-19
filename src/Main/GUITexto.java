package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.util.List;
import tools.Tools;
import tools.CaixaDeFerramentas;


public class GUITexto {

    Tools tools = new Tools();
    
    CaixaDeFerramentas cf = new CaixaDeFerramentas();

    Saida saida = new Saida();
    Controle controle = new Controle();
    
  

    public void telaList() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        List<Pizza> lt = controle.listar();

        System.out.println("NOME;INGREDIENTES;ID;DATA_CRIAÇÃO;VALOR");
        for (Pizza pizza : lt) {
            System.out.println(pizza);
        }

        entrada.pausaEnter();
    }

    public void telaRetrieve() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("RETRIEVE\n");
        int id = entrada.lerNumeroInteiro("Digite o ID da pizza");
        Pizza pizza = controle.buscar(id);
        if (pizza != null) {
            saida.imprimirNumeroInteiro("ID", pizza.getId());
            saida.rotuloString("Nome:", pizza.getNome());
            saida.rotuloString("Ingredientes", pizza.getIngredientes());
            saida.imprimirNumeroDouble("Valor", pizza.getValor());
            saida.rotuloString("Data",cf.converteDeDateParaString(pizza.getData_criacao()) );
        } else {
            saida.imprimirNumeroInteiro("Não encontrou esse id", id);
        }
        entrada.pausaEnter();
    }

    public void telaCreate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("INSERT\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do pizza");
        Pizza trab = controle.buscar(id);
        if (trab == null) { //não achou, então pode adicionar
            Pizza pizza = new Pizza();
            pizza.setId(id);
            pizza.setNome(entrada.lerString("Digite o nome"));
            pizza.setIngredientes(entrada.lerString("Digite os ingredientes"));
            pizza.setData_criacao(entrada.lerDate("Data de criação"));
            pizza.setValor(entrada.lerNumeroDouble("Qual o valor que você da para ela"));
            controle.adicionar(pizza);
        } else {
            System.out.println(trab);
            System.out.println("Pizza cadastrada!");
            entrada.pausaEnter();
        }
    }

    public void telaUpdate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("UPDATE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID da pizza");
        Pizza pizza = controle.buscar(id);
        if (pizza != null) { //achou, então pode alterar
            Pizza pizzaAntigo = pizza; //guarda dados para pesquisa no controle
            pizza.setId(id);
            saida.rotuloString("Nome atual: ", pizza.getNome());
            pizza.setNome(entrada.lerString("Digite o novo nome"));
            saida.rotuloString("Ingredientes atuais", pizza.getIngredientes());
            pizza.setIngredientes(entrada.lerString("Digite os novos ingredientes"));
            saida.imprimirNumeroDouble("Valor atual", pizza.getValor());
            pizza.setValor(entrada.lerNumeroDouble("Valor novo"));
            saida.rotuloString("Data antiga", cf.converteDeDateParaString(pizza.getData_criacao()) );
            pizza.setData_criacao(cf.converteDeStringParaDate(entrada.lerString("Data de criação")));
            controle.alterar(pizza, pizzaAntigo);
        } else {
            System.out.println("Pizza não cadastrada, impossível alterar. 😎");
            entrada.pausaEnter();
        }
    }

    public void telaDelete() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("DELETE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do pizza");
        Pizza pizza = controle.buscar(id);
        if (pizza != null) { //achou, então pode excluir
            pizza.setId(id);
            saida.imprimirNumeroInteiro("ID", id);
            saida.rotuloString("Nome: ", pizza.getNome());
            saida.rotuloString("Ingredientes", pizza.getIngredientes());
            saida.imprimirNumeroDouble("Valor", pizza.getValor());
            saida.imprimirNumeroDouble("Valor", pizza.getValor());
            if (entrada.lerConfirmacao("Excluir essa pizza?")) {
                controle.excluir(pizza);
            }
        } else {
            System.out.println("Pizza não cadastrada, exclusão impossível. 😎");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        String caminho = "Pizza.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("CRUD - Pizza\n");
            System.out.println("\nMenu Principal\n");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Listar");
            System.out.println("9 - Sair");

            opcao = entrada.lerNumeroInteiro("Qual a opção");

            switch (opcao) {
                case 1:
                    telaCreate();
                    break;
                case 2:
                    telaRetrieve();
                    break;
                case 3:
                    telaUpdate();
                    break;
                case 4:
                    telaDelete();
                    break;
                case 5:
                    telaList();
                    break;
                case 9:
                    //antes de sair, transferir para o HD
                    controle.gravarLista(caminho);
                    System.out.println("\n\nBye!");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente. 😎");
            }

        }

    }

}
