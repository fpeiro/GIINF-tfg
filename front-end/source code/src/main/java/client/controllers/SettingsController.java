/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import static client.ServiceController.get;
import static client.ServiceController.post;
import static client.auxs.Conversor.toModeloDTO;
import static client.auxs.Conversor.toUsuario;
import static client.auxs.Conversor.toUsuarioDTO;
import client.classes.Evaluacion;
import client.classes.Factor;
import client.classes.Modelo;
import client.classes.Usuario;
import static client.controllers.MainController.eModel;
import static client.controllers.MainController.eval;
import static client.controllers.MainController.modelos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static client.controllers.MainController.nicks;
import static client.controllers.MainController.usuarios;
import static client.controllers.MainController.view;
import static client.controllers.MainController.xfactor;
import client.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Felipe
 */
@Controller
public class SettingsController {

    @RequestMapping(value = {"/confview1", "/confview1/"}, method = RequestMethod.GET)
    public String paginaConfiguracion() {
        view = "confview1";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview1", "/confview1/"}, method = RequestMethod.POST)
    public String cambiarModelo(@RequestParam("chInput") int index) throws Exception {
        view = "confview1";
        int i = 0;
        for (Entry<String, Modelo> entry : modelos.entrySet()) {
            if (i == index) {
                entry.getValue().setActivo(true);
                eModel = entry.getValue();
                eval = new Evaluacion();
                post("modelo/activar", toModeloDTO(eModel), Void.class);
            } else {
                entry.getValue().setActivo(false);
            }
            i++;
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview1", "/confview1/"}, params = "tonext", method = RequestMethod.POST)
    public String paginaAdicionDeModelo() {
        view = "confview2";
        eModel = new Modelo();
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview2", "/confview2/"}, method = RequestMethod.GET)
    public String paginaEdicionDeModelo() {
        view = "confview2";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview2", "/confview2/"}, params = "chname", method = RequestMethod.POST)
    public String renombrarModelo(@RequestParam("newname") String newname) throws Exception {
        view = "confview2";
        eModel.setName(newname);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview2", "/confview2/"}, params = "delete", method = RequestMethod.POST)
    public String eliminarModelo() throws Exception {
        view = "confview1";
        post("modelo/eliminar", toModeloDTO(eModel), Void.class);
        modelos.remove(eModel.getStrid());
        eModel = new Modelo();
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview3", "/confview3/"}, method = RequestMethod.GET)
    public String paginaEdicionDeOperaciones() {
        view = "confview3";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview3", "/confview3/"}, method = RequestMethod.POST)
    public String asignarOpciones(@RequestParam("opvalue") String opvalue) {
        eModel.setOperacion(opvalue);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview4", "/confview4/"}, method = RequestMethod.GET)
    public String paginaCategorias() {
        view = "confview4";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview4", "/confview4/"}, params = "add", method = RequestMethod.POST)
    public String anadirCategoria() {
        eModel.addCat();
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview4", "/confview4/"}, params = "rename", method = RequestMethod.POST)
    public String renombrarCategoria(@RequestParam("renInput") String[] indices) {
        int catid = Integer.parseInt(indices[0]);
        String newname = indices[1];
        eModel.renameCat(catid, newname);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview9/{catid}", "/confview9/{catid}/"}, method = RequestMethod.GET)
    public String paginaEdicionDeCategoria(@PathVariable("catid") String catid) {
        view = "confview9";
        eModel.setSelectCat(catid);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview9", "/confview9/"}, method = RequestMethod.POST)
    public String editarCategoria(@RequestParam("catname") String catname, @RequestParam("opvalue") String opvalue) {
        eModel.changeCatName(catname);
        eModel.changeOper(opvalue);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview4", "/confview4/"}, params = "remove", method = RequestMethod.POST)
    public String eliminarCategoria(@RequestParam("removeInput") String indice) {
        int catid = Integer.parseInt(indice);
        eModel.removeCat(catid);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview5", "/confview5/"}, method = RequestMethod.GET)
    public String paginaEdicionDeFactores() {
        view = "confview5";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview5", "/confview5/"}, params = "add", method = RequestMethod.POST)
    public String anadirFactor(@RequestParam("addInput") int catid) {
        eModel.addFac(catid);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview5", "/confview5/"}, params = "edit", method = RequestMethod.POST)
    public String editarFactor(@RequestParam("editInput") String[] indices) {
        int catid = Integer.parseInt(indices[0]);
        int facid = Integer.parseInt(indices[1]);
        xfactor = eModel.getFac(catid, facid);
        view = "confview6";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview5", "/confview5/"}, params = "rename", method = RequestMethod.POST)
    public String renombrarFactor(@RequestParam("renInput") String[] indices) {
        int catid = Integer.parseInt(indices[0]);
        int facid = Integer.parseInt(indices[1]);
        String newname = indices[2];
        eModel.renameFac(catid, facid, newname);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview5", "/confview5/"}, params = "remove", method = RequestMethod.POST)
    public String eliminarFactor(@RequestParam("removeInput") String[] indices) {
        int catid = Integer.parseInt(indices[0]);
        int facid = Integer.parseInt(indices[1]);
        eModel.removeFac(catid, facid);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview2", "/confview2/", "/confview3", "/confview3/", "/confview4", "/confview4/",
        "/confview5", "/confview5/", "/confview6", "/confview6/", "/confview7", "/confview7/",
        "/confview8", "/confview8/", "/confview9", "/confview9/"}, params = "toback", method = RequestMethod.POST)
    public String cancelarEdicionDeModelo() {
        view = "confview1";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview2", "/confview2/", "/confview3", "/confview3/", "/confview4", "/confview4/",
        "/confview5", "/confview5/", "/confview6", "/confview6/", "/confview7", "/confview7/",
        "/confview8", "/confview8/", "/confview9", "/confview9/"}, params = "tonext", method = RequestMethod.POST)
    public String guardarEdicionDeModelo() throws Exception {
        view = "confview1";
        for (Entry<String, Modelo> entry : modelos.entrySet()) {
            entry.getValue().setActivo(false);
        }
        eModel.setActivo(true);
        eval = new Evaluacion();
        post("modelo", toModeloDTO(eModel), Void.class);
        modelos.put(eModel.getStrid(), eModel);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview6", "/confview6/"}, method = RequestMethod.GET)
    public String paginaEdicionDeOpciones() {
        view = "confview6";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview6", "/confview6/"}, params = "add", method = RequestMethod.POST)
    public String anadirOpcion(@ModelAttribute("xfactor") Factor fctr) {
        xfactor.assign(fctr);
        xfactor.addOpt();
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview6", "/confview6/"}, params = "saveed", method = RequestMethod.POST)
    public String asignarOpciones(@ModelAttribute("xfactor") Factor fctr) {
        xfactor.assign(fctr);
        eModel.replaceFac(xfactor);
        view = "confview6";
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview7", "/confview7/"}, method = RequestMethod.GET)
    public String paginaEdicionDeUsuarios() throws Exception {
        view = "confview7";
        UsuarioDTO[] udtos = get("admin/usuarios", UsuarioDTO[].class);
        List<Usuario> usrs = new ArrayList<>();
        for (UsuarioDTO udto : udtos) {
            usrs.add(toUsuario(udto));
        }
        usuarios = usrs;
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview7", "/confview7/"}, method = RequestMethod.POST)
    public String deshabilitarUsuario(@RequestParam("disIndex") int disIndex) throws Exception {
        Usuario usr = usuarios.get(disIndex);
        usr.setDisabled(!usr.isDisabled());
        post("admin/usuario", toUsuarioDTO(usr), Void.class);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview8", "/confview8/"}, method = RequestMethod.GET)
    public String paginaComparticionDeModelo() throws Exception {
        view = "confview8";
        String[] ncks = get("usuarios", String[].class);
        nicks = Arrays.asList(ncks);
        return "redirect:/";
    }

    @RequestMapping(value = {"/confview8", "/confview8/"}, method = RequestMethod.POST)
    public String compartirModelo(@ModelAttribute("eModel") Modelo mod) throws Exception {
        eModel.setUsuarios(mod.getUsuarios());
        return "redirect:/";
    }
}
