package com.aluracursos.gutendex.Main;

import com.aluracursos.gutendex.models.Data;
import com.aluracursos.gutendex.models.DataBooks;
import com.aluracursos.gutendex.services.ApiClient;
import com.aluracursos.gutendex.services.DataConverter;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String URL_API = "https://gutendex.com/books/";

    private ApiClient apiClient = new ApiClient();

    private DataConverter converter = new DataConverter();

    private Scanner imput = new Scanner(System.in);


    public void showMenu() {

        var json = apiClient.getData(URL_API);
        System.out.println(json);

        var data = converter.getData(json, Data.class);
        System.out.println(data);

        // Top 10 de los libros mas descargados
        System.out.println("Top 10 de libros mas descargados");
        data.booksList().stream()
                .sorted(Comparator.comparing(DataBooks::downloads).reversed())
                .limit(10)
                .map(b -> b.title().toUpperCase())
                .forEach(System.out::println);


        // Busqueda de libro por nombre
        System.out.println("¿Que libro desea leer hoy?");
        var searchedBook = imput.nextLine();
        json = apiClient.getData(
                URL_API + "?search=" + searchedBook.replace(" ", "+").toUpperCase()
        );

        var foundBook = converter.getData(json, Data.class);
        Optional<DataBooks> book = foundBook.booksList().stream()
                .filter(b -> b.title().contains(searchedBook))
                .findFirst();

        if (book.isPresent()) {
            System.out.println("El lobro que desea leer es: ");
            System.out.println(book.get());
        } else {
            System.out.println("El libro no se pudo encontrar, intente nuevamente");
        }

    }
}
