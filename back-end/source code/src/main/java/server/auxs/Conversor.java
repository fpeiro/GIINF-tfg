/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.auxs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import server.classes.Categoria;
import server.classes.Evaluacion;
import server.classes.Factor;
import server.classes.Modelo;
import server.classes.Modelo.TipoDeDatos;
import server.classes.Usuario;
import server.dtos.CategoriaDTO;
import server.dtos.EvaluacionDTO;
import server.dtos.FactorDTO;
import server.dtos.ModeloDTO;
import server.dtos.ModeloDTO.TipoDeDatosDTO;
import server.dtos.UsuarioDTO;

/**
 *
 * @author Felipe
 */
public final class Conversor {

    public static Modelo toModelo(ModeloDTO mdto) {
        Modelo modelo = new Modelo();
        modelo.setStrid(mdto.getStrid());
        modelo.setOperacion(mdto.getOperacion());
        Map<String, Evaluacion> evaluaciones = new LinkedHashMap<>();
        new ArrayList<>(mdto.getEvaluaciones().values()).forEach((edto) -> {
            evaluaciones.put(edto.getStrid(), toEvaluacion(edto));
        });
        modelo.setEvaluaciones(evaluaciones);
        List<Categoria> categorias = new ArrayList<>();
        mdto.getCategorias().forEach((cdto) -> {
            categorias.add(toCategoria(cdto));
        });
        modelo.setCategorias(categorias);
        modelo.setAutor("anonimo");
        modelo.setName(mdto.getName());
        modelo.setActivo(mdto.isActivo());
        modelo.setUsuarios(mdto.getUsuarios());
        return modelo;
    }

    public static Evaluacion toEvaluacion(EvaluacionDTO edto) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setStrid(edto.getStrid());
        evaluacion.setName(edto.getName());
        Map<String, Object> interms = new LinkedHashMap<>();
        edto.getFactors().entrySet().forEach((entry) -> {
            interms.put(entry.getKey(), entry.getValue());
        });
        evaluacion.setFactors(interms);
        evaluacion.setResParciales(edto.getResParciales());
        evaluacion.setPuntuacion(edto.getPuntuacion());
        evaluacion.setAutor("anonimo");
        evaluacion.setTime(edto.getTime());
        return evaluacion;
    }

    public static Categoria toCategoria(CategoriaDTO cdto) {
        Categoria categoria = new Categoria();
        categoria.setStrid(cdto.getStrid());
        categoria.setName(cdto.getName());
        categoria.setFactores(toFactores(cdto.getFactores()));
        categoria.setOperacion(cdto.getOperacion());
        return categoria;
    }

    public static List<Factor> toFactores(List<FactorDTO> lfdto) {
        List<Factor> listaDeFactores = new ArrayList<>();
        for (FactorDTO fdto : lfdto) {
            Factor factor = new Factor();
            factor.setStrid(fdto.getStrid());
            factor.setValores(fdto.getValores());
            factor.setScores(fdto.getScores());
            factor.setName(fdto.getName());
            if (fdto.getDataType() != null) {
                factor.setDataType(toTipoDeDatos(fdto.getDataType()));
            }
            listaDeFactores.add(factor);
        }
        return listaDeFactores;
    }

    public static Usuario toUsuario(UsuarioDTO udto) {
        Usuario usuario = new Usuario();
        usuario.setAdmin(udto.isAdmin());
        usuario.setDisabled(udto.isDisabled());
        usuario.setCodigo(udto.getCodigo());
        usuario.setNick(udto.getNick());
        usuario.setPassword(udto.getPassword());
        usuario.setEmail(udto.getEmail());
        return usuario;
    }

    public static TipoDeDatos toTipoDeDatos(TipoDeDatosDTO tdto) {
        return TipoDeDatos.valueOf(tdto.name());
    }

    public static ModeloDTO toModeloDTO(Modelo modelo) {
        ModeloDTO mdto = new ModeloDTO();
        mdto.setStrid(modelo.getStrid());
        mdto.setOperacion(modelo.getOperacion());
        Map<String, EvaluacionDTO> edtos = new LinkedHashMap<>();
        new ArrayList<>(modelo.getEvaluaciones().values()).forEach((evaluacion) -> {
            edtos.put(evaluacion.getStrid(), toEvaluacionDTO(evaluacion));
        });
        mdto.setEvaluaciones(edtos);
        List<CategoriaDTO> cdtos = new ArrayList<>();
        modelo.getCategorias().forEach((categoria) -> {
            cdtos.add(toCategoriaDTO(categoria));
        });
        mdto.setCategorias(cdtos);
        mdto.setName(modelo.getName());
        mdto.setActivo(modelo.isActivo());
        mdto.setUsuarios(modelo.getUsuarios());
        return mdto;
    }

    public static EvaluacionDTO toEvaluacionDTO(Evaluacion evaluacion) {
        EvaluacionDTO edto = new EvaluacionDTO();
        edto.setStrid(evaluacion.getStrid());
        edto.setName(evaluacion.getName());
        Map<String, Map<String, Object>> interms = new LinkedHashMap<>();
        evaluacion.getFactors().entrySet().forEach((entry) -> {
            interms.put(entry.getKey(), (Map<String, Object>) entry.getValue());
        });
        edto.setFactors(interms);
        edto.setResParciales(evaluacion.getResParciales());
        edto.setPuntuacion(evaluacion.getPuntuacion());
        edto.setTime(evaluacion.getTime());
        return edto;
    }

    public static CategoriaDTO toCategoriaDTO(Categoria categoria) {
        CategoriaDTO cdto = new CategoriaDTO();
        cdto.setStrid(categoria.getStrid());
        cdto.setName(categoria.getName());
        cdto.setFactores(toFactoresDTO(categoria.getFactores()));
        cdto.setOperacion(categoria.getOperacion());
        return cdto;
    }

    public static List<FactorDTO> toFactoresDTO(List<Factor> listaDeFactores) {
        List<FactorDTO> lfdto = new ArrayList<>();
        for (Factor factor : listaDeFactores) {
            FactorDTO fdto = new FactorDTO();
            fdto.setName(factor.getName());
            fdto.setStrid(factor.getStrid());
            fdto.setValores(factor.getValores());
            fdto.setScores(factor.getScores());
            if (factor.getDataType() != null) {
                fdto.setDataType(toTipoDeDatosDTO(factor.getDataType()));
            }
            lfdto.add(fdto);
        }
        return lfdto;
    }

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO udto = new UsuarioDTO();
        udto.setAdmin(usuario.isAdmin());
        udto.setDisabled(usuario.isDisabled());
        udto.setCodigo(usuario.getCodigo());
        udto.setNick(usuario.getNick());
        udto.setPassword(usuario.getPassword());
        udto.setEmail(usuario.getEmail());
        return udto;
    }

    public static TipoDeDatosDTO toTipoDeDatosDTO(TipoDeDatos tipoDeDatos) {
        return TipoDeDatosDTO.valueOf(tipoDeDatos.name());
    }
}
