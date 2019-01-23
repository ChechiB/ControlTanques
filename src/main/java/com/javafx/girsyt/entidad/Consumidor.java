package com.javafx.girsyt.entidad;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread {

    private Monitor monitor;
    String mensaje[];
    private DatosTanqueGuiDTO datosTanqueGuiDTO;
    private int cantidadTanques=0;


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



    }



    public String[] getMensaje() {
        System.out.println("Get Mensaje consumidor");
         return mensaje;
    }
}
