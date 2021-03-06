package com.cristhoper.moneyhelper.models;

import android.util.Log;
import android.widget.Toast;

import com.cristhoper.moneyhelper.activities.NewOperationActivity;

/**
 * Created by Cris on 12/09/2017.
 */

public class Saldo {
    private Double saldoAhorro = 0.0, saldoCredito = 2000.00, saldoEfectivo = 0.0, mtEgreso = 0.0, mtIngreso = 0.0;
    private Operation operacion;
    private String mensaje;

    private static Saldo _INSTANCE = null;

    private Saldo(){}

    public static Saldo getInstance(){
        if (_INSTANCE == null)
            _INSTANCE = new Saldo();
        return _INSTANCE;
    }

    public void obtenerSaldos(Operation op){
        Double monto = op.getMonto();
        String tipoDinero = op.getTipoDinero();
        String tipoCuenta = op.getTipoCuenta();

        //Se analiza el tipo de monto (Ingreso o Egreso)
        switch (tipoDinero){
            case "Egreso":
                switch (tipoCuenta){
                    case "Ahorro":
                        if(saldoAhorro >= monto){
                            saldoAhorro = hallarSaldo(saldoAhorro, monto);
                        }else{
                            imprimirMensaje();
                        }
                        break;
                    case "Efectivo":
                        if(saldoEfectivo >= monto){
                            saldoEfectivo = hallarSaldo(saldoEfectivo, monto);
                        }else{
                            imprimirMensaje();
                        }
                        break;
                    default:
                        if(saldoCredito >= monto){
                            saldoCredito = hallarSaldo(saldoCredito, monto);
                        }else{
                            imprimirMensaje();
                        }
                        break;
                }
                //Si es Egreso, se analiza si el monto ingresado no es mayor al saldo disponible
                /*if (saldoAhorro >= monto || saldoCredito >= monto || saldoEfectivo >= monto){
                    mensaje = null;
                    mtEgreso = mtEgreso + monto;
                    switch (tipoCuenta){
                        case "Ahorro":
                            saldoAhorro = saldoAhorro - monto;
                            break;
                        case "Efectivo":
                            saldoEfectivo = saldoEfectivo - monto;
                            break;
                        default:
                            saldoCredito = saldoCredito - monto;
                            break;
                    }
                } else {
                    mensaje = "No dispone del saldo suficiente para realizar la operación";
                }*/
                break;

            default:
                //Caso en el que el tipo de dinero es Ingreso
                mensaje = null;
                mtIngreso = (mtIngreso + monto);

                switch (tipoCuenta){
                    case "Ahorro":
                        saldoAhorro = saldoAhorro + monto;
                        break;
                    case "Efectivo":
                        saldoEfectivo = saldoEfectivo + monto;
                        break;
                    default:
                        saldoCredito = saldoCredito + monto;
                        break;
                }
                break;
        }
    }

    // MÉTODOS DE AYUDA
    private void imprimirMensaje(){
        mensaje = "No dispone del saldo suficiente para realizar la operación";
    }

    private Double hallarSaldo(Double tipoSaldo, Double monto){
        mensaje = null;
        mtEgreso = mtEgreso + monto;

        tipoSaldo = tipoSaldo - monto;
        return tipoSaldo;
    }


    // GETTERS AND SETTERS
    public Double getSaldoAhorro() {
        return saldoAhorro;
    }

    public void setSaldoAhorro(Double saldoAhorro) {
        this.saldoAhorro = saldoAhorro;
    }

    public Double getSaldoCredito() {
        return saldoCredito;
    }

    public void setSaldoCredito(Double saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

    public Double getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(Double saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public Operation getOperacion() {
        return operacion;
    }

    public void setOperacion(Operation operacion) {
        this.operacion = operacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Double getMtEgreso() {
            return mtEgreso;
    }

    public Double getMtIngreso() {
        return mtIngreso;
    }

    @Override
    public String toString() {
        return "Saldo{" +
                "saldoAhorro=" + saldoAhorro +
                ", saldoCredito=" + saldoCredito +
                ", saldoEfectivo=" + saldoEfectivo +
                ", operacion=" + operacion +
                '}';
    }
}
