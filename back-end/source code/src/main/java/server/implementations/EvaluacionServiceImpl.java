/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.implementations;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import static server.auxs.Conversor.toEvaluacion;
import static server.auxs.Conversor.toEvaluacionDTO;
import server.classes.Categoria;
import server.classes.Evaluacion;
import server.classes.Factor;
import server.classes.Modelo;
import server.daos.EvaluacionDAO;
import server.dtos.EvaluacionDTO;
import server.exceptions.OperacionNoValida;
import static server.implementations.ModeloServiceImpl.anadirModelo;
import static server.implementations.ModeloServiceImpl.buscarModeloActivo;
import server.interfaces.EvaluacionService;

/**
 *
 * @author Felipe
 */
@Component
@RestController
@RequestMapping
public class EvaluacionServiceImpl implements EvaluacionService {

    protected static EvaluacionDAO evaluacionDAO;
    ScriptEngineManager factory;
    ScriptEngine engine;

    public EvaluacionServiceImpl() {
        factory = new ScriptEngineManager();
        engine = factory.getEngineByName("JavaScript");
    }

    @Autowired
    public void setEvaluacionDAO(EvaluacionDAO evaluacionDAO) {
        EvaluacionServiceImpl.evaluacionDAO = evaluacionDAO;
    }

    public static Evaluacion anadirEvaluacion(Evaluacion evaluacion) throws Exception {
        Evaluacion ev = evaluacionDAO.buscarPorStrid(evaluacion.getStrid());
        if (ev == null) {
            evaluacionDAO.insertarYSustituir(evaluacion);
            return evaluacion;
        } else {
            ev.set(evaluacion);
            evaluacionDAO.actualizar(ev);
            return ev;
        }
    }

    public static Evaluacion buscarEvaluacion(String strid) throws Exception {
        return evaluacionDAO.buscarPorStrid(strid);
    }

    public static void limpiarEvaluaciones() {
        evaluacionDAO.limpiar();
    }

    @Override
    @RequestMapping(value = "/evaluacion", method = POST, consumes = "application/json", produces = "application/json")
    public EvaluacionDTO calcularEvaluacion(@RequestBody EvaluacionDTO edto, Principal principal) throws Exception {
        Evaluacion evaluacion = toEvaluacion(edto);
        evaluacion.setAutor(principal.getName());
        Modelo modelo = buscarModeloActivo(evaluacion.getAutor());

        List<Float> resParciales = new ArrayList<>();
        int index = 0;
        try {
            for (Categoria categoria : modelo.getCategorias()) {
                if (categoria.getOperacion() != null && !"".equals(categoria.getOperacion())) {
                    String catoper = parseOperation(categoria.getOperacion());
                    definirVariables(evaluacion, modelo, catoper);
                    float result = Float.valueOf(String.valueOf(engine.eval(catoper)));
                    engine.eval("var cat_" + index + " = " + result);
                    resParciales.add(round(result));
                } else {
                    engine.eval("var cat_" + index + " = 0");
                    resParciales.add((float) 0.0);
                }
                index++;
            }
        } catch (NumberFormatException | ScriptException e) {
            throw new OperacionNoValida();
        }
        evaluacion.setResParciales(resParciales);

        try {
        if (modelo.getOperacion() != null && !"".equals(modelo.getOperacion())) {
            String modoper = parseOperation(modelo.getOperacion());
            definirVariables(evaluacion, modelo, modoper);
            float punt = Float.valueOf(String.valueOf(engine.eval(modoper)));
            evaluacion.setPuntuacion(round(punt));
        } else {
            evaluacion.setPuntuacion((float) 0.0);
        }
        } catch (NumberFormatException | ScriptException e) {
            throw new OperacionNoValida();
        }

        anadirEvaluacion(evaluacion);
        modelo.getEvaluaciones().put(evaluacion.getStrid(), evaluacion);
        anadirModelo(modelo);
        return toEvaluacionDTO(evaluacion);
    }

    public void definirVariables(Evaluacion evaluacion, Modelo modelo, String operation) throws ScriptException {
        for (String oper : operation.split(" ")) {
            if (oper.startsWith("fac")) {
                String[] indices = oper.split("_");
                String catname = modelo.getCategorias().get(Integer.valueOf(indices[1])).getName();
                Factor factor = modelo.getCategorias().get(Integer.valueOf(indices[1])).getFactores().get(Integer.valueOf(indices[2]));
                Object value = ((Map<String, Object>) evaluacion.getFactors().get(catname)).get(factor.getName());
                if (value instanceof Integer) {
                    engine.eval("var " + oper + " = " + value);
                } else if (value instanceof Double) {
                    engine.eval("var " + oper + " = " + value);
                } else if (value instanceof LinkedHashMap) {
                    String get = (String) new ArrayList<>(((LinkedHashMap) value).values()).get(0);
                    int valid = factor.getValores().indexOf(get);
                    engine.eval("var " + oper + " = " + factor.getScores().get(valid));
                } else if (value instanceof Boolean) {
                    if (!(Boolean) value) {
                        engine.eval("var " + oper + " = " + factor.getScores().get(0));
                    } else {
                        engine.eval("var " + oper + " = " + factor.getScores().get(1));
                    }
                }
            }
        }
    }

    public String parseOperation(String operation) {
        return operation.replace("×", "*").replace("÷", "/");
    }

    public float round(float number) {
        return (float) (Math.round(number * 100.0) / 100.0);
    }
}
