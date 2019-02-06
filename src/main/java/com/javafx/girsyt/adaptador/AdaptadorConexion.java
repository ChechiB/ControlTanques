package com.javafx.girsyt.adaptador;

import com.javafx.girsyt.dto.PaqueteAEnviarDTO;

import java.io.IOException;
import java.net.InetAddress;

public interface AdaptadorConexion {
    String establecerConexion() throws IOException;
    void establecerConexionCliente(InetAddress ipCliente, int puerto);
    int enviarMensaje() throws IOException;
    void recibirMensaje();

}
