package Main;

/**
 * @author Bruno Kameni
 */
public class Main {

    public static void main(String[] args) {
        Entrada entrada = new Entrada();
        Processamento processamento = new Processamento();
        Saida saida = new Saida();
        
        //entrada
        double a = entrada.lerNumeroDouble("Digite um valor para A");
        double b = entrada.lerNumeroDouble("Digite um valor para B");

        //processamento
        Double soma = processamento.getSoma(a, b);

        //saída
        saida.imprimirNumeroDouble("Resultado da soma", soma);

    }

}
