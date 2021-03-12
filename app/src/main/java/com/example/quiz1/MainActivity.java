package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button registerbtn;
    private TextView listaRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerbtn = findViewById(R.id.registrarBtn);
        listaRegis = findViewById(R.id.listaRegistro);

        listaUsuarios();

        registerbtn.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, newRegister.class);
                    startActivity(i);
                }
        );

    }

    public void listaUsuarios(){
        SharedPreferences preferences = getSharedPreferences("encuesta", MODE_PRIVATE);
        String usuarios = preferences.getString("nombre+calificacion", "no hay encuestados");
        listaRegis.setText(usuarios);
        Log.e(">>>", usuarios);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaUsuarios();
    }
}