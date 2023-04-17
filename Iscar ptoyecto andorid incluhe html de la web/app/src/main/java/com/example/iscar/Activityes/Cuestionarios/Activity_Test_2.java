package com.example.iscar.Activityes.Cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.Activityes.Graficos.Activity_Secciones_Graficos;
import com.example.iscar.Adapters.AdapterPreguntaRadiobuttons_2;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTest;

import java.util.List;

public class Activity_Test_2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_estadisticas;
    public static List<RecyclerTest> arrayListTest;
    private Bundle datos;
    private TextView miUsuario;
    private String elUsuario;
    private String laSeccion;
    private String elTema;
    private String stringTabla;
    private Activity_Test activity_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tst_resultados);
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        elTema = datos.getString("Tema");
        arrayListTest = Activity_Test.getArrayListTestRespuestas();
        miUsuario = (TextView) findViewById(R.id.tVUsuVerAcTST);
        miUsuario.setText(elUsuario);

        recyclerView = findViewById(R.id.reciclerTemarioTest);
        btn_estadisticas = findViewById(R.id.btn_estadisticas);

        inflateViewRecycler();

        btn_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verGraficos(v);
            }
        });


    }

    public void verGraficos(View v) {
        Intent intent = new Intent(getApplicationContext(), Activity_Secciones_Graficos.class);
        intent.putExtra("usuario", elUsuario);
        startActivity(intent);

    }


    private void inflateViewRecycler() {
        //Adaptador que recibe la lista, contxt e interface OnClickListener de AdapterDirectorio
        AdapterPreguntaRadiobuttons_2 elementAdapter = new AdapterPreguntaRadiobuttons_2(Activity_Test_2.this, arrayListTest);

        //Evita  invalidar el diseño cuando cambie el contenido del adaptador.
        recyclerView.setHasFixedSize(true);
        //Hace el listado lineal (arriba-abajo)
        LinearLayoutManager llm = new LinearLayoutManager(Activity_Test_2.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemViewCacheSize(arrayListTest.size());
        //Anima el Item
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //añade el tipo de decoración
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, llm.getOrientation());
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity_Test_2.this, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Adaptador que utilizamos
        recyclerView.setAdapter(elementAdapter);
    }

}