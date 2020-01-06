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
import server.classes.Evaluacion;
import server.exceptions.EvaluacionYaExiste;

/**
 *
 * @author Felipe
 */
@Repository
@Transactional(propagation = SUPPORTS, readOnly = true)
public class EvaluacionDAO {

    @PersistenceContext
    EntityManager em;

    public Evaluacion buscar(String id) {
        return em.find(Evaluacion.class, id);
    }

    public Evaluacion buscarPorStrid(String strid) {
        try {
            return em.createQuery("SELECT E FROM Evaluacion E WHERE E.strid = :STRID", Evaluacion.class)
                    .setParameter("STRID", strid).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Evaluacion> buscarPorAutor(String autor) {
        try {
            return em.createQuery("SELECT E FROM Evaluacion E WHERE E.autor = :AUTOR", Evaluacion.class)
                    .setParameter("AUTOR", autor).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = EvaluacionYaExiste.class, readOnly = false)
    public void insertar(Evaluacion evaluacion) throws Exception {
        try {
            em.persist(evaluacion);
            em.flush();
        } catch (Exception e) {
            throw new EvaluacionYaExiste();
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = EvaluacionYaExiste.class, readOnly = false)
    public void insertarYSustituir(Evaluacion evaluacion) throws Exception {
        eliminarPorStrid(evaluacion.getStrid());
//        try {
        em.persist(evaluacion);
        em.flush();
//        } catch (Exception e) {
//            throw new EvaluacionYaExiste();
//        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void actualizar(Evaluacion evaluacion) {
        em.merge(evaluacion);
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminar(Evaluacion evaluacion) {
        em.remove(em.merge(evaluacion));
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminarPorStrid(String strid) {
        Evaluacion eval = buscarPorStrid(strid);
        if (eval != null) {
            em.remove(eval);
        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void limpiar() {
        try {
            List<Evaluacion> lev = em.createNativeQuery("SELECT * FROM EVALUACION E LEFT JOIN MODELO_EVALUACIONES ME "
                    + "ON ME.EVALUACIONES_ID = E.ID WHERE ME.EVALUACIONES_ID IS NULL", Evaluacion.class).getResultList();
            lev.forEach((ev) -> {
                eliminar(ev);
            });
        } catch (Exception e) {
        }
    }
}
