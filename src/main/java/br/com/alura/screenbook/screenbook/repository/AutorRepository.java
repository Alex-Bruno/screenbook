package br.com.alura.screenbook.screenbook.repository;

import br.com.alura.screenbook.screenbook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findFirstByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento >= :ano OR a.anoFalecimento IS NULL) ORDER BY a.nome")
    List<Autor> obterAutoresVivosEmDeterninadoAno(Integer ano);
}
