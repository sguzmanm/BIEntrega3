package Soporte;

import Dimension.DimPerfilPersonal;

import java.util.HashMap;
import java.util.PriorityQueue;

public class MiniDimensiones {
    //HAsh de equivalencia de llave del egresado a su perfil
    private HashMap<String, String> egresadoPracticante = new HashMap<>();

    private HashMap<String, PriorityQueue<DimPerfilPersonal>> egresadoPersonal = new HashMap<>();

    private HashMap<String, String> egresadoGrado = new HashMap<>();

    //GETTERS AND SETTERS


    public HashMap<String, String> getEgresadoPracticante() {
        return egresadoPracticante;
    }

    public HashMap<String, PriorityQueue<DimPerfilPersonal>> getEgresadoPersonal() {
        return egresadoPersonal;
    }

    public HashMap<String, String> getEgresadoGrado() {
        return egresadoGrado;
    }
}