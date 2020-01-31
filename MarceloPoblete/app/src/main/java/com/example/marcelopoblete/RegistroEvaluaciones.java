package com.example.marcelopoblete;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegistroEvaluaciones extends AppCompatActivity {


    TextInputLayout til_weight, til_evaDate, til_height;
    Button btn_registro_evaluacion, btn_evdate;
    TextView menu, welcome;
    String altura, name, apellido, fechaNac;
    ListView lv_show_reg;
    String[] datos;
    int userIdBd;
    private int mDay, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_evaluaciones);

        welcome = findViewById(R.id.welcome);
        til_weight = findViewById(R.id.til_weight);
        til_height = findViewById(R.id.til_altura_reg);
        til_evaDate = findViewById(R.id.til_evaDate);
        btn_registro_evaluacion = findViewById(R.id.btn_registro_evaluacion);
        btn_evdate = findViewById(R.id.btn_evdate);
        menu = findViewById(R.id.tv_menu);
        lv_show_reg = findViewById(R.id.lv_show_reg);


        String nombre = getIntent().getStringExtra("name");
        welcome.setText(nombre+"!\n\n¡Bienvenid@ a Gym Fitness Club!\n");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Menu.class);
                startActivity(intent);
            }
        });


        userIdBd = Integer.parseInt(getIntent().getStringExtra("userId"));

//      Muestro los datos previamente insertados
        mostrarRegistro(userIdBd);




        datos = mostrarRegistro(userIdBd);

        ArrayAdapter<String> adapter;
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lv_show_reg.setAdapter(adapter);


        btn_registro_evaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double heightReg = Double.parseDouble(til_height.getEditText().getText().toString());
                double weightREg = Double.parseDouble(til_weight.getEditText().getText().toString());
                String evDate =til_evaDate.getEditText().getText().toString();

//                Instancia clase calculo.
                double imc = Calculos.calcularIMC(weightREg, heightReg);

//                inserto datos a la bd
                insertarEvaluacion(evDate,heightReg,weightREg, imc, userIdBd);

//                NUEVO
//                ====================================

//              Muestro los datos recien insertados
                datos = mostrarRegistro(userIdBd);

                ArrayAdapter<String> adapter;
                adapter= new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,datos);
                lv_show_reg.setAdapter(adapter);

//                NUEVO
//                ====================================


            }
        });

        lv_show_reg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),EliminarEvaluaciones.class);
                intent.putExtra("rowId", datos[position].split(";")[0]);
                intent.putExtra("dato", datos[position].split(";")[1]);

//                intent.putExtra("dato", datos[position]);
//                intent.putExtra("rowId", datos[position]);
                startActivity(intent);

//                intent.putExtra("dato", datos[position]);
//                intent.putExtra("rowId", datos[0]);
//                Toast.makeText(RegistroEvaluaciones.this, datos[position],Toast.LENGTH_SHORT).show();
            }
        });


        final Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);

        btn_evdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dia=    (dayOfMonth<10)? "0"+ String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        String mes= (month+1<10)? "0"+String.valueOf(month+1):String.valueOf(month+1);
                        String anio= String.valueOf(year);

                        til_evaDate.getEditText().setText(dia+"-"+(mes)+"-"+anio);

                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });




    }
    public void insertarEvaluacion(String fecha, double altura,double peso,double imc, int id_user){

        DbHelper dbHelper = new DbHelper(this, "db_gym_dev", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("altura", altura);
            contentValues.put("peso", peso);
            contentValues.put("imc", imc);
            contentValues.put("fecha", fecha);
            contentValues.put("id_usuario", id_user);
            long nRow = db.insert("tbl_evaluaciones", null, contentValues);
            if (nRow>0){
                Toast.makeText(this,"La evaluación fue registrada exitosamente. " , Toast.LENGTH_LONG).show();
//                mostrarRegistro(userIdBd);
            }else {
                Toast.makeText(this,"La evaluación no se pudo registrar." , Toast.LENGTH_SHORT).show();
            }
        }
    }
//    String tbl_evaluaciones = "CREATE TABLE tbl_evaluaciones ( id integer PRIMARY KEY AUTOINCREMENT,
//    fecha String, altura numeric,peso numeric,imc numeric, id_usuario integer )";

    public String[] mostrarRegistro(int idUsuario) {
        DbHelper dbHelper = new DbHelper(this, "db_gym_dev", null, 1);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        if (sqLiteDatabase != null) {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_evaluaciones where id_usuario = '" + idUsuario + "' ", null);
            int cant = cursor.getCount();
            int auxiliary = 0;
            datos = new String[cant];
            if (cursor.moveToFirst()) {
                do {
                    String row= "\nId Evaluación: " + cursor.getString(0) + ";" +
                            "\nFecha: " + cursor.getString(1) + "." +
                            "\nPeso: " + cursor.getString(3) + "." +
                            "\nAltura: " + cursor.getString(2) + "." +
                            "\nImc: " + cursor.getString(4) + "" +
                            "\nId Usuario" + cursor.getString(5);
                    datos[auxiliary] = row;
                    auxiliary++;

                } while (cursor.moveToNext());
            }
        }
        return datos;
    }





}
