/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.implementations;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import static server.auxs.Conversor.toUsuarioDTO;
import server.classes.Usuario;
import server.daos.UsuarioDAO;
import server.dtos.UsuarioDTO;
import server.exceptions.UserCodeNoCoinciden;
import server.exceptions.UserPassNoCoinciden;
import server.exceptions.UsuarioInhabilitado;
import server.exceptions.UsuarioNoExiste;
import server.exceptions.UsuarioYaExiste;
import server.interfaces.UsuarioService;

/**
 *
 * @author Felipe
 */
@Component
@RestController
@RequestMapping
public class UsuarioServiceImpl implements UsuarioService {

    protected static UsuarioDAO usuarioDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        UsuarioServiceImpl.usuarioDAO = usuarioDAO;
    }

    @Override
    @RequestMapping(value = "/usuario", method = GET, produces = "application/json")
    public UsuarioDTO identificarse(Principal usuario) throws Exception {
        Usuario us = getUsuario(usuario.getName());
        if (us != null && us.isDisabled() == true) {
            throw new UsuarioInhabilitado();
        }
        try {
            User user = (User) UsernamePasswordAuthenticationToken.class.cast(usuario).getPrincipal();
            Set<String> roles = user.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
            if (roles.contains("ROLE_ADMIN")) {
                return new UsuarioDTO(user.getUsername(), true);
            }
            return new UsuarioDTO(user.getUsername());
        } catch (Exception e) {
            throw new UserPassNoCoinciden();
        }
    }

    @Override
    @RequestMapping(value = "/usuario", method = POST, consumes = "application/json")
    public void registrarse(@RequestBody UsuarioDTO udto) throws Exception {
        if (getUsuario(udto.getNick()) != null) {
            throw new UsuarioYaExiste();
        }
        Usuario usuario = new Usuario();
        usuario.setNick(udto.getNick());
        usuario.setPassword(passwordEncoder.encode(udto.getPassword()));
        usuario.setCodigo(UUID.randomUUID().toString());
        usuarioDAO.insertar(usuario);
    }

    @Override
    @RequestMapping(value = "/usuario/recuperacion", method = POST, consumes = "application/json")
    public void cambiarContrasena(@RequestBody UsuarioDTO udto) throws Exception {
        Usuario usuario = getUsuario(udto.getNick());
        if (usuario == null) {
            throw new UsuarioNoExiste();
        }
        if (usuario.getCodigo().equals(udto.getCodigo())) {
            usuario.setPassword(passwordEncoder.encode(udto.getPassword()));
            usuario.setCodigo(UUID.randomUUID().toString());
            usuarioDAO.actualizar(usuario);
        } else {
            throw new UserCodeNoCoinciden();
        }
    }

    @Override
    @RequestMapping(value = "/admin/usuario", method = POST, produces = "application/json")
    public void deshabilitarUsuario(@RequestBody UsuarioDTO udto) throws Exception {
        Usuario usuario = getUsuario(udto.getNick());
        if (usuario == null) {
            throw new UsuarioNoExiste();
        }
        usuario.setDisabled(udto.isDisabled());
        usuarioDAO.actualizar(usuario);
    }

    @Override
    @RequestMapping(value = "/admin/usuarios", method = GET, produces = "application/json")
    public List<UsuarioDTO> recuperarUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        usuarioDAO.buscarTodos().forEach((usuario) -> {
            lista.add(toUsuarioDTO(usuario));
        });
        return lista;
    }

    @Override
    @RequestMapping(value = "/usuarios", method = GET, produces = "application/json")
    public List<String> recuperarNicks() {
        List<String> lista = new ArrayList<>();
        usuarioDAO.buscarTodos().forEach((usuario) -> {
            lista.add(usuario.getNick());
        });
        return lista;
    }

    public static Usuario getUsuario(String nick) {
        return usuarioDAO.buscar(nick);
    }
}
