package com.javafx.girsyt.entidad;

import java.util.Date;

public class RemontajeImp {

        private int numRemontaje;
        private Date inicioRemontaje;
        private Date finRemontaje;
        private String habilitacionRemontaje;


        public int getNumRemontaje() {
            return numRemontaje;
        }

        public void setNumRemontaje(int numRemontaje) {
            this.numRemontaje = numRemontaje;
        }

        public Date getInicioRemontaje() {
            return inicioRemontaje;
        }

        public void setInicioRemontaje(Date inicioRemontaje) {
            this.inicioRemontaje = inicioRemontaje;
        }

        public Date getFinRemontaje() {
            return finRemontaje;
        }

        public void setFinRemontaje(Date finRemontaje) {
            this.finRemontaje = finRemontaje;
        }

        public String getHabilitacionRemontaje() {
            return habilitacionRemontaje;
        }

        public void setHabilitacionRemontaje(String habilitacionRemontaje) {
            this.habilitacionRemontaje = habilitacionRemontaje;
        }
}
