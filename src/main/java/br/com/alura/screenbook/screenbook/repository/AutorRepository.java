package br.com.alura.screenbook.screenbook.repository;

import br.com.alura.screenbook.screenbook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findFirstByNomeContainingIgnoreCase(String nome);
}
