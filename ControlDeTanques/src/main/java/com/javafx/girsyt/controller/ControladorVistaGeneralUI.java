package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.javafx.girsyt.entidad.Consumidor;
import com.javafx.girsyt.entidad.DTOMensaje;
import com.javafx.girsyt.entidad.ServidorHiloImp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorVistaGeneralUI extends Application{

    @FXML
    private Button btn_sincronizar;

    @FXML
    private Button btn_conectar;

    @FXML
    private GridPane gridPane_plantilla = new GridPane();

    @FXML
    private Pane pane_vista_general;
    @FXML
    private Label label_vista_general;

    @FXML
    private ControladorPlantillaTanqueUI controladorPlantillaTanqueUI;


    @FXML
    private Pane panel_tanque;

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
    private List<ControladorPlantillaTanqueUI> controladorPlantillaTanqueUIList = new ArrayList<ControladorPlantillaTanqueUI>();
    private boolean ready = false;

    private ControllerEstablecerConexion controllerEstablecerConexion;
    private ControladorSincronizarTanques controladorSincronizarTanques;
    private ConectarBackground conectarBackground;
 //   private Consumidor consumidor;
    private DatosTanqueGuiDTO mensajeRecibido;
    private HiloConsumidor hiloConsumidor;
    private Parent root,root2;
    private boolean estadoConexion = false;
    private Thread t;
    private int posColumna = 0, posFila = 0;


    @FXML
    private void conectar(ActionEvent event) throws IOException {

        controllerEstablecerConexion = new ControllerEstablecerConexion();
        conectarBackground =new ConectarBackground();
        label_vista_general.textProperty().bind(conectarBackground.messageProperty());


        System.out.println(conectarBackground.getState());

        conectarBackground.start();


    }


    @FXML
    void mostrarTanque(ActionEvent event) {

    }

    @FXML
    void sincronizarTanques(ActionEvent event) {
        ready = true;
        controladorSincronizarTanques.sincronizar(ready);
        //lamar desde este controlador al controlador del consumidor
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        label_vista_general.textProperty().unbind();
        label_vista_general.textProperty().bind(conectarBackground.messageProperty());
        System.out.println("En Start");


    }


    private void prueba(){
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call()  {
                hiloConsumidor = new HiloConsumidor();
                hiloConsumidor.start();
                return null;
            }
        };
        //start Task

        Thread t = new Thread(task);
        t.setDaemon(true); // thread will not prevent application shutdown
        t.start();


    }


    private class ConectarBackground extends Service<String>{


        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    System.out.println("Call");
                    String estado=controllerEstablecerConexion.establecerConexion();
                    updateMessage(estado);


                        //start Task
                        Task<Void> recibirDatos = new Task<Void>(){
                            @Override
                            protected Void call()  {
                                controllerEstablecerConexion.recibirMensajes();
                                return null;
                            }
                        };
                        Thread t2 = new Thread(recibirDatos);
                        t2.setDaemon(true); // thread will not prevent application shutdown
                        t2.start();

                    hiloConsumidor = new HiloConsumidor();
                    hiloConsumidor.start();

                    /*    Task<Void> consumirDatos = new Task<Void>(){
                                @Override
                                protected Void call()  {
                                    System.out.println("En call Consumir datos");
                                    hiloConsumidor = new HiloConsumidor();
                                    hiloConsumidor.start();
                                    return null;
                                }
                        };

                    t = new Thread(consumirDatos);

                    System.out.println("En while consumidor");

                    t.start();
                   */



                    return null;
                }

            };

        }
    }


    private class ConsumidorHilo extends Task<DatosTanqueGuiDTO>{


        @Override
        protected DatosTanqueGuiDTO call() throws Exception {
                while (estadoConexion){
                    //obtener primero el consumidor
                    //despues obtener el Dto
                    //Separacion de funciones como en Servidor
                    //Mirar video https://www.youtube.com/watch?v=y43Ra15sd7k&t=2662s
                    controllerEstablecerConexion.actualizarGUI();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                }

            return null;
        }
    }


    private class HiloConsumidor extends Service<DatosTanqueGuiDTO>{

        @Override
        protected Task<DatosTanqueGuiDTO> createTask() {
            return new Task<DatosTanqueGuiDTO>() {
                @Override
                protected DatosTanqueGuiDTO call() throws Exception {
                    System.out.println("En hilo consumidor");
                    mensajeRecibido = controllerEstablecerConexion.actualizarGUI();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (mensajeRecibido.getCodigoOperacion()){
                                case 0:
                                    try {
                                        // root = loadUI("/views/plantillaTanqueUI");
                                        System.out.println(mensajeRecibido.getIdTanque());
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/plantillaTanqueUI.fxml"));

                                        root = (Parent) fxmlLoader.load();
                                        ControladorPlantillaTanqueUI controladorPlantillaTanqueUI = fxmlLoader.getController();
                                        controladorPlantillaTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/views/tanqueUI.fxml"));
                                        //En vez de un array, armar Hashmap con el controladorPlantillaTanque y controladorTanque
                                        root2 = (Parent) fxmlLoader2.load();
                                        ControladorTanqueUI controladorTanqueUI = fxmlLoader2.getController();
                                        controladorTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                        controladorPlantillaTanqueUI.setParent(root2);
                                        controladorPlantillaTanqueUI.initScene();
                                        controladorPlantillaTanqueUI.setControladorTanqueUI(controladorTanqueUI);
                                        controladorPlantillaTanqueUIList.add(controladorPlantillaTanqueUI);
                                        //gridPane_plantilla.add(root,posColumna,posFila);
                                        gridPane_plantilla.addColumn(posColumna,root);
                                        //Setear los datos del tanque
                                        //New tanque con sus respsctivos IDs. Crear array
                                        ++posColumna;
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    break;
                            }
                            hiloConsumidor.restart();

                        }

                    });
                    return mensajeRecibido;
                }



            };
        }


    }

}