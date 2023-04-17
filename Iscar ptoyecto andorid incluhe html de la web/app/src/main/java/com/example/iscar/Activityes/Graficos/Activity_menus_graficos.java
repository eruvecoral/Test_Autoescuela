package com.example.iscar.Activityes.Graficos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.iscar.R;

public class Activity_menus_graficos extends AppCompatActivity implements View.OnClickListener {

    public ImageButton IB_Secciones;
    public ImageButton IB_Temas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_graficos);

        IB_Secciones = (ImageButton) findViewById(R.id.IB_Secciones);
        IB_Temas = (ImageButton) findViewById(R.id.IB_Temas);

        IB_Secciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        IB_Temas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

}