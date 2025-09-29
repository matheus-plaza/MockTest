package io.github.matheusplaza.mock.repository;

import io.github.matheusplaza.mock.entity.CarroEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroRepositorySQLTest {
    //testes de integração com o banco de dados em memória H2 usando script SQL para popular o banco

    @Autowired
    CarroRepository carroRepository;

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo() {
        List<CarroEntity> list = carroRepository.findByModelo("Hatch");

        Assertions.assertEquals(1, list.size());

        CarroEntity carro = list.get(0);
        Assertions.assertEquals("Hatch", carro.getModelo());
        Assertions.assertEquals(80.0, carro.getValorDiaria());
    }
}
