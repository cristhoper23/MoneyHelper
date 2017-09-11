package com.cristhoper.moneyhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cristhoper.moneyhelper.R;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_OPERATION_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nuevaOperacion(View view){
        startActivityForResult(new Intent(this, NewOperationActivity.class), NEW_OPERATION_REQUEST);
    }
}
