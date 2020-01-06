/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.classes;

import static client.controllers.MainController.eModel;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.ocpsoft.prettytime.PrettyTime;

/**
 *
 * @author Felipe
 */
public class Evaluacion {

    private String strid, name, removeElmnt, selectCat;
    private Date time;
    private Map<String, Map<String, Object>> factors;
    private List<Float> resParciales;
    private float puntuacion;

    public Evaluacion() {
        strid = UUID.randomUUID().toString();
        name = "";
        time = new Date();
        removeElmnt = "";
        selectCat = "0";
        List<Categoria> categorias = eModel.getCategorias();
        factors = assignFactors(categorias);
        resParciales = new ArrayList<>();
    }

    private Map<String, Map<String, Object>> assignFactors(List<Categoria> categorias) {
        Map<String, Map<String, Object>> outmap = new LinkedHashMap<>();
        for (Categoria categoria : categorias) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Factor factor : categoria.getFactores()) {
                if (null != factor.getDataType()) {
                    switch (factor.getDataType()) {
                        case BOOLEAN:
                            map.put(factor.getName(), false);
                            break;
                        case PERCENTAGE:
                            map.put(factor.getName(), (double) factor.getScores().get(0));
                            break;
                        case OPTION:
                            map.put(factor.getName(), new SimpleEntry(factor.getValores(), factor.getValores().get(0)));
                            break;
                        case NUMBER:
                            map.put(factor.getName(), Math.round(factor.getScores().get(0)));
                            break;
                        default:
                            break;
                    }
                }
            }
            outmap.put(categoria.getName(), map);
        }
        return outmap;
    }

    public String getPrettyTime(Locale locale) {
        PrettyTime p = new PrettyTime(locale);
        return p.format(time);
    }

    public void recoverFactors(Evaluacion edto) {
        convertFactors(edto);
    }

    public void backupFactors(Evaluacion edto) {
        edto.convertFactors(this);
        removeElmnt = edto.removeElmnt;
        setSelectCat(edto.getSelectCat());
    }

    public void convertFactors(Evaluacion edto) {
        for (String j : factors.keySet()) {
            for (String k : factors.get(j).keySet()) {
                if (edto.factors.get(j) != null && edto.factors.get(j).get(k) != null) {
                    if (factors.get(j).get(k) instanceof Integer) {
                        Object get = edto.factors.get(j).get(k);
                        factors.get(j).put(k, Integer.valueOf(String.valueOf(get)));
                    } else if (factors.get(j).get(k) instanceof Double) {
                        Object get = edto.factors.get(j).get(k);
                        factors.get(j).put(k, Double.valueOf(String.valueOf(get)));
                    } else if (factors.get(j).get(k) instanceof SimpleEntry
                            && String.valueOf(edto.factors.get(j).get(k)).split("=").length > 1) {
                        Object skey = ((SimpleEntry) factors.get(j).get(k)).getKey();
                        Object svalue = String.valueOf(edto.factors.get(j).get(k)).split("=")[1];
                        factors.get(j).put(k, new SimpleEntry(skey, svalue));
                    } else if (factors.get(j).get(k) instanceof Boolean) {
                        Boolean get = Boolean.valueOf(String.valueOf(edto.factors.get(j).get(k)));
                        factors.get(j).put(k, get);
                    }
                }
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
     * @return the removeElmnt
     */
    public String getRemoveElmnt() {
        return removeElmnt;
    }

    /**
     * @param removeElmnt the removeElmnt to set
     */
    public void setRemoveElmnt(String removeElmnt) {
        this.removeElmnt = removeElmnt;
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
     * @return the selectCat
     */
    public String getSelectCat() {
        return selectCat;
    }

    /**
     * @param selectCat the selectCat to set
     */
    public void setSelectCat(String selectCat) {
        this.selectCat = selectCat;
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
