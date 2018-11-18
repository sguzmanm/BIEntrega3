package Dimension;

import Soporte.Reportable;

public class DimEgresado extends Reportable {
    private int id;

    private String edadEgresado;

    private String generoEgresado;

    private String celularPrincipalEgresado;

    private String emailEgresado;

    private String nivelVinculacionEgresado;

    private double indiceRelacionamiento;
    
    private double indiceRelacionamientoEstandarizado;

    private int idPerfilPractica;

    private int idPerfilPersonal;

    private int idPerfilGrado;
    //Codigo para hash

    public String toString()
    {
        return edadEgresado+";"+generoEgresado+";"+celularPrincipalEgresado+";"+emailEgresado+
                ";"+nivelVinculacionEgresado+";"+indiceRelacionamiento+";"+indiceRelacionamientoEstandarizado+";"+idPerfilGrado+";"+
                idPerfilPersonal+";"+idPerfilPractica;
    }
    //Class headers
    @Override
    public String getClassHeaders() {
        if(edadEgresado==null || edadEgresado.trim().equals(""))
            edadEgresado="-1";
        if(generoEgresado==null || generoEgresado.trim().equals(""))
            generoEgresado="D";
        if(celularPrincipalEgresado==null || celularPrincipalEgresado.trim().equals(""))
            celularPrincipalEgresado="D";
        if(emailEgresado==null || emailEgresado.trim().equals(""))
            emailEgresado="D";
        if(idPerfilPractica==0)
            idPerfilPractica=-1;
        if(idPerfilPersonal==0)
            idPerfilPersonal=-1;
        if(idPerfilGrado==0)
            idPerfilGrado=-1;
        return id+";"+toString();
    }
    //Getters and setters
    public int getIdEgresado() {
        return id;
    }

    public void setIdEgresado(int idEgresado) {
        this.id = idEgresado;
    }

    public String getEdadEgresado() {
        return edadEgresado;
    }

    public void setEdadEgresado(String edadEgresado) {
        this.edadEgresado = edadEgresado;
    }

    public String getGeneroEgresado() {
        return generoEgresado;
    }

    public void setGeneroEgresado(String generoEgresado) {
        this.generoEgresado = generoEgresado;
    }

    public String getCelularPrincipalEgresado() {
        return celularPrincipalEgresado;
    }

    public void setCelularPrincipalEgresado(String celularPrincipalEgresado) {
        this.celularPrincipalEgresado = celularPrincipalEgresado;
    }

    public String getEmailEgresado() {
        return emailEgresado;
    }

    public void setEmailEgresado(String emailEgresado) {
        this.emailEgresado = emailEgresado;
    }

    public String getNivelVinculacionEgresado() {
        return nivelVinculacionEgresado;
    }

    public void setNivelVinculacionEgresado(String nivelVinculacionEgresado) {
        this.nivelVinculacionEgresado = nivelVinculacionEgresado;
    }

    public double getIndiceRelacionamiento() {
        return indiceRelacionamiento;
    }

    public void setIndiceRelacionamiento(double indiceRelacionamiento) {
        this.indiceRelacionamiento = indiceRelacionamiento;
    }

    public int getIdPerfilPractica() {
        return idPerfilPractica;
    }

    public void setIdPerfilPractica(int idPerfilPractica) {
        this.idPerfilPractica = idPerfilPractica;
    }

    public int getIdPerfilPersonal() {
        return idPerfilPersonal;
    }

    public void setIdPerfilPersonal(int idPerfilPersonal) {
        this.idPerfilPersonal = idPerfilPersonal;
    }

    public int getIdPerfilGrado() {
        return idPerfilGrado;
    }

    public void setIdPerfilGrado(int idPerfilGrado) {
        this.idPerfilGrado = idPerfilGrado;
    }
    
    public double getIndiceRelacionamientoEstandarizado()
    {
    	return indiceRelacionamientoEstandarizado;
    }
    
    public void setIndiceRelacionamientoEstandarizado(double a)
    {
    	indiceRelacionamientoEstandarizado=a;
    }


}
