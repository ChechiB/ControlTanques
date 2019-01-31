package com.javafx.girsyt.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class RemontajeTable {

    private final SimpleStringProperty numeroRemontaje;
    private final SimpleStringProperty inicioRemontaje;
    private final SimpleStringProperty finRemontaje;
    private final SimpleObjectProperty estadoRemontaje;

    public String getNumeroRemontaje() {
        return numeroRemontaje.get();
    }

    public SimpleStringProperty numeroRemontajeProperty() {
        return numeroRemontaje;
    }

    public void setNumeroRemontaje(String numeroRemontaje) {
        this.numeroRemontaje.set(numeroRemontaje);
    }

    public String getInicioRemontaje() {
        return inicioRemontaje.get();
    }

    public SimpleStringProperty inicioRemontajeProperty() {
        return inicioRemontaje;
    }

    public void setInicioRemontaje(String inicioRemontaje) {
        this.inicioRemontaje.set(inicioRemontaje);
    }

    public String getFinRemontaje() {
        return finRemontaje.get();
    }

    public SimpleStringProperty finRemontajeProperty() {
        return finRemontaje;
    }

    public void setFinRemontaje(String finRemontaje) {
        this.finRemontaje.set(finRemontaje);
    }

    public Object getEstadoRemontaje() {
        return estadoRemontaje.get();
    }

    public void setEstadoRemontaje(Object estadoRemontaje) {
        this.estadoRemontaje.set(estadoRemontaje);
    }

    public RemontajeTable(String numeroRemontaje, String inicioRemontaje, String finRemontaje, Object estadoRemontaje){
        this.numeroRemontaje = new SimpleStringProperty(numeroRemontaje);
        this.inicioRemontaje =  new SimpleStringProperty(inicioRemontaje);
        this.finRemontaje =  new SimpleStringProperty(finRemontaje);
        this.estadoRemontaje = new SimpleObjectProperty(estadoRemontaje);
    }



}
