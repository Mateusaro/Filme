package com.example.filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeHistoricoRepository extends JpaRepository<FilmeHistorico, Long> {
}
