package com.javafx.girsyt.entidad;

import java.util.ArrayList;
import java.util.List;

public class Monitor {

    private List<DTOMensaje> dtoMensajeList = new ArrayList<DTOMensaje>();
    private int siguienteTanque = 0;
    // Flags para saber el estado del buffer
    private boolean estaLlena = false;
    private boolean estaVacia = true;
    private DTOMensaje dtoMensaje;



    public synchronized DTOMensaje getMensaje(){
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

        siguienteTanque--;
        // Comprueba si se retiró el ultimo Tanque
        if( siguienteTanque == 0 ) {
            System.out.println("Lista Vacia 2");
            estaVacia = true;
        }
        // El buffer no puede estar lleno, porque acabamos de consumir
        estaLlena = false;
        notify();
        //Se elimina el DTOTanque de la lista
        dtoMensaje = new DTOMensaje();

        dtoMensaje = dtoMensajeList.get(siguienteTanque);
        System.out.println(dtoMensaje.getMensaje());
        System.out.println("En getMensaje");


        //dtoMensajeList.iterator().remove();
        // Devuelve la letra al thread consumidor
        return( dtoMensaje );
    }

    // Método para añadir letras al buffer
    public synchronized void setDtoMensaje( DTOMensaje dtoMensaje ) {
        System.out.println("En setDtoMensaje");

        // Espera hasta que haya sitio para otra letra
        while( estaLlena == true )
        {
            try {
                wait(); // Se sale cuando estaLlena cambia a false
            } catch( InterruptedException e ) {
                ;
            }
        }

        System.out.println("Agregando tanque");
        // Añade un DTOTanque
        dtoMensajeList.add(siguienteTanque,dtoMensaje);
        System.out.println("Mensajes en la cola: " + dtoMensajeList.size());

        // Cambia al siguiente lugar disponible
        siguienteTanque++;
        // Comprueba si el buffer está lleno
        if( siguienteTanque == 100) {
            estaLlena = true;
        }
        estaVacia = false;

        System.out.println(estaVacia);
        notify();
    }
}
