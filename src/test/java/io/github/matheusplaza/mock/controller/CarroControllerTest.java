package io.github.matheusplaza.mock.controller;

import io.github.matheusplaza.mock.entity.CarroEntity;
import io.github.matheusplaza.mock.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CarroService service;

    @Test
    void deveSalvarUmCarro() throws Exception {
        // Cenário
        CarroEntity carroSalvo = new CarroEntity(1L, "Civic", 120.0, 2020);
        when(service.salvar(Mockito.any(CarroEntity.class))).thenReturn(carroSalvo);

        String json = """
                {
                    "modelo": "Civic",
                    "valorDiaria": 120.0,
                    "anoFabricacao": 2020
                }
                """;

        // Execução
        mvc.perform(
                        post("/carros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )

//              Verificação
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("Civic"));
    }

    @Test
    void deveObterDetalhesDoCarro() throws Exception {
        // Cenário
        CarroEntity carroExistente = new CarroEntity(1L, "Civic", 120.0, 2020);
        when(service.buscarPorId(1L)).thenReturn(carroExistente);

        // Execução e Verificação
        mvc.perform(get("/carros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("Civic"))
                .andExpect(jsonPath("$.ano").value(2020));
    }

    @Test
    void deveRetornarNotFoundAoBuscarCarroInexistente() throws Exception {
        // Cenário
        when(service.buscarPorId(Mockito.anyLong()))
                .thenThrow(new IllegalArgumentException("Recurso não encontrado"));

        // Execução e Verificação
        mvc.perform(get("/carros/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveListarCarros() throws Exception {
        when(service.buscarTodos()).thenReturn(
                java.util.List.of(
                        new CarroEntity(1L, "Civic", 120.0, 2020),
                        new CarroEntity(2L, "Corolla", 150.0, 2021)
                )
        );

        mvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].modelo").value("Civic"))
                .andExpect(jsonPath("$[1].modelo").value("Corolla"));

    }

    @Test
    void deveAtualizarCarro() throws Exception {
        // Cenário
        CarroEntity carroAtualizado = new CarroEntity(1L, "Civic", 130.0, 2020);
        when(service.atualizar(Mockito.eq(1L), Mockito.any(CarroEntity.class)))
                .thenReturn(carroAtualizado);

        String json = """
                {
                    "modelo": "Civic",
                    "valorDiaria": 130.0,
                    "anoFabricacao": 2020
                }
                """;

        // Execução e Verificação
        mvc.perform(
            put("/carros/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.valorDiaria").value(130.0));
    }

    @Test
    void deveAtualizarCarroInexistente() throws Exception {
        // Cenário
        when(service.atualizar(Mockito.eq(99L), Mockito.any(CarroEntity.class)))
                .thenThrow(new IllegalArgumentException("Recurso não encontrado"));

        String json = """
                {
                    "modelo": "Civic",
                    "valorDiaria": 130.0,
                    "anoFabricacao": 2020
                }
                """;

        // Execução e Verificação
        mvc.perform(
            put("/carros/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarCarro() throws Exception {
        // Cenário
        Mockito.doNothing().when(service).deletar(any());

        // Execução e Verificação
        mvc.perform(delete("/carros/1"))
                .andExpect(status().isNoContent());

    }

    @Test
    void deveFalharAoDeletarCarro() throws Exception {
        // Cenário
        Mockito.doThrow(IllegalArgumentException.class).when(service).deletar(any());

        // Execução e Verificação
        mvc.perform(delete("/carros/1"))
                .andExpect(status().isNotFound());

        Mockito.verify(service, Mockito.times(1)).deletar(1L);
    }

}





































