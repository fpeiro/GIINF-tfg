/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import java.security.Principal;
import server.dtos.ModeloDTO;

/**
 *
 * @author Felipe
 */
public interface ModeloService {

    public void guardarModelo(ModeloDTO mdto, Principal principal) throws Exception;
    
    public void activarModelo(ModeloDTO mdto, Principal principal);
    
    public void eliminarModelo(ModeloDTO mdto, Principal principal);
    
    public ModeloDTO[] recuperarModelos(Principal principal);
}
