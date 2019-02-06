package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.RemontajeDTO;
import com.javafx.girsyt.expert.ExpertEnviarDatos;
import com.javafx.girsyt.fabrica.FabricaExpertos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ControllerEnviarDatos {

    ExpertEnviarDatos expertoEnviarDatos;

    //Envio de hora de la pc
    public int enviarDatos(StringBuffer horaFecha, String ipTanqueCliente, int port) throws IOException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        return expertoEnviarDatos.enviarDatos(horaFecha, ipTanqueCliente, port);

    }

    //Envio de inicio de Conexion
    public int enviarDatos(int bitConexion,String ipTanqueCliente,  int port) throws IOException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        return expertoEnviarDatos.enviarDatos(bitConexion, ipTanqueCliente, port);

    }

    //Envio de temperaturas
    public int enviarDatos(Double contenidoTMax,Double contenidoTMin, String ipTanqueCliente,  int port) throws IOException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        return expertoEnviarDatos.enviarDatos(contenidoTMax,contenidoTMin, ipTanqueCliente, port);

    }

    //Envío de Remontajes
    public int enviarDatos(int port,ArrayList<String> remontajesConfigurados, String ipTanqueCliente) throws IOException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        return  expertoEnviarDatos.enviarDatos(port,remontajesConfigurados,ipTanqueCliente);

    }
}
