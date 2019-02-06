package com.javafx.girsyt.entidad;

import java.util.Date;
import java.util.Timer;

public class TemperaturaActual {
    private double temperatura;
    private String fechaTemperaturaActual;
    private String horaTemperaturaActual;

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getFechaTemperaturaActual() {
        return fechaTemperaturaActual;
    }

    public void setFechaTemperaturaActual(String fechaTemperaturaActual) {
        this.fechaTemperaturaActual = fechaTemperaturaActual;
    }

    public String getHoraTemperaturaActual() {
        return horaTemperaturaActual;
    }

    public void setHoraTemperaturaActual(String horaTemperaturaActual) {
        this.horaTemperaturaActual = horaTemperaturaActual;
    }
}
