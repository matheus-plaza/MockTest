package io.github.matheusplaza.mock.service;

import io.github.matheusplaza.mock.entity.CarroEntity;
import io.github.matheusplaza.mock.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(
        MockitoExtension.class
)
public class CarroServiceTest {
    //testes unitários com mockito


    @InjectMocks
    CarroService carroService;

    @Mock
    CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro() {

        CarroEntity carro = new CarroEntity("Sedan", 120.0, 2020);
        carro.setId(1L);

        when(carroRepository.save(Mockito.any())).thenReturn(carro);

        var carroSalvo = carroService.salvar(carro);

        assertNotNull(carroSalvo);
        assertEquals(carro.getModelo(), carroSalvo.getModelo());
        verify(carroRepository).save(Mockito.any());

    }

    @Test
    void deveDarErrAoAoSalvarCarroComValorDiariaZero() {
        var carro2 = new CarroEntity("Sedan", 0, 2020);

        var erro = catchThrowable(() -> carroService.salvar(carro2));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        verify(carroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveAtualizarCarro() {
        var carroExistente = new CarroEntity("Gol", 120.0, 2020);
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Ferrari", 120.0, 2020);
        carroAtualizado.setId(1L);
        when(carroRepository.save(Mockito.any())).thenReturn(carroAtualizado);

        var carro2 = new CarroEntity("Sedan", 0, 2020);
        Long id = 1L;

        var resultado = carroService.atualizar(id, carro2);

        assertEquals(resultado.getModelo(), resultado.getModelo());
        verify(carroRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deveDarErroAoAtualizarCarroInexistente() {
        var carro2 = new CarroEntity("Sedan", 0, 2020);
        Long id = 1L;

        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.atualizar(id, carro2));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);
        verify(carroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDeletarCarro() {
        var carroExistente = new CarroEntity("Gol", 120.0, 2020);
        carroExistente.setId(1L);
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carroExistente));

        carroService.deletar(1L);

        verify(carroRepository).delete(Mockito.any());

        verify(carroRepository).findById(Mockito.any());
    }

    @Test
    void deveDarErroAoDeletarCarroInexistente() {
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.deletar(1L));

        verify(carroRepository, Mockito.never()).delete(Mockito.any());
        assertThat(erro).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveBuscarCarroPorId() {
        var carroExistente = new CarroEntity("Gol", 120.0, 2020);
        carroExistente.setId(1L);
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carroExistente));

        var resultado = carroService.buscarPorId(1L);

        assertEquals(resultado.getModelo(), resultado.getModelo());
    }

    @Test
    void deveDarErroAoBuscarCarroInexistentePorId() {
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var erro = catchThrowable(() -> carroService.buscarPorId(1L));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveBuscarTodosOsCarros() {

        var carro1 = new CarroEntity(1L, "Gol", 120.0, 2020);
        var carro2 = new CarroEntity(2L, "Ferrari", 120.0, 2020);

        when(carroRepository.findAll()).thenReturn(Arrays.asList(carro1, carro2));

        var list = carroService.buscarTodos();
        verify(carroRepository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions(carroRepository);
        assertEquals(list.size(), 2);
        assertThat(list).isEqualTo(Arrays.asList(carro1, carro2));
    }
}
