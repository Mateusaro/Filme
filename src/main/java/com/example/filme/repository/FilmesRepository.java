package com.example.filme.repository;

import com.example.filme.model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Long> {
}
