package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class newRegister extends AppCompatActivity {

    private EditText nombre, id;
    private Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        nombre = findViewById(R.id.nombreET);
        id = findViewById(R.id.idET);
        continuar = findViewById(R.id.continuarBtn);

        continuar.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, nexoEpidemiologico.class);
                    startActivity(i);
                }
        );
    }
}