package com.example.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmesRepository filmesRepository;

    @Autowired
    private FilmeHistoricoRepository filmeHistoricoRepository;

    public Filmes createFilme(Filmes filme) {
        filme.setUltimaAcao("CREATE");
        filme.setDataUltimaAlteracao(LocalDateTime.now());
        Filmes savedFilme = filmesRepository.save(filme);
        registrarHistorico(savedFilme, "CREATE");
        return savedFilme;
    }

    public Filmes updateFilme(Long id, Filmes filme) {
        Filmes existingFilme = filmesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        existingFilme.setTitulo(filme.getTitulo());
        existingFilme.setDiretor(filme.getDiretor());
        existingFilme.setAnoLancamento(filme.getAnoLancamento());

        existingFilme.setUltimaAcao("UPDATE");
        existingFilme.setDataUltimaAlteracao(LocalDateTime.now());
        Filmes updatedFilme = filmesRepository.save(existingFilme);
        registrarHistorico(updatedFilme, "UPDATE");
        return updatedFilme;
    }

    public void deleteFilme(Long id) {
        Filmes filme = filmesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        filmesRepository.delete(filme);
        registrarHistorico(filme, "DELETE");
    }

    private void registrarHistorico(Filmes filme, String acao) {
        FilmeHistorico historico = new FilmeHistorico();
        historico.setFilmeId(filme.getId());
        historico.setTitulo(filme.getTitulo());
        historico.setAcao(acao);
        historico.setDataAlteracao(LocalDateTime.now());
        filmeHistoricoRepository.save(historico);
    }

    public List<Filmes> getAllFilmes() {
        return filmesRepository.findAll();
    }

    public List<FilmeHistorico> getHistorico() {
        return filmeHistoricoRepository.findAll();
    }
}
