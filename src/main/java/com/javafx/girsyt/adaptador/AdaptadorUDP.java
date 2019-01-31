package com.javafx.girsyt.adaptador;


import com.javafx.girsyt.controller.ControladorVistaGeneralUI;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;
import com.javafx.girsyt.entidad.*;
import com.javafx.girsyt.expert.ExpertEstablecerConexion;

import java.io.IOException;
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


    @Override
    public void enviarMensaje(PaqueteAEnviarDTO paqueteAEnviarDTO){

     /*   if (paqueteAEnviarDTO.getBitIndicador() == 2){
            mensaje = new String(paqueteAEnviarDTO.getBitIndicador()+"-"+ paqueteAEnviarDTO.gettMin()+"-"+ paqueteAEnviarDTO.gettMax());
            clienteImp.setMensaje(mensaje);
            System.out.println("Mensaje bitIndicador = 2: " +mensaje);
        }else if (paqueteAEnviarDTO.getBitIndicador() == 3){
            mensaje = new String(paqueteAEnviarDTO.getBitIndicador()+"-"+ paqueteAEnviarDTO.getHoraFecha());
        }else if (paqueteAEnviarDTO.getBitIndicador() == 4){
            mensaje = new String(paqueteAEnviarDTO.getBitIndicador(),remontajes);
        }else if (paqueteAEnviarDTO.getBitStop() == 0){
            mensaje = String.valueOf(paqueteAEnviarDTO.getBitStop());
            bitStop = 1;
        }else if (paqueteAEnviarDTO.getBitConectar() == 1){
            mensaje = String.valueOf(paqueteAEnviarDTO.getBitConectar());
            bitConectar = 0;
        } */

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

    private String armarMensaje(String contenidoTMax, String contenidoTMin, int bitTemperatura) {
        return mensaje = new String(bitTemperatura+"-"+ contenidoTMin+"-"+ contenidoTMax+"-");
    }

    private String armarMensajeD(String bitRemontaje, String str) {
        return mensaje = new String(bitRemontaje+ "-" + str);
    }



}
