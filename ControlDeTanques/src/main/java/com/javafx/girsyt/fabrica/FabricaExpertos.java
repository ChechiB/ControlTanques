package com.javafx.girsyt.fabrica;

import com.javafx.girsyt.decorator.DecoratorEnviarDatos;
import com.javafx.girsyt.decorator.DecoratorEstablecerConexion;
import com.javafx.girsyt.decorator.DecoratorSincronizarTanques;

public class FabricaExpertos {
    private static FabricaExpertos instancia;

    public void finalize() throws Throwable {

    }

    public static FabricaExpertos getinstancia() {
        if (instancia == null) {

            instancia = new FabricaExpertos();
        }
        return instancia;
    }

    public Object crearExperto(String nombreClase){
        if (nombreClase.compareTo("EstablecerConexion")==0){
            System.out.println("Creando experto");
            return new DecoratorEstablecerConexion();
        }else if(nombreClase.compareTo("EnviarDatos")==0){
            return new DecoratorEnviarDatos();
        }else if(nombreClase.compareTo("SincronizarTanques")==0){
            return new DecoratorSincronizarTanques();
        }
        return null;

    }
}