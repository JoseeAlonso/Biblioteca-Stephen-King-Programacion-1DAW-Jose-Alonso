import controller.APIController;
import controller.BibliotecaController;
import controller.FileController;
import model.Libro;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        APIController apiController = new APIController();
        FileController fileController = new FileController();

        BibliotecaController bibliotecaController = new BibliotecaController();

        List<Libro> favoritosGuardados = fileController.importarFavoritos();

        if (favoritosGuardados != null && !favoritosGuardados.isEmpty()) {
            bibliotecaController.getBiblioteca().setFavoritos(favoritosGuardados);
        }

        boolean salir = false;

        System.out.println("\n¡Bienvenido a la mejor biblioteca del Stephen King!\nRecuerda importar los libros primero :)...");

        while (!salir) {
            try {
                System.out.println("\n--- Menú Biblioteca ---");
                System.out.println("1. Importar libros");
                System.out.println("2. Buscar libro por endpoint");
                System.out.println("3. Buscar libro por ID");
                System.out.println("4. Seleccionar libro como favorito");
                System.out.println("5. Exportar como favorito");
                System.out.println("6. Importar favoritos");
                System.out.println("7. Salir");
                System.out.println("Elige una opción: ");

                int opcion = scn.nextInt();
                scn.nextLine();

                switch (opcion) {
                    case 1:
                        if (!bibliotecaController.bibliotecaVacia()) {
                            System.out.println("Los libros ya han sido importados.");
                            break;
                        }
                        List<Libro> libros = apiController.obtenerLibros();
                        bibliotecaController.importarLibros();
                        System.out.println("Libros importados: " + libros.size());
                        break;

                    case 2:
                        System.out.println("Introduce el endpoint del libro que quieres buscar");
                        int endPoint = scn.nextInt();
                        Libro libroBuscadoEP = apiController.obtenerLibroPorEndPoint(endPoint);
                        if (libroBuscadoEP != null) {
                            System.out.println("Información del libro: " + libroBuscadoEP);
                        } else {
                            System.out.println("No se encontró el libro con dicho endpoint. Intenta con otro...");
                        }
                        break;

                    case 3:
                        if (bibliotecaController.getBiblioteca().getLibros() == null || bibliotecaController.getBiblioteca().getLibros().isEmpty()) {
                            System.out.println("Primero debes importar los libros (opción 1)");
                            break;
                        }
                        System.out.println("Introduce el ID del libro");
                        int idLibro = scn.nextInt();
                        Libro libroBuscadoId = bibliotecaController.buscarLibro(idLibro);
                        if (libroBuscadoId != null) {
                            System.out.println("Libro encontrado: " + libroBuscadoId);
                        } else {
                            System.out.println("No se encontró ningún libro con ese ID en la biblioteca...");
                        }
                        break;

                    case 4:
                        if (bibliotecaController.getBiblioteca().getLibros() == null || bibliotecaController.getBiblioteca().getLibros().isEmpty()) {
                            System.out.println("Primero debes importar los libros (opción 1)");
                            break;
                        }

                        System.out.println("Introduce el Id del libro que quieras agregar como favorito:");
                        int idFavorito = scn.nextInt();

                        Libro libro = bibliotecaController.buscarLibro(idFavorito);

                        if (libro == null) {
                            System.out.println("No existe el libro con ese ID en la biblioteca...");
                            break;
                        }

                        boolean agregado = bibliotecaController.agregarFavorito(idFavorito);

                        if (agregado) {
                            System.out.println("Libro agregado a favoritos\n" + bibliotecaController.buscarLibro(idFavorito));
                        } else {
                            System.out.println("El libro ya está agregado como favoritos");
                        }
                        break;

                    case 5:
                        if (bibliotecaController.getBiblioteca().getLibros() == null || bibliotecaController.getBiblioteca().getLibros().isEmpty()) {
                            System.out.println("Primero debes importar los libros (opción 1)");
                            break;
                        }
                        fileController.exportarFavoritos(bibliotecaController.getBiblioteca().getFavoritos());
                        break;

                    case 6:
                        List<Libro> favoritosImportados = fileController.importarFavoritos();

                        if (favoritosImportados.isEmpty()) {
                            System.out.println("No hay favoritos guardados aún...");
                        } else {
                            bibliotecaController.getBiblioteca().setFavoritos(favoritosImportados);

                            System.out.println("Favoritos importados correctamente: " + favoritosImportados.size());
                            for (Libro libroImp : favoritosImportados) {
                                System.out.println("Favorito: " + libroImp);
                            }
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del sistema...");
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dato no válida, introduce un número.");
                scn.nextLine();
            }
        }
    }
}