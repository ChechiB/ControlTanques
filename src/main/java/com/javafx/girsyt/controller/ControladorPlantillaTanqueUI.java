package com.javafx.girsyt.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPlantillaTanqueUI implements Initializable {

    //Variables

    @FXML
    private Button btn_ver_tanque;

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
    private Label label_nro_tanque;


    @FXML
    private Label lbl_estadoTanque;

    @FXML
    private Pane pane_estadoTanque;

    private ControladorTanqueUI controladorTanqueUI;
    private Parent root2;
    private Stage stage;

    //Metodos


    public Label getLbl_estadoTanque() {
        return lbl_estadoTanque;
    }

    public void setLbl_estadoTanque(Label lbl_estadoTanque) {
        this.lbl_estadoTanque = lbl_estadoTanque;
    }

    public Pane getPane_estadoTanque() {
        return pane_estadoTanque;
    }

    public void setPane_estadoTanque(Pane pane_estadoTanque) {
        this.pane_estadoTanque = pane_estadoTanque;
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

    public Label getLabel_nro_tanque() {
        return label_nro_tanque;
    }

    public void setLabel_nro_tanque(Label label_nro_tanque) {
        this.label_nro_tanque = label_nro_tanque;
    }

    public Button getBtn_ver_tanque() {
        return btn_ver_tanque;
    }

    public void setBtn_ver_tanque(Button btn_ver_tanque) {
        this.btn_ver_tanque = btn_ver_tanque;
    }


    @FXML
    void mostrarTanque(ActionEvent event) {
        //Crear antes. Aca solo llamar a las GUIs correspondientes


        stage.show();

        /*
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/tanqueUI.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initScene(){
        stage= new Stage();
        stage.setScene(new Scene(root2));
    }

    public void setControladorTanqueUI(ControladorTanqueUI controladorTanqueUI) {
        this.controladorTanqueUI = controladorTanqueUI;

    }

    public void setParent(Parent root2) {
        this.root2 = root2;
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
}
