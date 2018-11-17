package Dimension;

import Soporte.Reportable;

public class DimPerfilGrado extends Reportable {

    private int id;

    private String beneficiarioQE;

    private String gradoEgresado;

    private String nivelGrado;

    private String semestreGrado;

    //Codigo hash
    public String toString()
    {
        return beneficiarioQE+";"+gradoEgresado+";"+nivelGrado+";"+semestreGrado;
    }

    //Class headers
    @Override
    public String getClassHeaders() {
        if(beneficiarioQE==null || beneficiarioQE.trim().equals(""))
            beneficiarioQE="D";
        return id+";"+toString();
    }

    //Getters y setters
    public int getIdPerfilGrado() {
        return id;
    }

    public void setIdPerfilGrado(int idPerfilGrado) {
        this.id = idPerfilGrado;
    }

    public String getBeneficiarioQE() {
        return beneficiarioQE;
    }

    public void setBeneficiarioQE(String beneficiarioQE) {
        this.beneficiarioQE = beneficiarioQE;
    }

    public String getGradoEgresado() {
        return gradoEgresado;
    }

    public void setGradoEgresado(String gradoEgresado) {
        this.gradoEgresado = gradoEgresado;
    }

    public String getNivelGrado() {
        return nivelGrado;
    }

    public void setNivelGrado(String nivelGrado) {
        this.nivelGrado = nivelGrado;
    }

    public String getSemestreGrado() {
        return semestreGrado;
    }

    public void setSemestreGrado(String semestreGrado) {
        this.semestreGrado = semestreGrado;
    }
}
