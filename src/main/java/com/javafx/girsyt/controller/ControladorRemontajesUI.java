package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.RemontajeDTO;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class ControladorRemontajesUI {

    private String ipTanque;
    private String idTanque;
    private int puerto;

    @FXML
    private DatePicker datePicker_Inicio;

    @FXML
    private DatePicker datePicker_Fin;

    @FXML
    private CheckBox checkbtn_periocidad;

    @FXML
    private Button btn_agregar;

    @FXML
    private JFXTimePicker horaInicio;

    @FXML
    private JFXTimePicker horaFin;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Spinner<LocalTime> spinner_timePickerInicio;

    @FXML
    private Spinner<LocalTime> spinner_timePickerFin;

    @FXML
    private TableView<RemontajeConfiguracionTable> table_remontajes;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, Integer> col_numeroRemontaje;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, String> col_horaInicioRemontaje;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, String> col_horaFinRemontaje;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, ComboBox<String>> col_estadoRemontaje;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, Button > col_eliminar;

    @FXML
    private TableColumn<RemontajeConfiguracionTable, CheckBox > col_periocidad;

    @FXML
    private Button btn_sincronizar;

    @FXML
    private Button btn_cancelar;
    @FXML
    private ComboBox<String> cmbBox_estadoRemontaje;


    private ObservableList<RemontajeConfiguracionTable> datosTablaRemontaje = FXCollections.observableArrayList();
    private ObservableList<String> listEstado=  FXCollections.observableArrayList("Habilitado","Deshabiilitado");
    private Parent root;
    private ControllerEnviarDatos controllerEnviarDatos;
    private ArrayList<RemontajeDTO> remontajesList = new ArrayList<>();
    private String periocidad;
    ComboBox<String> comboBoxEstado;
    private ControladorTanqueUI controladorTanqueUI;

    /*Metodos getters and setters*/

    public ArrayList<RemontajeDTO> getRemontajesList() {
        return remontajesList;
    }

    public void setRemontajesList(ArrayList<RemontajeDTO> remontajesList) {
        this.remontajesList = remontajesList;
    }

    public TableView<RemontajeConfiguracionTable> getTable_remontajes() {
        return table_remontajes;
    }

    public void setTable_remontajes(TableView<RemontajeConfiguracionTable> table_remontajes) {
        this.table_remontajes = table_remontajes;
    }

    public TableColumn<RemontajeConfiguracionTable, Integer> getCol_numeroRemontaje() {
        return col_numeroRemontaje;
    }

    public void setCol_numeroRemontaje(TableColumn<RemontajeConfiguracionTable, Integer> col_numeroRemontaje) {
        this.col_numeroRemontaje = col_numeroRemontaje;
    }

    public TableColumn<RemontajeConfiguracionTable, String> getCol_horaInicioRemontaje() {
        return col_horaInicioRemontaje;
    }

    public void setCol_horaInicioRemontaje(TableColumn<RemontajeConfiguracionTable, String> col_horaInicioRemontaje) {
        this.col_horaInicioRemontaje = col_horaInicioRemontaje;
    }

    public TableColumn<RemontajeConfiguracionTable, String> getCol_horaFinRemontaje() {
        return col_horaFinRemontaje;
    }

    public void setCol_horaFinRemontaje(TableColumn<RemontajeConfiguracionTable, String> col_horaFinRemontaje) {
        this.col_horaFinRemontaje = col_horaFinRemontaje;
    }

    public TableColumn<RemontajeConfiguracionTable, ComboBox<String>> getCol_estadoRemontaje() {
        return col_estadoRemontaje;
    }

    public void setCol_estadoRemontaje(TableColumn<RemontajeConfiguracionTable, ComboBox<String>> col_estadoRemontaje) {
        this.col_estadoRemontaje = col_estadoRemontaje;
    }

    public ObservableList<RemontajeConfiguracionTable> getDatosTablaRemontaje() {
        return datosTablaRemontaje;
    }

    public void setDatosTablaRemontaje(ObservableList<RemontajeConfiguracionTable> datosTablaRemontaje) {
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

    public JFXTimePicker getHoraInicio() {
        return horaInicio;
    }

    public JFXTimePicker getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(JFXTimePicker horaFin) {
        this.horaFin = horaFin;
    }

    public void setHoraInicio(JFXTimePicker horaInicio) {
        this.horaInicio = horaInicio;
    }

    @FXML
    void enviar(ActionEvent event) throws IOException {
        int opcion = showAlertMessage();

        switch (opcion){
            case 0:
                break;
            case 1:
                controllerEnviarDatos = new ControllerEnviarDatos();
                controllerEnviarDatos.enviarDatos(puerto,getRemontajes(), ipTanque);

                controladorTanqueUI.actualizarTabla(datosTablaRemontaje);
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }


    private String getPeriocidad(CheckBox periocidad) {
        if(periocidad.isSelected()){
            this.periocidad = "1";
        }else{
            this.periocidad ="0";
        }

        return this.periocidad;

    }

    //Se cambiaron los spinner genericos por los TimerPicker de Foenix 9
    /*public void inicializarSpinners(){
        TimePickerSpinner timePickerSpinnerInicio = new TimePickerSpinner();
        SpinnerValueFactory<LocalTime> valorHoraInicio = timePickerSpinnerInicio.initSpinnerTime();
        getSpinner_timePickerInicio().setValueFactory(valorHoraInicio);
        getSpinner_timePickerInicio().getEditor().setAlignment(Pos.BASELINE_RIGHT);
        TimePickerSpinner timePickerSpinnerFin = new TimePickerSpinner();
        SpinnerValueFactory<LocalTime> valorHoraFin = timePickerSpinnerFin.initSpinnerTime();
        getSpinner_timePickerFin().setValueFactory(valorHoraFin);
        getSpinner_timePickerFin().getEditor().setAlignment(Pos.BASELINE_RIGHT);
    }*/

    public void setRemontajesConfiguracion(ArrayList<RemontajeDTO> remontajesList) {

        this.remontajesList = remontajesList;
       //Manejo de la tabla de remontajes
        int i=0;

        while(i <remontajesList.size()) {
            ComboBox<String> comboBoxEstado= new ComboBox<>();
            Button btn_eliminar = new Button();
            btn_eliminar.setText("Eliminar");
            CheckBox checkBox_periocidad = new CheckBox();
            checkBox_periocidad.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            ObservableList<String> listEstado = FXCollections.observableArrayList("Habilitado","Deshabiilitado");
            comboBoxEstado.setItems(listEstado);
            if(remontajesList.get(i).getHabilitacionRemontaje()){
                comboBoxEstado.getSelectionModel().selectFirst();
            }else{
                comboBoxEstado.getSelectionModel().selectLast();
            }
            RemontajeConfiguracionTable remontajeTable = new RemontajeConfiguracionTable(remontajesList.get(i).getNumRemontaje(), remontajesList.get(i).getInicioRemontaje(), remontajesList.get(i).getFinRemontaje(), comboBoxEstado, checkBox_periocidad, btn_eliminar);
            datosTablaRemontaje.add(remontajeTable);
            eliminar(btn_eliminar,remontajeTable);
            ++i;
        }

        col_numeroRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable,Integer>("numeroRemontaje"));
        col_horaInicioRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable,String>("inicioRemontaje"));
        col_horaFinRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable,String>("finRemontaje"));
        col_estadoRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable,ComboBox<String>>("estadoRemontaje"));
        col_eliminar.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable,Button>("eliminar"));
        col_periocidad.setCellValueFactory(new PropertyValueFactory<RemontajeConfiguracionTable, CheckBox>("periocidad"));

        table_remontajes.getStyleClass().add("css/tableStyle.css");
        table_remontajes.setItems(datosTablaRemontaje);

    }

    public void initialize(){
        //inicializarSpinners();
    }

    public void setParent(Parent root) {
        this.root = root;
    }


    @FXML
    void agregarRemontaje(ActionEvent event) {
       System.out.println(horaInicio.getValue().toString());
        if(datosTablaRemontaje.size()>=4){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Limite de Remontajes alcanzado");
        }else if (datosTablaRemontaje.size()<4){
            ComboBox comboBox= new ComboBox<>();
            comboBox.setItems(listEstado);
            comboBox.getSelectionModel().select(0);
            CheckBox checkBox_periocidad = new CheckBox();
            checkBox_periocidad.setVisible(true);
            checkBox_periocidad.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            Button btn_eliminar = new Button();
            btn_eliminar.setText("Eliminar");
            RemontajeConfiguracionTable remontajeTable=new RemontajeConfiguracionTable((5-datosTablaRemontaje.size()), horaInicio.getValue().toString(), horaFin.getValue().toString(), comboBox, checkBox_periocidad, btn_eliminar);
            datosTablaRemontaje.add(remontajeTable);
            eliminar(btn_eliminar,remontajeTable);
        }
    }

    private ArrayList<String> getRemontajes(){
        ArrayList <String> arrayList_mensaje = new ArrayList<>();
        String estado="";


        for (RemontajeConfiguracionTable table: datosTablaRemontaje) {

            if(table.getEstadoRemontaje().getSelectionModel().getSelectedItem().equals("Habilitado")){
                estado = "1";
            }else{
                estado= "0";
            }
            arrayList_mensaje.add(estado);
            arrayList_mensaje.add(table.getInicioRemontaje());
            arrayList_mensaje.add(table.getFinRemontaje());
            arrayList_mensaje.add(getPeriocidad(table.getPeriocidad()));
        }

        return arrayList_mensaje;
    }

    private void eliminar(Button btn_eliminar, RemontajeConfiguracionTable remontajeTable){
         btn_eliminar.setOnAction(new EventHandler<ActionEvent>() {
            int i;
            @Override
            public void handle(ActionEvent event) {
                datosTablaRemontaje.remove(remontajeTable);
            }
        });

    }

    public void setIpTanque(String ipTanque) {
        this.ipTanque = ipTanque;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void setControladorTanqueUI(ControladorTanqueUI controladorTanqueUI) {
        this.controladorTanqueUI = controladorTanqueUI;
    }

    private int showAlertMessage() {
        int opcion=0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Envio de remontajes");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea enviar los remontajes configurados?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            opcion = 0;
        } else if (option.get() == ButtonType.OK) {
            opcion = 1;
        } else if (option.get() == ButtonType.CANCEL) {
            opcion = 2;
        } else {
            opcion=3;
        }

        return opcion;
    }

}
