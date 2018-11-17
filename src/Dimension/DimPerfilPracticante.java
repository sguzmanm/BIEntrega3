package Dimension;

import Soporte.Reportable;

public class DimPerfilPracticante extends Reportable {

    private int id;

    private int comunicacionVerbal;

    private int autonomiaEIniciativa;

    private int compromisoEtico;

    private int disposicionAlAprendizaje;

    private String semestrePractica;

    //Cdigo hash
    public String toString()
    {
        return comunicacionVerbal+";"+autonomiaEIniciativa+";"+compromisoEtico+";" +
                disposicionAlAprendizaje+";"+semestrePractica;
    }
    //Class headers
    @Override
    public String getClassHeaders() {
        if(autonomiaEIniciativa==0)
            autonomiaEIniciativa=-1;
        if(compromisoEtico==0)
        	compromisoEtico=-1;
        if(comunicacionVerbal==0)
        	comunicacionVerbal=-1;
        if(disposicionAlAprendizaje==0)
        	disposicionAlAprendizaje=-1;
        return id+";"+toString();
    }
    //Getters y setters

    public int getIdPerfilPractica() {
        return id;
    }

    public void setIdPerfilPractica(int id) {
        this.id = id;
    }

    public int getComunicacionVerbal() {
        return comunicacionVerbal;
    }

    public void setComunicacionVerbal(String id) {
    	int temp=0;
    	try
    	{
    		temp=Integer.parseInt(id);
    	}
    	catch(NumberFormatException e)
    	{
    		
    	}
        this.comunicacionVerbal = temp;
    }

    public int getAutonomiaEIniciativa() {
        return autonomiaEIniciativa;
    }

    public void setAutonomiaEIniciativa(String id) {
    	int temp=0;
    	try
    	{
    		temp=Integer.parseInt(id);
    	}
    	catch(NumberFormatException e)
    	{
    		
    	}
        this.autonomiaEIniciativa = temp;
    }

    public int getCompromisoEtico() {
        return compromisoEtico;
    }

    public void setCompromisoEtico(String id) {
    	int temp=0;
    	try
    	{
    		temp=Integer.parseInt(id);
    	}
    	catch(NumberFormatException e)
    	{
    		
    	}
        this.compromisoEtico = temp;
    }

    public int getDisposicionAlAprendizaje() {
        return disposicionAlAprendizaje;
    }

    public void setDisposicionAlAprendizaje(String id) {
    	int temp=0;
    	try
    	{
    		temp=Integer.parseInt(id);
    	}
    	catch(NumberFormatException e)
    	{
    		
    	}
        this.disposicionAlAprendizaje = temp;
    }

    public String getSemestrePractica() {
        return semestrePractica;
    }

    public void setSemestrePractica(String semestrePractica) {
        this.semestrePractica = semestrePractica;
    }
}
