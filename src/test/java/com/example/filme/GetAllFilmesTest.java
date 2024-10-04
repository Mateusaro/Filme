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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetAllFilmesTest {

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
    public void testGetAllFilmes() throws Exception {
        Filmes filme1 = new Filmes();
        filme1.setId(1L);
        filme1.setTitulo("Filme 1");

        Filmes filme2 = new Filmes();
        filme2.setId(2L);
        filme2.setTitulo("Filme 2");

        List<Filmes> filmes = Arrays.asList(filme1, filme2);

        when(filmeService.getAllFilmes()).thenReturn(filmes);

        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Filme 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Filme 2"));

        verify(filmeService, times(1)).getAllFilmes();
    }
}
