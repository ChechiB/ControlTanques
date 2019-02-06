package com.javafx.girsyt.adaptador;


import com.javafx.girsyt.controller.ControladorVistaGeneralUI;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;
import com.javafx.girsyt.dto.RemontajeDTO;
import com.javafx.girsyt.entidad.*;
import com.javafx.girsyt.expert.ExpertEstablecerConexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AdaptadorUDP  implements AdaptadorConexion {

    private ServidorImp servidor;
    private Monitor monitor;
    private PaqueteRecibidoDTO paqueteRecibidoDTO;
    private ExpertEstablecerConexion expertEstablecerConexion;
    private DatagramSocket socketServidor = null;
    private Consumidor consumidor ;
    private ControladorVistaGeneralUI controladorVistaGeneral;
    private ClienteImp clienteImp;
    private String mensaje= "";


    @Override
    public String establecerConexion() throws IOException {

        String estado;
        //Clase que relaciona al consumidor y al generador de mensajes(servidor)
        monitor = new Monitor();
        servidor = new ServidorImp();
        socketServidor = servidor.establecerConexionServidor();

        return servidor.getEstado();
    }

    @Override
    public void establecerConexionCliente(InetAddress ipCliente, int puerto) {
        clienteImp = new ClienteImp(ipCliente,puerto);
        clienteImp.conectar();

    }


    //Debe devolver algo para saber que se recibio el mensaje
    @Override
    public int enviarMensaje() throws IOException {
        clienteImp.run();
        return  clienteImp.getEstadoEnvioMensaje();
    }

    public void armarMensaje(PaqueteAEnviarDTO paqueteAEnviarDTO){
        StringBuffer mensajeRemontaje = new StringBuffer();

         if (paqueteAEnviarDTO.getBitIndicador() == 2){
            mensaje = new String(paqueteAEnviarDTO.getBitIndicador()+"-"+ paqueteAEnviarDTO.gettMin()+"-"+ paqueteAEnviarDTO.gettMax());
            clienteImp.setMensaje(mensaje);
         }else if (paqueteAEnviarDTO.getBitIndicador() == 3) {
             mensaje = new String(paqueteAEnviarDTO.getBitIndicador() + "-" + paqueteAEnviarDTO.getHoraFecha());
             clienteImp.setMensaje(mensaje);
         }else if(paqueteAEnviarDTO.getBitIndicador()==4){

             for (String remontaje: paqueteAEnviarDTO.getRemontajes()) {
                 mensajeRemontaje.append(remontaje+"-");
             }
             mensaje = String.valueOf(paqueteAEnviarDTO.getBitIndicador()) + "-" + mensajeRemontaje.toString();
             clienteImp.setMensaje(mensaje);
         }else{
             mensaje = String.valueOf(paqueteAEnviarDTO.getBitConectar());
             clienteImp.setMensaje(mensaje);
         }

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
