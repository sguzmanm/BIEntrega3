package Dimension;

import Soporte.Reportable;

public class Fact extends Reportable {


    private int asistencia;

    private int inscripcion;

    private int idEgresado;

    private int idEvento;

    private int idFechaInicio;

    private int idFechaFin;

    private int idUnidadAcademica;
    
    private int idPerfilPractica;
    
    private int idPerfilPersonal;
    
    private int idPerfilGrado;

    //Codigo hash
    public String toString()
    {
        return asistencia+";"+inscripcion+";"+idEgresado+";"+idEvento+";"+idFechaFin
                +";"+idFechaInicio+";"+idUnidadAcademica+";"+idPerfilPractica+";"+idPerfilPersonal+";"+idPerfilGrado;
    }
    //Class headers
    @Override
    public String getClassHeaders() {
        if(idEgresado==0)
            idEgresado=-1;
        if(idEvento==0)
            idEvento=-1;
        if(idFechaFin==0)
            idFechaFin=-1;
        if(idFechaInicio==0)
            idFechaInicio=-1;
        if(idUnidadAcademica==0)
            idUnidadAcademica=-1;
        if(idPerfilPractica==0)
            idPerfilPractica=-1;
        if(idPerfilPersonal==0)
            idPerfilPersonal=-1;
        if(idPerfilGrado==0)
            idPerfilGrado=-1;
        return toString();
    }
    //Getters y setters

    public int getIdFechaInicio() {
        return idFechaInicio;
    }

    public void setIdFechaInicio(int idFechaInicio) {
        this.idFechaInicio = idFechaInicio;
    }

    public int getIdFechaFin() {
        return idFechaFin;
    }

    public void setIdFechaFin(int idFechaFin) {
        this.idFechaFin = idFechaFin;
    }

    public int getIdUnidadAcademica() {
        return idUnidadAcademica;
    }

    public void setIdUnidadAcademica(int idUnidadAcademica) {
        this.idUnidadAcademica = idUnidadAcademica;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public int getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(int inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getIdEgresado() {
        return idEgresado;
    }

    public void setIdEgresado(int idEgresado) {
        this.idEgresado = idEgresado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
	public int getIdPerfilPracticante() {
		return idPerfilPractica;
	}
	public void setIdPerfilPracticante(int idPerfilPracticante) {
		this.idPerfilPractica = idPerfilPracticante;
	}
	public int getIdPerfilProfesional() {
		return idPerfilPersonal;
	}
	public void setIdPerfilProfesional(int idPerfilProfesional) {
		this.idPerfilPersonal = idPerfilProfesional;
	}
	public int getIdPerfilGrado() {
		return idPerfilGrado;
	}
	public void setIdPerfilGrado(int idPerfilGrado) {
		this.idPerfilGrado = idPerfilGrado;
	}
    
    
}
