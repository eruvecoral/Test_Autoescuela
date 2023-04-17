package com.example.iscar.Activityes.Temario;
/**
 * Activity con los directorios de los tests (carpetas de test)
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.Adapters.AdapterDirectorioSecciones;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerElemento;

import java.util.ArrayList;
import java.util.List;

public class ActivityDirectorioSeccionesTemario extends AppCompatActivity {

    private List<RecyclerElemento> elementos;
    private Bundle datos;
    private TextView miUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio_secciones_temario);
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        String elUsuario = datos.getString("usuario");
        miUsuario=(TextView)findViewById(R.id.TVUsuTemarioSecciones);
        miUsuario.setText(elUsuario);

        init();

    }

    public void init()
    {
        elementos = new ArrayList<RecyclerElemento>();
        //Cargar numero de secciones

        //Anyadimos elementos ejemplo a la lista TODO
        elementos.add((new RecyclerElemento("Sección 1 ","nuevo")));
        elementos.add((new RecyclerElemento("Sección 2 ","nuevo")));
        elementos.add((new RecyclerElemento("Sección 3 ","nuevo")));
        elementos.add((new RecyclerElemento("Sección 4 ","nuevo")));
        elementos.add((new RecyclerElemento("Sección 5 ","nuevo")));


        //Adaptador que recibe la lista, contxt e interface OnClickListener de AdapterDirectorio
        AdapterDirectorioSecciones elementAdapter = new AdapterDirectorioSecciones(elementos,this, new AdapterDirectorioSecciones.OnItemClickListener(){

            @Override
            public void onItemClick(RecyclerElemento item) {
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
    public void goToTest(RecyclerElemento item)
    {
        Intent i = new Intent(this, ActivityTemasTemario.class);
        i.putExtra("Seccion",  item.getSeccion());
        i.putExtra("usuario",miUsuario.getText().toString());
        startActivity(i);
    }

}