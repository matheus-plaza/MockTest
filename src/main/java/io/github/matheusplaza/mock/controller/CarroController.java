package io.github.matheusplaza.mock.controller;

import io.github.matheusplaza.mock.entity.CarroEntity;
import io.github.matheusplaza.mock.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping()
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try {
            var carroSalvo = carroService.salvar(carro);
            return ResponseEntity.status(201).body(carroSalvo);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CarroEntity> pesquisa(@PathVariable("id") Long id){
        try {
            CarroEntity carroEntity = carroService.buscarPorId(id);
            return ResponseEntity.ok(carroEntity);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping()
    public ResponseEntity<List<CarroEntity>> listarTodos(){
        return ResponseEntity.ok(carroService.buscarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id, @RequestBody CarroEntity carro){
        try {
            var carroAtualizado = carroService.atualizar(id, carro);
            return ResponseEntity.ok(carroAtualizado);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluir(@PathVariable("id") Long id){
        try {
            carroService.deletar(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}


