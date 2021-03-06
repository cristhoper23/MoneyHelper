package com.cristhoper.moneyhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cristhoper.moneyhelper.R;
import com.cristhoper.moneyhelper.models.Saldo;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_OPERATION_REQUEST = 100;

    private TextView textAhorro, textCredito, textEfectivo, txtPorcentajes;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAhorro = (TextView) findViewById(R.id.txtAhorro);
        textCredito = (TextView) findViewById(R.id.txtCredito);
        textEfectivo = (TextView) findViewById(R.id.txtEfectivo);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtPorcentajes = (TextView) findViewById(R.id.txtPorcentajes);

        mostrarSaldos();
    }

    public void nuevaOperacion(View view){
        startActivityForResult(new Intent(this, NewOperationActivity.class), NEW_OPERATION_REQUEST);
    }

    public void verGrafica(View view){
        startActivity(new Intent(this, ChartActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mostrarSaldos();
    }

    private void mostrarSaldos(){
        Saldo saldoActual = Saldo.getInstance();

        textAhorro.setText(saldoActual.getSaldoAhorro().toString());
        textCredito.setText(saldoActual.getSaldoCredito().toString());
        textEfectivo.setText(saldoActual.getSaldoEfectivo().toString());

        //Progreso de los ingresos y egresos en porcentaje
        double mtIngreso = saldoActual.getMtIngreso();
        double mtEgreso = saldoActual.getMtEgreso();
        double mTotal = mtIngreso + mtEgreso;

        mtIngreso = Math.round((mtIngreso * 100)/(mTotal));
        mtEgreso = Math.round((mtEgreso * 100)/(mTotal));

        int i = (int) mtIngreso;
        int e = (int) mtEgreso;

        txtPorcentajes.setText(i + " %   /   " + e + " %");

        //Progreso de la barra
        progressBar.setProgress(i);
    }
}
