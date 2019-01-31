package com.javafx.girsyt.entidad;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorImp {
    private int puerto = 5558;
    private String estado = null;

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DatagramSocket establecerConexionServidor() throws SocketException {

        DatagramSocket socketServidor = null;
        byte [] enviarDatos = new byte[1024];

        try {
            //DatagramSocket da soporte a sockets para el envío y recepción de datagramas UDP
            socketServidor= new DatagramSocket(puerto);//// Puerto es en el que el servidor esta escuchando
            estado = "CONECTADO";
        } catch (IOException e)
        {
            ++puerto;
            socketServidor= new DatagramSocket(puerto);
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
        return socketServidor;
    }

}
