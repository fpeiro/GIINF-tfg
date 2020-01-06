/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;
import org.springframework.transaction.annotation.Transactional;
import server.classes.Usuario;
import server.exceptions.UsuarioYaExiste;

/**
 *
 * @author Felipe
 */
@Repository
@Transactional(propagation = SUPPORTS, readOnly = true)
public class UsuarioDAO {

    @PersistenceContext
    EntityManager em;

    public Usuario buscar(String nick) {
        return em.find(Usuario.class, nick);
    }

    public List<Usuario> buscarTodos() {
        try {
            return em.createQuery("SELECT U FROM Usuario U", Usuario.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(propagation = REQUIRED,
            rollbackFor = UsuarioYaExiste.class, readOnly = false)
    public void insertar(Usuario usuario) throws Exception {
        try {
        em.persist(usuario);
        em.flush();
        } catch (Exception e) {
            throw new UsuarioYaExiste();
        }
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void actualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Transactional(propagation = REQUIRED, readOnly = false)
    public void eliminar(Usuario usuario) {
        em.remove(em.merge(usuario));
    }
}
