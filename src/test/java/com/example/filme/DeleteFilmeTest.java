package com.example.filme;

import com.example.filme.controller.FilmeController;
import com.example.filme.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteFilmeTest {

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
    public void testDeleteFilme() throws Exception {
        mockMvc.perform(delete("/filmes/delete/1"))
                .andExpect(status().isOk());

        verify(filmeService, times(1)).deleteFilme(1L);
    }
}
