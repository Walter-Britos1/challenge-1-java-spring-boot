package com.aluracursos.gutendex.services;

public interface IDataConverter {
    <T> T getData(String json, Class<T> clase);
}
