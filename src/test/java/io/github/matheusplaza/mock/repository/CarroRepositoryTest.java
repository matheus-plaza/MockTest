package io.github.matheusplaza.mock.repository;

import io.github.matheusplaza.mock.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")// nome do resource/application-"""test""".properties
class CarroRepositoryTest {
    //testes de integração com o banco de dados em memória H2

    @Autowired
    private CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro(){
        var carro = new CarroEntity("Hatch", 100, 2020);

        CarroEntity carroSalvo = carroRepository.save(carro);

        assertNotNull(carroSalvo);
        assertNotNull(carroSalvo.getId());
        assertEquals("Hatch", carroSalvo.getModelo());

    }

    @Test
    void deveBuscarPeloId(){
        var carro = new CarroEntity("Hatch", 100, 2020);
        CarroEntity carroSalvo = carroRepository.save(carro);

        CarroEntity carroEncontrado = carroRepository.findById(carroSalvo.getId()).orElse(null);

        assertNotNull(carroEncontrado);
        assertEquals(carroSalvo.getId(), carroEncontrado.getId());
    }

    @Test
    void deveAtualizarUmCarro(){
        var carro = new CarroEntity("Hatch", 100, 2020);
        CarroEntity carroSalvo = carroRepository.save(carro);

        carroSalvo.setModelo("Sedan");
        carroSalvo.setValorDiaria(150.0);
        CarroEntity carroAtualizado = carroRepository.save(carroSalvo);

        assertNotNull(carroAtualizado);
        assertEquals("Sedan", carroAtualizado.getModelo());
        assertEquals(150.0, carroAtualizado.getValorDiaria());
    }

}