/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class EvaluacionDTO {

    private String strid, name;
    private Date time;
    private Map<String, Map<String, Object>> factors;
    private List<Float> resParciales;
    private float puntuacion;

    public EvaluacionDTO() {
        strid = UUID.randomUUID().toString();
        name = "";
        time = new Date();
        factors = new LinkedHashMap<>();
        resParciales = new ArrayList<>();
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
     * @return the factors
     */
    public Map<String, Map<String, Object>> getFactors() {
        return factors;
    }

    /**
     * @param factors the factors to set
     */
    public void setFactors(Map<String, Map<String, Object>> factors) {
        this.factors = factors;
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
     * @return the resParciales
     */
    public List<Float> getResParciales() {
        return resParciales;
    }

    /**
     * @param resParciales the resParciales to set
     */
    public void setResParciales(List<Float> resParciales) {
        this.resParciales = resParciales;
    }

    /**
     * @return the puntuacion
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
