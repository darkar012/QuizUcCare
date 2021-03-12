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
                    String nombreS = preferences.getString("nombre+calificacion", "");
                    String userS = preferences.getString("nombre+calificacion", "");
                    String usuario = nombre + ", "+ ide + ", "+ calification + "\n";
                    String nombreCal = nombre + ", "+ calification + "\n";
                    preferences.edit().putString("nombre+calificacion", nombreS + nombreCal).apply();
                    preferences.edit().putString("encuestados", userS + usuario).apply();
finish();
                }
        );

    }

    private void sintomasCalification(CheckBox check) {
        yaSumo = false;
        if (check.isChecked() && !yaSumo) {
            sintomasR = sintomasR + 3;
            yaSumo = true;
        } else if (yaSumo) {
            sintomasR = sintomasR - 3;
            yaSumo = false;
        }
    }

    private void isCheckboxClicked() {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (fiebre.isChecked() || dolorG.isChecked() || congestionNasal.isChecked() || tos.isChecked() || fatiga.isChecked() || difRespirar.isChecked() || ninguno.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        continuarBtn3.setEnabled(true);
                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        continuarBtn3.setEnabled(false);
                                    }
                            );
                        }
                    }
                }
        ).start();
    }
}