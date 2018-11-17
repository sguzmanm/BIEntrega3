package Dimension;

import Soporte.Reportable;

public class DimEvento extends Reportable {

    private int id;

    private String nombreEvento;

    private String categoriaEvento;

    private String tipoEvento;

    private String codigoEvento;

    //Código único para hash
    public String toString()
    {
        return nombreEvento+";"+categoriaEvento+";"+tipoEvento+";"+codigoEvento;
    }
    //Class header
    @Override
    public String getClassHeaders() {
        if(nombreEvento==null || nombreEvento.trim().equals(""))
        {
            nombreEvento="EVENTO";
        }
        if (categoriaEvento == null || categoriaEvento.trim().equals(""))
        {
            categoriaEvento="Desconocido";
        }
        if(tipoEvento==null || tipoEvento.trim().equals(""))
            tipoEvento="DESC";
        if(codigoEvento==null || codigoEvento.trim().equals(""))
            codigoEvento="DE-00000000";
        return id+";"+toString();
    }

    //Getters y setters
    public String getCodigoEvento()
    {
        return codigoEvento;
    }

    public void setCodigoEvento(String s)
    {
        codigoEvento=s;
    }


    public int getIdEvento() {
        return id;
    }

    public void setIdEvento(int idEvento) {
        this.id = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(String categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
