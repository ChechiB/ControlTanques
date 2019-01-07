package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.controller.ControllerEstablecerConexion;
import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;

import java.io.IOException;

public class ExpertEstablecerConexion {
    private AdaptadorUDP adaptadorUDP;
    private ControllerEstablecerConexion controllerEstablecerConexion;
    private DatosTanqueGuiDTO datosTanqueGuiDTO;

    public String establecerConexion() throws IOException {

            System.out.print("En experto establecer conexion");
            adaptadorUDP = (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
            System.out.println("Abriendo Puerto");

        return adaptadorUDP.establecerConexion();

    }

    public void recibirMensajes(){
        System.out.println("Por recibir mensaje");
        adaptadorUDP.recibirMensaje();

    }
    public void actualizarGUI(PaqueteRecibidoDTO paqueteRecibidoDTO) {
        datosTanqueGuiDTO = new DatosTanqueGuiDTO();
        //Armado de DTO

        datosTanqueGuiDTO.setDireccionIP(paqueteRecibidoDTO.getDireccionIP());
        datosTanqueGuiDTO.setEstadoEnfriamiento(paqueteRecibidoDTO.getEstadoEnfriamiento());
        datosTanqueGuiDTO.setEstadoRemontaje(paqueteRecibidoDTO.getEstadoRemontaje());
        datosTanqueGuiDTO.setHoraDispositivo(paqueteRecibidoDTO.getHoraDispositivo());
        datosTanqueGuiDTO.setPeriocidad(paqueteRecibidoDTO.getPeriocidad());
        datosTanqueGuiDTO.setRemontaje(paqueteRecibidoDTO.getRemontaje());

    }
    public DatosTanqueGuiDTO actualizarGUI(){

        System.out.println("En Experto establecer Conexion");
        return adaptadorUDP.getDTOMensaje();
    }
}
