/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import java.security.Principal;
import server.dtos.EvaluacionDTO;

/**
 *
 * @author Felipe
 */
public interface EvaluacionService {

    public EvaluacionDTO calcularEvaluacion(EvaluacionDTO edto, Principal principal) throws Exception;
}
