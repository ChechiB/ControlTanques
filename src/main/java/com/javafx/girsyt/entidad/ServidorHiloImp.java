package com.javafx.girsyt.entidad;

import com.javafx.girsyt.controller.ControladorVistaGeneralUI;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class ServidorHiloImp extends Task<Void> {

    private DatagramSocket socketServidor;
    private byte [] recibirDatos = new byte[1024];
    private PaqueteRecibidoDTO paqueteRecibidoDTO;
    private boolean estadoHilo = false;
    private int cont = 0;
    private ControladorVistaGeneralUI controladorVistaGeneralUI;
    private  DTOMensaje dtoMensaje;
    private Monitor monitor;
    private List<DTOMensaje> dtoMensajeList = new ArrayList<DTOMensaje>();


    public ServidorHiloImp(DatagramSocket socketServidor, Monitor monitor){
        this.socketServidor = socketServidor;
        this.monitor = monitor;
        System.out.println("En el constructor del Hilo Servidor");
    }

    public void setPaqueteRecibidoDTO(PaqueteRecibidoDTO paqueteRecibidoDTO) {
        this.paqueteRecibidoDTO = paqueteRecibidoDTO;
    }

    public PaqueteRecibidoDTO getPaqueteRecibidoDTO() {
        return paqueteRecibidoDTO;
    }

    public DTOMensaje getDtoMensaje() {
        return dtoMensaje;
    }

    @Override
    protected Void call() throws Exception {

        System.out.print("En hilo escuchando");

        //Creacion del holder
                DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
        try {
            socketServidor.receive(recibirPaquete); //recepciona el paquete
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Port: " + recibirPaquete.getPort() + " Address: " + recibirPaquete.getAddress().toString());
        //Se extraen los datos como cadena
        String p = new String(recibirPaquete.getData());
        System.out.println(p);
        dtoMensaje = new DTOMensaje();
        dtoMensaje.setMensaje(p.trim());

        monitor.setDtoMensaje(dtoMensaje);

        return null;
    }

}
