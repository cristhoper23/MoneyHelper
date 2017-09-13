package com.cristhoper.moneyhelper.repositories;

import com.cristhoper.moneyhelper.models.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 11/09/2017.
 */

public class OperationRepository {

    private static OperationRepository _INSTANCE = null;

    private OperationRepository(){}

    public static OperationRepository getInstance(){
        if (_INSTANCE == null)
            _INSTANCE = new OperationRepository();
        return _INSTANCE;
    }

    private List<Operation> operaciones = new ArrayList<>();

    public List<Operation> getOperaciones() {
        return operaciones;
    }

    public void agregarOperacion(Operation operation){
        this.operaciones.add(operation);
    }
}
