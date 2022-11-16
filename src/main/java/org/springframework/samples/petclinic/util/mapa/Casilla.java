package org.springframework.samples.petclinic.util.mapa;

public class Casilla {
    
    Integer x;
    Integer y;
    Boolean borde;
    Boolean masMenos;
    Boolean interrogante;

    public Casilla(Integer x, Integer y, Boolean borde, Boolean masMenos, Boolean interrogante){
        this.x = x;
        this.y = y;
        this.borde = borde;
        this.masMenos = masMenos;
        this.interrogante = interrogante;
    }

    
}
