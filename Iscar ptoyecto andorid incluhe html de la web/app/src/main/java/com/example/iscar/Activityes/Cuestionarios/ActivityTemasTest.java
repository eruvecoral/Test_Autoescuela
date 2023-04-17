package com.example.iscar.Activityes.Cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.Activityes.Cuestionarios.Activity_Test;
import com.example.iscar.Adapters.AdapterDirectorioTemario;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTemario;

import java.util.ArrayList;
import java.util.List;

public class ActivityTemasTest extends AppCompatActivity {

    private Bundle datos;
    private TextView miUsuario;
    private String laSeccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_temario);
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        String elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        miUsuario= findViewById(R.id.TVUsuarioWeb);
        miUsuario.setText(elUsuario);

        init();

    }

    public void init()
    {
        List<RecyclerTemario> elementos = new ArrayList<>();
        int nTemas = 0;
        if(laSeccion.equals("Sección 1 ") || laSeccion.equals("Sección 8 ")){
            nTemas=8;
        }else if (laSeccion.equals("Sección 2 ")|| laSeccion.equals("Sección 3 ")|| laSeccion.equals("Sección 4 ")|| laSeccion.equals("Sección 5 ")|| laSeccion.equals("Sección 6 ")|| laSeccion.equals("Sección 7 ")){
            nTemas=7;
        }
        for (int i = 1; i<nTemas+1; i++){
            elementos.add((new RecyclerTemario("","Tema " + i, "Nuevo ")));
        }


        //Adaptador que recibe la lista, contxt e interface OnClickListener de AdapterDirectorio
        AdapterDirectorioTemario elementAdapter = new AdapterDirectorioTemario(elementos,this, new AdapterDirectorioTemario.OnItemClickListener(){

            @Override
            public void onItemClick(RecyclerTemario item) {
                goToSeccion(item);
            }

            });
        //Enlace con el recyclerView de ActivityDirectorio
        RecyclerView rv = findViewById(R.id.reciclerTemarioTest);
        //Evita  invalidar el diseño cuando cambie el contenido del adaptador.
        rv.setHasFixedSize(true);
        //Hace el listado lineal (arriba-abajo)
        rv.setLayoutManager(new LinearLayoutManager(this));
        //Adaptador que utilizamos
        rv.setAdapter(elementAdapter);

    }

    /**
     * Metodo que recibe un AdapterDirectorio de la reciclerView y crea
     * un intent para abrir una nueva activity ClaseTst
     */
    public void goToSeccion(RecyclerTemario item)
    {
        Intent i = new Intent(this, Activity_Test.class);
        i.putExtra("Sección",  item.getSeccion());
        i.putExtra("Tema",  item.getTema());
        i.putExtra("usuario",miUsuario.getText().toString());
        startActivity(i);
    }



}