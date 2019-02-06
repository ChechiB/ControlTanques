package com.javafx.girsyt.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class RemontajeConfiguracionTable {

    private final SimpleObjectProperty<Integer> numeroRemontaje;
    private final SimpleStringProperty inicioRemontaje;
    private final SimpleStringProperty finRemontaje;
    private final SimpleObjectProperty<ComboBox<String>> estadoRemontaje;
    private final SimpleObjectProperty<Button> eliminar;
    private final SimpleObjectProperty<CheckBox> periocidad;


    public CheckBox getPeriocidad() {
        return periocidad.get();
    }

    public SimpleObjectProperty<CheckBox> periocidadProperty() {
        return periocidad;
    }

    public void setPeriocidad(CheckBox periocidad) {
        this.periocidad.set(periocidad);
    }

    public Integer getNumeroRemontaje() {
        return numeroRemontaje.get();
    }

    public SimpleObjectProperty<Integer> numeroRemontajeProperty() {
        return numeroRemontaje;
    }

    public void setNumeroRemontaje(Integer numeroRemontaje) {
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

    public ComboBox<String> getEstadoRemontaje() {
        return estadoRemontaje.get();
    }

    public SimpleObjectProperty<ComboBox<String>> estadoRemontajeProperty() {
        return estadoRemontaje;
    }

    public void setEstadoRemontaje(ComboBox<String> estadoRemontaje) {
        this.estadoRemontaje.set(estadoRemontaje);
    }

    public Button getEliminar() {
        return eliminar.get();
    }

    public SimpleObjectProperty<Button> eliminarProperty() {
        return eliminar;
    }

    public void setEliminar(Button eliminar) {
        this.eliminar.set(eliminar);
    }

    public RemontajeConfiguracionTable(Integer numeroRemontaje, String inicioRemontaje, String finRemontaje, ComboBox<String> estadoRemontaje, CheckBox periocidad, Button eliminar){

        this.numeroRemontaje = new SimpleObjectProperty<Integer>(numeroRemontaje);
        this.inicioRemontaje =  new SimpleStringProperty(inicioRemontaje);
        this.finRemontaje =  new SimpleStringProperty(finRemontaje);
        this.estadoRemontaje = new SimpleObjectProperty<ComboBox<String>>(estadoRemontaje);
        this.eliminar = new SimpleObjectProperty<Button>(eliminar);
        this.periocidad = new SimpleObjectProperty<CheckBox>(periocidad);
    }
}
