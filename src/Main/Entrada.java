package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import tools.CaixaDeFerramentas;

class Entrada {

    Scanner teclado = new Scanner(System.in);

    public void pausaEnter() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Pressione [Enter] para continuar");
        teclado.nextLine();
    }

    public Double lerNumeroDouble(String msg) {
        Scanner teclado = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg + ": ");
                double x = teclado.nextDouble();
                return x;
            } catch (Exception e) {
                System.out.println("Erro, digite um double...");
                teclado = new Scanner(System.in);
            }
        }
    }

    public Integer lerNumeroInteiro(String msg) {
        Scanner teclado = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg + ": ");
                int x = teclado.nextInt();
                return x;
            } catch (Exception e) {
                System.out.println("Erro, digite um inteiro...");
                teclado = new Scanner(System.in);
            }
        }
    }

    public String lerString(String msg) {
        Scanner teclado = new Scanner(System.in);
        //evita que o usuário deixe a string vazia
        while (true) {
            try {
                System.out.print(msg + ": ");
                String x = teclado.nextLine();
                if (x.trim().isEmpty()) {
                    int k = 3 / 0; //propositalmente provoca um erro para ir para o catch
                }
                return x;
            } catch (Exception e) {
                System.out.println("Erro, a string não pode ser vazia.");
                teclado = new Scanner(System.in);
            }
        }
    }

    public Date lerDate(String msg) {
        Scanner teclado = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        //evita que o usuário deixe data errada
        while (true) {
            try {
                System.out.print(msg + ": ");
                String x = teclado.nextLine();
                Date dt = cf.converteDeStringParaDate(x);
                if (dt == null) {
                    int k = 3 / 0;
                }
                return dt;
            } catch (Exception e) {
                System.out.println("Erro na data.");
                teclado = new Scanner(System.in);
            }
        }
    }

    public boolean lerConfirmacao(String msg) {
        Scanner teclado = new Scanner(System.in);
        //ler uma resposta sim ou não do usuário
        while (true) {
            try {
                System.out.print(msg + " (S ou N) ");
                char x = teclado.next().charAt(0);
                if (x == 's' || x == 'S') {
                    return true;
                } else if (x == 'n' || x == 'N') {
                    return false;
                } else {
                    int a = 3 / 0;//provocar um erro propositalmente
                }
            } catch (Exception e) {
                System.out.println("Erro, são válidas apenas as letras S ou N.");
                teclado = new Scanner(System.in);
            }
        }
    }

    public String lerTelefone(String msg) {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        Scanner teclado = new Scanner(System.in);
        String x;
        while (true) {
            String num = teclado.nextLine();
            String vazia = "";
            for (int i = 0; i < num.length(); i++) {
                if ("0".equals(num.charAt(i)) || "1".equals(num.charAt(i))
                        || "2".equals(num.charAt(i)) || "3".equals(num.charAt(i))
                        || "4".equals(num.charAt(i)) || "5".equals(num.charAt(i))
                        || "6".equals(num.charAt(i)) || "7".equals(num.charAt(i))
                        || "8".equals(num.charAt(i)) || "9".equals(num.charAt(i))) {
                    vazia = vazia.concat(String.valueOf(num.charAt(i)));
                }
            }
            x = cf.formataTelefone(vazia);
            if (x != null) {
                break;
            }
            System.out.println("Digitou errado, faça novamente.");
        }
        return x;
    }
}
