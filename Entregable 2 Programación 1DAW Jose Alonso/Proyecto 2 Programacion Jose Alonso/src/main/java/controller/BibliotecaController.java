package controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Biblioteca;
import model.Libro;

import java.util.List;

@Data
@AllArgsConstructor
public class BibliotecaController {

    private APIController apiController;
    private Biblioteca biblioteca;


    public BibliotecaController() {
        this.biblioteca = new Biblioteca();
        this.apiController = new APIController();
    }

    public void importarLibros() {

        List<Libro> libros = apiController.obtenerLibros();
        for (Libro libro : libros) {
            biblioteca.agregarLibro(libro);
        }
        System.out.println("Biblioteca importada correctamente\n");
    }

    public Libro buscarLibro(int id){

        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getId() == id){
                return libro;
            }
        }
        return null;
    }

    public boolean agregarFavorito(int id) {

        Libro libro = buscarLibro(id);

        if (libro != null && !biblioteca.getFavoritos().contains(libro)) {
            biblioteca.getFavoritos().add(libro);
            System.out.println("Favorito agregado correctamente...\n");
            return true;
        }
        return false;
    }

    public boolean bibliotecaVacia(){
        return biblioteca.getLibros() == null || biblioteca.getLibros().isEmpty();
    }
}