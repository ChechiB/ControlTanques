package com.javafx.girsyt.entidad;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread {

    private Monitor monitor;
    String mensaje;
    private DatosTanqueGuiDTO datosTanqueGuiDTO;
    private int cantidadTanques=0;
    private ArrayList <TanqueImp> tanqueImpArrayList = new ArrayList<TanqueImp>();


    //Constructor Consumidor --> Mantiene una copia propia del objeto (Monitor) compartido
    public Consumidor( Monitor monitor ) {
        System.out.println("Creando consumidor");
        this.monitor = monitor;
    }

    public void run() {
        System.out.println("En run");

         mensaje = monitor.getMensaje();
     }

     //Armado de los distintos mensajes ---> Armado va en el Experto
    private void armarDto(String mensaje) {

        //Aca no se arma el DTO. Deberia devolver msj y el experto deberia crear y manejar el DTO
        System.out.println("En armado DTO");

        datosTanqueGuiDTO = new DatosTanqueGuiDTO();

        String[] parts = mensaje.split("-");

        switch (Integer.parseInt(parts[0])) {
            //Codigo de operacion == 0 --> Mensaje de configuracion de tanques
            //Setear creacion de objeto Tanque, Remontaje, Temperatura, etc; de acuerdo al codigo de operacion
            case 0:
                //Creacion de Tanque
                TanqueImp tanqueImp = new TanqueImp();

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

                tanqueImpArrayList.add(tanqueImp);
                break;
        }



    }



    public DatosTanqueGuiDTO getDtoMensaje() {
        System.out.println("Get Mensaje consumidor");
         return datosTanqueGuiDTO;
    }
}
