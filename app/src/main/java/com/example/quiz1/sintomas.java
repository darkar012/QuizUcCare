package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class sintomas extends AppCompatActivity {
    private Button continuarBtn3;
    private CheckBox fiebre, dolorG, congestionNasal, tos, fatiga, difRespirar, ninguno;
    private int nexoR, sintomasR;
    private boolean yaSumo;
    private String nombre,ide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);

        continuarBtn3=findViewById(R.id.continuarBtn3);
        fiebre = findViewById(R.id.fiebre);
        dolorG = findViewById(R.id.dolorG);
        congestionNasal = findViewById(R.id.congesN);
        tos = findViewById(R.id.tos);
        fatiga = findViewById(R.id.fatiga);
        difRespirar = findViewById(R.id.dificultadR);
        ninguno = findViewById(R.id.ningunS);

        nexoR = getIntent().getExtras().getInt("nexoCal");
        nombre = getIntent().getExtras().getString("name");
        ide = getIntent().getExtras().getString("id");

        isCheckboxClicked();

        continuarBtn3.setEnabled(false);

        continuarBtn3.setOnClickListener(
                (v)->{
                    sintomasCalification(fiebre);
                    sintomasCalification(dolorG);
                    sintomasCalification(congestionNasal);
                    sintomasCalification(tos);
                    sintomasCalification(fatiga);
                    sintomasCalification(difRespirar);
                    sintomasCalification(ninguno);

                    SharedPreferences preferences = getSharedPreferences("encuesta", MODE_PRIVATE);
                    int total = nexoR + sintomasR;
                    String calification = String.valueOf(total);
                    String nombreCal = nombre + ", "+ calification + "\n";
                    String usuario = nombre + ", "+ ide + ", "+ calification + "\n";
                    preferences.edit().putString("nombre+calificacion", nombreCal).apply();
                    preferences.edit().putString("encuestados", usuario).apply();

                }
        );

    }

    private void sintomasCalification(CheckBox check) {
        yaSumo = false;
        if (check.isChecked() && !yaSumo) {
            sintomasR = sintomasR + 3;
            yaSumo = true;
        } else {
            sintomasR = sintomasR - 3;
            yaSumo = false;
        }
    }

    private void isCheckboxClicked() {
        new Thread(
                () -> {
                    while (true) {
                        if (fiebre.isChecked() || dolorG.isChecked() || congestionNasal.isChecked() || tos.isChecked() || fatiga.isChecked() || difRespirar.isChecked() || ninguno.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        continuarBtn3.setEnabled(true);
                                    }
                            );
                        }
                    }
                }
        ).start();
    }
}