package com.pedro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pedro.dao.EquipoDAO;
import com.pedro.dao.XogadorDAO;
import com.pedro.model.Equipo;
import com.pedro.model.Xogador;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportarJSON {

    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final XogadorDAO xogadorDAO = new XogadorDAO();

    public void exportarEquiposAJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            List<Equipo> equipos = equipoDAO.listarEquipos();
            objectMapper.writeValue(new File(filePath), equipos);
            System.out.println("Equipos exportados correctamente a " + filePath);
        } catch (IOException e) {
            System.out.println("Error al exportar equipos: " + e.getMessage());
        }
    }

    public void exportarXogadoresAJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            List<Xogador> xogadores = xogadorDAO.listarXogadores();
            objectMapper.writeValue(new File(filePath), xogadores);
            System.out.println("Xogadores exportados correctamente a " + filePath);
        } catch (IOException e) {
            System.out.println("Error al exportar xogadores: " + e.getMessage());
        }
    }
}