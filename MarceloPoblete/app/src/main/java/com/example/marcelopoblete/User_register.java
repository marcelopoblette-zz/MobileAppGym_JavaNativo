package com.example.marcelopoblete;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class User_register extends AppCompatActivity {
    TextInputLayout tilUsername, tilPass, tilName, tilLastName, tilBirthDate;
    String username, pass, name, lastName, birthDate;
    private int mDay, mMonth, mYear;
    Button btnRegister, btnBirthDate;
    TextView menu;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        menu = findViewById(R.id.tv_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Menu.class);
                startActivity(intent);
            }
        });






        //Referencias
        tilUsername = findViewById(R.id.textInputLayout_username) ;
        tilPass = findViewById(R.id.textInputLayout_password) ;
        tilName = findViewById(R.id.textInputLayout_name) ;
        tilLastName = findViewById(R.id.textInputLayout_last_name) ;
        tilBirthDate = findViewById(R.id.textInputLayout_birthdate) ;
        btnRegister = findViewById(R.id.btn_registrar);
        btnBirthDate = findViewById(R.id.btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = tilUsername.getEditText().getText().toString();
                pass = tilPass.getEditText().getText().toString();
                name = tilName.getEditText().getText().toString();
                lastName = tilLastName.getEditText().getText().toString();
                birthDate = tilBirthDate.getEditText().getText().toString();

                if ((username.length()>0)&&(pass.length()>0)&&(name.length()>0)&&(lastName.length()>0)&&(birthDate.length()>0))
                {
                    insertarUsuario(username, name, lastName, birthDate, pass);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("nombreUsuario", username);
                    intent.putExtra("password", pass);
                    intent.putExtra("nombre", name);
                    intent.putExtra("apellido", lastName);
                    intent.putExtra("fecNac", birthDate);

                    startActivity(intent);
//                    Toast.makeText(view.getContext(), "El Usuario fue Registrado con Exito! ", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(view.getContext(), "Favor Complete Todos los Campos. ", Toast.LENGTH_SHORT).show();
                }
                if (username.length()==0){
                    tilUsername.setError("Campo Requerido");
                }
                if (pass.length()==0){
                    tilPass.setError("Campo Requerido");
                }
                if (name.length()==0){
                    tilName.setError("Campo Requerido");
                }
                if (lastName.length()==0){
                    tilLastName.setError("Campo Requerido");
                }
                if (birthDate.length()==0){
                    tilBirthDate.setError("Campo Requerido");
                }





            }
        });
        final Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);

        btnBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dia=    (dayOfMonth<10)? "0"+ String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                         String mes= (month+1<10)? "0"+String.valueOf(month+1):String.valueOf(month+1);
                         String anio= String.valueOf(year);

                        tilBirthDate.getEditText().setText(dia+"-"+(mes)+"-"+anio);

                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });
    }
    public void insertarUsuario(String username, String name, String lastName, String birthDate, String pass ) {
        DbHelper dbHelper = new DbHelper(this,"db_gym_dev",null,1);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        if(sqLiteDatabase != null){
            ContentValues contentValues =new ContentValues();

//            username string, nombre string, apellido string, fechaNac string, altura decimal, clave string

            contentValues.put("username",username);
            contentValues.put("nombre",name);
            contentValues.put("apellido",lastName);
            contentValues.put("fechaNac",birthDate);
            contentValues.put("clave",pass);
            long nRow = sqLiteDatabase.insert("tbl_usuarios",null,contentValues);
            if(nRow>0){
                Toast.makeText(this, "El usuario fue registrado exitosamente ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "El usuario no se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
