package com.aluracursos.gutendex.Main;

import com.aluracursos.gutendex.services.ApiClient;

public class Main {
    private ApiClient apiClient = new ApiClient();

    private final String API_URL = "https://gutendex.com/books/";

    public void showData() {
        String json = apiClient.getData(API_URL);
        System.out.println(json);
    }
}
