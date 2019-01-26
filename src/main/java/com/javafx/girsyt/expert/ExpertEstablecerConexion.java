package com.javafx.girsyt.expert;

import com.javafx.girsyt.adaptador.AdaptadorUDP;
import com.javafx.girsyt.adaptador.FactoriaAdaptadorConexion;
import com.javafx.girsyt.controller.ControllerEstablecerConexion;
import com.javafx.girsyt.dto.DatosTanqueGuiDTO;
import com.javafx.girsyt.dto.RemontajeDTO;
import com.javafx.girsyt.entidad.TanqueImp;

import java.io.IOException;
import java.util.ArrayList;

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
        TanqueImp tanqueImp = null;
        String[] parts = mensaje[0].split("-");

        String[] datosTanque = mensaje[1].split("-");

        System.out.println(mensaje[1]);

        switch (Integer.parseInt(parts[0])) {
            //Codigo de operacion == 0 --> Mensaje de configuracion de tanques
            case 0:

               if(existenciaTanque(datosTanque)){

                   tanqueImp = new TanqueImp();

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
                }
                break;

            case 1:
                int i = 0;
                try{
                    tanqueImp = getTanque(datosTanque); //Agregar If con tanque distinto de nulo
                }catch( Exception e){
                    System.out.println("tanqueImp1: " + tanqueImp);
                }

                if (tanqueImp!=null) {

                    //Es necesario crear un DTO nuevo?


                        datosTanqueGuiDTO.setCodigoOperacion(1);
                        //Datos estaticos. Se recibe una vez
                        datosTanqueGuiDTO.setTipoPaquete("estatico");
                        datosTanqueGuiDTO.setBandera(0);

                        ArrayList<RemontajeDTO> remontajeDTOArrayList = new ArrayList<>();

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
                                    //Falta agregar numero remontaje en TanqueImp
                                    tanqueImp.setRemontaje(parts[2], parts[3], parts[4],0);
                                    remontajeDTOArrayList.add(createRemontajeDTO(parts[2], parts[3], parts[4],0));
                                    break;
                                case 7:
                                    tanqueImp.setRemontaje(parts[5], parts[6], parts[7],1);
                                    remontajeDTOArrayList.add(createRemontajeDTO(parts[5], parts[6], parts[7],1));
                                    break;
                                case 10:
                                    tanqueImp.setRemontaje(parts[8], parts[9], parts[10],2);
                                    remontajeDTOArrayList.add(createRemontajeDTO(parts[8], parts[9], parts[10],2));
                                    break;
                                case 13:
                                    tanqueImp.setRemontaje(parts[11], parts[12], parts[13],3);
                                    remontajeDTOArrayList.add(createRemontajeDTO(parts[11], parts[12], parts[13],3));
                                    break;
                                case 14: //Habilitacion periocidad
                                    tanqueImp.setPeriocidad(parts[i]);
                                    datosTanqueGuiDTO.setPeriocidad(parts[i]);
                                    break;
                                case 15:      //Temperatura Máxima
                                    tanqueImp.setTempMaxima(Double.parseDouble(parts[i]));
                                    datosTanqueGuiDTO.setTemperaturaMaximaInicial(parts[i]);
                                    break;
                                case 16:   //Temperatura Mínima
                                    tanqueImp.setTempMinima(Double.parseDouble(parts[i]));
                                    datosTanqueGuiDTO.setTemperaturaMinimaInicial(parts[i]);
                                    break;
                            }

                            datosTanqueGuiDTO.setRemontaje(remontajeDTOArrayList);
                        }
                    }

                break;
            case 2:

                try{
                    tanqueImp = getTanque(datosTanque); //Agregar If con tanque distinto de nulo
                }catch( Exception e){
                    System.out.println("tanqueImp1: " + tanqueImp);
                }
                if (tanqueImp!=null) {

                    datosTanqueGuiDTO.setIdTanque(datosTanque[0].substring(datosTanque[0].length()-2, datosTanque[0].length()));
                //Datos dinamicos
                     /*
                            a-b-cc,c-dd:dd
                               a: estado de remontaje 1 --> Si esta remontando
                               b: estado de enfriado 1 --> Si esta Enfriando
                               c: temperatura actual 4
                               d: hora del equipo 5
                     */
                datosTanqueGuiDTO.setCodigoOperacion(2);
                datosTanqueGuiDTO.setBandera(1);
                tanqueImp.setTipoPaquete("dinamico");

                for (i = 1; i < parts.length; ++i){


                    switch (i){
                        case 1:
                            datosTanqueGuiDTO.setEstadoRemontaje(parts[i]);
                            tanqueImp.setEstadoRemontaje(parts[i]);
                            break;
                        case 2:
                            datosTanqueGuiDTO.setEstadoEnfriamiento(parts[i]);
                            tanqueImp.setEstadoEnfriamiento(parts[i]);
                            break;
                        case 3:
                            datosTanqueGuiDTO.setTemperaturaActual(parts[i]);
                            tanqueImp.setTemperaturaActual(parts[i]);
                            break;
                        case 4:
                            datosTanqueGuiDTO.setHoraDispositivo(parts[i]);
                            tanqueImp.setHoraDispositivo(parts[i]);
                            break;
                    }
                }

            }
                break;
        }
        return datosTanqueGuiDTO;
    }

    private boolean existenciaTanque(String[] datosTanque){
        boolean unicidad = false;
        int i = 0;

        if (tanqueImpArrayList == null || tanqueImpArrayList.isEmpty()){
            unicidad = true;
        }else if(!(tanqueImpArrayList == null)){

            while(i<tanqueImpArrayList.size()){
                if (!(tanqueImpArrayList.get(i).getDireccionIP().equals(datosTanque[0].trim()))){
                    unicidad = true;
                }
                ++i;

            }
        }
        return unicidad;
    }


    private TanqueImp getTanque (String[]datosTanque){
        TanqueImp tanqueImp = new TanqueImp();
        int i = 0;

        while (i < tanqueImpArrayList.size()) {
            if (tanqueImpArrayList.get(i).getDireccionIP().equals(datosTanque[0].trim())) {
                   tanqueImp = tanqueImpArrayList.get(i);

            }
            ++i;
        }


        return tanqueImp;
    }

    private RemontajeDTO createRemontajeDTO(String habilitacionRemontaje, String inicioRemontaje, String finRemontaje, int numeroRemontaje){
        RemontajeDTO remontajeDTO = new RemontajeDTO();
        remontajeDTO.setHabilitacionRemontaje(habilitacionRemontaje);
        remontajeDTO.setInicioRemontaje(inicioRemontaje);
        remontajeDTO.setFinRemontaje(finRemontaje);
        remontajeDTO.setNumRemontaje(numeroRemontaje);

        return remontajeDTO;
    }


}
