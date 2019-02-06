package com.javafx.girsyt.dto;

import java.util.ArrayList;

public class DatosTanqueGuiDTO {

    private int port;
    private int codigoOperacion;
    private Double temperaturaMaximaInicial;
    private Double temperaturaMinimaInicial;
    private String estadoRemontaje;
    private String estadoEnfriamiento;
    private String temperaturaActual;
    private String horaDispositivo;
    private String periocidad;
    private ArrayList<RemontajeDTO> arrayRemontaje = new ArrayList<>();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(String idTanque) {
        this.idTanque = idTanque;
    }

    private String idTanque;


    public int getCodigoOperacion() {
        System.out.println("codigoOperacion: " + codigoOperacion);
        return codigoOperacion;
    }

    public void setCodigoOperacion(int codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public String getEstadoRemontaje() {
        return estadoRemontaje;
    }

    public void setEstadoRemontaje(String estadoRemontaje) {
        this.estadoRemontaje = estadoRemontaje;
    }

    public String getEstadoEnfriamiento() {
        return estadoEnfriamiento;
    }

    public void setEstadoEnfriamiento(String estadoEnfriamiento) {
        this.estadoEnfriamiento = estadoEnfriamiento;
    }

    public String getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(String temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
    }

    public String getHoraDispositivo() {
        return horaDispositivo;
    }

    public void setHoraDispositivo(String horaDispositivo) {
        this.horaDispositivo = horaDispositivo;
    }

    public Double getTemperaturaMaximaInicial() {
        return temperaturaMaximaInicial;
    }

    public void setTemperaturaMaximaInicial(Double temperaturaMaximaInicial) {
        this.temperaturaMaximaInicial = temperaturaMaximaInicial;
    }

    public Double getTemperaturaMinimaInicial() {
        return temperaturaMinimaInicial;
    }

    public void setTemperaturaMinimaInicial(Double temperaturaMinimaInicial) {
        this.temperaturaMinimaInicial = temperaturaMinimaInicial;
    }
    private String direccionIP; // Con la direccion IP se puede obtener el numero de tanque al que corresponde

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {

        this.direccionIP = direccionIP;
    }

    public ArrayList<RemontajeDTO> getRemontaje() {
        return arrayRemontaje;
    }


    public void setRemontaje(ArrayList<RemontajeDTO> remontajes) {

      this.arrayRemontaje =remontajes;

    }

}


