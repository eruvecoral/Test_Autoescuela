package com.example.iscar.Activityes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iscar.Activityes.Cuestionarios.ActivityDirectorioSeccionesCuestionarios;
import com.example.iscar.Activityes.Graficos.Activity_Secciones_Graficos;
import com.example.iscar.Activityes.Temario.ActivityDirectorioSeccionesTemario;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerElemento;

import java.util.List;

public class Activity_temario_Test extends AppCompatActivity {

    private Button btn_temario,btn_test,btn_graficos;
    private List<RecyclerElemento> elementos;
    private Bundle datos;
    public String elUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elige_temario_test);
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        //Botones de redirección
        btn_temario = (Button) findViewById(R.id.btn_temario);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_graficos = (Button) findViewById(R.id.btn_puntuaciones);

        //metodo onClick
        btn_temario.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               verTemarios(view) ;
                                           }
                                       }
        );

        btn_test.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               verTests(view) ;
                                           }
                                       }
        );

        btn_graficos.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View view) {
                                            verGraficos(view) ;
                                        }
                                    }
        );

    }

    public void verTemarios(View v)
    {
        Intent intent= new Intent(getApplicationContext(), ActivityDirectorioSeccionesTemario.class);
        intent.putExtra("usuario",elUsuario);
        startActivity(intent);

    }

    public void verTests(View v)
    {
        Intent intent= new Intent(getApplicationContext(), ActivityDirectorioSeccionesCuestionarios.class);
        intent.putExtra("usuario",elUsuario);
        startActivity(intent);

    }

    public void verGraficos(View v)
    {
        Intent intent= new Intent(getApplicationContext(), Activity_Secciones_Graficos.class);
        intent.putExtra("usuario",elUsuario);
        startActivity(intent);

    }


}