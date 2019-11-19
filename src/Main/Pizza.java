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
public class Pizza {

    String nome;
    String ingredientes;
    int id;
    Date data_criacao;
    double valor;

    public Pizza(String nome, String ingredientes, int id, Date data_criacao, double valor) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.id = id;
        this.data_criacao = data_criacao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pizza() {
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return nome + ";" + ingredientes + ";" + id + ";" + cf.converteDeDateParaString(data_criacao) + ";" + valor;
    }
}