package com.cristhoper.moneyhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cristhoper.moneyhelper.R;
import com.cristhoper.moneyhelper.models.Saldo;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_OPERATION_REQUEST = 100;

    private TextView textAhorro, textCredito, textEfectivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAhorro = (TextView) findViewById(R.id.txtAhorro);
        textCredito = (TextView) findViewById(R.id.txtCredito);
        textEfectivo = (TextView) findViewById(R.id.txtEfectivo);

        mostrarSaldos();
    }

    public void nuevaOperacion(View view){
        startActivityForResult(new Intent(this, NewOperationActivity.class), NEW_OPERATION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mostrarSaldos();
    }

    public void mostrarSaldos(){
        Saldo saldoActual = Saldo.getInstance();

        textAhorro.setText(saldoActual.getSaldoAhorro().toString());
        textCredito.setText(saldoActual.getSaldoCredito().toString());
        textEfectivo.setText(saldoActual.getSaldoEfectivo().toString());
    }
}
