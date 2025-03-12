package com.pedro;

import com.pedro.dao.EquipoDAO;
import com.pedro.dao.XogadorDAO;
import com.pedro.model.Equipo;
import com.pedro.model.Xogador;
import com.pedro.service.ExportarJSON;
import com.pedro.service.ImportarJSON;
import com.pedro.service.MongoDBService;
import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Insertar datos de prueba en PostgreSQL
        EquipoDAO equipoDAO = new EquipoDAO();
        XogadorDAO xogadorDAO = new XogadorDAO();

        Equipo equipo1 = new Equipo();
        equipo1.setNome("Equipo A");
        equipo1.setCidade("Ciudad A");
        equipoDAO.insertarEquipo(equipo1);

        Equipo equipo = equipoDAO.obtenerEquipoPorId(equipo1.getId_equipo());
        if (equipo != null) {
            Xogador xogador = new Xogador();
            xogador.setNome("Jugador 1");
            xogador.setApellidos("Apellido 1");
            xogador.setPosicion("Delantero");
            xogador.setData_nacemento(Date.valueOf("2000-01-01"));
            xogador.setNacionalidade("Español");
            xogador.setIdequipo(equipo); // Asigna el equipo al xogador
            xogadorDAO.insertarXogador(xogador); // Inserta el xogador en PostgreSQL
        } else {
            System.out.println("El equipo no existe.");
        }

        // Exportar datos a JSON
        ExportarJSON exportadorService = new ExportarJSON();
        exportadorService.exportarEquiposAJson("equipos.json");
        exportadorService.exportarXogadoresAJson("xogadores.json");

        // Importar datos desde JSON
        ImportarJSON importadorService = new ImportarJSON();
        List<Equipo> equipos = importadorService.leerJson("equipos.json", Equipo[].class);
        List<Xogador> xogadores = importadorService.leerJson("xogadores.json", Xogador[].class);

        // Insertar datos en MongoDB
        MongoDBService mongoDBService = new MongoDBService(); // Abre la conexión aquí
        try {
            if (equipos != null) {
                mongoDBService.insertarEquipos(equipos);
            } else {
                System.out.println("Error: No se pudieron cargar los equipos desde el JSON.");
            }

            if (xogadores != null) {
                mongoDBService.insertarXogadores(xogadores);
            } else {
                System.out.println("Error: No se pudieron cargar los xogadores desde el JSON.");
            }
        } finally {
            // Cerrar la conexión al final
            mongoDBService.close();
        }
    }
}