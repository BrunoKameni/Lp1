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
        List<Contatos> lt = controle.listar();

        System.out.println("DDD; Número; Nome; Data; Status");
        for (Contatos contatos : lt) {
            System.out.println(contatos);
        }

        entrada.pausaEnter();
    }

    public void telaRetrieve() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("RETRIEVE\n");
        int id = entrada.lerNumeroInteiro("Digite o DDD do contato");
        Contatos contatos = controle.buscar(id);
        if (contatos != null) {
            saida.imprimirNumeroInteiro("DDD", contatos.getDdd());
            saida.rotuloString("Número:", contatos.getNumero());
            saida.rotuloString("Nome", contatos.getNome());
            saida.rotuloString("Data", cf.converteDeDateParaString(contatos.getData()));
            saida.rotuloString("Status",contatos.getStatus());
        } else {
            saida.imprimirNumeroInteiro("Não foi encontrado esse DDD", id);
        }
        entrada.pausaEnter();
    }

    public void telaCreate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("INSERT\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o DDD do contato");
        Contatos trab = controle.buscar(id);
        if (trab == null) { //não achou, então pode adicionar
            Contatos contatos = new Contatos();
            contatos.setDdd(id);
            contatos.setNumero(entrada.lerString("Digite o número (apenas números, por favor!)"));
            contatos.setNome(entrada.lerString("Digite o nome do contato"));
            contatos.setData(entrada.lerDate("Digite a data de criação"));
            contatos.setStatus(entrada.lerString("Digite o status (familiar, amigo, empresa, etc...)"));
            controle.adicionar(contatos);
        } else {
            System.out.println(trab);
            System.out.println("Contatos cadastrados!");
            entrada.pausaEnter();
        }
    }

    public void telaUpdate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("UPDATE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o DDD do contato");
        Contatos contatos = controle.buscar(id);
        if (contatos != null) { //achou, então pode alterar
            Contatos contatosAntigo = contatos; //guarda dados para pesquisa no controle
            contatos.setDdd(id);
            saida.imprimirNumeroInteiro("DDD atual: ", contatos.getDdd());
            contatos.setDdd(entrada.lerNumeroInteiro("Digite o novo DDD"));
            saida.rotuloString("Número atual", contatos.getNumero());
            contatos.setNumero(entrada.lerString("Digite o novo número"));
            saida.rotuloString("Nome atual", contatos.getNome());
            contatos.setNome(entrada.lerString("Nome novo"));
            saida.rotuloString("Data antiga", cf.converteDeDateParaString(contatos.getData()));
            contatos.setData(cf.converteDeStringParaDate(entrada.lerString("Data nova")));
            controle.alterar(contatos, contatosAntigo);
        } else {
            System.out.println("Contatos não cadastrada, impossível alterar. 😎");
            entrada.pausaEnter();
        }
    }

    public void telaDelete() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("DELETE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o DDD do contatos");
        Contatos contatos = controle.buscar(id);
        if (contatos != null) { //achou, então pode excluir
            contatos.setDdd(id);
            saida.imprimirNumeroInteiro("DDD", id);
            saida.imprimirNumeroInteiro("DDD: ", contatos.getDdd());
            saida.rotuloString("Número", contatos.getNumero());
            saida.rotuloString("Nome", contatos.getNome());
            saida.rotuloString("Data de criação", cf.converteDeDateParaString(contatos.getData()));
            saida.rotuloString("Status", contatos.getStatus());
            if (entrada.lerConfirmacao("Excluir essa contatos?")) {
                controle.excluir(contatos);
            }
        } else {
            System.out.println("Contatos não cadastrada, exclusão impossível. 😎");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        String caminho = "Contatos.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("CRUD - Contatos\n");
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
                    System.out.println("\n\nObrigado pela preferência!");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente. 😎");
            }

        }

    }

}
