/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;
import org.springframework.transaction.annotation.Transactional;
import server.classes.Categoria;
import server.classes.Modelo;
import server.exceptions.ModeloYaExiste;

/**
 *
 * @author Felipe
 */
@Repository
@Transactional(propagation = SUPPORTS, readOnly = true)
public class ModeloDAO {

    @PersistenceContext
    EntityManager em;

    public Modelo buscar(String id) {
        return em.find(Modelo.class, id);
    }

    public Modelo buscarPorStrid(String strid) {
        try {
            return em.createQuery("SELECT M FROM Modelo M WHERE M.strid = :STRID", Modelo.class)
                    .setParameter("STRID", strid).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modelo> buscarPorAutor(String autor) {
        try {
            return em.createQuery("SELECT M FROM Modelo M WHERE M.autor = :AUTOR", Modelo.class)
                    .setParameter("AUTOR", autor).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Modelo buscarActivo(String autor) {
        return em.createQuery("SELECT M FROM Modelo M WHERE M.autor = :AUTOR AND M.activo = true", Modelo.class)
                .setParameter("AUTOR", autor).getSingleResult();
    }

    public Categoria buscarCategoria(String autor, String nombre) {
        Modelo modelo = buscarActivo(autor);
        for (Categoria categoria : modelo.getCategorias()) {
            if (categoria.getName().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = ModeloYaExiste.class, readOnly = false)
    public void insertar(Modelo modelo) throws Exception {
        try {
            em.persist(modelo);
            em.flush();
        } catch (Exception e) {
            throw new ModeloYaExiste();
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = ModeloYaExiste.class, readOnly = false)
    public void insertarYSustituir(Modelo modelo) throws Exception {
        eliminarPorStrid(modelo.getStrid());
//        try {
            em.persist(modelo);
            em.flush();
//        } catch (Exception e) {
//            throw new ModeloYaExiste();
//        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void actualizar(Modelo modelo) {
        em.merge(modelo);
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void activarModelo(String autor, String strid) {
        desactivarModelos(autor);
        try {
            em.createQuery("UPDATE Modelo M SET M.activo = true WHERE M.autor = :AUTOR AND M.strid = :STRID")
                    .setParameter("AUTOR", autor).setParameter("STRID", strid).executeUpdate();
        } catch (Exception e) {
        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void desactivarModelos(String autor) {
        try {
            em.createQuery("UPDATE Modelo M SET M.activo = false WHERE M.autor = :AUTOR")
                    .setParameter("AUTOR", autor).executeUpdate();
        } catch (Exception e) {
        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminar(Modelo modelo) {
        em.remove(em.merge(modelo));
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminarPorStrid(String strid) {
        Modelo mod = buscarPorStrid(strid);
        if (mod != null) {
            em.remove(mod);
        }
    }
}
