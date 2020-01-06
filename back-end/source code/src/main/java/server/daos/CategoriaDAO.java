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
import server.classes.Categoria;
import server.exceptions.CategoriaYaExiste;

/**
 *
 * @author Felipe
 */
@Repository
@Transactional(propagation = SUPPORTS, readOnly = true)
public class CategoriaDAO {

    @PersistenceContext
    EntityManager em;

    public Categoria buscar(String id) {
        return em.find(Categoria.class, id);
    }

    public Categoria buscarPorStrid(String strid) {
        try {
            return em.createQuery("SELECT C FROM Categoria C WHERE C.strid = :STRID", Categoria.class)
                    .setParameter("STRID", strid).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = CategoriaYaExiste.class, readOnly = false)
    public void insertar(Categoria categoria) throws Exception {
        try {
            em.persist(categoria);
            em.flush();
        } catch (Exception e) {
            throw new CategoriaYaExiste();
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = CategoriaYaExiste.class, readOnly = false)
    public void insertarYSustituir(Categoria categoria) throws Exception {
        eliminarPorStrid(categoria.getStrid());
//        try {
            em.persist(categoria);
            em.flush();
//        } catch (Exception e) {
//            throw new CategoriaYaExiste();
//        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void actualizar(Categoria categoria) {
        em.merge(categoria);
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminar(Categoria categoria) {
        em.remove(em.merge(categoria));
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminarPorStrid(String strid) {
        Categoria cat = buscarPorStrid(strid);
        if (cat != null) {
            em.remove(cat);
        }
    }
}
