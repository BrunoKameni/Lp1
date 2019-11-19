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

    private List<Pizza> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Pizza trabalhador) {
        lista.add(trabalhador);
    }

    public List<Pizza> listar() {
        return lista;
    }

    public Pizza buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId()==(id)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Pizza trabalhador, Pizza trabalhadorAntigo) {
        lista.set(lista.indexOf(trabalhadorAntigo), trabalhador);

    }

    public void excluir(Pizza trabalhador) {
        lista.remove(trabalhador);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Pizza trabalhador : lista) {
            listaDeString.add(trabalhador.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Pizza
        Pizza pizza;
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            pizza = new Pizza(aux[0], aux[1], Integer.valueOf(aux[2]), cf.converteDeStringParaDate(aux[3]), Double.valueOf(aux[4]));
            lista.add(pizza);
        }
    }

}
