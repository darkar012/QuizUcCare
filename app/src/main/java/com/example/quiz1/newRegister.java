package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newRegister extends AppCompatActivity {

    private EditText nombre, id;
    private Button continuar;
    private String name, ide;
    private boolean noNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        nombre = findViewById(R.id.nombreET);
        id = findViewById(R.id.idET);
        continuar = findViewById(R.id.continuarBtn);

        continuar.setOnClickListener(
                (v) -> {
                    name = nombre.getText().toString();
                    ide = id.getText().toString();
                    validation();
                    if (noNumber) {
                        Intent i = new Intent(this, nexoEpidemiologico.class);
                        i.putExtra("name", name);
                        i.putExtra("id", ide);
                        startActivity(i);
finish();
                    }
                }
        );
    }

    protected void validation() {
        noNumber = true;
        if (name == null || name.isEmpty() || ide == null || ide.isEmpty()) {
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_LONG).show();
            noNumber = false;
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                    Toast.makeText(this, "Ha ingresado numeros en el nombre", Toast.LENGTH_LONG).show();
                    name = "";
                    noNumber = false;
                } else {
                    noNumber = true;
                }
            }
        }
        String usuarios = getSharedPreferences("encuesta",MODE_PRIVATE).getString("encuestados", "");
        if (usuarios.contains(ide)) {
            Toast.makeText(this, "Esta persona ya fue registrada", Toast.LENGTH_LONG).show();
            noNumber = false;
        }
    }
}
