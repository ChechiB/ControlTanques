package com.javafx.girsyt.controller;

import com.javafx.girsyt.expert.ExpertSincronizarTanques;
import com.javafx.girsyt.fabrica.FabricaExpertos;

//Deberia enviar un bit para indicar que la pc esta lista para recibir los tanques ya existentes
public class ControladorSincronizarTanques {
    private ExpertSincronizarTanques expertSincronizarTanques;

    public void sincronizar(boolean ready) {
        expertSincronizarTanques = (ExpertSincronizarTanques) FabricaExpertos.getinstancia().crearExperto("SincronizarTanques");
        expertSincronizarTanques.sincronizar(ready);
    }
}
