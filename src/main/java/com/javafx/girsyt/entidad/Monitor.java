package com.javafx.girsyt.entidad;

import java.util.ArrayList;
import java.util.List;

public class Monitor {

    private List<String[]> mensajeArrayList = new ArrayList<String[]>();
    private int siguienteMensaje = 0;
    // Flags para saber el estado del buffer
    private boolean estaLlena = false;
    private boolean estaVacia = true;
    private String [] mensaje = new String[2];



    public synchronized String[] getMensaje(){
        System.out.println("Get mensaje de la lista");
        // No se puede consumir si el buffer está vacío
        System.out.println(estaVacia);
        while( estaVacia == true )
        {
            System.out.println("Lista vacia");
            try {
                wait(); // Se sale cuando estaVacia cambia a false
            } catch( InterruptedException e ) {
                ;
            }
        }
        // Decrementa la cuenta, ya que va a consumir un DTOTanque

        siguienteMensaje--;
        // Comprueba si se retiró el ultimo Tanque
        if( siguienteMensaje == 0 ) {
            System.out.println("Lista Vacia 2");
            estaVacia = true;
        }
        // El buffer no puede estar lleno, porque acabamos de consumir
        estaLlena = false;
        notify();
        mensaje = mensajeArrayList.get(siguienteMensaje);
        return( mensaje );
    }

    // Método para añadir letras al buffer
    public synchronized void setMensaje( String[] mensaje ) {
        System.out.println("En setDtoMensaje");

        // Espera hasta que haya sitio para otra letra
        while( estaLlena == true )
        {
            try {
                wait(); // Se sale cuando estaLlena cambia a false
            } catch( InterruptedException e ) {

            }
        }

        System.out.println("Agregando tanque");
        // Añade un DTOTanque
        mensajeArrayList.add(siguienteMensaje,mensaje);
        System.out.println("Mensajes en la cola: " + mensajeArrayList.size());

        // Cambia al siguiente lugar disponible
        siguienteMensaje++;
        // Comprueba si el buffer está lleno
        if( siguienteMensaje == 100) {
            estaLlena = true;
        }
        estaVacia = false;

        System.out.println(estaVacia);
        notify();
    }
}
