/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dtos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class ModeloDTO {

    public enum TipoDeDatosDTO {
        BOOLEAN, PERCENTAGE, OPTION, NUMBER
    }
    private Map<String, EvaluacionDTO> evaluaciones;
    private String operacion;
    private List<String> usuarios;
    private List<CategoriaDTO> categorias;
    private String strid, name;
    private boolean activo;

    public ModeloDTO() {
        evaluaciones = new LinkedHashMap<>();
        usuarios = new ArrayList<>();
        categorias = new ArrayList<>();
        strid = UUID.randomUUID().toString();
        name = "";
    }

    /**
     * @return the categorias
     */
    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias
     */
    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the evaluaciones
     */
    public Map<String, EvaluacionDTO> getEvaluaciones() {
        return evaluaciones;
    }

    /**
     * @param evaluaciones the evaluaciones to set
     */
    public void setEvaluaciones(Map<String, EvaluacionDTO> evaluaciones) {
        this.evaluaciones = evaluaciones;
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
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
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
     * @return the usuarios
     */
    public List<String> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<String> usuarios) {
        this.usuarios = usuarios;
    }
}