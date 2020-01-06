/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import server.classes.Modelo.TipoDeDatos;

/**
 *
 * @author Felipe
 */
@Entity
public class Factor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strid, name;
    private TipoDeDatos dataType;
    @ElementCollection(targetClass = String.class)
    private List<String> valores;
    @ElementCollection(targetClass = Float.class)
    private List<Float> scores;
    
    public Factor() {
        strid = UUID.randomUUID().toString();
        valores = new ArrayList<>();
        scores = new ArrayList<>();
    }
    
    public Factor(Factor factor) {
        this.strid = UUID.randomUUID().toString();
        this.name = factor.name;
        this.dataType = factor.dataType;
        this.valores = factor.valores;
        this.scores = factor.scores;
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
     * @return the dataType
     */
    public TipoDeDatos getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(TipoDeDatos dataType) {
        this.dataType = dataType;
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
     * @return the valores
     */
    public List<String> getValores() {
        return valores;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(List<String> valores) {
        this.valores = valores;
    }

    /**
     * @return the scores
     */
    public List<Float> getScores() {
        return scores;
    }

    /**
     * @param scores the scores to set
     */
    public void setScores(List<Float> scores) {
        this.scores = scores;
    }
}
