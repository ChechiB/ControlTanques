package com.javafx.girsyt.dto;

public class PaqueteRecibidoDTO {

        private int port;
        private String temperaturaMaximaInicial;
        private String temperaturaMinimaInicial;
        private String estadoRemontaje;
        private String estadoEnfriamiento;
        private String temperaturaActual;
        private String horaDispositivo;
        private String tipoPaquete;
        private int bandera;
        private String periocidad;
        private RemontajeDTO[] arrayRemontaje = new RemontajeDTO[4];

        public String getPeriocidad() {
            return periocidad;
        }

        public void setPeriocidad(String periocidad) {
            this.periocidad = periocidad;
        }

        public String getTipoPaquete() {
            return tipoPaquete;
        }

        public void setTipoPaquete(String tipoPaquete) {
            this.tipoPaquete = tipoPaquete;
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

        public String getTemperaturaMaximaInicial() {
            return temperaturaMaximaInicial;
        }

        public void setTemperaturaMaximaInicial(String temperaturaMaximaInicial) {
            this.temperaturaMaximaInicial = temperaturaMaximaInicial;
        }

        public String getTemperaturaMinimaInicial() {
            return temperaturaMinimaInicial;
        }

        public void setTemperaturaMinimaInicial(String temperaturaMinimaInicial) {
            this.temperaturaMinimaInicial = temperaturaMinimaInicial;
        }
        private String direccionIP; // Con la direccion IP se puede obtener el numero de tanque al que corresponde

        public String getDireccionIP() {
            return direccionIP;
        }

        public void setDireccionIP(String direccionIP) {

            this.direccionIP = direccionIP;
        }

        public RemontajeDTO [] getRemontaje() {
            return arrayRemontaje;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public void setBandera(int i) {
            bandera = i;
        }

        public int getBandera() {
            return bandera;
        }

        /*public void setRemontaje(String[] remontajes, int i, int j) {

            RemontajeDTO remontaje = new RemontajeDTO();

            remontaje.setHabilitacionRemontaje(remontajes[i-3]);
            remontaje.setInicioRemontaje(remontajes[i-2]);
            remontaje.setFinRemontaje(remontajes[i-1]);
            remontaje.setNumRemontaje(j);
            arrayRemontaje[j] = remontaje;
        } */
    }


