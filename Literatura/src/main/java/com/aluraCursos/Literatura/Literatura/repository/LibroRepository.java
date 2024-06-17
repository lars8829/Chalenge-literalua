package com.aluraCursos.Literatura.Literatura.repository;

import com.aluraCursos.Literatura.Literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

  /*  Optional<Libro> findByIdioma(String idioma);*/

    /*@Query(value = "")
    List<Libro> finByIdioma();*/

    List<Libro> findByIdioma(String idioma);

}
