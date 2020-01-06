/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Felipe
 */
@Entity
public class Modelo implements Serializable {

    public enum TipoDeDatos {
        BOOLEAN, PERCENTAGE, OPTION, NUMBER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strid, name, autor;
    private boolean activo;
    @ElementCollection(targetClass = String.class)
    private List<String> usuarios;
    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "modelo_evaluaciones", joinColumns = @JoinColumn(name = "id"))
    private Map<String, Evaluacion> evaluaciones;
    private String operacion;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany
    @JoinColumn(name = "categorias_id", referencedColumnName = "id")
    private List<Categoria> categorias;

    public Modelo() {
        strid = UUID.randomUUID().toString();
        usuarios = new ArrayList<>();
        evaluaciones = new LinkedHashMap<>();
        categorias = new ArrayList<>();
    }

    public Modelo(Modelo modelo, String autor) {
        this.strid = UUID.randomUUID().toString();
        this.name = modelo.name;
        this.autor = autor;
        this.activo = modelo.activo;
        this.operacion = modelo.operacion;
        this.usuarios = new ArrayList<>();
        this.evaluaciones = new LinkedHashMap<>();
        this.categorias = new ArrayList<>();
        modelo.categorias.forEach((categoria) -> {
            categorias.add(new Categoria(categoria));
        });
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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
     * @return the evaluaciones
     */
    public Map<String, Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    /**
     * @param evaluaciones the evaluaciones to set
     */
    public void setEvaluaciones(Map<String, Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
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
