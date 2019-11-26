package Main;

import java.util.ArrayList;
import java.util.List;
import tools.CaixaDeFerramentas;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class Controle {

    private List<Contatos> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Contatos contatos) {
        lista.add(contatos);
    }

    public List<Contatos> listar() {
        return lista;
    }

    public Contatos buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getDdd()==(id)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Contatos contatos, Contatos contatosAntigo) {
        lista.set(lista.indexOf(contatosAntigo), contatos);

    }

    public void excluir(Contatos contatos) {
        lista.remove(contatos);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Contatos contatos : lista) {
            listaDeString.add(contatos.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Contatos
        Contatos pizza;
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            pizza = new Contatos(Integer.valueOf(aux[0]), aux[1], aux[2], cf.converteDeStringParaDate(aux[3]), aux[4]);
            lista.add(pizza);
        }
    }

}
