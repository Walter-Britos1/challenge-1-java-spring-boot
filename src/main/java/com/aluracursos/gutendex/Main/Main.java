package com.aluracursos.gutendex.Main;

import com.aluracursos.gutendex.models.Data;
import com.aluracursos.gutendex.models.DataBooks;
import com.aluracursos.gutendex.services.ApiClient;
import com.aluracursos.gutendex.services.DataConverter;

import java.util.Comparator;

public class Main {
    private static final String URL_API = "https://gutendex.com/books/";

    private ApiClient apiClient = new ApiClient();

    private DataConverter converter = new DataConverter();


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

    }
}
