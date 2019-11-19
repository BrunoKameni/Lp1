package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.text.DecimalFormat;
import java.util.Date;

class Saida {

   public void imprimirNumeroDouble(String msg,double num) {
        //formata e imprime um número double
        DecimalFormat df = new DecimalFormat("###,##0.00");
        System.out.println(msg + ": " + df.format(num));
    }

    public void rotuloString(String rotulo,String s) {
        System.out.println(rotulo+": "+s);
    }
    public void imprimirNumeroInteiro(String rotulo,int i) {
        System.out.println(rotulo+": "+i);
    }

    void converteDeStringParaDate(String data_de_Criação, Date data_criacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
