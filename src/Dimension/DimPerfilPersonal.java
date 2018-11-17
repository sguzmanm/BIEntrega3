package Dimension;

import Soporte.Reportable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DimPerfilPersonal extends Reportable implements Comparable<DimPerfilPersonal> {

    private int id;

    private String paisResidenciaActual;

    private String estadoResidenciaActual;

    private String ciudadResidenciaActual;

    private String empresaActual;

    private String cargoActual;

    private String donante;

    private String profesor;

    private String fechaActualizacion;

    private String fechaExpiracion;

    //Codigo hash
    public String toString()
    {
        return paisResidenciaActual+";"+estadoResidenciaActual+
                ";"+ciudadResidenciaActual+";"+empresaActual+";"+
                cargoActual+";"+donante+";"+profesor+";"+fechaActualizacion+";"
                +fechaExpiracion;
    }

    //Class headers
    @Override
    public String getClassHeaders() {
        if(paisResidenciaActual==null || paisResidenciaActual.trim().equals(""))
            paisResidenciaActual="NA";
        if(estadoResidenciaActual==null || estadoResidenciaActual.trim().equals(""))
            estadoResidenciaActual="NA";
        if(ciudadResidenciaActual==null || ciudadResidenciaActual.trim().equals(""))
            ciudadResidenciaActual="NA";
        if(empresaActual==null || empresaActual.trim().equals(""))
            empresaActual="NA";
        if(cargoActual==null || cargoActual.trim().equals(""))
            cargoActual="NA";
        if(donante==null || donante.trim().equals(""))
            donante="D";
        if(profesor==null || profesor.trim().equals(""))
            profesor="D";
        if(fechaActualizacion==null || fechaActualizacion.trim().equals(""))
            fechaActualizacion="01/01/1900";
        if(fechaExpiracion==null || fechaExpiracion.trim().equals(""))
            fechaExpiracion="01/01/2099";

        return id+";"+toString();
    }
    //Getters y setters


    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getIdPerfilPersonal() {
        return id;
    }

    public void setIdPerfilPersonal(int idPerfilPersonal) {
        this.id = idPerfilPersonal;
    }

    public String getPaisResidenciaActual() {
        return paisResidenciaActual;
    }

    public void setPaisResidenciaActual(String paisResidenciaActual) {
        this.paisResidenciaActual = paisResidenciaActual;
    }

    public String getEstadoResidenciaActual() {
        return estadoResidenciaActual;
    }

    public void setEstadoResidenciaActual(String estadoResidenciaActual) {
        this.estadoResidenciaActual = estadoResidenciaActual;
    }

    public String getCiudadResidenciaActual() {
        return ciudadResidenciaActual;
    }

    public void setCiudadResidenciaActual(String ciudadResidenciaActual) {
        this.ciudadResidenciaActual = ciudadResidenciaActual;
    }

    public String getEmpresaActual() {
        return empresaActual;
    }

    public void setEmpresaActual(String empresaActual) {
        this.empresaActual = empresaActual;
    }

    public String getCargoActual() {
        return cargoActual;
    }

    public void setCargoActual(String cargoActual) {
        this.cargoActual = cargoActual;
    }

    public String getDonante() {
        return donante;
    }

    public void setDonante(String donante) {
        this.donante = donante;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    @Override
    public int compareTo(DimPerfilPersonal o) {
        SimpleDateFormat FORMATO_FECHA=new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date mine=FORMATO_FECHA.parse(this.fechaActualizacion);
            Date theirs=FORMATO_FECHA.parse(o.fechaActualizacion);
            return -1*mine.compareTo(theirs);
        }
        catch(Exception e)
        {
            return 0;
        }
    }
}
