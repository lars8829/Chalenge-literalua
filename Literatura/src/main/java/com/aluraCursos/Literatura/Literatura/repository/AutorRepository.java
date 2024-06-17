package com.aluraCursos.Literatura.Literatura.repository;

import com.aluraCursos.Literatura.Literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

   List<Autor> findByfechaMuerteGreaterThanAndFechaNacimientoLessThanEqual(String yearBusquedaVivoAutor, String yearBusquedaNacimiento);





}
