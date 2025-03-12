package com.pedro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImportarJSON {

    public <T> List<T> leerJson(String filePath, Class<T[]> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            T[] objetos = objectMapper.readValue(new File(filePath), clazz);
            return Arrays.asList(objetos);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}