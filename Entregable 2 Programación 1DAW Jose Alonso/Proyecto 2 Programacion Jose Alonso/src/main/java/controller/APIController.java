package controller;

import com.google.gson.Gson;
import model.Libro;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class APIController {

    public List<Libro> obtenerLibros(){

        List<Libro> libros = new ArrayList<>();

        try {
            Gson gson = new Gson();

            HttpClient client = HttpClient.newHttpClient();
            String urlBase = "https://stephen-king-api.onrender.com/api/books";
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(urlBase))
                    .GET()
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject objectLibros = new JSONObject(response.body());
            JSONArray arrayLibros = objectLibros.getJSONArray("data");

            for(int i = 0; i < arrayLibros.length(); i++){
                JSONObject libroJSON = arrayLibros.getJSONObject(i);
                Libro libro = gson.fromJson(libroJSON.toString(), Libro.class);
                libros.add(libro);
            }
            System.out.println("Libros importados correctamente...");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    public Libro obtenerLibroPorEndPoint(int id){
        String path = "https://stephen-king-api.onrender.com/api/book/" + id;

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(path))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }

            JSONObject objectLibros = new JSONObject(response.body());
            JSONObject libroJSON = objectLibros.getJSONObject("data");

            return new Gson().fromJson(libroJSON.toString(), Libro.class);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener el libro, comuníquese con el Administrador");
        }

        return null;
    }

}
