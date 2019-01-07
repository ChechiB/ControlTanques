package com.javafx.girsyt.decorator;

import com.javafx.girsyt.expert.ExpertEstablecerConexion;

import java.io.IOException;

public class DecoratorEstablecerConexion extends ExpertEstablecerConexion {

    @Override
    public String establecerConexion( ) throws IOException {
       return super.establecerConexion();
    }
}
