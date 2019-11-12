package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class Controle {

    private List<Passaro> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Passaro trabalhador) {
        lista.add(trabalhador);
    }

    public List<Passaro> listar() {
        return lista;
    }

    public Passaro buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId()==(id)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Passaro trabalhador, Passaro trabalhadorAntigo) {
        lista.set(lista.indexOf(trabalhadorAntigo), trabalhador);

    }

    public void excluir(Passaro trabalhador) {
        lista.remove(trabalhador);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Passaro trabalhador : lista) {
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
        //converter de CSV para Passaro
        Passaro passaro;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            passaro = new Passaro(Integer.valueOf(aux[0]), aux[1], aux[2], Double.valueOf(aux[3]));
            lista.add(passaro);
        }
    }

}
