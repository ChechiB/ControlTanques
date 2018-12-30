package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.dto.PaqueteAEnviarDTO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class ExpertEnviarDatos {
    PaqueteAEnviarDTO paqueteAEnviarDTOaqueteAEnviar;
    AdaptadorUDP adaptadorConexionUDPC;
    String remontajes[];

    public void enviarDatos(String ipTanqueCliente) throws FileNotFoundException, UnknownHostException{

        paqueteAEnviarDTOaqueteAEnviar = new PaqueteAEnviarDTO();
        paqueteAEnviarDTOaqueteAEnviar.setBitIndicador(1);
        paqueteAEnviarDTOaqueteAEnviar.setIpDireccion(ipTanqueCliente);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
        //Llamada al adaptador metodo enviar con ip y paquete
    }


    public void enviarDatos(int bitConexion,String ipTanqueCliente) throws FileNotFoundException, UnknownHostException {
        paqueteAEnviarDTOaqueteAEnviar = new PaqueteAEnviarDTO();
        paqueteAEnviarDTOaqueteAEnviar.setIpDireccion(ipTanqueCliente);

        if (bitConexion == 0){
            paqueteAEnviarDTOaqueteAEnviar.setBitStop(bitConexion);
        }else if (bitConexion==1){
            paqueteAEnviarDTOaqueteAEnviar.setBitConectar(bitConexion);
        }

        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
    }

    public void enviarDatos(String jLabelNumeroTanque, String contenidoTMax,String contenidoTMin,String ipTanqueCliente) throws UnknownHostException, FileNotFoundException{
        paqueteAEnviarDTOaqueteAEnviar = new PaqueteAEnviarDTO();
        paqueteAEnviarDTOaqueteAEnviar.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTOaqueteAEnviar.setNumeroTanque(jLabelNumeroTanque);
        paqueteAEnviarDTOaqueteAEnviar.setBitIndicador(2);
        paqueteAEnviarDTOaqueteAEnviar.settMax(contenidoTMax);
        paqueteAEnviarDTOaqueteAEnviar.settMin(contenidoTMin);

        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
    }

    public void enviarDatos(String horaFecha,String ipTanqueCliente) throws FileNotFoundException, UnknownHostException{
        paqueteAEnviarDTOaqueteAEnviar = new PaqueteAEnviarDTO();
        paqueteAEnviarDTOaqueteAEnviar.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTOaqueteAEnviar.setBitIndicador(3);
        paqueteAEnviarDTOaqueteAEnviar.setHoraFecha(horaFecha);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
    }

    public void enviarDatos(TableView<?> jTableRConfigurados, String jLabelNumeroTanque, String periocidad, String ipTanqueCliente) throws FileNotFoundException, UnknownHostException{

        paqueteAEnviarDTOaqueteAEnviar = new PaqueteAEnviarDTO();
        paqueteAEnviarDTOaqueteAEnviar.setIpDireccion(ipTanqueCliente);
        paqueteAEnviarDTOaqueteAEnviar.setNumeroTanque(jLabelNumeroTanque);
        paqueteAEnviarDTOaqueteAEnviar.setBitIndicador(4);
        paqueteAEnviarDTOaqueteAEnviar.setRemontaje(jTableRConfigurados);
        paqueteAEnviarDTOaqueteAEnviar.setPeriocidad(periocidad);
        adaptadorConexionUDPC =  (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
    }


}
