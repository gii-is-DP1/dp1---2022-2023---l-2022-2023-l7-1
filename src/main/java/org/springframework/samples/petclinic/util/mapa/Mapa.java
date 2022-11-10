package org.springframework.samples.petclinic.util.mapa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.util.Territorio;

import lombok.Getter;


public class Mapa {
    
    private static Integer FILA_MINIMA = 5;

    private static Integer FILA_MAXIMA = 9;

    List<List<Territorio>> matriz; 


    public Mapa(){
        this.matriz = crearMapa();
    }

    public static List<Integer> dado(Integer numDados){
        List<Integer> res = new ArrayList<>();
        for(int j = 1; j <= numDados; j++){
            Integer n = (int) (Math.random()*6) +1;
            res.add(n);
        }
        return res;
    }

    public static List<Territorio> rellenarFila(Integer numElementos){
        // Creamos las filas del mapa con numElementos necesarios
        List<Territorio> res = new ArrayList<>();

        while(numElementos >0){
               res.add(Territorio.NA);
               numElementos--;
        }

        return res;
    }

    public static List<List<Territorio>> crearMapa(){
        // Creamos una lista de listas que será la matriz con 5,6,7,8,9,8,7,6,5 columnas

        List<List<Territorio>> matriz = new ArrayList();

        int numeroDeFilas= 9;
        for (int i = 1; i <= numeroDeFilas; i++) {

            if(i<=5){
                matriz.add(rellenarFila(i+4));
            }else{
                matriz.add(rellenarFila(FILA_MAXIMA+FILA_MINIMA-i));
            }
        }

        return matriz;
    }

    public void cambiarCasilla(Integer fila, Integer columna, Territorio territorio){
        matriz.get(fila-1).set(columna-1, territorio);
    }

    public String toString(){
        return matriz.toString();
    }

    public List<List<Territorio>> getMatriz(){
        return this.matriz;
    }
    public static void main(String[] args) {
		Mapa res = new Mapa();
        

        
        System.out.println(res.toString());

        
        System.out.println(dado(5));
    }
}


