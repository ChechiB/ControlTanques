package com.javafx.girsyt.decorator;

import com.javafx.girsyt.controller.ControladorVistaGeneralUI;
import com.javafx.girsyt.entidad.DTOMensaje;
import com.javafx.girsyt.expert.ExpertEstablecerConexion;

import java.io.IOException;

public class DecoratorEstablecerConexion extends ExpertEstablecerConexion {

    @Override
    public String establecerConexion( ) throws IOException {
       return super.establecerConexion();
    }
}
