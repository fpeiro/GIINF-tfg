/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.classes.Usuario;
import static server.implementations.UsuarioServiceImpl.getUsuario;

/**
 *
 * @author Felipe
 */
@Service
public class ServicioDeUsuarios implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        Usuario usuario = getUsuario(nick);
        if (usuario == null) {
            throw new UsernameNotFoundException("");
        }

        UserBuilder userBuilder = User.withUsername(usuario.getNick()).password(usuario.getPassword());
        if (usuario.isAdmin()) {
            userBuilder.roles("ADMIN");
        }
        return userBuilder.roles("").build();
    }

}
