/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.classes;

import static client.controllers.MainController.eval;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Felipe
 */
public class Modelo {

    public enum TipoDeDatos {
        BOOLEAN, PERCENTAGE, OPTION, NUMBER
    }
    private Map<String, Evaluacion> evaluaciones;
    private String operacion;
    private List<Categoria> categorias;
    private List<String> usuarios;
    private Evaluacion actual;
    private boolean activo;
    private String strid, name, selectEv, selectName, selectCat;

    public Modelo() {
        evaluaciones = new LinkedHashMap<>();
        operacion = "";
        categorias = new ArrayList<>();
        usuarios = new ArrayList<>();
        activo = true;
        strid = UUID.randomUUID().toString();
        name = "";
        selectEv = "-1";
        selectName = "";
    }

//    private List<Categoria> loadCategories() {
//        String categories[] = {"Protección", "Carga del sistema", "Utilidad"};
//        Factor factors[][] = {{new Factor("Protección contra ataques de día 0", BOOLEAN),
//            new Factor("Detección de malware extendido", BOOLEAN)},
//        {new Factor("Ralentización al acceder a páginas web", BOOLEAN),
//            new Factor("Ralentización al descargar programas", BOOLEAN),
//            new Factor("Ralentización al ejecutar software", BOOLEAN),
//            new Factor("Ralentización al instalar programas", BOOLEAN),
//            new Factor("Ralentización al copiar archivos", BOOLEAN)},
//        {new Factor("Falsas alarmas al visitar un sitio web", BOOLEAN),
//            new Factor("Falsos negativos de malware", BOOLEAN),
//            new Factor("Avisos erróneos en software seguro", BOOLEAN),
//            new Factor("Bloqueos erróneos en software seguro", BOOLEAN)}};
//
//        List<Categoria> list = new ArrayList<>();
//        int counter = 0;
//        for (Factor vfactors[] : factors) {
//            list.add(new Categoria(categories[counter], new ArrayList<>(Arrays.asList(vfactors))));
//            counter++;
//        }
//        return list;
//    }
    public String[] transOperation() {
        String[] split = new String[0];
        if (!"".equals(getOperacion()) && getOperacion() != null) {
            split = getOperacion().split(" ");
        }
        return split;
    }

    public String[] transOper() {
        String[] split = new String[0];
        if (!"".equals(getOper()) && getOper() != null) {
            split = getOper().split(" ");
        }
        return split;
    }

    public String getOper() {
        return getCategorias().get(Integer.valueOf(selectCat)).getOperacion();
    }

    public String getCatName() {
        return getCategorias().get(Integer.valueOf(selectCat)).getName();
    }


    public List<Factor> getFactors() {
        return getCategorias().get(Integer.valueOf(selectCat)).getFactores();
    }

    public void changeOper(String opvalue) {
        getCategorias().get(Integer.valueOf(selectCat)).setOperacion(opvalue);
    }

    public void changeCatName(String catname) {
        getCategorias().get(Integer.valueOf(selectCat)).setName(catname);
    }

    public void addEv(Evaluacion edto) {
        getEvaluaciones().put(edto.getStrid(), edto);
        actual = edto;
        selectName = actual.getName();
        selectEv = Integer.toString(getEvaluaciones().size() - 1);
    }

    public void addCat() {
        getCategorias().add(new Categoria());
    }

    public void addFac(int catid) {
        getCategorias().get(catid).getFactores().add(new Factor());
    }

    public void replaceFac(Factor fctr) {
        for (Categoria categoria : getCategorias()) {
            for (Factor factor : categoria.getFactores()) {
                if (factor.getStrid().equals(fctr.getStrid())) {
                    factor = fctr;
                }
            }
        }
    }

    public void assign(Modelo ldto) {
        if (ldto != null) {
            selectEv = ldto.selectEv;
            actual = (new ArrayList<>(getEvaluaciones().values())).get(Integer.parseInt(selectEv));
            selectName = actual.getName();
        } else {
            selectEv = "-1";
            actual = null;
            selectName = "";
        }
    }

    public void renameEv(Modelo ldto) {
        assign(ldto);
        actual.setName(ldto.getSelectName());
        selectName = ldto.getSelectName();
        evaluaciones.put(actual.getStrid(), actual);
    }

    public void deleteEv(Modelo ldto) {
        assign(ldto);
        evaluaciones.remove(actual.getStrid());
        assign(null);
    }

    public void renameCat(int catid, String newname) {
        getCategorias().get(catid).setName(newname);
    }

    public void removeCat(int catid) {
        getCategorias().remove(catid);
    }

    public void renameFac(int catid, int facid, String newname) {
        getCategorias().get(catid).getFactores().get(facid).setName(newname);
    }

    public Factor getFac(int catid, int facid) {
        return getCategorias().get(catid).getFactores().get(facid);
    }

    public void removeFac(int catid, int facid) {
        getCategorias().get(catid).getFactores().remove(facid);
    }

    public Evaluacion retrieve() {
        if (!"-1".equals(selectEv)) {
            return (new ArrayList<>(getEvaluaciones().values())).get(Integer.parseInt(selectEv));
        } else {
            return eval;
        }
    }

    /**
     * @return the actual
     */
    public Evaluacion getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(Evaluacion actual) {
        this.actual = actual;
    }

    /**
     * @return the selectEv
     */
    public String getSelectEv() {
        return selectEv;
    }

    /**
     * @param selectEv the selectEv to set
     */
    public void setSelectEv(String selectEv) {
        this.selectEv = selectEv;
    }

    /**
     * @return the selectName
     */
    public String getSelectName() {
        return selectName;
    }

    /**
     * @param selectName the selectName to set
     */
    public void setSelectName(String selectName) {
        this.selectName = selectName;
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
}
