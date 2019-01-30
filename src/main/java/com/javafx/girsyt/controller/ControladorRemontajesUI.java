package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.RemontajeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ControladorRemontajesUI {

    @FXML
    private CheckBox checkbtn_periocidad;

    @FXML
    private Button btn_agregar;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Spinner<LocalTime> spinner_timePickerInicio;

    @FXML
    private Spinner<LocalTime> spinner_timePickerFin;

    @FXML
    private TableView<RemontajeTable> table_remontajes;

    @FXML
    private TableColumn<RemontajeTable, String> col_numeroRemontaje;

    @FXML
    private TableColumn<RemontajeTable, String> col_horaInicioRemontaje;

    @FXML
    private TableColumn<RemontajeTable, String> col_horaFinRemontaje;

    @FXML
    private TableColumn<RemontajeTable, String> col_estadoRemontaje;

    @FXML
    private Button btn_aceptar;

    @FXML
    private Button btn_cancelar;

    private ObservableList<RemontajeTable> datosTablaRemontaje = FXCollections.observableArrayList();


    public TableView<RemontajeTable> getTable_remontajes() {
        return table_remontajes;
    }

    public void setTable_remontajes(TableView<RemontajeTable> table_remontajes) {
        this.table_remontajes = table_remontajes;
    }

    public TableColumn<RemontajeTable, String> getCol_numeroRemontaje() {
        return col_numeroRemontaje;
    }

    public void setCol_numeroRemontaje(TableColumn<RemontajeTable, String> col_numeroRemontaje) {
        this.col_numeroRemontaje = col_numeroRemontaje;
    }

    public TableColumn<RemontajeTable, String> getCol_horaInicioRemontaje() {
        return col_horaInicioRemontaje;
    }

    public void setCol_horaInicioRemontaje(TableColumn<RemontajeTable, String> col_horaInicioRemontaje) {
        this.col_horaInicioRemontaje = col_horaInicioRemontaje;
    }

    public TableColumn<RemontajeTable, String> getCol_horaFinRemontaje() {
        return col_horaFinRemontaje;
    }

    public void setCol_horaFinRemontaje(TableColumn<RemontajeTable, String> col_horaFinRemontaje) {
        this.col_horaFinRemontaje = col_horaFinRemontaje;
    }

    public TableColumn<RemontajeTable, String> getCol_estadoRemontaje() {
        return col_estadoRemontaje;
    }

    public void setCol_estadoRemontaje(TableColumn<RemontajeTable, String> col_estadoRemontaje) {
        this.col_estadoRemontaje = col_estadoRemontaje;
    }

    public ObservableList<RemontajeTable> getDatosTablaRemontaje() {
        return datosTablaRemontaje;
    }

    public void setDatosTablaRemontaje(ObservableList<RemontajeTable> datosTablaRemontaje) {
        this.datosTablaRemontaje = datosTablaRemontaje;
    }

    public CheckBox getCheckbtn_periocidad() {
        return checkbtn_periocidad;
    }

    public void setCheckbtn_periocidad(CheckBox checkbtn_periocidad) {
        this.checkbtn_periocidad = checkbtn_periocidad;
    }

    public Button getBtn_agregar() {
        return btn_agregar;
    }

    public void setBtn_agregar(Button btn_agregar) {
        this.btn_agregar = btn_agregar;
    }

    public Button getBtn_eliminar() {
        return btn_eliminar;
    }

    public void setBtn_eliminar(Button btn_eliminar) {
        this.btn_eliminar = btn_eliminar;
    }


    public Button getBtn_aceptar() {
        return btn_aceptar;
    }

    public void setBtn_aceptar(Button btn_aceptar) {
        this.btn_aceptar = btn_aceptar;
    }

    public Button getBtn_cancelar() {
        return btn_cancelar;
    }

    public void setBtn_cancelar(Button btn_cancelar) {
        this.btn_cancelar = btn_cancelar;
    }

    public Spinner<LocalTime> getSpinner_timePickerInicio() {
        return spinner_timePickerInicio;
    }

    public void setSpinner_timePickerInicio(Spinner<LocalTime> spinner_timePickerInicio) {
        this.spinner_timePickerInicio = spinner_timePickerInicio;
    }

    public Spinner<LocalTime> getSpinner_timePickerFin() {
        return spinner_timePickerFin;
    }

    public void setSpinner_timePickerFin(Spinner<LocalTime> spinner_timePickerFin) {
        this.spinner_timePickerFin = spinner_timePickerFin;
    }

    public void inicializarSpinners(){
        TimePickerSpinner timePickerSpinnerInicio = new TimePickerSpinner();
        SpinnerValueFactory<LocalTime> valorHoraInicio = timePickerSpinnerInicio.initSpinnerTime();
        getSpinner_timePickerInicio().setValueFactory(valorHoraInicio);
        TimePickerSpinner timePickerSpinnerFin = new TimePickerSpinner();
        SpinnerValueFactory<LocalTime> valorHoraFin = timePickerSpinnerFin.initSpinnerTime();
        getSpinner_timePickerFin().setValueFactory(valorHoraFin);
    }

    public void setRemontajesConfiguracion(ArrayList<RemontajeDTO> remontaje) {
        //Manejo de la tabla de remontajes
        int i=0;

        while(i <remontaje.size()) {
            datosTablaRemontaje.add(new RemontajeTable(String.valueOf(remontaje.get(i).getNumRemontaje()), remontaje.get(i).getInicioRemontaje(), remontaje.get(i).getFinRemontaje(), remontaje.get(i).getHabilitacionRemontaje()));
            ++i;
        }

        col_numeroRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("numeroRemontaje"));
        col_horaInicioRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("inicioRemontaje"));
        col_horaFinRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("finRemontaje"));
        col_estadoRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("estadoRemontaje"));

        table_remontajes.setItems(datosTablaRemontaje);
    }
}
