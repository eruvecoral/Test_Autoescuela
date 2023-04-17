package com.example.iscar.Activityes.Graficos;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iscar.Adapters.AdapterCarpetasTemasGraficas;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTemario;
import com.example.iscar.Padres.TablaResultados;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_Temas_Graficos extends AppCompatActivity {

    private List<RecyclerTemario> elementos;
    private Bundle datos;
    private TextView miUsuario, miSeccion;
    private String stringTablaRespuestas;
    private List<TablaResultados> losResultados;
    private String laSeccion;
    private String elUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_graficos);


        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        miUsuario = (TextView) findViewById(R.id.tVUsuVer);
        miUsuario.setText(elUsuario);
        String countSeccion = String.valueOf(laSeccion.charAt(laSeccion.length() - 2));
        stringTablaRespuestas = "tabla_puntuacion_usuario_" + elUsuario;
        elementos = new ArrayList<RecyclerTemario>();
        obtenerTemas("http://192.168.56.1/autoescuela_rabel/obtenerTemasPorSeccion.php", stringTablaRespuestas, ""+countSeccion );
    }

    private void inflateViewRecycler() {
        //Adaptador que recibe la lista, contxt e interface OnClickListener de AdapterDirectorio
        AdapterCarpetasTemasGraficas elementAdapter = new AdapterCarpetasTemasGraficas(elementos,this, new AdapterCarpetasTemasGraficas.OnItemClickListener(){

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
        Intent i = new Intent(this, Activity_GridLayout_graficos_temas.class);
        i.putExtra("Sección",  item.getSeccion());
        i.putExtra("Tema",  item.getTema());
        i.putExtra("usuario",miUsuario.getText().toString());
        startActivity(i);
    }

    public void obtenerTemas(String responseBody, String stringTabla, String seccion) {

        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("temas");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject data = result.getJSONObject(i);
                        String miElemento = data.optString("tema");
                        RecyclerTemario miRecyclerTemario = new RecyclerTemario(laSeccion,miElemento,"");
                        elementos.add(miRecyclerTemario);
                    }
                    inflateViewRecycler();

                } catch (JSONException e) {
                    Toast.makeText(Activity_Temas_Graficos.this, e.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Activity_Temas_Graficos.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Temas_Graficos.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Temas_Graficos.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", stringTabla);
                params.put("seccion", seccion);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
