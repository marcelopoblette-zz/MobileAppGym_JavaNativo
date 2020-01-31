package com.example.marcelopoblete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ConsultarEvaluaciones extends AppCompatActivity {
    TextInputLayout tilDatecon, tilWeightcon;
    Button btnAsk;
    ListView lvData;
    String[] datos;
    TextView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consultar_evaluaciones);

        menu = findViewById(R.id.tv_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Menu.class);
                startActivity(intent);
            }
        });


        String userName = getIntent().getStringExtra("nombreUsuario");


        tilDatecon = findViewById(R.id.tilDatecon);
        tilWeightcon = findViewById(R.id.tilWeightcon);
        btnAsk = findViewById(R.id.btn_consultar_evaluacion);
        lvData = findViewById(R.id.lvData);
        //listView Data

        datos = new String[] {"F 05/11/2019 - IMC: 12","F 05/11/2019 - IMC: 13","F 07/11/2019 - IMC: 14"};
//        //Adapter
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
//        //Show data
//        revisé esto muchas veces y no se porque se cae. si comento la siguiente linea no se cae, no entiendo porque
        lvData.setAdapter(adapter);



        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fecha =tilDatecon.getEditText().getText().toString();
                String peso = tilWeightcon.getEditText().getText().toString();
                String registro = "F: "+fecha+"IMC"+peso;
            }
        });

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });


    }
}
