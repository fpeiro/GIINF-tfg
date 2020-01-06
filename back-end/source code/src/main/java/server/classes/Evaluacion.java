/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import server.auxs.JpaConverterJson;

/**
 *
 * @author Felipe
 */
@Entity
public class Evaluacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strid, name, autor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Convert(attributeName = "value.", converter = JpaConverterJson.class)
    @ElementCollection
    @Column(name="factors", length=1024)
    private Map<String, Object> factors;
    @ElementCollection(targetClass = Float.class)
    private List<Float> resParciales;
    private float puntuacion;

    public Evaluacion() {
        factors = new LinkedHashMap<>();
        resParciales = new ArrayList<>();
    }
    
    public void set(Evaluacion evaluacion) {
        this.autor = evaluacion.autor;
        this.factors = evaluacion.factors;
        this.name = evaluacion.name;
        this.puntuacion = evaluacion.puntuacion;
        this.resParciales = evaluacion.resParciales;
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
    public Map<String, Object> getFactors() {
        return factors;
    }

    /**
     * @param factors the factors to set
     */
    public void setFactors(Map<String, Object> factors) {
        this.factors = factors;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
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
