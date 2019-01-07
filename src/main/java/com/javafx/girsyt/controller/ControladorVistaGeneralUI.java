package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

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
    private Label label_estadoServicio;

    @FXML
    private List<ControladorPlantillaTanqueUI> controladorPlantillaTanqueUIList = new ArrayList<ControladorPlantillaTanqueUI>();
    private boolean ready = false;

    private ControllerEstablecerConexion controllerEstablecerConexion;
   // private ControladorSincronizarTanques controladorSincronizarTanques;
    private ConectarBackground conectarBackground;
 //   private Consumidor consumidor;
    private DatosTanqueGuiDTO mensajeRecibido;
    private HiloConsumidor hiloConsumidor;
    private Parent root,root2;
    private boolean estadoConexion = false;
    private Thread t;
    private int posColumna = 0, posFila = 0;
    private HashMap <ControladorPlantillaTanqueUI,ControladorTanqueUI> hasmapControladores;
    private HashMap<Integer, ControladorPlantillaTanqueUI> grillaTanque;


    @FXML
    private void conectar(ActionEvent event) throws IOException {

        controllerEstablecerConexion = new ControllerEstablecerConexion();
        conectarBackground =new ConectarBackground();
        label_estadoServicio.textProperty().bind(conectarBackground.messageProperty());


        System.out.println(conectarBackground.getState());

        conectarBackground.start();


    }


    @FXML
    void mostrarTanque(ActionEvent event) {

    }

    @FXML



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
                    String estado = controllerEstablecerConexion.establecerConexion();
                    label_estadoServicio.setStyle("-fx-text-fill: green");
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
                                    if (verificarUnicidad(mensajeRecibido,controladorPlantillaTanqueUIList)){
                                        try {
                                            System.out.println("En try");
                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/plantillaTanqueUI.fxml"));
                                            Parent root = fxmlLoader.load();
                                            ControladorPlantillaTanqueUI controladorPlantillaTanqueUI = new ControladorPlantillaTanqueUI();
                                            controladorPlantillaTanqueUI = fxmlLoader.getController();
                                            controladorPlantillaTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/views/tanqueUI.fxml"));
                                            root2 = (Parent) fxmlLoader2.load();
                                            ControladorTanqueUI controladorTanqueUI = fxmlLoader2.getController();
                                            controladorTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                            controladorPlantillaTanqueUI.setParent(root2);
                                            controladorPlantillaTanqueUI.initScene();
                                            controladorPlantillaTanqueUI.setControladorTanqueUI(controladorTanqueUI);
                                            controladorPlantillaTanqueUIList.add(controladorPlantillaTanqueUI);
                                            System.out.println("Tamaño " + controladorPlantillaTanqueUIList.size());
                                            gridPane_plantilla.addColumn(posColumna, root);
                                            ++posColumna;

                                            //Cuando ya haya cuatro tanques colocar nueva fila
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                    break;
                            }}

                            hiloConsumidor.restart();

                        }
                    });
                    return mensajeRecibido;
                }



            };
        }


    }

    private boolean verificarUnicidad(DatosTanqueGuiDTO mensajeRecibido, List<ControladorPlantillaTanqueUI> controladorPlantillaTanqueUIList) {
        boolean unicidad = false;// ya esta en la lista
        Iterator<ControladorPlantillaTanqueUI> iteratorLista;
        //Si la lista esta vacía devolver true

        if (controladorPlantillaTanqueUIList == null || controladorPlantillaTanqueUIList.isEmpty()){
            unicidad = true;
        }else if(!(controladorPlantillaTanqueUIList == null)){
            System.out.println("Size lista: " +controladorPlantillaTanqueUIList.size());
            iteratorLista = controladorPlantillaTanqueUIList.iterator();

           while(iteratorLista.hasNext()){
               System.out.println("While iterator");
               //System.out.println("Id tanque lista: " + iteratorLista.next().getLabel_nro_tanque().getText());
               System.out.println("Id tanque recibido:" + mensajeRecibido.getIdTanque());
               if (!(iteratorLista.next().getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque()))){
                   unicidad = true;
               }

           }
        }
        return unicidad;
    }


}