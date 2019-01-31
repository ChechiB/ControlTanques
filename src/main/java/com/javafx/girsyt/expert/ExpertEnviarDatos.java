package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import com.javafx.girsyt.dto.RemontajeDTO;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ExpertEnviarDatos {
    PaqueteAEnviarDTO paqueteAEnviarDTO;
    AdaptadorUDP adaptadorConexionUDPC;
    String remontajes[];

    public void enviarDatos(String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException{

        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setBitIndicador(1);
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.establecerConexionCliente(InetAddress.getByName(ipTanqueCliente), puerto);
        adaptadorConexionUDPC.enviarMensaje(paqueteAEnviarDTO);

        //Llamada al adaptador metodo enviar con ip y paquete
    }


    public void enviarDatos(int bitConexion,String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException {
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);

        if (bitConexion == 0){
            paqueteAEnviarDTO.setBitStop(bitConexion);
        }else if (bitConexion==1){
            paqueteAEnviarDTO.setBitConectar(bitConexion);
        }

        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.enviarMensaje(paqueteAEnviarDTO);

    }

    public void enviarDatos(String jLabelNumeroTanque, String contenidoTMax,String contenidoTMin,String ipTanqueCliente, int puerto) throws UnknownHostException, FileNotFoundException{
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setNumeroTanque(jLabelNumeroTanque);
        paqueteAEnviarDTO.setBitIndicador(2);
        paqueteAEnviarDTO.settMax(contenidoTMax);
        paqueteAEnviarDTO.settMin(contenidoTMin);

        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.enviarMensaje(paqueteAEnviarDTO);

    }

    public void enviarDatos(StringBuffer horaFecha,String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException{
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setBitIndicador(3);
        paqueteAEnviarDTO.setHoraFecha(horaFecha.toString());
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.enviarMensaje(paqueteAEnviarDTO);

    }

    public void enviarDatos(ArrayList<RemontajeDTO> remontajesConfigurados, String jLabelNumeroTanque, String periocidad, String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException{

        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setNumeroTanque(jLabelNumeroTanque);
        paqueteAEnviarDTO.setBitIndicador(4);
        paqueteAEnviarDTO.setRemontaje(remontajesConfigurados);
        paqueteAEnviarDTO.setPeriocidad(periocidad);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.enviarMensaje(paqueteAEnviarDTO);

    }


}
