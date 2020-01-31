package com.example.marcelopoblete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    TextView tv_userReg, tv_evReg, tv_AskEv, tv_delEv, tv_endSess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv_userReg = findViewById(R.id.user_register);
        tv_evReg = findViewById(R.id.ev_register);
        tv_AskEv = findViewById(R.id.ev_ask);
        tv_delEv = findViewById(R.id.del_ev);
        tv_endSess= findViewById(R.id.end_sess);

        tv_userReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), User_register.class);
                startActivity(intent);
            }
        });
        tv_evReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistroEvaluaciones.class);
                startActivity(intent);
            }
        });

        tv_AskEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConsultarEvaluaciones.class);
                startActivity(intent);

            }
        });

        tv_delEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EliminarEvaluaciones.class);
                startActivity(intent);
            }
        });

        tv_endSess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });





    }
}
