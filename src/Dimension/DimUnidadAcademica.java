package Dimension;

import Soporte.Reportable;

public class DimUnidadAcademica extends Reportable {
    private int id;

    private String areaFacultad;

    private String facultad;

    private String departamento;

    //Codigo hash
    public String toString()
    {
        return areaFacultad+";"+facultad+";"+departamento;
    }

    //Class headers
    @Override
    public String getClassHeaders() {
        if(areaFacultad==null || areaFacultad.trim().equals(""))
            areaFacultad="Desconocido";
        if(facultad==null || facultad.trim().equals(""))
            facultad="Desconocido";
        if(departamento==null || departamento.trim().equals(""))
            departamento="Desconocido";
        return id+";"+toString();
    }

    //Getters y setters
    public int getIdUnidadAcademica() {
        return id;
    }

    public void setIdUnidadAcademica(int idUnidadAcademica) {
        this.id = idUnidadAcademica;
    }

    public String getAreaFacultad() {
        return areaFacultad;
    }

    public void setAreaFacultad(String areaFacultad) {
        this.areaFacultad = areaFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
