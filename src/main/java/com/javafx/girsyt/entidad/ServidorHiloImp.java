package com.javafx.girsyt.entidad;

import javafx.concurrent.Task;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServidorHiloImp extends Task<Void> {

    private DatagramSocket socketServidor;
    private byte [] buffer = new byte[1024];
    private Monitor monitor;

    public ServidorHiloImp(DatagramSocket socketServidor, Monitor monitor){
        this.socketServidor = socketServidor;
        this.monitor = monitor;
        System.out.println("En el constructor del Hilo Servidor");
    }


    @Override
    protected Void call() throws Exception {

        System.out.print("En hilo escuchando");

        try {
            DatagramPacket recibirPaquete = new DatagramPacket(buffer, buffer.length);
            socketServidor.receive(recibirPaquete); //recepciona el paquete
            //Se extraen los datos como cadena
            String mensaje[] = new String[2];


            String s = new String(recibirPaquete.getData());


            System.out.println("Data: " + s);
            mensaje[0] = s;
            mensaje[1] = new String( recibirPaquete.getAddress().getHostAddress() + "-" + recibirPaquete.getPort())+"-";

            monitor.setMensaje(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
