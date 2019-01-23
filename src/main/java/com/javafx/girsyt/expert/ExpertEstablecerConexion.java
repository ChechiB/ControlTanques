package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.controller.ControllerEstablecerConexion;
import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.javafx.girsyt.dto.PaqueteRecibidoDTO;
import com.javafx.girsyt.entidad.TanqueImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExpertEstablecerConexion {
    private AdaptadorUDP adaptadorUDP;
    private ControllerEstablecerConexion controllerEstablecerConexion;
    private DatosTanqueGuiDTO datosTanqueGuiDTO;
    private ArrayList<TanqueImp> tanqueImpArrayList = new ArrayList<TanqueImp>();
    private TanqueImp tanqueImp;
    private String direccionIP;

    public ArrayList<TanqueImp> getTanqueImpArrayList() {
        return tanqueImpArrayList;
    }

    public void setTanqueImpArrayList(ArrayList<TanqueImp> tanqueImpArrayList) {
        this.tanqueImpArrayList = tanqueImpArrayList;
    }


    public String establecerConexion() throws IOException {

            System.out.print("En experto establecer conexion");
            adaptadorUDP = (AdaptadorUDP) FactoriaAdaptadorConexion.getInstance().crearAdaptadorConexion("AdaptadorUDP");
            System.out.println("Abriendo Puerto");

        return adaptadorUDP.establecerConexion();

    }

    public void recibirMensajes() {
        System.out.println("Por recibir mensaje");
        adaptadorUDP.recibirMensaje();

    }

    public DatosTanqueGuiDTO actualizarGUI() {

        String mensaje[] = adaptadorUDP.getMensaje();
        datosTanqueGuiDTO = new DatosTanqueGuiDTO();

        String[] parts = mensaje[0].split("-");

        String[] datosTanque = mensaje[1].split("-");

        System.out.println(mensaje[1]);

        switch (Integer.parseInt(parts[0])) {
            //Codigo de operacion == 0 --> Mensaje de configuracion de tanques
            //Setear creacion de objeto Tanque, Remontaje, Temperatura, etc; de acuerdo al codigo de operacion
            case 0:
                System.out.println("Creando Tanque y DTO");
                //Creacion de Tanque
                TanqueImp tanqueImp = new TanqueImp();

                for (int i = 0; i < parts.length; i++) {
                    switch (i) {
                        case 0:
                            datosTanqueGuiDTO.setCodigoOperacion(Integer.parseInt(parts[i]));
                            break;
                        case 1:
                            datosTanqueGuiDTO.setIdTanque(parts[i].substring(parts[i].length()-2, parts[i].length()));
                            tanqueImp.setIdTanque(Integer.parseInt(parts[i].substring(parts[i].length()-2, parts[i].length())));
                            datosTanqueGuiDTO.setDireccionIP(parts[i]);
                            tanqueImp.setDireccionIP(parts[i]);
                            break;
                        case 2:
                            datosTanqueGuiDTO.setPort(Integer.parseInt(parts[i]));
                            tanqueImp.setPuerto(Integer.parseInt(parts[i]));
                            break;
                    }
                }

                tanqueImpArrayList.add(tanqueImp);
                break;

            case 1:
                int i = 0;
                TanqueImp tanqueImp1 = null;
                try{
                    System.out.println(datosTanque);
                    // #TODO: Aca hace cosas raras, revisar el getTanque. Te amooouuuuu <3
                    tanqueImp1 = getTanque(datosTanque); //Agregar If con tanque distinto de nulo
                }catch( Exception e){
                    System.out.println("tanqueImp1: " + tanqueImp1);
                }

                if (tanqueImp1!=null) {
                    datosTanqueGuiDTO.setCodigoOperacion(1);
                    //Es necesario crear un DTO nuevo?
                    datosTanqueGuiDTO = new DatosTanqueGuiDTO();
                    if (parts[i + 1].length() == 2) {

                        //Datos estaticos. Se recibe una vez
                        datosTanqueGuiDTO.setTipoPaquete("estatico");
                        datosTanqueGuiDTO.setBandera(0);

                        for (i = 1; i < parts.length; ++i) {
                            //Crear DtoRemontaje y asociarlo al tanque con temperatura y demas
                            switch (i) {
                                case 1:
                                    datosTanqueGuiDTO.setIdTanque(parts[i]);
                                    break;
                                //Remontajes
                                //Inicio primer remontaje
                                //Seria bueno para automatizar las cantidad de remontajes, analizar la estructura del mensaje segun eso determinar si es remontaje, direccionIp, etc--> Analizador sintactico
                                case 4:
                                    tanqueImp1.setRemontaje(parts[2], parts[3], parts[4]);
                                    break;
                                case 7:
                                    tanqueImp1.setRemontaje(parts[5], parts[6], parts[7]);
                                    break;
                                case 10:
                                    tanqueImp1.setRemontaje(parts[8], parts[9], parts[10]);
                                    break;
                                case 13:
                                    tanqueImp1.setRemontaje(parts[11], parts[12], parts[13]);
                                    break;
                                case 14: //Habilitacion periocidad
                                    tanqueImp1.setPeriocidad(parts[i]);
                                    datosTanqueGuiDTO.setPeriocidad(parts[i]);
                                    break;
                                case 15:      //Temperatura Máxima
                                    tanqueImp1.setTempMaxima(Double.parseDouble(parts[i]));
                                    datosTanqueGuiDTO.setTemperaturaMaximaInicial(parts[i]);
                                    break;
                                case 16:   //Temperatura Mínima
                                    tanqueImp1.setTempMinima(Double.parseDouble(parts[i]));
                                    break;
                            }
                        }
                    }else if(parts[i].length() ==1){
                        //Datos dinamicos
                     /*
                            a-b-cc,c-dd:dd
                               a: estado de remontaje 1 --> Si esta remontando
                               b: estado de enfriado 1 --> Si esta Enfriando
                               c: temperatura actual 4
                               d: hora del equipo 5
                     */
                        System.out.print("Dinamico");
                        datosTanqueGuiDTO.setCodigoOperacion(1);
                        datosTanqueGuiDTO.setBandera(1);
                        tanqueImp1.setTipoPaquete("dinamico");
                        datosTanqueGuiDTO.setIdTanque((String.valueOf(tanqueImp1.getIdTanque())));
                        for (i = 1; i < parts.length; ++i){
                            
                            
                            switch (i){
                                case 1:
                                    datosTanqueGuiDTO.setEstadoRemontaje(parts[i]);
                                    tanqueImp1.setEstadoRemontaje(parts[i]);
                                    break;
                                case 2:
                                    datosTanqueGuiDTO.setEstadoEnfriamiento(parts[i]);
                                    tanqueImp1.setEstadoEnfriamiento(parts[i]);
                                    break;
                                case 3:
                                    datosTanqueGuiDTO.setTemperaturaActual(parts[i]);
                                    tanqueImp1.setTemperaturaActual(parts[i]);
                                    break;
                                case 4:
                                    datosTanqueGuiDTO.setHoraDispositivo(parts[i]);
                                    tanqueImp1.setHoraDispositivo(parts[i]);
                                    break;
                            }
                        }
                    }


                }


        }
        return datosTanqueGuiDTO;
    }



    private TanqueImp getTanque (String[]datosTanque){
        //Agregar execpcion si no hay tanques

        TanqueImp tanqueImp = new TanqueImp();
        int i = 0;

        //Si la lista esta vacía devolver true

        if (!(tanqueImpArrayList == null || tanqueImpArrayList.isEmpty())) {
            while (i < tanqueImpArrayList.size()) {
                if (tanqueImpArrayList.get(i).getDireccionIP().equals(datosTanque[0].trim())) {
                    tanqueImp = tanqueImpArrayList.get(i);
                }else{
                    tanqueImp = null;
                }
                ++i;
            }
        }else {
            tanqueImp = null;
        }

        return tanqueImp;
    }
}
