package com.alura.challenge.springboot.challenge.model;

import jakarta.persistence.*;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idioma = new ArrayList<>();
    private Double totalDescargas;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma();
        this.totalDescargas = datosLibro.totalDescargas();
        this.autores = datosLibro.autores().stream()
                .map(datosAutor -> new Autor(datosAutor.nombre(), datosAutor.fechaDeNacimiento(), datosAutor.fechaDeFallecimiento()))
                .collect(Collectors.toList());
    }
//geters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Double getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Double totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    //metodo toString


    @Override
    public String toString() {

        return "--------LIBRO-------" +
                "\n Titulo:'" + titulo +
                 autores +
                "\n Idioma:" + idioma +
                "\n Total Descargas:" + totalDescargas +
                 "\n----------------------";
    }
}
