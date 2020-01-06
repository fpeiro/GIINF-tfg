/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.classes;

import client.classes.Modelo.TipoDeDatos;
import static client.classes.Modelo.TipoDeDatos.NUMBER;
import static client.classes.Modelo.TipoDeDatos.OPTION;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class Factor {

    private String strid, name;
    private TipoDeDatos dataType;
    private List<String> valores;
    private List<Float> scores;

    public Factor() {
        strid = UUID.randomUUID().toString();
        this.name = "";
        this.dataType = TipoDeDatos.values()[0];
        this.valores = new ArrayList<>();
        this.scores = loadScores();
    }

    public Factor(String name, TipoDeDatos dataType) {
        strid = UUID.randomUUID().toString();
        this.name = name;
        this.dataType = dataType;
        this.valores = new ArrayList<>();
        this.scores = loadScores();
    }

    private List<Float> loadScores() {
        List<Float> scrs = new ArrayList<>();
        scrs.add((float) 0);
        scrs.add((float) 1);
        scrs.add((float) 1);
        return scrs;
    }

    public void addOpt() {
        valores.add("");
        while (scores.size() < valores.size()) {
            scores.add((float) 0.5);
        }
    }

    public void assign(Factor factor) {
        this.strid = factor.strid;
        this.name = factor.name;
        this.dataType = factor.dataType;
        List<String> nValores = new ArrayList<>();
        for (String valor : factor.valores) {
            if (valor != null) {
                nValores.add(valor);
            }
        }
        this.valores = nValores;
        if (factor.dataType != NUMBER) {
            this.scores = factor.scores;
        } else {
            this.scores = new ArrayList<>();
            for (Float score : factor.scores) {
                this.scores.add((float) Math.round(score));
            }
        }
        if (factor.dataType == OPTION) {
            while (this.scores.size() < factor.valores.size()) {
                this.scores.add((float) 0.5);
            }
        } else if (factor.scores.isEmpty()) {
            this.scores = loadScores();
        } else {
            while (this.scores.size() < 2) {
                this.scores.add((float) 0.5);
            }
        }
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
