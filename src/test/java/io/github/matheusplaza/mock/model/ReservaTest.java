package io.github.matheusplaza.mock.model;

import io.github.matheusplaza.mock.model.exception.ReservaInvalidaExcepttion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("Matheus");
        carro = new Carro("Sedan", 100.0, 2020);
    }

    @Test
    public void deveCriarReserva(){
        //1. Cenário
        int dias = 3;

        //2. Execução
        var reserva = new Reserva(cliente, carro, dias);

        //3. Verificação
        assertTrue(reserva.getCliente() == cliente);
        assertThat(reserva.getCarro()).isEqualTo(carro);
    }

    @Test
    void deveDarErroAoCriarUmaReservaComQuantidadeDiasInvalida(){
        //1. Cenário
        int dias = 0;

        //2. Execução
        var exception = assertThrows(ReservaInvalidaExcepttion.class, () -> {
            new Reserva(cliente, carro, dias);
        });

        //3. Verificação

        //Junit
        assertThrows(ReservaInvalidaExcepttion.class, () -> new Reserva(cliente, carro, dias));
        assertThat(exception.getMessage()).isEqualTo("A quantidade de dias deve ser maior que zero");
        assertDoesNotThrow(() -> new Reserva(cliente, carro, 1));

        //AssertJ
        Throwable throwable = catchThrowable(() -> new Reserva(cliente, carro, dias));

        Assertions.assertThat(throwable).isInstanceOf(ReservaInvalidaExcepttion.class)
                .hasMessage("A quantidade de dias deve ser maior que zero");
    }

    @Test
    void deveCalcularTotalReserva(){

        var reserva = new Reserva(cliente, carro, 3);

        //2. Execução
        double total = reserva.calcularTotal();

        //3. Verificação
        assertThat(total).isEqualTo(300.0);
    }
}
