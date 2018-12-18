package com.javafx.girsyt.persistencia;

import com.javafx.girsyt.entidad.TanqueImp;

import java.util.List;

public interface InterfaceDAO {
    List<Object> getObjetoList();
    TanqueImp getObjeto(String id);
    void guardar(Object object);
}
