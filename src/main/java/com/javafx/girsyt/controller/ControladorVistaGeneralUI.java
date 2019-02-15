package com.javafx.girsyt.controller;

import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.jfoenix.controls.JFXMasonryPane;
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
import javafx.scene.layout.TilePane;
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
    private TilePane tilePane_tanque;

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
    private JFXMasonryPane paneMasonry;

    private List<ControladorPlantillaTanqueUI> controladorPlantillaTanqueUIList = new ArrayList<ControladorPlantillaTanqueUI>();
    private List<ControladorTanqueUI> controladorTanqueUIList= new ArrayList<>();
    private boolean ready = false;
    private ControllerEstablecerConexion controllerEstablecerConexion;
    private ConectarBackground conectarBackground;
    private DatosTanqueGuiDTO mensajeRecibido;
    private HiloConsumidor hiloConsumidor;
    private Parent root,root2;
    private boolean estadoConexion = false;
    private Thread t;
    private int posColumna = 0, posFila = 0;
    private List<List<Object>> tanquesList = new ArrayList<List<Object>>();



    @FXML
    private void conectar(ActionEvent event) throws IOException {

        controllerEstablecerConexion = new ControllerEstablecerConexion();
        conectarBackground =new ConectarBackground();
        label_estadoServicio.textProperty().bind(conectarBackground.messageProperty());

        System.out.println(conectarBackground.getState());

        conectarBackground.start();


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("En start");
            label_vista_general.textProperty().unbind();
            label_vista_general.textProperty().bind(conectarBackground.messageProperty());

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


    private class HiloConsumidor extends Service<DatosTanqueGuiDTO>{

        @Override
        protected Task<DatosTanqueGuiDTO> createTask() {
            return new Task<DatosTanqueGuiDTO>() {
                @Override
                protected DatosTanqueGuiDTO call() throws Exception {
                    mensajeRecibido = controllerEstablecerConexion.actualizarGUI();
                    System.out.println("Codigo operacion: " + mensajeRecibido.getCodigoOperacion());
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
                                            controladorPlantillaTanqueUI = new ControladorPlantillaTanqueUI();
                                            controladorPlantillaTanqueUI = fxmlLoader.getController();
                                            controladorPlantillaTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/views/tanqueUI.fxml"));
                                             root2 = (Parent) fxmlLoader2.load();

                                            ControladorTanqueUI controladorTanqueUI = fxmlLoader2.getController();
                                            controladorTanqueUI.getLabel_nro_tanque().setText(mensajeRecibido.getIdTanque());
                                            controladorTanqueUI.getLabel_estadoConexionTanque().setText("CONECTADO");
                                            controladorTanqueUI.setIpTanque(mensajeRecibido.getDireccionIP());
                                            controladorTanqueUI.setPuerto(mensajeRecibido.getPort());
                                            controladorTanqueUI.setControladorPlantilla(controladorPlantillaTanqueUI);

                                            controladorTanqueUI.inicializarMenu();

                                            controladorPlantillaTanqueUI.setParent(root2);
                                            controladorPlantillaTanqueUI.initScene();
                                            controladorPlantillaTanqueUI.setControladorTanqueUI(controladorTanqueUI);
                                            controladorPlantillaTanqueUIList.add(controladorPlantillaTanqueUI);
                                            controladorTanqueUIList.add(controladorTanqueUI);
                                            tanquesList.add(Arrays.asList(controladorPlantillaTanqueUI,controladorPlantillaTanqueUI));
                                            System.out.println("Tamaño " + controladorPlantillaTanqueUIList.size());

                                            paneMasonry.getChildren().add(root);
                                            System.out.println("Pos columna " + posColumna);
                                            ++posColumna;

                                            ControllerEnviarDatos controllerEnviarDatos = new ControllerEnviarDatos();
                                            controllerEnviarDatos.enviarDatos(1, controladorTanqueUI.getIpTanque(),controladorTanqueUI.getPuerto());

                                            //Cuando ya haya cuatro tanques colocar nueva fila
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    break;

                                case 1:
                                    int i=0;
                                    int j= 0;
                                    while(i<controladorPlantillaTanqueUIList.size()){

                                        //Comparar instancias de controlador para actualizar el tanque correcto
                                        if(controladorPlantillaTanqueUIList.get(i).getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque())) {
                                           controladorPlantillaTanqueUIList.get(i).getLabel_temp_min().textProperty().setValue(String.valueOf(mensajeRecibido.getTemperaturaMinimaInicial()));
                                           controladorPlantillaTanqueUIList.get(i).getLabel_temp_max().textProperty().setValue(String.valueOf(mensajeRecibido.getTemperaturaMaximaInicial()));

                                            while (j<controladorTanqueUIList.size()) {
                                                if (controladorTanqueUIList.get(j).getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque())) {
                                                    controladorTanqueUIList.get(i).getLabel_temp_min().textProperty().setValue(String.valueOf(mensajeRecibido.getTemperaturaMinimaInicial()));
                                                    controladorTanqueUIList.get(i).getLabel_temp_max().textProperty().setValue(String.valueOf(mensajeRecibido.getTemperaturaMaximaInicial()));
                                                    controladorTanqueUIList.get(i).setRemontaje(mensajeRecibido.getRemontaje());
                                                    controladorTanqueUIList.get(i).setRemontajesTable(mensajeRecibido.getRemontaje());
                                                    controladorTanqueUIList.get(i).inicializarLineChart();
                                                }
                                                ++j;
                                            }
                                        }
                                        ++i;

                                    }
                                    break;

                                case 2:
                                    i=0;
                                    j= 0;

                                    while(i<controladorPlantillaTanqueUIList.size()){
                                        System.out.println("En while case 2");
                                        //Comparar instancias de controlador para actualizar el tanque correcto
                                        if(controladorPlantillaTanqueUIList.get(i).getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque())) {
                                            controladorPlantillaTanqueUIList.get(i).getLabel_temp_actual().textProperty().setValue(mensajeRecibido.getTemperaturaActual());
                                            controladorPlantillaTanqueUIList.get(i).setEstadoEnfriamiento(mensajeRecibido.getEstadoEnfriamiento());
                                            controladorPlantillaTanqueUIList.get(i).setEstadoRemontaje(mensajeRecibido.getEstadoRemontaje());

                                            while (j<controladorTanqueUIList.size()) {
                                                if (controladorTanqueUIList.get(j).getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque())) {
                                                    System.out.println("En case 1 GUI");
                                                    //controladorPlantillaTanqueUIList.get(i).getLabel_temp_min().setText(mensajeRecibido.getTemperaturaMinimaInicial());

                                                    controladorTanqueUIList.get(j).getLabel_temp_actual().textProperty().setValue(mensajeRecibido.getTemperaturaActual());
                                                    controladorTanqueUIList.get(j).setEstadoEnfriamiento(mensajeRecibido.getEstadoEnfriamiento());
                                                    controladorTanqueUIList.get(j).setEstadoRemontaje(mensajeRecibido.getEstadoRemontaje());
                                                    controladorTanqueUIList.get(j).getLabel_hora_dispositivo().textProperty().setValue(mensajeRecibido.getHoraDispositivo());
                                                    controladorTanqueUIList.get(i).updateTemperaturaLineChart();

                                                }
                                                ++j;
                                            }
                                        }
                                        ++i;

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
               if (!(iteratorLista.next().getLabel_nro_tanque().getText().equals(mensajeRecibido.getIdTanque()))){
                   unicidad = true;
               }

           }
        }
        return unicidad;
    }


}