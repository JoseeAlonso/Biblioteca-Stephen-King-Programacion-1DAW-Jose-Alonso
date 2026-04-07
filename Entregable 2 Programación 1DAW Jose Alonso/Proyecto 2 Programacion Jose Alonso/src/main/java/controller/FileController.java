package controller;

import model.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    private static final String CARPETA_FAVORITOS = "favoritos";
    private static final String ARCHIVO_FAVORITOS = "favoritos.obj";

    private File getArchivoFavoritos() {
        File dir = new File(CARPETA_FAVORITOS);
        if (!dir.exists()) dir.mkdirs();
        return new File(dir, ARCHIVO_FAVORITOS);
    }

    public void exportarFavoritos(List<Libro> favoritos) {
        if (favoritos == null) favoritos = new ArrayList<>();

        try {
            File file = getArchivoFavoritos();

            List<Libro> favoritosExistentes = new ArrayList<>();
            if (file.exists() && file.length() > 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    favoritosExistentes = (List<Libro>) ois.readObject();
                }
            }

            for (Libro libro : favoritos) {
                if (!favoritosExistentes.contains(libro)) {
                    favoritosExistentes.add(libro);
                }
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(favoritosExistentes);
            }

            System.out.println("Favoritos exportados correctamente en: " + file.getAbsolutePath());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al exportar favoritos");
            e.printStackTrace();
        }
    }

    public List<Libro> importarFavoritos() {
        List<Libro> favoritos = new ArrayList<>();
        try {
            File file = getArchivoFavoritos();
            if (!file.exists() || file.length() == 0) {
                System.out.println("No hay favoritos guardados");
                return favoritos;
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                favoritos = (List<Libro>) ois.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al importar favoritos");
            e.printStackTrace();
        }
        return favoritos;
    }
}