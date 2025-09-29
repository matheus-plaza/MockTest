package io.github.matheusplaza.mock.repository;

import io.github.matheusplaza.mock.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

    List<CarroEntity> findByModelo(String modelo);
}
