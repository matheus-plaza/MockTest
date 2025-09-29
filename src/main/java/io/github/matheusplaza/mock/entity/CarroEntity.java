package io.github.matheusplaza.mock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class CarroEntity {

    public CarroEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private double valorDiaria;
    private int ano;

    public int getAno() {
        return ano;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAno(int ano) {
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

    public CarroEntity(Long id, String modelo, double valorDiaria, int ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
        this.id = id;
    }

    public CarroEntity(String modelo, double valorDiaria, int ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }
}
