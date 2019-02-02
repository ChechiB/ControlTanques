package com.javafx.girsyt.decorator;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import com.javafx.girsyt.dto.RemontajeDTO;
import com.javafx.girsyt.expert.ExpertEnviarDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DecoratorEnviarDatos extends ExpertEnviarDatos {
    public void enviarDatos(int bitConexion,String ipTanqueCliente, int puerto) throws IOException {
       super.enviarDatos(bitConexion,ipTanqueCliente,puerto);
    }

    public void enviarDatos(String jLabelNumeroTanque, String contenidoTMax,String contenidoTMin,String ipTanqueCliente, int puerto) throws UnknownHostException, FileNotFoundException {
        super.enviarDatos(jLabelNumeroTanque, contenidoTMax, contenidoTMin, ipTanqueCliente, puerto);
    }

    public void enviarDatos(StringBuffer horaFecha,String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException{
       super.enviarDatos(horaFecha, ipTanqueCliente, puerto);
    }

    public void enviarDatos(ArrayList<RemontajeDTO> remontajesConfigurados, String jLabelNumeroTanque, String periocidad, String ipTanqueCliente, int puerto) throws FileNotFoundException, UnknownHostException{
        super.enviarDatos(remontajesConfigurados, jLabelNumeroTanque, periocidad, ipTanqueCliente, puerto);
    }

}
