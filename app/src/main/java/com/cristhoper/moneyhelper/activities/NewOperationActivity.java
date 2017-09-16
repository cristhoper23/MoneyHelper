package com.cristhoper.moneyhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.cristhoper.moneyhelper.R;
import com.cristhoper.moneyhelper.models.Operation;
import com.cristhoper.moneyhelper.models.Saldo;
import com.cristhoper.moneyhelper.repositories.OperationRepository;

import java.util.List;

public class NewOperationActivity extends AppCompatActivity {

    private EditText montoEditText;
    private Spinner SpinnerCuenta;

    private String montoDinero, tipoDinero, tipoCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_operation);

        montoEditText = (EditText) findViewById(R.id.montoEditText);
        SpinnerCuenta = (Spinner) findViewById(R.id.tipoCuentaSpinner);

        SpinnerCuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (position == 0){
                    tipoCuenta = null;
                } else {
                    tipoCuenta = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void radioButtonClicked(View view){
        switch (((RadioButton) view).getText().toString()){
            case "Ingreso":
                tipoDinero = "Ingreso";
                break;
            case "Egreso":
                tipoDinero = "Egreso";
                break;
        }
    }

    public void registrarOperacion(View view){
        montoDinero = montoEditText.getText().toString();
        boolean montoValido = validarMonto(montoDinero);

        if (!montoValido){
            Toast.makeText(this, "Monto no válido", Toast.LENGTH_SHORT).show();
            return;
        }else if (tipoDinero == null){
            Toast.makeText(this, "Seleccionar Ingreso o Egreso", Toast.LENGTH_SHORT).show();
            return;
        }else if (tipoCuenta == null){
            Toast.makeText(this, "Seleccionar tipo de cuenta", Toast.LENGTH_SHORT).show();
            return;
        }

        Operation operacion = new Operation(Double.parseDouble(montoDinero), tipoDinero, tipoCuenta);

        //Se almacena cada operación en el array list
        OperationRepository objectOperationRep = OperationRepository.getInstance();
        objectOperationRep.agregarOperacion(operacion);


        Saldo saldo = Saldo.getInstance();
        saldo.obtenerSaldos(operacion);

        //Se verifica que el atributo mensaje de la clase Saldo sea nulo, de modo que se pueda retornar al activity principal
        if (saldo.getMensaje() == null) {
            finish();
        } else {
            Toast.makeText(this, saldo.getMensaje(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarMonto(String monto){
        try
        {
            Double.parseDouble(monto);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
