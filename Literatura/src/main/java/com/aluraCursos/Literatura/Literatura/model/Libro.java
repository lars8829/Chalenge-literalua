package com.aluraCursos.Literatura.Literatura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    @ManyToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "libro_autor",
            joinColumns = {@JoinColumn(
                    name = "libro_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "autor_id"
            )}
    )
    private List<Autor> autores;

//    public Libro(DatosLibros datos) {
//        this.titulo = datos.titulo();
//        this.idioma = datos.idioma().get(0);
//
//    }

    public Libro(DatosLibros datos) {
        this.titulo = datos.titulo();
        this.idioma = datos.idioma().get(0);
        this.autores = datos.autor().stream()
                    .map(a -> new Autor(datos.autor().get(0).nombreAutor(),
                            datos.autor().get(0).fechaNacimiento(),
                            datos.autor().get(0).fechaMuerte()))
                .collect(Collectors.toList());
    }

    public Libro() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", autores=" + autores;
    }


}
