package com.javafx.girsyt.controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ControladorMenuTanque {

    @FXML
    private Button btn_conectarMenu;

    @FXML
    private Button btn_sincronizarMenu;

    @FXML
    private Button btn_historialMenu;

    @FXML
    private VBox vBox_menu;

    @FXML
    private VBox vBox_menuButtons;

    @FXML
    private ImageView imgView_conexion;

    private ControllerEnviarDatos controllerEnviarDatos;

    private String ipTanque;

    private int puerto;
    private int estadoConexion;


    public Button getBtn_conectarMenu() {
        return btn_conectarMenu;
    }

    public void setBtn_conectarMenu(Button btn_conectarMenu) {
        this.btn_conectarMenu = btn_conectarMenu;
    }

    public Button getBtn_sincronizarMenu() {
        return btn_sincronizarMenu;
    }

    public void setBtn_sincronizarMenu(Button btn_sincronizarMenu) {
        this.btn_sincronizarMenu = btn_sincronizarMenu;
    }

    public Button getBtn_historialMenu() {
        return btn_historialMenu;
    }

    public void setBtn_historialMenu(Button btn_historialMenu) {
        this.btn_historialMenu = btn_historialMenu;
    }

    public VBox getvBox_menu() {
        return vBox_menu;
    }

    public void setvBox_menu(VBox vBox_menu) {
        this.vBox_menu = vBox_menu;
    }

    public VBox getvBox_menuButtons() {
        return vBox_menuButtons;
    }

    public void setvBox_menuButtons(VBox vBox_menuButtons) {
        this.vBox_menuButtons = vBox_menuButtons;
    }

    public ControllerEnviarDatos getControllerEnviarDatos() {
        return controllerEnviarDatos;
    }

    public void setControllerEnviarDatos(ControllerEnviarDatos controllerEnviarDatos) {
        this.controllerEnviarDatos = controllerEnviarDatos;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getPuerto(){
        return puerto;
    }

    public ImageView getImgView_conexion() {
        return imgView_conexion;
    }

    public void setImgView_conexion(ImageView imgView_conexion) {
        this.imgView_conexion = imgView_conexion;
    }

    public String getIpTanque() {
        return ipTanque;
    }

    private void setBehaviorButtons(){

        this.setColorButtonHover(btn_conectarMenu);
        this.setColorButtonHover(btn_historialMenu);
        this.setColorButtonHover(btn_sincronizarMenu);


        for (Node node: vBox_menuButtons.getChildren()) {
            System.out.println("En for nodes");

            if(node.getAccessibleText() != null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) ->{
                    switch (node.getAccessibleText()){
                        case "conectar":
                            ControlBotones controlBotones = new ControlBotones();
                            controlBotones.start();
                            imgView_conexion.setImage(new Image("images/DataTransfer_50px.png"));
                            break;
                        case "sincronizar":
                            StringBuffer horaFecha = new StringBuffer();
                            horaFecha = horaFecha.append(this.getHoraActual());
                            horaFecha = horaFecha.append("-");
                            horaFecha = horaFecha.append(this.getFechaActual());
                            horaFecha = horaFecha.append("-");
                            horaFecha = horaFecha.append(this.getDiaSemana()).append("-");
                            controllerEnviarDatos = new ControllerEnviarDatos();
                            try {
                                controllerEnviarDatos.enviarDatos(horaFecha,getIpTanque(),getPuerto());
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case "historial":

                            try {
                                FXMLLoader fxmlHistorial = new FXMLLoader(getClass().getResource("/views/historialTemperaturaUI.fxml"));
                                Parent historialRoot = (Parent) fxmlHistorial.load();

                                Stage historialStage = new Stage();
                                historialStage.setScene(new Scene(historialRoot));
                                historialStage.show();
                                System.out.println("En historial");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                    }
                        }

                        );
            }
            
        }

    }


    private void setColorButtonHover(Button button) {

            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button.setStyle("-fx-background-color: #c0c0c0");
                }
            });

            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button.setStyle("-fx-background-color: #ebebe0");
                }
            });
    }

    public void initialize(){
        setBehaviorButtons();
    }

    private String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        return formateador.format(ahora);
    }

    private String getFechaActual() {
        Date ahora = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        return dateFormat.format(ahora);
    }

    private String getDiaSemana(){
        Calendar c = Calendar.getInstance();
        String diaSemana = null;
        int dia =  c.get(Calendar.DAY_OF_WEEK);
        switch (dia) {
            case Calendar.SUNDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.MONDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.TUESDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.WEDNESDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.THURSDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.FRIDAY:
                diaSemana = Integer.toString(dia);
                break;
            case Calendar.SATURDAY:
                diaSemana = Integer.toString(dia);
                break;
            default:
                break;
        }

        return diaSemana;
    }

    public void setIpTanque(String ipTanque) {
        this.ipTanque = ipTanque;
    }


    public void setEstadoConexion(int estadoConexion) {
        this.estadoConexion = estadoConexion;
    }

    private class ControlBotones extends Service<Integer> {
        int i;

        @Override
        protected Task<Integer> createTask() {
            return new Task<Integer>(){

                @Override
                protected Integer call() throws Exception {
                    controllerEnviarDatos = new ControllerEnviarDatos();

                    Task<Integer> enviar = new Task<Integer>(){
                        @Override
                        protected Integer call() throws IOException {
                             i =controllerEnviarDatos.enviarDatos(estadoConexion,ipTanque,puerto);
                             if(i ==0){

                             }
                            return i;
                        }
                    };

                    Thread t2 = new Thread(enviar);
                    t2.setDaemon(true); // thread will not prevent application shutdown
                    t2.start();

                    return i;
                }
            };
        }
    }
}
