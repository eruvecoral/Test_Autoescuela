package com.example.iscar.Activityes;

import static com.android.volley.Request.Method.POST;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iscar.Activityes.Cuestionarios.Activity_Test;
import com.example.iscar.Padres.TablaResultados;
import com.example.iscar.Padres.Usuario;
import com.example.iscar.R;

import java.util.HashMap;
import java.util.Map;

public class Activity_Registro_Usuario extends Activity {


    private Button btn_crea_usu;
    public EditText et_usuario, et_pass, et_nom, et_Apellidos;
    private RequestQueue queueInsertaDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        //Enlaces a los elementos visuales
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_nom = (EditText) findViewById(R.id.et_nom);
        et_Apellidos = (EditText) findViewById(R.id.et_Apellidos);

        btn_crea_usu = (Button) findViewById(R.id.btn_crea_usu);
        //metodo onClick

        btn_crea_usu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(et_usuario.getText().toString().isEmpty() || et_pass.getText().toString().isEmpty()|| et_nom.getText().toString().isEmpty() || et_Apellidos.getText().toString().isEmpty()) {
                    Toast.makeText(Activity_Registro_Usuario.this, "Has de cumplimentar todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setUsuario(et_usuario.getText().toString());
                    nuevoUsuario.setPassword(et_pass.getText().toString());
                    nuevoUsuario.setNombre(et_nom.getText().toString());
                    nuevoUsuario.setApellidos(et_Apellidos.getText().toString());
                    creaUsuario("http://192.168.56.1/autoescuela_rabel/inserta_usuario.php", nuevoUsuario);
                }
            }
        });

    }


    public void creaUsuario(String responseBody, Usuario usu) {
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.contains("1")) {
                    Toast.makeText(Activity_Registro_Usuario.this, "Usuario creado correctamente", Toast.LENGTH_LONG).show();
                    creaIntent(usu);
                }else{
                    Toast.makeText(Activity_Registro_Usuario.this, "Algun error al insertar el usuario", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Registro_Usuario.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Registro_Usuario.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", usu.getUsuario());
                params.put("password", usu.getPassword());
                params.put("nombre", usu.getNombre());
                params.put("apellidos", usu.getApellidos());
                return params;
            }
        };
        //Intancia de la conexion con los datos
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void creaIntent(Usuario usu) {
        Intent intent= new Intent(getApplicationContext(),Activity_temario_Test.class);
        intent.putExtra("usuario", usu.getUsuario());
        startActivity(intent);
    }

}
