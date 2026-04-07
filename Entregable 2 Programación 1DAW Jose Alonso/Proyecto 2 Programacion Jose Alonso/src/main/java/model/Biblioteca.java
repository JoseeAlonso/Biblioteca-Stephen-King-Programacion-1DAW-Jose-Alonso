package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class Biblioteca {


    private List<Libro> libros;
    private List<Libro> favoritos;

    public Biblioteca(){
        libros = new ArrayList<>();
        favoritos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro){
        libros.add(libro);
    }

}