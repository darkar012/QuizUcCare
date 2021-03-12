package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class nexoEpidemiologico extends AppCompatActivity {
    private Button continuarBtn2;
    private CheckBox contacto, ninguno, viajeInt, viajeNac, trabSal;
    private String name, ide;
    private int nexoR;
    private boolean yaSumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexo_epidemiologico);

        continuarBtn2 = findViewById(R.id.continuarBtn2);
        contacto = findViewById(R.id.contactoCH);
        ninguno = findViewById(R.id.ningunCB);
        viajeInt = findViewById(R.id.viajeIntCB);
        viajeNac = findViewById(R.id.viajeNacCB);
        trabSal = findViewById(R.id.trabajoSCB);
        name = getIntent().getExtras().getString("name");
        ide = getIntent().getExtras().getString("id");

        isCheckboxClicked();

        continuarBtn2.setOnClickListener(
                (v) -> {
                    nexoCalification(contacto);
                    nexoCalification(ninguno);
                    nexoCalification(viajeNac);
                    nexoCalification(viajeInt);
                    nexoCalification(trabSal);

                    Log.e(">>>", "" + nexoR);

                    Intent i = new Intent(this, sintomas.class);
                    i.putExtra("nexoCal", nexoR);
                    i.putExtra("name", name);
                    i.putExtra("id", ide);
                    startActivity(i);
                    finish();
                }
        );
    }

    public void isCheckboxClicked() {
        continuarBtn2.setEnabled(false);
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (contacto.isChecked() || ninguno.isChecked() || viajeInt.isChecked() || viajeNac.isChecked() || trabSal.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        continuarBtn2.setEnabled(true);
                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        continuarBtn2.setEnabled(false);
                                    }
                            );
                        }
                    }
                }
        ).start();

    }

    public void nexoCalification(CheckBox check) {
        yaSumo = false;
        if (check.isChecked()) {
            nexoR = nexoR + 3;
            yaSumo = true;
        } else if (yaSumo) {
            nexoR = nexoR - 3;
            yaSumo = false;
        }
    }
}