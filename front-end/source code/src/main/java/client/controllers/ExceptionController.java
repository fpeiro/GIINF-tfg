/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import client.exceptions.UserPassNoCoinciden;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import static client.controllers.MainController.error;
import client.exceptions.EvaluacionNoValida;
import client.exceptions.UserCodeNoCoinciden;
import client.exceptions.UsuarioInhabilitado;
import client.exceptions.UsuarioYaExiste;

/**
 *
 * @author Felipe
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String errorDesconocido() {
        error = true;
        return "redirect:/?error=unknown";
    }

    @ExceptionHandler(ResourceAccessException.class)
    public String errorConexion() {
        error = true;
        return "redirect:/?error=lostcon";
    }

    @ExceptionHandler(UserPassNoCoinciden.class)
    public String errorIdentificacion() {
        return "redirect:/?error=notmatch";
    }

    @ExceptionHandler(UsuarioInhabilitado.class)
    public String errorAcceso() {
        return "redirect:/?error=banned";
    }

    @ExceptionHandler(UsuarioYaExiste.class)
    public String errorRegistro() {
        return "redirect:/?error=notav";
    }

    @ExceptionHandler(UserCodeNoCoinciden.class)
    public String errorCambioContrasena() {
        return "redirect:/?error=notmatch2";
    }

    @ExceptionHandler(EvaluacionNoValida.class)
    public String errorEvaluacion() {
        error = true;
        return "redirect:/?error=inveval";
    }
}
