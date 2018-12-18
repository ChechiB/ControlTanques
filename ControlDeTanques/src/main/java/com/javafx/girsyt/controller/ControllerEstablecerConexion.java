package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.javafx.girsyt.entidad.DTOMensaje;
import com.javafx.girsyt.expert.ExpertEstablecerConexion;
import com.javafx.girsyt.fabrica.FabricaExpertos;

import java.io.IOException;

public class ControllerEstablecerConexion {

    private ExpertEstablecerConexion expertEstablecerConexion;
    private ControladorTanqueUI controladorTanqueUI;

    public String establecerConexion() throws IOException {
        expertEstablecerConexion = (ExpertEstablecerConexion) FabricaExpertos.getinstancia().crearExperto("EstablecerConexion");
       return expertEstablecerConexion.establecerConexion();


    }

    public DatosTanqueGuiDTO actualizarGUI() throws IOException {
        System.out.println("En Controlador establecer Conexion");
        return expertEstablecerConexion.actualizarGUI();
    }

    public void recibirMensajes() {
        expertEstablecerConexion.recibirMensajes();
    }
}
