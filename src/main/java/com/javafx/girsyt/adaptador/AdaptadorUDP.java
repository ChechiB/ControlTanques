package com.javafx.girsyt.adaptador;


import com.javafx.girsyt.controller.ControladorVistaGeneralUI;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;
import com.javafx.girsyt.entidad.*;
import com.javafx.girsyt.expert.ExpertEstablecerConexion;

import java.io.IOException;
import java.net.DatagramSocket;

public class AdaptadorUDP implements AdaptadorConexion {
    ServidorImp servidor;
    private Monitor monitor;
    private PaqueteRecibidoDTO paqueteRecibidoDTO;
    private ExpertEstablecerConexion expertEstablecerConexion;
    private DatagramSocket socketServidor = null;
    private Consumidor consumidor ;
    private ControladorVistaGeneralUI controladorVistaGeneral;

    public String establecerConexion() throws IOException {
        String estado;
        //Clase que relaciona al consumidor y al generador de mensajes(servidor)
        monitor = new Monitor();


        byte [] enviarDatos = new byte[1024];
        servidor = new ServidorImp();


        int port= servidor.getPuerto();

        try {
            //DatagramSocket da soporte a sockets para el envío y recepción de datagramas UDP
            socketServidor= new DatagramSocket(port);//// Puerto es en el que el servidor esta escuchando
            estado = "CONECTADO";
        } catch (IOException e)
        {
            ++port;
            socketServidor= new DatagramSocket(port);
            System.out.println("Error de conexion");
            estado = "ERROR DE CONEXION";
            // System.exit ( 0 );
            //Deberia de devolver un valor para indicar que el boton servidor debe ser habilitado
        }
        System.out.println("Servidor escuchando");
        //String initConexion = "1";
        //enviarDatos = initConexion.getBytes();

        //DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, InetAddress.getByName(ipTanqueCliente),3651);
        //socketServidor.send(enviarPaquete);

        //Dispositivo deberia enviar un msj con los tanques existentes
        //while(true){

        //}
        //Creacion de varios clientes
        /*while (true) {
           ServidorHiloImp servidorHiloImp = new ServidorHiloImp(socketServidor);
           servidorHiloImp.run();


        }*/
        return estado;

    }


    public void enviarMensaje(){


    }

    //Manejo de los distintos mensajes
    public void recibirMensaje(){
        while(true){

            ServidorHiloImp servidorHiloImp = new ServidorHiloImp(socketServidor,monitor);

            servidorHiloImp.run();
        }
    }


    public String[] getMensaje() {
        System.out.println("En AdaptadorUDP establecer Conexion");
        consumidor = new Consumidor(monitor);
        consumidor.run();


        return consumidor.getMensaje();
    }

}
