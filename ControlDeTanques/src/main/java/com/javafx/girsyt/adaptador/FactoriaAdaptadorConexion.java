package com.javafx.girsyt.adaptador;

public class FactoriaAdaptadorConexion {

    static FactoriaAdaptadorConexion instancia;
    private AdaptadorUDP adaptadorUDP;

    public void finalize() throws Throwable {

    }

    public static FactoriaAdaptadorConexion getInstance() {

        if (instancia == null) {

            instancia = new FactoriaAdaptadorConexion();
        }
        return instancia;

    }

    public Object crearAdaptadorConexion(String nombreClase){
        if (nombreClase.compareTo("AdaptadorUDP")==0){
            System.out.println("Creando Adaptador");
            return new AdaptadorUDP();
        }
        return null;

    }

}
