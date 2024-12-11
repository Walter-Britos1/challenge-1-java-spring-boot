package com.aluracursos.gutendex.Main;

import com.aluracursos.gutendex.models.Data;
import com.aluracursos.gutendex.services.ApiClient;
import com.aluracursos.gutendex.services.DataConverter;

public class Main {
    private static final String URL_API = "https://gutendex.com/books/";

    private ApiClient apiClient = new ApiClient();

    private DataConverter converter = new DataConverter();


    public void showMenu() {

        var json = apiClient.getData(URL_API);
        System.out.println(json);

        var data = converter.getData(json, Data.class);
        System.out.println(data);
    }
}
