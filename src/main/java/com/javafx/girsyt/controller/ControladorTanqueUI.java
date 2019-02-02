package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.RemontajeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ControladorTanqueUI {

    private ControllerEnviarDatos controllerEnviarDatos;

    private Parent root2;

    private String ipTanque;

    private int puerto;

    private Stage remontajes;

    private String periocidad;

    @FXML
    private Pane pane_estadoTanque;

    @FXML
    private LineChart<String, Number> chart_temp;

    @FXML
    private HBox hbox_menu;

    @FXML
    private Pane hbox_pane_linea;

    @FXML
    private Pane hbox_pane_vbox;

    @FXML
    private Label label_nro_tanque;

    @FXML
    private TableView<RemontajeTable> table_remontajes;

    @FXML
    private TableColumn<RemontajeTable, String> col_numeroRemontaje;

    @FXML
    private TableColumn<RemontajeTable, String> col_horaInicioRemontaje;

    @FXML
    private TableColumn<RemontajeTable, String> col_horaFinRemontaje;

    @FXML
    private TableColumn<RemontajeTable, ImageView> col_estadoRemontaje;

    @FXML
    private Button btn_configurar_remontaje;

    @FXML
    private Button btn_enviar_remontaje;

    @FXML
    private Spinner<Double> spinner_temp_min;

    @FXML
    private Spinner<Double> spinner_temp_max;

    @FXML
    private Button btn_enviar_temp;

    @FXML
    private Label label_hora_dispositivo;

    @FXML
    private ImageView img_enfriado;

    @FXML
    private ImageView img_remontaje;

    @FXML
    private Label label_temp_actual;

    @FXML
    private Label label_temp_max;

    @FXML
    private Label label_temp_min;

    @FXML
    private Label label_estadoConexionTanque;

    private ObservableList<RemontajeTable> datosTablaRemontaje = FXCollections.observableArrayList();

    private ControladorRemontajesUI controladorRemontajesUI;

    private ArrayList<RemontajeDTO> remontaje = new ArrayList<>();

    private boolean tableEmpty = true;

    @FXML
    private Label lbl_estadoTanque;

    private XYChart.Series<String, Number> actual= new XYChart.Series<>();
    private XYChart.Series<String, Number> maxima= new XYChart.Series<>();
    private XYChart.Series<String, Number> minima= new XYChart.Series<>();
    private boolean estadoTanque = true;

    public Pane getPane_estadoTanque() {
        return pane_estadoTanque;
    }

    public void setPane_estadoTanque(Pane pane_estadoTanque) {
        this.pane_estadoTanque = pane_estadoTanque;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getPuerto(){
        return puerto;
    }

    public ArrayList<RemontajeDTO> getRemontaje() {
        return remontaje;
    }

    public void setRemontaje(ArrayList<RemontajeDTO> remontaje) {
        this.remontaje = remontaje;
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

    public TableColumn<RemontajeTable, ImageView> getCol_estadoRemontaje() {
        return col_estadoRemontaje;
    }

    public void setCol_estadoRemontaje(TableColumn<RemontajeTable, ImageView> col_estadoRemontaje) {
        this.col_estadoRemontaje = col_estadoRemontaje;
    }

    public ObservableList<RemontajeTable> getDatosTablaRemontaje() {
        return datosTablaRemontaje;
    }

    public void setDatosTablaRemontaje(ObservableList<RemontajeTable> datosTablaRemontaje) {
        this.datosTablaRemontaje = datosTablaRemontaje;
    }

    public ControladorRemontajesUI getControladorRemontajesUI() {
        return controladorRemontajesUI;
    }

    public void setControladorRemontajesUI(ControladorRemontajesUI controladorRemontajesUI) {
        this.controladorRemontajesUI = controladorRemontajesUI;
    }

    public Label getLabel_estadoConexionTanque() {
        return label_estadoConexionTanque;
    }

    public void setLabel_estadoConexionTanque(Label label_estadoConexionTanque) {
        this.label_estadoConexionTanque = label_estadoConexionTanque;
    }

    public ControllerEnviarDatos getControllerEnviarDatos() {
        return controllerEnviarDatos;
    }

    public void setControllerEnviarDatos(ControllerEnviarDatos controllerEnviarDatos) {
        this.controllerEnviarDatos = controllerEnviarDatos;
    }

    public String getIpTanque() {
        return ipTanque;
    }

    public void setIpTanque(String ipTanque) {
        this.ipTanque = ipTanque;
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public LineChart<?, ?> getChart_temp() {
        return chart_temp;
    }

    public void setChart_temp(LineChart<String, Number> chart_temp) {
        this.chart_temp = chart_temp;
    }

    public Label getLabel_nro_tanque() {
        return label_nro_tanque;
    }

    public void setLabel_nro_tanque(Label label_nro_tanque) {
        this.label_nro_tanque = label_nro_tanque;
    }

    public TableView<?> getTable_remontajes() {
        return table_remontajes;
    }

    public void setTable_remontajes(TableView<RemontajeTable> table_remontajes) {
        this.table_remontajes = table_remontajes;
    }

    public Button getBtn_configurar_remontaje() {
        return btn_configurar_remontaje;
    }

    public void setBtn_configurar_remontaje(Button btn_configurar_remontaje) {
        this.btn_configurar_remontaje = btn_configurar_remontaje;
    }

    public Button getBtn_enviar_remontaje() {
        return btn_enviar_remontaje;
    }

    public void setBtn_enviar_remontaje(Button btn_enviar_remontaje) {
        this.btn_enviar_remontaje = btn_enviar_remontaje;
    }

    public Spinner<Double> getSpinner_temp_min() {
        return spinner_temp_min;
    }

    public void setSpinner_temp_min(Spinner<Double> spinner_temp_min) {
        this.spinner_temp_min = spinner_temp_min;
    }

    public Spinner<Double> getSpinner_temp_max() {
        return spinner_temp_max;
    }

    public void setSpinner_temp_max(Spinner<Double> spinner_temp_max) {
        this.spinner_temp_max = spinner_temp_max;
    }

    public Button getBtn_enviar_temp() {
        return btn_enviar_temp;
    }

    public void setBtn_enviar_temp(Button btn_enviar_temp) {
        this.btn_enviar_temp = btn_enviar_temp;
    }

    public Label getLabel_hora_dispositivo() {
        return label_hora_dispositivo;
    }

    public void setLabel_hora_dispositivo(Label label_hora_dispositivo) {
        this.label_hora_dispositivo = label_hora_dispositivo;
    }

    public ImageView getImg_enfriado() {
        return img_enfriado;
    }

    public void setImg_enfriado(ImageView img_enfriado) {
        this.img_enfriado = img_enfriado;
    }

    public ImageView getImg_remontaje() {
        return img_remontaje;
    }

    public void setImg_remontaje(ImageView img_remontaje) {
        this.img_remontaje = img_remontaje;
    }

    public Label getLabel_temp_actual() {
        return label_temp_actual;
    }

    public void setLabel_temp_actual(Label label_temp_actual) {
        this.label_temp_actual = label_temp_actual;
    }

    public Label getLabel_temp_max() {
        return label_temp_max;
    }

    public void setLabel_temp_max(Label label_temp_max) {
        this.label_temp_max = label_temp_max;
    }

    public Label getLabel_temp_min() {
        return label_temp_min;
    }

    public void setLabel_temp_min(Label label_temp_min) {
        this.label_temp_min = label_temp_min;
    }

    @FXML
    void configurarRemontaje(ActionEvent event) throws IOException {

        if(tableEmpty){
            controladorRemontajesUI.setRemontajesConfiguracion(getRemontaje());
            tableEmpty = false;
        }


        remontajes.show();
    }

    @FXML
    void enviarTemperatura(ActionEvent event) {
        controllerEnviarDatos = new ControllerEnviarDatos();

    }

    public void setEstadoEnfriamiento(String estadoEnfriamiento){
        if (estadoEnfriamiento.equals("0")){
            getImg_enfriado().setImage(new Image("images/IconoLedAmarilloApagado.png"));
        }else{
            getImg_enfriado().setImage(new Image("images/IconoLedAmarillo.png"));
        }

    }

    public void setEstadoRemontaje(String estadoRemontaje){
        if (estadoRemontaje.equals("0")){
            getImg_remontaje().setImage(new Image("images/IconoLedRojoApagado.png"));
        }else{
            getImg_remontaje().setImage(new Image("images/IconoLedRojo.png"));
        }

    }

    public void setRemontajesTable(ArrayList<RemontajeDTO> remontaje) {

        this.remontaje = remontaje;
        System.out.println("");

        //Manejo de la tabla de remontajes
        int i=0;

        while(i <remontaje.size()) {
            ImageView imageViewEstadoRemontaje = new ImageView();

            if(remontaje.get(i).getHabilitacionRemontaje()){
                imageViewEstadoRemontaje.setImage(new Image("images/Checkmark_64px.png"));
                imageViewEstadoRemontaje.setFitHeight(20);
                imageViewEstadoRemontaje.setFitWidth(20);
            }else{

                imageViewEstadoRemontaje.setImage(new Image("images/Delete_64px.png"));
                imageViewEstadoRemontaje.setFitHeight(20);
                imageViewEstadoRemontaje.setFitWidth(20);
            }
            datosTablaRemontaje.add(new RemontajeTable(String.valueOf(remontaje.get(i).getNumRemontaje()), remontaje.get(i).getInicioRemontaje(), remontaje.get(i).getFinRemontaje(),imageViewEstadoRemontaje));
            ++i;
        }

        col_numeroRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("numeroRemontaje"));
        col_horaInicioRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("inicioRemontaje"));
        col_horaFinRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,String>("finRemontaje"));
        col_estadoRemontaje.setCellValueFactory(new PropertyValueFactory<RemontajeTable,ImageView>("estadoRemontaje"));
        table_remontajes.setItems(datosTablaRemontaje);

    }

    private void inicializarSpinners(){
        //No aumenta la parte fraccionaria https://medium.com/@joshwickham/creating-an-integer-spinner-in-javafx-f8fda8d12ae5
        SpinnerValueFactory<Double>  value = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100,0.0,0.1);
        SpinnerValueFactory<Double>  value2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100,0.0, 0.1);

        getSpinner_temp_min().setValueFactory(value);
        getSpinner_temp_min().getEditor().setAlignment(Pos.BASELINE_RIGHT);
        getSpinner_temp_max().setValueFactory(value2);
        getSpinner_temp_max().getEditor().setAlignment(Pos.BASELINE_RIGHT);


    }

    public void updateTemperaturaLineChart(){
        minima.getData().add(new XYChart.Data<String, Number>(getLabel_hora_dispositivo().textProperty().getValue(),Double.parseDouble(getLabel_temp_min().textProperty().getValue())));
        maxima.getData().add(new XYChart.Data<String, Number>(getLabel_hora_dispositivo().textProperty().getValue(),Double.parseDouble(getLabel_temp_max().textProperty().getValue())));

        //Colocar en un array las temperaturas para poder graficar. Sino solo grafica un punto.
        actual.getData().add(new XYChart.Data<String, Number>(getLabel_hora_dispositivo().textProperty().getValue(),Double.parseDouble(getLabel_temp_actual().textProperty().getValue())));
        actual.setName("Temperatura");

    }

    public void inicializarMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/vboxMenu2.fxml"));
        VBox vboxMenu = fxmlLoader.load();
        ControladorMenuTanque controladorMenuTanque = fxmlLoader.getController();
        controladorMenuTanque.setIpTanque(getIpTanque());
        controladorMenuTanque.setPuerto(getPuerto());

        if(estadoTanque){
            getLabel_estadoConexionTanque().setText("CONECTADO");
            controladorMenuTanque.getBtn_conectarMenu().setText("Desconectado");
            //Bit 1 indica que quiere recibir datos
            //Bit 0 indica que desea dejar de recibir datos
            controladorMenuTanque.setEstadoConexion(0);
            //controladorMenuTanque.getBtn_conectarMenu().getGraphic()..clipProperty().setValue(new ImageView(new Image("images/Stop_48px.png")));

        }else{
            getLabel_estadoConexionTanque().setText("DESCONECTADO");
            getPane_estadoTanque().setStyle("-fx-background-color: #d2302e");
            controladorMenuTanque.getBtn_conectarMenu().setText("Conectar");
            controladorMenuTanque.setEstadoConexion(1);
        }
        hbox_pane_vbox.getChildren().add(vboxMenu);
    }


    @FXML
    public void initialize() throws IOException {
       inicializarSpinners();
       inicializarConfiguracionRemontajesUI();
    }

    public void inicializarLineChart() {
        /*xAxis = new NumberAxis("nombre",0.0,100.0,10.0);
        final CategoryAxis yAxis = new CategoryAxis();
        chart_temp = new LineChart(xAxis, yAxis);*/

        minima = new XYChart.Series<>();
        maxima = new XYChart.Series<>();
        actual = new XYChart.Series<>();

        minima.setName("Minima");
        maxima.setName("Maxima");
        actual.setName("Actual");

        chart_temp.getData().add(actual);
        chart_temp.getData().add(minima);
        chart_temp.getData().add(maxima);


    }

    private void inicializarConfiguracionRemontajesUI() throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/views/remontajeUI.fxml"));
        Parent root = (Parent) fxml.load();

        controladorRemontajesUI = new ControladorRemontajesUI();
        controladorRemontajesUI = fxml.getController();
        controladorRemontajesUI.setParent(root);
        remontajes = new Stage();
        remontajes.setScene(new Scene(root));
    }

    public void setEstadoTanque(boolean estadoTanque){
        this.estadoTanque = estadoTanque;
    }

    public boolean getEstadoTanque(){
        return estadoTanque;
    }


}
