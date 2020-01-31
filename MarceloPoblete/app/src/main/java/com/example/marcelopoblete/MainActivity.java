package com.example.marcelopoblete;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout txtUser, txtPass;
    Button btnIngresar;
    TextView txt_registrarse;
    String altura, userId, name, apellido, fechaNac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias

        txtUser = findViewById(R.id.textInputLayout_user);
        txtPass = findViewById(R.id.textInputLayout_password);
        btnIngresar= findViewById(R.id.btn_ingresar);
        txt_registrarse = findViewById(R.id.btn_registrarse);



        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Boton ingresar
                String userName = txtUser.getEditText().getText().toString();
                String pass = txtPass.getEditText().getText().toString();
                login(userName,pass);
                Intent intent =  new Intent(view.getContext(),RegistroEvaluaciones.class);

                intent.putExtra("userId",userId);
                intent.putExtra("userName",userName);
                intent.putExtra("name",name);
                intent.putExtra("apellido",apellido);
                intent.putExtra("fechaNac",fechaNac);
                intent.putExtra("userHeght",altura);

//                Toast.makeText(this, "Bienvenid@ a Gym Fitness Club :" +name+" "+apellido, Toast.LENGTH_LONG).show();

                startActivity(intent);




            }
        });


        //Boton Registrar
        txt_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), User_register.class);
                startActivity(intent);
            }
        });
    }
//    String tbl_usuarios = "CREATE TABLE tbl_usuarios ( id integer PRIMARY KEY AUTOINCREMENT, username string, nombre string, apellido string, fechaNac string, altura decimal, clave string);";

    public void login(String userName, String pass){
        DbHelper dBhelper = new DbHelper(this,"db_gym_dev",null,1);
        SQLiteDatabase db = dBhelper.getReadableDatabase();

        if(db != null) {


            Cursor fullQuery = db.rawQuery("SELECT * FROM  tbl_usuarios where username = '"+userName+"' and clave = '"+pass+"' ",null);

            int cantidad = fullQuery.getCount();
            if (cantidad <1) {
                Toast.makeText(this, "Usuario o Contraseña incorrectos\n Intente nuevamente o Registrese", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, User_register.class);
//                startActivity(intent);

            }
//            Toast.makeText(this, "CANTIDAD:"+cantidad, Toast.LENGTH_SHORT).show();
            if(cantidad == 1){
                if(fullQuery.moveToFirst()){
                    do {
                        userId = fullQuery.getString(0);
                        userName= fullQuery.getString(1);
                        name= fullQuery.getString(2);
                        apellido= fullQuery.getString(3);
                        fechaNac= fullQuery.getString(4);
                        altura = fullQuery.getString(5);
                    }while (fullQuery.moveToNext());
                }


            }
        }
    }
}
