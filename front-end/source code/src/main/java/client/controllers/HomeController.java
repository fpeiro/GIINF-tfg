/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import static client.ServiceController.post;
import static client.auxs.Conversor.toEvaluacion;
import static client.auxs.Conversor.toEvaluacionDTO;
import static client.auxs.Conversor.toModeloDTO;
import client.classes.Evaluacion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import client.classes.Modelo;
import static client.controllers.MainController.eModel;
import static client.controllers.MainController.eval;
import static client.controllers.MainController.modelos;
import static client.controllers.MainController.view;
import client.dtos.EvaluacionDTO;
import client.exceptions.EvaluacionNoValida;
import java.util.Map.Entry;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author Felipe
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, method = RequestMethod.GET)
    public String paginaInicio() throws Exception {
        view = "evalview1";
        if (!modelos.containsKey(eModel.getStrid())) {
            int i = 0;
            for (Entry<String, Modelo> entry : modelos.entrySet()) {
                if (i == 0) {
                    entry.getValue().setActivo(true);
                    eModel = entry.getValue();
                    eval = new Evaluacion();
                    post("modelo/activar", toModeloDTO(eModel), Void.class);
                } else {
                    entry.getValue().setActivo(false);
                }
                i++;
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, method = RequestMethod.POST)
    public String verEvaluacion(@ModelAttribute("eModel") Modelo ldto) {
        view = "evalview1";
        eModel.assign(ldto);
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, params = "tonext", method = RequestMethod.POST)
    public String crearEvaluacion() {
        view = "evalview2";
        eModel.assign(null);
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, params = "toback", method = RequestMethod.POST)
    public String editarEvaluacion() {
        view = "evalview2";
        eval = eModel.retrieve();
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, params = "chname", method = RequestMethod.POST)
    public String renombrarEvaluacion(@ModelAttribute("eModel") Modelo ldto) throws Exception {
        view = "evalview1";
        eModel.renameEv(ldto);
        post("modelo", toModeloDTO(eModel), Void.class);
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview1", "/evalview1/"}, params = "delete", method = RequestMethod.POST)
    public String eliminarEvaluacion(@ModelAttribute("eModel") Modelo ldto) throws Exception {
        view = "evalview1";
        eModel.deleteEv(ldto);
        post("modelo", toModeloDTO(eModel), Void.class);
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview2", "/evalview2/"}, method = RequestMethod.GET)
    public String paginaAsignacionDeCriterios() {
        view = "evalview2";
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview2", "/evalview2/"}, method = RequestMethod.POST)
    public String asignarCriterios_estatico(@ModelAttribute("eval") Evaluacion edto) {
        view = "evalview2";
        eval.recoverFactors(edto);
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview2", "/evalview2/"}, params = "tonext", method = RequestMethod.POST)
    public String asignarCriterios(@ModelAttribute("eval") Evaluacion edto) throws Exception {
        eval.recoverFactors(edto);
        try {
            Evaluacion res = toEvaluacion(post("evaluacion", toEvaluacionDTO(eval), EvaluacionDTO.class));
            eModel.addEv(res);
        } catch (HttpServerErrorException e) {
            throw new EvaluacionNoValida();
        }
        view = "evalview1";
        eval = new Evaluacion();
        return "redirect:/";
    }

    @RequestMapping(value = {"/evalview2", "/evalview2/"}, params = "toback", method = RequestMethod.POST)
    public String cancelarAsignacionDeCriterios(@ModelAttribute("eval") Evaluacion edto) {
        view = "evalview1";
        eval.recoverFactors(edto);
        eval = eModel.retrieve();
        return "redirect:/";
    }
}
