package com.javafx.girsyt.dto;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PaqueteAEnviarDTO {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

/*
    El equipo recibe: Todos estos datos ocupan el mismo byte? o es un byte por cada estado?
        0 si hubo error o se termina la conexion
        1 si se inicia la conexion  Puede enviar un 1 y un 2 al mismo tiempo o lo hace en paquetes distintos?
        2 si se reciben temperaturas
        3 si se reciben horas de equipo
        4 si se reciben horas de remontaje

        SEGUIDO DE LA INFORMACION EN UN MISMO STRING Faltan delimitadores para poder diferenciar los distintos datos o un archivo Json

        Al setear hora de remontaje el equipo recibe el siguiente string:
        4
        HH:MM(inicio remontaje 1)
        HH:MM(fin remontaje 1)
        HH:MM(inicio remontaje 2)
        HH:MM(fin remontaje 2)
        0
        0
        0
        0
        0
        0

        Al setear las temperaturas el equipo recibe el siguiente string:
        Si es error recibe un cero. sino:

        2
        gg,d (temp. maxima)   El string viene con esos formatos?
        gg,d (temp. minima)

        Para poner en hora al equipo se envia
        3
        HH:MM:SS


        ----------------
*/
  private String mensaje;


        private String ipDireccion;
        private String[] arrayRemontaje = new String[12];
        private String periocidad;
        private int puerto;
        private String numeroTanque;
        private int bitStop; //error O TERMINA LA CONEXION
        private int bitConectar;
        private int bitIndicador;
        private Double tMax;
        private Double tMin;
        private StringBuffer cadena = new StringBuffer();;
        private String horaFecha;
        private ArrayList<String> remontajes;

        public int getBitStop() {
            return bitStop;
        }

        public String getPeriocidad() {
            return periocidad;
        }

        public void setPeriocidad(String periocidad) {
            this.periocidad = periocidad;
        }

        public void setBitStop(int bitStop) {
            this.bitStop = bitStop;
            bitConectar = 0;
        }
        public String getHoraFecha() {
            return horaFecha;
        }

        public void setHoraFecha(String horaFecha) {
            this.horaFecha = horaFecha;
        }

        public Double gettMax() {
            return tMax;
        }

        public void settMax(Double tMax) {
            this.tMax = tMax;
        }

        public Double gettMin() {
            return tMin;
        }

        public void settMin(Double tMin) {
            this.tMin = tMin;
        }

        public void setIpDireccion(String ipDireccion) throws UnknownHostException {
            this.ipDireccion = ipDireccion;
        }


        public void setNumeroTanque(String numeroTanque) throws FileNotFoundException, UnknownHostException {
            this.numeroTanque = numeroTanque;
            //Cambiar por llamada a la BD para conseguir ip del tanque
            //TanqueIP tanqueIP = new TanqueIP(this);

        }

        public String getMensaje() {
            return mensaje;
        }

        public String getNumeroTanque() {
            return numeroTanque;
        }

        protected String getConfiguracionMensaje(){
            return mensaje;
        }

        public void setRemontaje(ArrayList<String> remontajesConfigurados) {
            remontajes = remontajesConfigurados;
        }
        public int getBitIndicador() {
            return bitIndicador;
        }

        public void setBitIndicador(int bitIndicador) {
            this.bitIndicador = bitIndicador;
        }

        public ArrayList<String> getRemontajes() {

            return remontajes;
        }

        public String getIpDireccion() {
            return ipDireccion;
        }

        public void setBitConectar(int bitConectar) {
            this.bitConectar = bitConectar;
            bitStop = 1;
        }

        public int getBitConectar() {
            return bitConectar;
        }



}
