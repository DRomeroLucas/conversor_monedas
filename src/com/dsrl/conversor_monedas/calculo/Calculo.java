package com.dsrl.conversor_monedas.calculo;

public class Calculo {
    private String primeraDivisa, segundaDivisa;
    int opcion = 0;

    public Calculo(int opcion) {
        this.opcion = opcion;
    }

    public void conversionDeDivisa() throws IllegalAccessException {

        switch(opcion){
            case 1:
                primeraDivisa = "USD";
                segundaDivisa = "ARS";
                break;
            case 2:
                primeraDivisa = "ARS";
                segundaDivisa = "USD";
                break;
            case 3:
                primeraDivisa = "BRL";
                segundaDivisa = "USD";
                break;
            case 4:
                primeraDivisa = "USD";
                segundaDivisa = "BRL";
                break;
            case 5:
                primeraDivisa = "USD";
                segundaDivisa = "COP";
                break;
            case 6:
                primeraDivisa = "COP";
                segundaDivisa = "USD";
                break;
            default:
                throw new IllegalAccessException("Opción inválilda: " + opcion);
        }
    }
    public String getPrimeraDivisa() {
        return primeraDivisa;
    }

    public void setPrimeraDivisa(String primeraDivisa) {
        this.primeraDivisa = primeraDivisa;
    }

    public String getSegundaDivisa() {
        return segundaDivisa;
    }

    public void setSegundaDivisa(String segundaDivisa) {
        this.segundaDivisa = segundaDivisa;
    }

}
