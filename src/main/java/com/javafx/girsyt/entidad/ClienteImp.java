package com.javafx.girsyt.entidad;


//Se encarga de enviar y recibir los mesnajes que vienen y van del servidor.

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteImp extends Thread{
    private String mensaje = "";

    public void sincronizar(boolean ready){
        if (ready){

        }
    }




    /*private void enviar(String mensaje){
        try {

            DatagramSocket socketCliente = null;

            try
            {
                socketCliente = new DatagramSocket();
            } catch (IOException e)
            {
                System.out.println("Error al crear el objeto socket cliente");
                System.exit ( 0 );
            }

            byte [] enviarDatos = new byte[1024];
            byte [] recibirDatos = new byte[1024];
            enviarDatos = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length,   InetAddress.getLocalHost(),8001);
            socketCliente.send(enviarPaquete);

            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            socketCliente.receive(recibirPaquete);

            socketCliente.close();

        } catch (IOException ex) {
        }
    }*/

    /*
    public void enviarPaquete{

        if (bitIndicador == 2){
            mensaje = armarMensaje(contenidoTMax,contenidoTMin, bitIndicador);
            System.out.println("Mensaje bitIndicador = 2: " +mensaje);
        }else if (bitIndicador == 3){
            mensaje = armarMensajeD(Integer.toString(bitIndicador),horaFecha);
        }else if (bitIndicador == 4){
            mensaje = armarMensajeD(Integer.toString(bitIndicador),remontajes);
        }else if (bitStop == 0){
            mensaje = String.valueOf(bitStop);
            bitStop = 1;
        }else if (bitConectar == 1){
            mensaje = String.valueOf(bitConectar);
            bitConectar = 0;
        }

        try {

            DatagramSocket socketCliente = null;

            try
            {
                socketCliente = new DatagramSocket();
            } catch (IOException e)
            {
                System.out.println("Error al crear el objeto socket cliente");
                System.exit ( 0 );
            }

            System.out.println("Cliente" + "Mensaje:" + mensaje);

            byte [] enviarDatos = new byte[1024];
            byte [] recibirDatos = new byte[1024];
            enviarDatos = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length,   InetAddress.getByName(ipTanqueCliente),8001);
            socketCliente.send(enviarPaquete);

            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            socketCliente.receive(recibirPaquete);

            socketCliente.close();

        } catch (IOException ex) {

        }

    }

    private String armarMensaje(String contenidoTMax, String contenidoTMin, int bitTemperatura) {
        return mensaje = new String(bitTemperatura+"-"+ contenidoTMin+"-"+ contenidoTMax+"-");
    }

    private String armarMensajeD(String bitRemontaje, String str) {
        return mensaje = new String(bitRemontaje+ "-" + str);
    }

    public String getHoraActual() {
        Date horaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        return formateador.format(horaActual);
    }*/

}