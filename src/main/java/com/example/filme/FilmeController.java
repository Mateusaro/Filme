package com.example.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filmes> getAllFilmes() {
        return filmeService.getAllFilmes();
    }

    @GetMapping("/historico")
    public List<FilmeHistorico> getHistorico() {
        return filmeService.getHistorico();
    }

    @PostMapping
    public Filmes createFilme(@RequestBody Filmes filme) {
        return filmeService.createFilme(filme);
    }

    @PutMapping("/{id}")
    public Filmes updateFilme(@PathVariable Long id, @RequestBody Filmes filme) {
        return filmeService.updateFilme(id, filme);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeService.deleteFilme(id);
    }
}
