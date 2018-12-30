package com.javafx.girsyt.controller;

import com.javafx.girsyt.expert.ExpertEnviarDatos;
import com.javafx.girsyt.fabrica.FabricaExpertos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class ControllerEnviarDatos {

    ExpertEnviarDatos expertoEnviarDatos;

    //Envio de hora de la pc
    public void enviarDatos(String horaFecha, String ipTanqueCliente) throws UnknownHostException, FileNotFoundException {
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(horaFecha, ipTanqueCliente);

    }

    //Envio de inicio de Conexion
    public void enviarDatos(int bitConexion,String ipTanqueCliente) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(bitConexion, ipTanqueCliente);

    }

    //Envio de temperaturas
    public void enviarDatos(String jLabelNumeroTanque, String contenidoTMax,String contenidoTMin, String ipTanqueCliente) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(jLabelNumeroTanque, contenidoTMax,contenidoTMin, ipTanqueCliente);

    }

    //Envío de Remontajes
    public void enviarDatos(TableView jTableRConfigurados, String jLabelNumeroTanque, String periocidad, String ipTanqueCliente) throws UnknownHostException, FileNotFoundException{
        expertoEnviarDatos= (ExpertEnviarDatos) FabricaExpertos.getinstancia().crearExperto("EnviarDatos");
        expertoEnviarDatos.enviarDatos(jTableRConfigurados,jLabelNumeroTanque, periocidad, ipTanqueCliente);

    }
}
