package com.example.iscar.Activityes.Cuestionarios;

import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iscar.Adapters.AdapterPreguntaRadiobuttons;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTest;
import com.example.iscar.Padres.TablaResultados;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_Test extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListView listView;
    private Button btn_validar;
    public static List<RecyclerTest> arrayListTest;
    public static List<RecyclerTest> arrayListTestRespuestas;
    public static AdapterPreguntaRadiobuttons elementAdapter;
    private Bundle datos;
    private TextView miUsuario;
    private String elUsuario;
    private String laSeccion;
    private String elTema;
    private String stringTabla;
    private String existeTabla;
    private String stringTablaRespuestas;
    private TablaResultados misResultados;
    public String respuestaExisteTabla;
    public String insertaDatosOK;
    public String tablaCreada = "";
    public boolean mostrarPuntuacion;
    public boolean isComplet;
    private RequestQueue queueExisteTabla;
    private RequestQueue queueExisteTabla2;
    private RequestQueue queueTablaCreada;
    private RequestQueue queueInsertaDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tst);
        queueExisteTabla = Volley.newRequestQueue(this);
        queueExisteTabla2 = Volley.newRequestQueue(this);
        queueTablaCreada = Volley.newRequestQueue(this);
        ;
        queueInsertaDato = Volley.newRequestQueue(this);
        ;
        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        elTema = datos.getString("Tema");
        miUsuario = (TextView) findViewById(R.id.tVUsuVerAcTST);
        miUsuario.setText(elUsuario);
        arrayListTestRespuestas = new ArrayList<RecyclerTest>();
        btn_validar = (Button) findViewById(R.id.btn_estadisticas);
        respuestaExisteTabla = "";
        insertaDatosOK = "";
        char countSeccion = laSeccion.charAt(laSeccion.length() - 2);
        char countTema = elTema.charAt(elTema.length() - 1);

        //random que cada vez que se entre generara que test de los tres se cargara
        int numero = (int) (Math.random() * (4 - 1) + 1);

        stringTabla = "seccion_" + countSeccion + "_tema" + countTema + "_test_" + numero;
        stringTablaRespuestas = "tabla_puntuacion_usuario_" + elUsuario;
        recyclerView = findViewById(R.id.reciclerTemarioTest);

        arrayListTest = new ArrayList<RecyclerTest>();
        elementAdapter = new AdapterPreguntaRadiobuttons();
        arrayListTest = obtenerPreguntas("http://192.168.56.1/autoescuela_rabel/devuelve_array_preguntas_test.php", stringTabla);
        isComplet = false;
        btn_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListTestRespuestas = getArrayListTestRespuestas();

                for (RecyclerTest elTest : arrayListTestRespuestas) {
                    if (elTest.getRespuesta()==null) {
                        Toast.makeText(Activity_Test.this, "Has de contestar todas las preguntas del cuestionario te falta la pregunta " + elTest.getId(), Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        isComplet = true;
                    }
                }
                if (isComplet) {


                    datosAInsertar();
                    existeTablaMethod();

                }else{
                    Toast.makeText(Activity_Test.this, "Has de contestar todas las preguntas", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });


    }

    private void createIntent() {
        Intent intent = new Intent(getApplicationContext(), Activity_Test_2.class);
        intent.putExtra("Sección", laSeccion);
        intent.putExtra("Tema", elTema);
        intent.putExtra("usuario", elUsuario);
        startActivity(intent);
    }

    private void existeTablaMethod() {
        String variable_auxiliar = null;
        existeTabla("http://192.168.56.1/autoescuela_rabel/existe_tabla.php", stringTablaRespuestas);
    }

    public void existeTabla(String responseBody, String tabla) {
        final int[] numeroTabla = new int[1];
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String mitable = response;
                respuestaExisteTabla = response.toString();

                if (response.equals("0")) {
                    //Creamos la tabla
                    seHaCreadoLaTabla("http://192.168.56.1/autoescuela_rabel/crea_tabla_cuestionarios_puntuacion.php", stringTablaRespuestas);
                    Toast.makeText(Activity_Test.this, tablaCreada, Toast.LENGTH_LONG).show();
                }
                if (response.equals("1")) {
                    //Creamos la tabla
                    insertaPuntuacion("http://192.168.56.1/autoescuela_rabel/insertar_puntuacion_cuestionarios.php", stringTablaRespuestas, misResultados);
                    Toast.makeText(Activity_Test.this, insertaDatosOK, Toast.LENGTH_LONG).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Test.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", tabla);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        queueExisteTabla.add(stringRequest);
    }

    public void seHaCreadoLaTabla(String responseBody, String stringTabla) {
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("ok")) {
                    existeTabla2("http://192.168.56.1/autoescuela_rabel/existe_tabla.php", stringTablaRespuestas);
                    Toast.makeText(Activity_Test.this, "Se ha creado la tabla", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Activity_Test.this, "Algun error seHaCreadoLaTabla", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Test.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", stringTabla);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        queueTablaCreada.add(stringRequest);

    }

    public void existeTabla2(String responseBody, String tabla) {
        final int[] numeroTabla = new int[1];
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                String mitable = response;
                respuestaExisteTabla = response.toString();

                if (response.equals("1")) {
                    insertaPuntuacion("http://192.168.56.1/autoescuela_rabel/insertar_puntuacion_cuestionarios.php", stringTablaRespuestas, misResultados);
                } else {
                    Toast.makeText(Activity_Test.this, "Algun error existeTabla2", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Test.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", tabla);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        queueExisteTabla2.add(stringRequest);
    }

    private void datosAInsertar() {
        misResultados = new TablaResultados();
        misResultados.setUsuarioCuestion(elUsuario);
        misResultados.setSeccion(laSeccion);
        misResultados.setTema(elTema);
        int countCorrectas = 0;

        for (RecyclerTest recyclerTest : arrayListTest) {
            if (recyclerTest.isEsCorrecta()) {
                countCorrectas++;
            }
        }
        misResultados.setN_correctas(countCorrectas);
    }

    public void insertaPuntuacion(String responseBody, String stringTabla, TablaResultados misResultados) {
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                insertaDatosOK = response.toString();

                if (response.contains("1")) {
                    createIntent();
                }else{
                    Toast.makeText(Activity_Test.this, "Algun error insertaPuntuacion", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Test.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", stringTabla);
                params.put("usuarioCuestion", misResultados.getUsuarioCuestion());
                params.put("seccion", misResultados.getSeccion());
                params.put("tema", misResultados.getTema());
                params.put("n_correctas", String.valueOf(misResultados.getN_correctas()));
                return params;
            }
        };
        //Intancia de la conexion con los datos
        queueInsertaDato.add(stringRequest);

    }

    public List<RecyclerTest> obtenerPreguntas(String responseBody, String stringTabla) {
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("preguntas");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject data = result.getJSONObject(i);
                        RecyclerTest recycler = new RecyclerTest();
                        recycler.setImagen(data.optString("foto"));
                        recycler.setId(data.optInt("idPregunta"));
                        recycler.setPregunta(data.optString("enunciado"));
                        recycler.setOpcA(data.optString("opcion1"));
                        recycler.setOpcB(data.optString("opcion2"));
                        recycler.setOpcC(data.optString("opcion3"));
                        recycler.setSolucion(data.optString("solucion"));
                        recycler.setExplicacion(data.optString("explicacion"));
                        arrayListTest.add(recycler);

                    }
                    for (RecyclerTest recyclerTest : arrayListTest) {
                        arrayListTestRespuestas.add(recyclerTest);
                    }
                    inflateViewRecycler();
                } catch (JSONException e) {
                    Toast.makeText(Activity_Test.this, e.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Activity_Test.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Test.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", stringTabla);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return arrayListTest;
    }

    private void inflateViewRecycler() {
        //Adaptador que recibe la lista, contxt e interface OnClickListener de AdapterDirectorio
        elementAdapter.setContext(Activity_Test.this);
        elementAdapter.setArrayListTest(arrayListTest);

        //Evita  invalidar el diseño cuando cambie el contenido del adaptador.
        recyclerView.setHasFixedSize(true);
        //Hace el listado lineal (arriba-abajo)
        LinearLayoutManager llm = new LinearLayoutManager(Activity_Test.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemViewCacheSize(arrayListTest.size());
        //Anima el Item
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //añade el tipo de decoración
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, llm.getOrientation());
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity_Test.this, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Adaptador que utilizamos
        recyclerView.setAdapter(elementAdapter);
    }

    public static List<RecyclerTest> getArrayListTestRespuestas() {
        return arrayListTestRespuestas;
    }

    public static void setArrayListTestRespuestas(List<RecyclerTest> listTestRespuestas, int position) {
        if (listTestRespuestas.get(position).getRespuesta() != null) {
            RecyclerTest elElemento = new RecyclerTest(listTestRespuestas.get(position).getId(), listTestRespuestas.get(position).getPregunta(),
                    listTestRespuestas.get(position).getSolucion(), listTestRespuestas.get(position).getExplicacion(),
                    listTestRespuestas.get(position).getImagen(), listTestRespuestas.get(position).getRespuesta(), listTestRespuestas.get(position).getOpcA(),
                    listTestRespuestas.get(position).getOpcB(), listTestRespuestas.get(position).getOpcC(), listTestRespuestas.get(position).isEsCorrecta());
            arrayListTestRespuestas.set(position, elElemento);
        } else {
            System.out.println("NO SE HA GUARDADO NINGUN ELEMENTO EN EL ARRAY");
        }
    }
}