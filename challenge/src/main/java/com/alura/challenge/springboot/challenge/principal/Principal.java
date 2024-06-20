package com.alura.challenge.springboot.challenge.principal;

import com.alura.challenge.springboot.challenge.model.*;
import com.alura.challenge.springboot.challenge.repository.LibroRepository;
import com.alura.challenge.springboot.challenge.service.ConsumoAPI;
import com.alura.challenge.springboot.challenge.service.ConvierteDatos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos convertir = new ConvierteDatos();
    private LibroRepository repository;
    private List<Libro> libros;

    //metodo para mostrar el menu
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    -------------------------------
                    Eliga la opcion a traves de su numero:
                    1- Buscar Libro por Titulo
                    2- Listar Libros Registrados
                    3- Listar Autores Registrados
                    4- Listar Autores Vivos En Un Determinado Año
                    5- Listar Libros Por Idioma
                                        
                    0- SALIR                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTituo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnDeterminadoAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //constructor principal parametro repositorio
    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

//metodos principal

    public DatosLibro obtenerDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Datos datos = objectMapper.readValue(json, Datos.class);

            // Verifica si la lista no está vacía
            if (datos.datosLibro().isEmpty()) {
                System.out.println("No se encontraron libros");
                muestraElMenu();
                return null;
            }

            DatosLibro datosLibro = datos.datosLibro().get(0);

            String nombresAutores = datosLibro.autores().stream()
                    .map(DatosAutor::nombre)
                    .collect(Collectors.joining(", "));

            // Agregar mensajes de depuración
            System.out.println("---------Libro---------:");
            System.out.println("Título: " + datosLibro.titulo());
            System.out.println("Autor: " + nombresAutores);
            System.out.println("Idiomas: " + datosLibro.idioma());
            System.out.println("Total Descargas: " + datosLibro.totalDescargas());
            System.out.println("------------------------");

            return datosLibro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //metodo menu 1
    private void buscarLibroPorTituo() {
        DatosLibro datosLibro = obtenerDatosLibro();
        if (datosLibro == null) {
            return;
        }
        Optional<Libro> libroExistente = repository.findByTitulo(datosLibro.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya esta registrado");
        } else {
            Libro libro = new Libro(datosLibro);
            repository.save(libro);
        }
    }

    //metodo menu 2
    private void listarLibrosRegistrados() {
        System.out.println("----Libros Registrados-----");
        libros = repository.findAll();
        libros.forEach(libro -> {
            Hibernate.initialize(libro.getAutores());
            System.out.println(libro);
        });
    }

    //metodo menu 3
    private void listarAutoresRegistrados() {
        List<Autor> obtenerAutoresRegistrados = repository.obtenerListaAutoresRegistrados();
        obtenerAutoresRegistrados.forEach(System.out::println);

//        obtenerAutoresRegistrados.forEach(autor -> {
//            List<String> titulosLibros = autor.getLibros().stream()
//                    .map(Libro::getTitulo)
//                    .collect(Collectors.toList());
//            System.out.printf("\n Autor: %s\n Fecha de nacimiento: %s\n Fecha de fallecimiento: %s\n Libros: %s\n",
//                    autor.getNombre(), autor.getFechaDeNacimiento(), autor.getFechaDeFallecimiento(), titulosLibros);
//        });
    }

    // metodo menu 4
    private void listarAutoresVivosEnUnDeterminadoAño() {
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar");
        var anioVivoAutor = teclado.nextInt();

        List<Autor> autoresVivos = repository.obtenerAutoresVivosEnUnDeterminadoAnio(anioVivoAutor);
        System.out.println("-----------------------------------");
        autoresVivos.forEach(System.out::println);
    }

    // metodo menu 5
    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar lo libros
                es - Español
                en - Ingles
                pt - Portugues
                """);
        var idioma = teclado.nextLine();
        List<Libro> obtenerLibrosPorIdioma = repository.obtenerLibrosPorIdioma(idioma);
        obtenerLibrosPorIdioma.forEach(System.out::println);
    }

}