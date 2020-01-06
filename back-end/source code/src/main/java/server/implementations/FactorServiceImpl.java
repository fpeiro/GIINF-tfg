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
import server.classes.Factor;
import server.daos.FactorDAO;

/**
 *
 * @author Felipe
 */
@Component
@RestController
@RequestMapping
public class FactorServiceImpl {

    protected static FactorDAO factorDAO;

    @Autowired
    public void setFactorDAO(FactorDAO factorDAO) {
        FactorServiceImpl.factorDAO = factorDAO;
    }

    public static void anadirFactor(Factor factor) throws Exception {
        factorDAO.insertarYSustituir(factor);
    }
}
