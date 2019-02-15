package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import com.javafx.girsyt.dto.RemontajeDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ExpertEnviarDatos {
    PaqueteAEnviarDTO paqueteAEnviarDTO;
    AdaptadorUDP adaptadorConexionUDPC;
    String remontajes[];

    //Envio de inicio o fin de conexion
    public int enviarDatos(int bitConexion,String ipTanqueCliente, int puerto) throws IOException {
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setBitConectar(bitConexion); //1 transmitir, 0 stop
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.establecerConexionCliente(InetAddress.getByName(ipTanqueCliente),puerto);
        adaptadorConexionUDPC.armarMensaje(paqueteAEnviarDTO);
        return adaptadorConexionUDPC.enviarMensaje();

    }

    //Envio de temperatura
    public int enviarDatos(Double contenidoTMax,Double contenidoTMin,String ipTanqueCliente, int puerto) throws IOException {
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setBitIndicador(2);
        paqueteAEnviarDTO.settMax(contenidoTMax);
        paqueteAEnviarDTO.settMin(contenidoTMin);

        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.establecerConexionCliente(InetAddress.getByName(ipTanqueCliente),puerto);
        adaptadorConexionUDPC.armarMensaje(paqueteAEnviarDTO);
        return adaptadorConexionUDPC.enviarMensaje();

    }

    //Envio de hora para sincronizacion
    public int enviarDatos(StringBuffer horaFecha, String ipTanqueCliente, int puerto) throws IOException {
        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setBitIndicador(3);
        paqueteAEnviarDTO.setHoraFecha(horaFecha.toString());
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.establecerConexionCliente(InetAddress.getByName(ipTanqueCliente),puerto);
        adaptadorConexionUDPC.armarMensaje(paqueteAEnviarDTO);
        return adaptadorConexionUDPC.enviarMensaje();

    }

    //Envio de remontajes configurados
    public int enviarDatos(int puerto,ArrayList<String> remontajesConfigurados,String ipTanqueCliente) throws IOException {

        paqueteAEnviarDTO = new PaqueteAEnviarDTO();
        paqueteAEnviarDTO.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTO.setBitIndicador(4);
        paqueteAEnviarDTO.setRemontaje(remontajesConfigurados);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        adaptadorConexionUDPC.establecerConexionCliente(InetAddress.getByName(ipTanqueCliente),puerto);
        adaptadorConexionUDPC.armarMensaje(paqueteAEnviarDTO);
        return adaptadorConexionUDPC.enviarMensaje();

    }




}
