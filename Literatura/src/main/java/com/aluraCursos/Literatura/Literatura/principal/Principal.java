package com.aluraCursos.Literatura.Literatura.principal;

import com.aluraCursos.Literatura.Literatura.model.Autor;
import com.aluraCursos.Literatura.Literatura.model.Datos;
import com.aluraCursos.Literatura.Literatura.model.DatosLibros;
import com.aluraCursos.Literatura.Literatura.model.Libro;
import com.aluraCursos.Literatura.Literatura.repository.AutorRepository;
import com.aluraCursos.Literatura.Literatura.repository.LibroRepository;
import com.aluraCursos.Literatura.Literatura.service.ConsumoAPI;
import com.aluraCursos.Literatura.Literatura.service.ConvierteDatos;

import java.util.*;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConvierteDatos conversor = new ConvierteDatos();

    private Scanner teclado = new Scanner(System.in);

    private LibroRepository repositorioLibros;

    private AutorRepository repositorioAutores;

    public Principal(LibroRepository repository, AutorRepository repository2) {
        this.repositorioLibros = repository;
        this.repositorioAutores = repository2;
    }


    private List<DatosLibros> datosLibros = new ArrayList<>();

  /*  public Principal(LibroRepository repository) {
    }*/

    // metodo muestra menu
    public void muestraElMenu() {

        var opcion = -1;

        while (opcion != 6) {
            var menu = """
                                        
                    1 - Buscar Libro
                    2 - Listar Libros Guardados
                    3 - Listar Autores registrados
                    4 - Listar Autores vivos en un determinado Año
                    5 - Listar Libros por Idioma   
                    6 - Salir
                                        
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibrosBd();
                    System.out.println();
                    break;
                case 3:
                    consultarAutoresBd();
                    System.out.println();
                    break;
                case 4:
                    consultarAutoresBdByFecha();
                    System.out.println();
                    break;
                case 5:
                    consultarLibrosBdIdiomas();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");

            }//FINAL SWITCH

        } // FINAL WHILE


        }

    //FINAL MENU

    private Datos getDatosLibros(){
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ", "+"));
        //System.out.println(json);
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
            if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
           // System.out.println(libroBuscado.get());
            }else{
            System.out.println("libro no encontrado");
        }
        return datosBusqueda;
    }
    private void  buscarLibro(){
        Datos datos = getDatosLibros(); // almacena valores que viene de la api
        DatosLibros libroEncontrado = datos.resultados().get(0);
        Libro libros = new Libro(libroEncontrado);
        repositorioLibros.save(libros);
        System.out.println(libros);
    }

    private void consultarLibrosBd() {
        List<Libro> librosLocales = repositorioLibros.findAll();
        librosLocales.forEach(System.out::println);
    }

    private void consultarAutoresBd() {
        List<Autor> autoresBd = repositorioAutores.findAll();
        autoresBd.forEach(System.out::println);
    }

    private void consultarAutoresBdByFecha(){
        System.out.println("Ingrese Fecha a consultar Autores vivos:");
        var fechaAutor = teclado.nextLine();
        List<Autor> autorBuscado = repositorioAutores.findByfechaMuerteGreaterThanAndFechaNacimientoLessThanEqual(fechaAutor,fechaAutor);
        System.out.println(Arrays.toString(autorBuscado.toArray()));
    }
    private void consultarLibrosBdIdiomas(){
        System.out.println("Ingrese idioma a cosultar libros:");
        var idiomaLibro = teclado.nextLine();
        List<Libro> idiomaBuscado = repositorioLibros.findByIdioma(idiomaLibro);
        System.out.println(Arrays.toString(idiomaBuscado.toArray()));
    }

} // FINAL CLASE

