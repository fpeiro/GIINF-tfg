/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.dtos;

import client.dtos.ModeloDTO.TipoDeDatosDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class FactorDTO {

    private String strid, name;
    private TipoDeDatosDTO dataType;
    private List<String> valores;
    private List<Float> scores;

    public FactorDTO() {
        this.strid = UUID.randomUUID().toString();
        this.valores = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    public FactorDTO(String name, TipoDeDatosDTO dataType) {
        this.strid = UUID.randomUUID().toString();
        this.name = name;
        this.dataType = dataType;
        this.valores = new ArrayList<>();
        this.scores = new ArrayList<>();
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
    public TipoDeDatosDTO getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(TipoDeDatosDTO dataType) {
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
