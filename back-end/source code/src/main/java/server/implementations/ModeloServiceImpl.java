/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.implementations;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import static server.auxs.Conversor.toModelo;
import static server.auxs.Conversor.toModeloDTO;
import server.classes.Categoria;
import server.classes.Evaluacion;
import server.classes.Modelo;
import server.daos.ModeloDAO;
import server.dtos.ModeloDTO;
import static server.implementations.CategoriaServiceImpl.anadirCategoria;
import static server.implementations.EvaluacionServiceImpl.anadirEvaluacion;
import static server.implementations.EvaluacionServiceImpl.limpiarEvaluaciones;
import server.interfaces.ModeloService;

/**
 *
 * @author Felipe
 */
@Component
@RestController
@RequestMapping
public class ModeloServiceImpl implements ModeloService {

    protected static ModeloDAO modeloDAO;

    @Autowired
    public void setModeloDAO(ModeloDAO modeloDAO) {
        ModeloServiceImpl.modeloDAO = modeloDAO;
    }

    @Override
    @RequestMapping(value = "/modelo", method = POST, consumes = "application/json")
    public void guardarModelo(@RequestBody ModeloDTO mdto, Principal principal) throws Exception {
        Modelo modelo = toModelo(mdto);
        modelo.setAutor(principal.getName());
        for (String usuario : modelo.getUsuarios()) {
            anadirModelo(new Modelo(modelo, usuario));
        }
        modelo.setUsuarios(new ArrayList<>());
        anadirModelo(modelo);
    }

    @Override
    @RequestMapping(value = "/modelo/activar", method = POST, consumes = "application/json")
    public void activarModelo(@RequestBody ModeloDTO mdto, Principal principal) {
        modeloDAO.activarModelo(principal.getName(), mdto.getStrid());
    }

    @Override
    @RequestMapping(value = "/modelo/eliminar", method = POST, consumes = "application/json")
    public void eliminarModelo(@RequestBody ModeloDTO mdto, Principal principal) {
        modeloDAO.eliminarPorStrid(mdto.getStrid());
        limpiarEvaluaciones();
    }

    @Override
    @RequestMapping(value = "/modelos", method = GET, produces = "application/json")
    public ModeloDTO[] recuperarModelos(Principal principal) {
        List<Modelo> modelos = modeloDAO.buscarPorAutor(principal.getName());
        List<ModeloDTO> mdtos = new ArrayList<>();
        modelos.forEach((modelo) -> {
            mdtos.add(toModeloDTO(modelo));
        });
        ModeloDTO[] marr = new ModeloDTO[mdtos.size()];
        return mdtos.toArray(marr);
    }

    public static void anadirModelo(Modelo modelo) throws Exception {
        for (Categoria categoria : modelo.getCategorias()) {
            anadirCategoria(categoria);
        }
        for (Evaluacion evaluacion : new ArrayList<>(modelo.getEvaluaciones().values())) {
            modelo.getEvaluaciones().put(evaluacion.getStrid(), anadirEvaluacion(evaluacion));
        }
        if (modelo.isActivo()) {
            modeloDAO.desactivarModelos(modelo.getAutor());
        }
        modeloDAO.insertarYSustituir(modelo);
        modeloDAO.activarModelo(modelo.getAutor(), modelo.getStrid());
        limpiarEvaluaciones();
    }

    public static Modelo buscarModeloActivo(String autor) {
        return modeloDAO.buscarActivo(autor);
    }

    public static Categoria buscarCategoria(String autor, String categoria) {
        return modeloDAO.buscarCategoria(autor, categoria);
    }
}
