/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class Categoria {

    private String strid, name;
    private List<Factor> factores;
    private String operacion;

    public Categoria() {
        strid = UUID.randomUUID().toString();
        this.name = "unnamed";
        this.factores = new ArrayList<>();
        operacion = "";
    }

    public Categoria(String name, List<Factor> factores) {
        strid = UUID.randomUUID().toString();
        this.name = name;
        this.factores = factores;
        operacion = "";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the factores
     */
    public List<Factor> getFactores() {
        return factores;
    }

    /**
     * @param factores the factores to set
     */
    public void setFactores(List<Factor> factores) {
        this.factores = factores;
    }

    /**
     * @return the strid
     */
    public String getStrid() {
        return strid;
    }

    /**
     * @param strid the strid to set
     */
    public void setStrid(String strid) {
        this.strid = strid;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}
