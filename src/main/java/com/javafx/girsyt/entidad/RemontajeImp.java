package com.javafx.girsyt.entidad;

import java.util.Date;

public class RemontajeImp {

        private int numRemontaje;
        private String inicioRemontaje;
        private String finRemontaje;
        private boolean habilitacionRemontaje;


        public int getNumRemontaje() {
            return numRemontaje;
        }

        public void setNumRemontaje(int numRemontaje) {
            this.numRemontaje = numRemontaje;
        }

        public String getInicioRemontaje() {
            return inicioRemontaje;
        }

        public void setInicioRemontaje(String inicioRemontaje) {
            this.inicioRemontaje = inicioRemontaje;
        }

        public String getFinRemontaje() {
            return finRemontaje;
        }

        public void setFinRemontaje(String finRemontaje) {
            this.finRemontaje = finRemontaje;
        }

        public boolean getHabilitacionRemontaje() {
            return habilitacionRemontaje;
        }

        public void setHabilitacionRemontaje(boolean habilitacionRemontaje) {
            this.habilitacionRemontaje = habilitacionRemontaje;
        }
}
