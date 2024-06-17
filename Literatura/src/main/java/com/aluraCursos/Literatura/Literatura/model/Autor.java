package com.aluraCursos.Literatura.Literatura.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombreAutor;
    private String fechaNacimiento;
    private String fechaMuerte;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor(){}  // conectar con la bd

    public Autor(String nombreAutor, String fechaNacimiento, String fechaMuerte) {
        this.nombreAutor = nombreAutor;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                ", nombreAutor='" + nombreAutor + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", fechaMuerte='" + fechaMuerte;
    }
}
