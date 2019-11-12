package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.util.List;
import tools.Tools;

public class GUITexto {

    Tools tools = new Tools();

    Saida saida = new Saida();
    Controle controle = new Controle();
    
  

    public void telaList() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        List<Passaro> lt = controle.listar();

        System.out.println("ID;NOME;COR;PESO");
        for (Passaro passaro : lt) {
            System.out.println(passaro);
        }

        entrada.pausaEnter();
    }

    public void telaRetrieve() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("RETRIEVE\n");
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro");
        Passaro passaro = controle.buscar(id);
        if (passaro != null) {
            saida.imprimirNumeroInteiro("ID", passaro.getId());
            saida.rotuloString("Nome:", passaro.getNome());
            saida.rotuloString("Cor", passaro.getCor());
            saida.imprimirNumeroDouble("Peso", passaro.getPeso());
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
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro");
        Passaro trab = controle.buscar(id);
        if (trab == null) { //não achou, então pode adicionar
            Passaro passaro = new Passaro();
            passaro.setId(id);
            passaro.setNome(entrada.lerString("Digite o nome"));
            passaro.setCor(entrada.lerString("Digite a cor"));
            passaro.setPeso(entrada.lerNumeroDouble("Peso"));
            controle.adicionar(passaro);
        } else {
            System.out.println(trab);
            System.out.println("Passaro já cadastrado");
            entrada.pausaEnter();
        }
    }

    public void telaUpdate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("UPDATE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro");
        Passaro passaro = controle.buscar(id);
        if (passaro != null) { //achou, então pode alterar
            Passaro passaroAntigo = passaro; //guarda dados para pesquisa no controle
            passaro.setId(id);
            saida.rotuloString("Nome atual: ", passaro.getNome());
            passaro.setNome(entrada.lerString("Digite o novo nome"));
            saida.rotuloString("Cor atual", passaro.getCor());
            passaro.setCor(entrada.lerString("Digite a cor nova"));
            saida.imprimirNumeroDouble("Peso", passaro.getPeso());
            passaro.setPeso(entrada.lerNumeroDouble("Peso"));
            controle.alterar(passaro, passaroAntigo);
        } else {
            System.out.println("Passaro não cadastrado, impossível alterar");
            entrada.pausaEnter();
        }
    }

    public void telaDelete() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("DELETE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro");
        Passaro passaro = controle.buscar(id);
        if (passaro != null) { //achou, então pode excluir
            passaro.setId(id);
            saida.imprimirNumeroInteiro("ID", id);
            saida.rotuloString("Nome: ", passaro.getNome());
            saida.rotuloString("Cor", passaro.getCor());
            saida.imprimirNumeroDouble("Peso", passaro.getPeso());
            if (entrada.lerConfirmacao("Excluir esse passaro?")) {
                controle.excluir(passaro);
            }
        } else {
            System.out.println("Passaro não cadastrado, exclusão impossível");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        String caminho = "Passaro.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("CRUD - Passaro\n");
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
                    System.out.println("\n\nBye");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente");
            }

        }

    }

}
