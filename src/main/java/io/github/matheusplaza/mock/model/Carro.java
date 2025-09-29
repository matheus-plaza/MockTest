package io.github.matheusplaza.mock.model;

public class Carro {
    private String modelo;
    private double valorDiaria;
    private int ano;

    public double calcularValorAluguel(int dias) {
        double desconto = 0.0;
        if (dias >= 5) {
            desconto = 50.0;
        }
        return (valorDiaria * dias) - desconto;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Carro(String modelo, double valorDiaria, int ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
