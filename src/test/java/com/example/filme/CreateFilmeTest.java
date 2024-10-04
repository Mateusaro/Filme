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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateFilmeTest {

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
    public void testCreateFilme() throws Exception {
        Filmes filme = new Filmes();
        filme.setTitulo("Filme Novo");

        when(filmeService.createFilme(any(Filmes.class))).thenReturn(filme);

        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Filme Novo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Filme Novo"));

        verify(filmeService, times(1)).createFilme(any(Filmes.class));
    }
}
