/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import client.CustomMessageSource;
import client.classes.Evaluacion;
import client.classes.Factor;
import client.classes.Modelo;
import client.classes.Usuario;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Felipe
 */
@Controller
public class MainController {

    @Autowired
    public CustomMessageSource messageSource;
    public static String view;
    public static Modelo eModel;
    public static Evaluacion eval;
    public static Factor xfactor;
    public static Usuario usuario;
    public static boolean conectado, error;
    public static Map<String, Modelo> modelos;
    public static List<Usuario> usuarios;
    public static List<String> nicks;

    public MainController() {
        reset();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String cargarVista(Model model) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);
        model.addAttribute("view", view);
        model.addAttribute("eModel", eModel);
        model.addAttribute("eval", eval);
        model.addAttribute("xfactor", xfactor);
        model.addAttribute("usuario", usuario);
        model.addAttribute("conectado", conectado);
        model.addAttribute("modelos", modelos);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("nicks", nicks);
        model.addAttribute("udto", new Usuario());
        Set<Map.Entry<Object, Object>> messages = messageSource.getMessages(currentLocale);
        for (Map.Entry<Object, Object> message : messages) {
            if (meetsview((String) message.getKey(), view)) {
                model.addAttribute(untilDot((String) message.getKey()), message.getValue());
            }
        }
        if (error == true) {
            error = false;
            return "error";
        }
        if ("login".equals(view) || "signin".equals(view) || "recover".equals(view)) {
            return "access";
        }
        if (conectado == false) {
            view = "login";
            return "redirect:/";
        }
        return "index";
    }

    public static void reset() {
        view = "login";
        eModel = new Modelo();
        eval = new Evaluacion();
        xfactor = new Factor();
        usuario = new Usuario();
        conectado = false;
        modelos = new LinkedHashMap<>();
        usuarios = new ArrayList<>();
        nicks = new ArrayList<>();
    }

    public String untilDot(String string) {
        int iend = string.indexOf(".");
        return iend != -1 ? string.substring(0, iend) : string;
    }

    public boolean meetsview(String string, String view) {
        return string.endsWith(view) || !string.contains(".");
    }
}
