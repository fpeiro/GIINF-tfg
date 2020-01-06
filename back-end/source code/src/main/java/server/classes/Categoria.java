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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Felipe
 */
@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strid, name;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany
    @JoinColumn(name = "factores_id", referencedColumnName = "id")
    private List<Factor> factores;
    private String operacion;

    public Categoria() {
        strid = UUID.randomUUID().toString();
        factores = new ArrayList<>();
    }

    public Categoria(Categoria categoria) {
        this.strid = UUID.randomUUID().toString();
        this.name = categoria.name;
        this.factores = new ArrayList<>();
        categoria.factores.forEach((factor) -> {
            factores.add(new Factor(factor));
        });
        this.operacion = categoria.operacion;
    }

    public Categoria(String name, List<Factor> factores) {
        strid = UUID.randomUUID().toString();
        this.name = name;
        this.factores = factores;
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
