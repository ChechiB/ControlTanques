package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.RemontajeDTO;
import com.javafx.girsyt.expert.ExpertEnviarDatos;
import com.javafx.girsyt.fabrica.FabricaExpertos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ControllerEnviarDatos {

    ExpertEnviarDatos expertoEnviarDatos;

    //Envio de hora de la pc
    public void enviarDatos(StringBuffer horaFecha, String ipTanqueCliente, int port) throws UnknownHostException, FileNotFoundException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(horaFecha, ipTanqueCliente, port);

    }

    //Envio de inicio de Conexion
    public void enviarDatos(int bitConexion,String ipTanqueCliente,  int port) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(bitConexion, ipTanqueCliente, port);

    }

    //Envio de temperaturas
    public void enviarDatos(String jLabelNumeroTanque, String contenidoTMax,String contenidoTMin, String ipTanqueCliente,  int port) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(jLabelNumeroTanque, contenidoTMax,contenidoTMin, ipTanqueCliente, port);

    }

    //Envío de Remontajes
    public void enviarDatos(ArrayList<RemontajeDTO> remontajesConfigurados, String jLabelNumeroTanque, String periocidad, String ipTanqueCliente, int port) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(remontajesConfigurados,jLabelNumeroTanque, periocidad, ipTanqueCliente, port);

    }
}
