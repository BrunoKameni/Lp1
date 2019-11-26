/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Date;
import tools.CaixaDeFerramentas;

/**
 *
 * @author a2020823
 */
public class Contatos {
    private String numero;
    private String nome;
    private Date data;
    private String status;
    private boolean whatsapp;

    public Contatos(int ddd, String numero, String nome, Date data, String status) {
        this.ddd = ddd;
        this.numero = numero;
        this.nome = nome;
        this.data = data;
        this.status = status;

    }

    public Contatos() {

    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return ddd + "; " + numero + "; " + nome + "; " + cf.converteDeDateParaString(data) + "; " + status;
    }

}
