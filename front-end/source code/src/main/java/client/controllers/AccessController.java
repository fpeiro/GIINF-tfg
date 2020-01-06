/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import static client.ServiceController.connect;
import static client.ServiceController.get;
import static client.ServiceController.post;
import static client.auxs.Conversor.toModelo;
import static client.auxs.Conversor.toUsuario;
import client.classes.Evaluacion;
import client.classes.Modelo;
import client.classes.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static client.controllers.MainController.conectado;
import static client.controllers.MainController.eModel;
import static client.controllers.MainController.eval;
import static client.controllers.MainController.modelos;
import static client.controllers.MainController.reset;
import static client.controllers.MainController.usuario;
import static client.controllers.MainController.view;
import client.dtos.ModeloDTO;
import client.dtos.UsuarioDTO;
import client.exceptions.UserCodeNoCoinciden;
import client.exceptions.UserPassNoCoinciden;
import client.exceptions.UsuarioInhabilitado;
import client.exceptions.UsuarioYaExiste;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author Felipe
 */
@Controller
public class AccessController {

    @RequestMapping(value = {"/login", "/login/"}, method = RequestMethod.GET)
    public String paginaIdentificacion() {
        view = "login";
        return "redirect:/";
    }

    @RequestMapping(value = {"/login", "/login/"}, method = RequestMethod.POST)
    public String identificarse(@ModelAttribute("udto") Usuario udto) throws Exception {
        connect(udto.getNick(), udto.getPassword());
        try {
            usuario = toUsuario(get("usuario", UsuarioDTO.class));
        } catch (HttpClientErrorException e) {
            throw new UserPassNoCoinciden();
        } catch (HttpServerErrorException e) {
            throw new UsuarioInhabilitado();
        }
        ModeloDTO[] marr = get("modelos", ModeloDTO[].class);
        if (marr.length > 0) {
            modelos.clear();
        }
        for (ModeloDTO mdto : marr) {
            Modelo modelo = toModelo(mdto);
            modelos.put(modelo.getStrid(), modelo);
            if (modelo.isActivo()) {
                eModel = modelo;
                eval = new Evaluacion();
            }
        }
        conectado = true;
        view = "evalview1";
        return "redirect:/";
    }

    @RequestMapping(value = {"/signin", "/signin/"}, method = RequestMethod.GET)
    public String paginaRegistro() {
        view = "signin";
        return "redirect:/";
    }

    @RequestMapping(value = {"/signin", "/signin/"}, method = RequestMethod.POST)
    public String registrarse(@ModelAttribute("udto") Usuario udto) throws Exception {
        try {
            post("usuario", udto, Void.class);
        } catch (HttpServerErrorException e) {
            throw new UsuarioYaExiste();
        }
        view = "login";
        return "redirect:/";
    }

    @RequestMapping(value = {"/recover", "/recover/"}, method = RequestMethod.GET)
    public String paginaCambioContrasena() {
        view = "recover";
        return "redirect:/";
    }

    @RequestMapping(value = {"/recover", "/recover/"}, method = RequestMethod.POST)
    public String cambiarContrasena(@ModelAttribute("udto") Usuario udto) throws Exception {
        try {
            post("usuario/recuperacion", udto, Void.class);
        } catch (HttpServerErrorException e) {
            throw new UserCodeNoCoinciden();
        }
        view = "login";
        return "redirect:/";
    }

    @RequestMapping(value = {"/logout", "/logout/"}, method = RequestMethod.GET)
    public String desconectarse() throws Exception {
        connect(null, null);
        reset();
        return "redirect:/";
    }
}
