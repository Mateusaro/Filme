package com.example.filme;

import com.example.filme.controller.FilmeController;
import com.example.filme.model.Filmes;
import com.example.filme.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UpdateFilmeTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FilmeService filmeService;

    @InjectMocks
    private FilmeController filmeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
    }

    @Test
    public void testUpdateFilme() throws Exception {
        Filmes filme = new Filmes();
        filme.setId(1L);
        filme.setTitulo("Filme Atualizado");

        when(filmeService.updateFilme(eq(1L), any(Filmes.class))).thenReturn(filme);

        mockMvc.perform(put("/filmes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Filme Atualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Filme Atualizado"));

        verify(filmeService, times(1)).updateFilme(eq(1L), any(Filmes.class));
    }
}
