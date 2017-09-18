package com.cristhoper.moneyhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cristhoper.moneyhelper.R;
import com.cristhoper.moneyhelper.models.Operation;
import com.cristhoper.moneyhelper.repositories.OperationRepository;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart = (BarChart) findViewById(R.id.barGraph);

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();

        OperationRepository opRepo = OperationRepository.getInstance();
        List<Operation> operaciones = opRepo.getOperaciones();

        int contador = 0;
        for (Operation op: operaciones){
            float monto  = (float) op.getMonto();
            String tipoDinero = op.getTipoDinero();

            if (tipoDinero.equals("Ingreso")) {
                entriesGroup1.add(new BarEntry(contador, monto));

                contador = contador + 1;
            } else {
                entriesGroup2.add(new BarEntry(contador, monto));

                contador = contador + 1;
            }

            BarDataSet set1 = new BarDataSet(entriesGroup1, "Ingresos");
            set1.setColors(new int[]{R.color.colorMoney}, this);
            BarDataSet set2 = new BarDataSet(entriesGroup2, "Egresos");
            set2.setColors(new int[]{R.color.colorAccent}, this);

            BarData data = new BarData(set1, set2);
            data.setBarWidth(0.9f);
            barChart.setData(data);

            barChart.setFitBars(true);
            barChart.invalidate();
        }
    }
}
