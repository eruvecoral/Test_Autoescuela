package com.example.iscar.Activityes.Cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.Adapters.AdapterDirectorioTemarioCuestionario;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTemario;

import java.util.ArrayList;
import java.util.List;

public class ActivityTemarioTemas_Cuestionario extends AppCompatActivity {

    private List<RecyclerTemario> elementos;
    private Bundle datos;
    private TextView miUsuario;
    private String elUsuario;
    private String laSeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_temario);
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        miUsuario=(TextView)findViewById(R.id.tVUsuVer);
        miUsuario.setText(elUsuario);

        init();

    }

    public void init()
    {
        elementos = new ArrayList<RecyclerTemario>();
        int nTemas = 0;
        if(laSeccion.equals("Sección 1 ") || laSeccion.equals("Sección 5 ")){
            nTemas=8;
        }else if (laSeccion.equals("Sección 2 ")|| laSeccion.equals("Sección 3 ")|| laSeccion.equals("Sección 4 ")){
            nTemas=7;
        }
        for (int i = 1; i<nTemas+1; i++){
            elementos.add((new RecyclerTemario(laSeccion,"Tema " + i,"nuevo")));
        }

        AdapterDirectorioTemarioCuestionario elementAdapter = new AdapterDirectorioTemarioCuestionario(elementos,this, new AdapterDirectorioTemarioCuestionario.OnItemClickListener(){

            @Override
            public void onItemClick(RecyclerTemario item) {
                goToTest(item);
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
     * @param item
     */
    public void goToTest(RecyclerTemario item)
    {
        Intent i = new Intent(this, Activity_Test.class);
        i.putExtra("Sección",  item.getSeccion());
        i.putExtra("Tema",  item.getTema());
        i.putExtra("usuario",miUsuario.getText().toString());
        startActivity(i);
    }
}