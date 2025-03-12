package com.pedro.dao;

import com.pedro.config.HibernateConfig;
import com.pedro.model.Equipo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EquipoDAO {

    public void insertarEquipo(Equipo equipo) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(equipo);
            transaction.commit();
            System.out.println("Equipo insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al insertar equipo: " + e.getMessage());
        }
    }

    public List<Equipo> listarEquipos() {
        List<Equipo> equipos = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Equipo> query = session.createQuery("from Equipo", Equipo.class);
            equipos = query.list();
            equipos.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error al listar equipos: " + e.getMessage());
        }
        return equipos;
    }

    public Equipo obtenerEquipoPorId(int idEquipo) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Equipo.class, idEquipo); // Recupera el equipo por su ID
        } catch (Exception e) {
            System.out.println("Error al obtener equipo: " + e.getMessage());
            return null;
        }
    }
}