package com.javafx.girsyt.entidad;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread {
    private Monitor monitor;
    DTOMensaje dtoMensaje;
    private DatosTanqueGuiDTO datosTanqueGuiDTO;
    private int cantidadTanques=0;


    public Consumidor( Monitor monitor ) {
        System.out.println("Creando consumidor");

        // Mantiene una copia propia del objeto compartido
        this.monitor = monitor;
    }

    public void run() {
        System.out.println("En run");

         dtoMensaje = monitor.getMensaje();
         armarDto(dtoMensaje);
         System.out.println(dtoMensaje.getMensaje());

     }

     //Armado de los distintos mensajes
    private void armarDto(DTOMensaje mensaje) {
        System.out.println("En armado DTO");

        datosTanqueGuiDTO = new DatosTanqueGuiDTO();
        String[] parts = mensaje.getMensaje().split("-");

        switch (Integer.parseInt(parts[0])) {
            //Codigo de operacion == 0 --> Mensaje de configuracion de tanques
            case 0:
                for (int i = 0; i < parts.length; i++) {
                    switch (i) {
                        case 0:
                            datosTanqueGuiDTO.setCodigoOperacion(Integer.parseInt(parts[i]));
                            break;
                        case 1:
                            datosTanqueGuiDTO.setIdTanque(parts[i].substring(10,12));
                            break;
                        case 2:
                            datosTanqueGuiDTO.setDireccionIP(parts[i]);
                            break;
                        case 3:
                            datosTanqueGuiDTO.setPort(Integer.parseInt(parts[i]));
                            break;
                    }
                }
                break;

        }

    }



    public DatosTanqueGuiDTO getDtoMensaje() {
        System.out.println("Get Mensaje consumidor");
         return datosTanqueGuiDTO;
    }
}
