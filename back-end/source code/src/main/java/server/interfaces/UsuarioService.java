/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import java.security.Principal;
import java.util.List;
import server.dtos.UsuarioDTO;

/**
 *
 * @author Felipe
 */
public interface UsuarioService {

    public UsuarioDTO identificarse(Principal usuario) throws Exception;

    public void registrarse(UsuarioDTO udto) throws Exception;

    public void cambiarContrasena(UsuarioDTO udto) throws Exception;

    public void deshabilitarUsuario(UsuarioDTO udto) throws Exception;

    public List<UsuarioDTO> recuperarUsuarios();

    public List<String> recuperarNicks();
}
