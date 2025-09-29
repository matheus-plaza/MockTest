package io.github.matheusplaza.mock.model;

import io.github.matheusplaza.mock.model.exception.ReservaInvalidaExcepttion;

public class Reserva {

    private Cliente cliente;
    private Carro carro;

    private int quantidadeDias;

    public double calcularTotal(){
        return carro.calcularValorAluguel(quantidadeDias);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public Reserva(Cliente cliente, Carro carro, int quantidadeDias) {
        this.cliente = cliente;
        this.carro = carro;
        if (quantidadeDias <= 0) {
            throw new ReservaInvalidaExcepttion("A quantidade de dias deve ser maior que zero");
        }
        this.quantidadeDias = quantidadeDias;
    }
}
