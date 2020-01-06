/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;
import org.springframework.transaction.annotation.Transactional;
import server.classes.Factor;
import server.exceptions.FactorYaExiste;

/**
 *
 * @author Felipe
 */
@Repository
@Transactional(propagation = SUPPORTS, readOnly = true)
public class FactorDAO {

    @PersistenceContext
    EntityManager em;

    public Factor buscar(String id) {
        return em.find(Factor.class, id);
    }

    public Factor buscarPorStrid(String strid) {
        try {
            return em.createQuery("SELECT F FROM Factor F WHERE F.strid = :STRID", Factor.class)
                    .setParameter("STRID", strid).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = FactorYaExiste.class, readOnly = false)
    public void insertar(Factor factor) throws Exception {
        try {
            em.persist(factor);
            em.flush();
        } catch (Exception e) {
            throw new FactorYaExiste();
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = FactorYaExiste.class, readOnly = false)
    public void insertarYSustituir(Factor factor) throws Exception {
        eliminarPorStrid(factor.getStrid());
//        try {
            em.persist(factor);
            em.flush();
//        } catch (Exception e) {
//            throw new FactorYaExiste();
//        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void actualizar(Factor factor) {
        em.merge(factor);
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminar(Factor factor) {
        em.remove(em.merge(factor));
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminarPorStrid(String strid) {
        Factor fac = buscarPorStrid(strid);
        if (fac != null) {
            em.remove(fac);
        }
    }
}
