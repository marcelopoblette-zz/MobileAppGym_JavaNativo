package com.example.marcelopoblete;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class EliminarEvaluaciones extends AppCompatActivity {
    TextInputLayout til_weight, til_evaDate;
    TextView menu, tv_lista;
    Button btn_buscar_evaluacion,btn_eliminar_evaluacion;
    String weight, imc,rowData, rowId, evDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_evaluaciones);

        menu = findViewById(R.id.tv_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Menu.class);
                startActivity(intent);
            }
        });
//        String tbl_evaluaciones = "CREATE TABLE tbl_evaluaciones ( id integer PRIMARY KEY AUTOINCREMENT,
//        fecha String, altura numeric,peso numeric,imc numeric, id_usuario integer )";


          rowData = getIntent().getStringExtra("dato");
          evDate = rowData.split(" ")[1];
          weight = rowData.split(" ")[3];
          imc = rowData.split(" ")[4];

          rowId = getIntent().getStringExtra("rowId");
          Log.i("RawID", rowId);



//        referencias

        tv_lista = findViewById(R.id.tv_lista);
        tv_lista.setText(/*rowId+"."+*/rowData);

        btn_buscar_evaluacion = findViewById(R.id.btn_buscar_evaluacion);
        btn_eliminar_evaluacion = findViewById(R.id.btn_eliminar_evaluacion);
        til_weight = findViewById(R.id.til_weight);
        til_evaDate = findViewById(R.id.til_evaDate);

        til_evaDate.getEditText().setText(evDate);
        til_weight.getEditText().setText(weight);
        imc= imc;

        btn_eliminar_evaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowDelete(rowId);

            }
        });


    }
//    String tbl_evaluaciones = "CREATE TABLE tbl_evaluaciones ( id integer PRIMARY KEY AUTOINCREMENT,
//    fecha String, altura numeric,peso numeric,imc numeric, id_usuario integer )";





    public void OnBackPressed(){
        super.onBackPressed();
    }

    public void rowDelete(String rowId){
        DbHelper dbHelper = new DbHelper(this, "db_gym_dev", null, 1);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        if (sqLiteDatabase != null) {
            int deleted = sqLiteDatabase.delete("tbl_evaluaciones","id = " +rowId,null);
            if(deleted>0){
                Toast.makeText(this, "El registro seleccionado fue eliminado con éxito.", Toast.LENGTH_LONG).show();
                OnBackPressed();
            }


        }

    }
}
