package com.pedro.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    // URI de la base de datos de MongoDB
    private static final String URI = "mongodb://admin:admin@localhost:27017";
    private static final String DATABASE_NAME = "futboldb";

    // Instancia única de MongoClient (Singleton)
    private static MongoClient mongoClient;
    private MongoDatabase database;

    /**
     * Establece la conexión con la base de datos de MongoDB.
     * Si ya existe una conexión, la reutiliza.
     *
     * @return Base de datos de MongoDB.
     */
    public MongoDatabase connect() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
            System.out.println("Conexión establecida con MongoDB");
        }
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        return this.database;
    }

    /**
     * Devuelve una colección de MongoDB.
     *
     * @param collectionName Nombre de la colección.
     * @return Colección de MongoDB.
     * @throws IllegalStateException Si la conexión no está establecida.
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        if (database == null) {
            throw new IllegalStateException("La conexión no está establecida. Llama a connect() primero.");
        }
        return this.database.getCollection(collectionName);
    }

    /**
     * Cierra la conexión con la base de datos de MongoDB.
     * Si la conexión ya está cerrada, no hace nada.
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null; // Limpia la instancia
            database = null; // Limpia la referencia a la base de datos
        }
    }

    /**
     * Metodo estático para obtener la instancia única de MongoClient.
     *
     * @return Instancia de MongoClient.
     */
    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient;
    }
}