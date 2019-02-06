package com.javafx.girsyt.decorator;


import com.javafx.girsyt.expert.ExpertEnviarDatos;

import java.io.IOException;
import java.util.ArrayList;

public class DecoratorEnviarDatos extends ExpertEnviarDatos {
    public int enviarDatos(int bitConexion,String ipTanqueCliente, int puerto) throws IOException {
      return super.enviarDatos(bitConexion,ipTanqueCliente,puerto);
    }

    public int enviarDatos(Double contenidoTMax,Double contenidoTMin,String ipTanqueCliente, int puerto) throws IOException {
        return super.enviarDatos(contenidoTMax, contenidoTMin, ipTanqueCliente, puerto);
    }

    public int enviarDatos(StringBuffer horaFecha, String ipTanqueCliente, int puerto) throws IOException {
         return super.enviarDatos(horaFecha, ipTanqueCliente, puerto);
    }

    public int enviarDatos(int puerto, ArrayList<String> remontajesConfigurados, String ipTanqueCliente ) throws IOException {
        return super.enviarDatos(puerto,remontajesConfigurados, ipTanqueCliente);
    }

}
