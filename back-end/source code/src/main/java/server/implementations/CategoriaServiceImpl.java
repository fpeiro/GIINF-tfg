/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.classes.Categoria;
import server.classes.Factor;
import server.daos.CategoriaDAO;
import static server.implementations.FactorServiceImpl.anadirFactor;

/**
 *
 * @author Felipe
 */
@Component
@RestController
@RequestMapping
public class CategoriaServiceImpl {

    protected static CategoriaDAO categoriaDAO;

    @Autowired
    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        CategoriaServiceImpl.categoriaDAO = categoriaDAO;
    }

    public static void anadirCategoria(Categoria categoria) throws Exception {
        for (Factor factor : categoria.getFactores()) {
            anadirFactor(factor);
        }
        categoriaDAO.insertarYSustituir(categoria);
    }
}
