package com.cristhoper.moneyhelper.models;

/**
 * Created by Alumno on 11/09/2017.
 */

public class Operation {
    //el monto se cambió de Double a double para poder convertirlo a float y pasarlo como parametro de la clase BarEntry (CharActivity)
    private double monto;
    private String tipoDinero;
    private String tipoCuenta;

    public Operation() {

    }
    public Operation(Double monto, String tipoDinero, String tipoCuenta) {
        this.monto = monto;
        this.tipoDinero = tipoDinero;
        this.tipoCuenta = tipoCuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoDinero() {
        return tipoDinero;
    }

    public void setTipoDinero(String tipoDinero) {
        this.tipoDinero = tipoDinero;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "monto=" + monto +
                ", tipoDinero='" + tipoDinero + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                '}';
    }
}
