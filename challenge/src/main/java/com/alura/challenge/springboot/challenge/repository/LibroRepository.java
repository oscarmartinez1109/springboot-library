package com.alura.challenge.springboot.challenge.repository;

import com.alura.challenge.springboot.challenge.model.Autor;
import com.alura.challenge.springboot.challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT a  FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> obtenerListaAutoresRegistrados();

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anioVivoAutor AND a.fechaDeFallecimiento >= :anioVivoAutor")
    List<Autor> obtenerAutoresVivosEnUnDeterminadoAnio (int anioVivoAutor);

    @Query("SELECT l FROM Libro l WHERE :idioma member of l.idioma")
    List<Libro> obtenerLibrosPorIdioma (String idioma);
}
