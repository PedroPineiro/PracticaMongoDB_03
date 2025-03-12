package com.pedro.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pedro.config.MongoDBConnection;
import com.pedro.model.Equipo;
import com.pedro.model.Xogador;
import org.bson.Document;
import java.util.List;

public class MongoDBService {

    private final MongoDBConnection mongoDBConnection;
    private final MongoDatabase database;

    public MongoDBService() {
        // Abrir la conexión al crear una instancia de MongoDBService
        this.mongoDBConnection = new MongoDBConnection();
        this.database = mongoDBConnection.connect();
    }

    public void insertarEquipos(List<Equipo> equipos) {
        try {
            MongoCollection<Document> collection = database.getCollection("equipos");

            // Insertar equipos
            for (Equipo equipo : equipos) {
                Document doc = new Document("id_equipo", equipo.getId_equipo())
                        .append("nome", equipo.getNome())
                        .append("cidade", equipo.getCidade());
                collection.insertOne(doc);
            }

            System.out.println("Equipos insertados en MongoDB correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar equipos en MongoDB: " + e.getMessage());
        }
    }

    public void insertarXogadores(List<Xogador> xogadores) {
        try {
            MongoCollection<Document> collection = database.getCollection("xogadores");

            // Insertar xogadores
            for (Xogador xogador : xogadores) {
                Document doc = new Document("id_xogador", xogador.getId_xogador())
                        .append("nome", xogador.getNome())
                        .append("apelidos", xogador.getApellidos())
                        .append("posicion", xogador.getPosicion())
                        .append("data_nacemento", xogador.getData_nacemento().toString())
                        .append("nacionalidade", xogador.getNacionalidade())
                        .append("id_equipo", xogador.getIdEquipo());
                collection.insertOne(doc);
            }

            System.out.println("Xogadores insertados en MongoDB correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar xogadores en MongoDB: " + e.getMessage());
        }
    }

    public void close() {
        // Cerrar la conexión manualmente
        mongoDBConnection.close();
        System.out.println("Conexión cerrada con MongoDB.");
    }
}