package io.github.matheusplaza.mock.service;

import io.github.matheusplaza.mock.entity.CarroEntity;
import io.github.matheusplaza.mock.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public CarroEntity salvar(CarroEntity carro) {
        if (carro.getValorDiaria() <= 0) {
            throw new IllegalArgumentException("Valor da diária deve ser maior que zero.");
        }

        return carroRepository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado) {
        var carro = carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado."));

        carro.setModelo(carroAtualizado.getModelo());
        carro.setValorDiaria(carroAtualizado.getValorDiaria());
        carro.setAno(carroAtualizado.getAno());

        return carroRepository.save(carro);
    }

    public void deletar(Long id) {
        var carro = carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado."));
        carroRepository.delete(carro);
    }

    public CarroEntity buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado."));
    }

    public List<CarroEntity> buscarTodos() {
        return carroRepository.findAll();
    }

}
