package com.javafx.girsyt.entidad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TanqueImp {

    private String estadoEnfriamiento;
    private String temperaturaActual;
    private String horaDispositivo;
    private String tipoPaquete;
    private int bandera;
    private String periocidad;
    private ArrayList<RemontajeImp> arrayRemontaje = new ArrayList<>();
    private TemperaturaImp temperaturaImp = new TemperaturaImp();
    private int idTanque;
    private double tempMinima;
    private double tempMaxima;
    private String estadoRemontaje;

    public double getTempMaxima() {
        return tempMaxima;
    }

    public void setTempMaxima(double tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public double getTempMinima() {
        return tempMinima;
    }

    public void setTempMinima(double getTempMinima) {
        this.tempMinima = getTempMinima;
    }

    public ArrayList<RemontajeImp> getArrayRemontaje() {
        return arrayRemontaje;
    }

    public void setArrayRemontaje(ArrayList<RemontajeImp> arrayRemontaje) {
        this.arrayRemontaje = arrayRemontaje;
    }



    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    private int puerto;
    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    private String direccionIP;
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

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public int getBandera() {
        return bandera;
    }

    public void setBandera(int bandera) {
        this.bandera = bandera;
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public TemperaturaImp getTemperaturaImp() {
        return temperaturaImp;
    }

    public void setTemperaturaImp(TemperaturaImp temperaturaImp) {
        this.temperaturaImp = temperaturaImp;
    }

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }


    public void setRemontaje(boolean habilitacionRemontaje, String inicioRemontaje, String finRemontaje, int numeroRemontaje) {

        RemontajeImp remontajeImp = new RemontajeImp();
        remontajeImp.setHabilitacionRemontaje(habilitacionRemontaje);
        remontajeImp.setNumRemontaje(numeroRemontaje);
        remontajeImp.setInicioRemontaje(inicioRemontaje);
        remontajeImp.setFinRemontaje(finRemontaje);
        arrayRemontaje.add(remontajeImp);

    }

    public void setEstadoRemontaje(String estadoRemontaje) {
        this.estadoRemontaje = estadoRemontaje;
    }
}
