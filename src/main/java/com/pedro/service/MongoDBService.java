package com.pedro.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pedro.model.Equipo;
import com.pedro.model.Xogador;
import org.bson.Document;
import java.util.List;

public class MongoDBService {

    private static final String URI = "mongodb://admin:admin@localhost:27017";
    private static final String DB_NAME = "futboldb";

    public void insertarEquipos(List<Equipo> equipos) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection("equipos");

            for (Equipo equipo : equipos) {
                Document doc = new Document("id_equipo", equipo.getId_equipo())
                        .append("nome", equipo.getNome())
                        .append("cidade", equipo.getCidade());
                collection.insertOne(doc);
            }

            System.out.println("Equipos insertados en MongoDB correctamente.");
        }
    }

    public void insertarXogadores(List<Xogador> xogadores) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection("xogadores");

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
        }
    }
}