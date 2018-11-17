package Dimension;

import Soporte.Reportable;

public class DimFechaInicio extends Reportable {

    private int id;

    private int anhoEvento;

    private String semestreEvento;

    private String cuatrimestreEvento;

    private String mesEvento;

    private int diaEvento;
    //Codigo apra hash
    public String toString()
    {
        return anhoEvento+";"+semestreEvento+";"+cuatrimestreEvento+";"+mesEvento+";"+diaEvento;
    }

    //Class headers
    @Override
    public String getClassHeaders() {

        return id+";"+toString();
    }

    public int getAnhoEvento() {
        return anhoEvento;
    }

    public void setAnhoEvento(int anhoEvento) {
        this.anhoEvento = anhoEvento;
    }

    public String getSemestreEvento() {
        return semestreEvento;
    }

    public void setSemestreEvento(String semestreEvento) {
        this.semestreEvento = semestreEvento;
    }

    public String getCuatrimestreEvento() {
        return cuatrimestreEvento;
    }

    public void setCuatrimestreEvento(String cuatrimestreEvento) {
        this.cuatrimestreEvento = cuatrimestreEvento;
    }

    public String getMesEvento() {
        return mesEvento;
    }

    public void setMesEvento(String mesEvento) {
        this.mesEvento = mesEvento;
    }

    public int getDiaEvento() {
        return diaEvento;
    }

    public void setDiaEvento(int diaEvento) {
        this.diaEvento = diaEvento;
    }

    public int getIdFecha() {

        return id;
    }

    public void setIdFecha(int idFecha) {
        this.id = idFecha;
    }
}
