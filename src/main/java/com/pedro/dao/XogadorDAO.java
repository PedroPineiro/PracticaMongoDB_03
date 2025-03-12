package com.pedro.dao;

import com.pedro.config.HibernateConfig;
import com.pedro.model.Xogador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class XogadorDAO {

    public void insertarXogador(Xogador xogador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(xogador);
            transaction.commit();
            System.out.println("Xogador insertado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar xogador: " + e.getMessage());
        }
    }

    public List<Xogador> listarXogadores() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Xogador", Xogador.class).list();
        } catch (Exception e) {
            System.out.println("Error al listar xogadores: " + e.getMessage());
            return null;
        }
    }
}