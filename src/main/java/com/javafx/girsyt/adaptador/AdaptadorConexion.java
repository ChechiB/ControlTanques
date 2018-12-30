package com.javafx.girsyt.adaptador;

import java.io.IOException;

public interface AdaptadorConexion {
    String establecerConexion() throws IOException;
    void enviarMensaje();
    void recibirMensaje();
}
