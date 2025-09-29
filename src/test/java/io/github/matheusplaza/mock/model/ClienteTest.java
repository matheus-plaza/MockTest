package io.github.matheusplaza.mock.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        //1. Cenário
        var cliente = new Cliente("Matheus");

        //2. Execução
        String nome = cliente.getNome();

        //3 Verificação
        assertNotNull(nome);
        assertTrue(nome.startsWith("M"));
        assertNotEquals(100, nome.length());
        assertThat(nome).isEqualTo("Matheus");
    }

    @Test
    void deveCriarClienteComNomeVazio(){
        //1. Cenário
        var cliente = new Cliente(null);

        //2. Execução
        String nome = cliente.getNome();

        //3 Verificação
        assertNull(nome);
    }


}
