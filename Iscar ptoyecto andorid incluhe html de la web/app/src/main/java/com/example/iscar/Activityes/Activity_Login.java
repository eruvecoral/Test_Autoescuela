package com.example.iscar.Activityes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iscar.R;

import java.util.HashMap;
import java.util.Map;

public class Activity_Login extends AppCompatActivity {

    private Button btn_validar,btn_crea_usu;
    public EditText et_nom, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Enlaces a los elementos visuales
        et_nom = (EditText) findViewById(R.id.et_usuario);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_validar = (Button) findViewById(R.id.btn_estadisticas);
        btn_crea_usu = (Button) findViewById(R.id.btn_crea_usu);
        //metodo onClick

        btn_validar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Aqui hay que cambiar mi IP por la tuya
                String usu = et_nom.getText().toString();
                validarUsuario("http://192.168.56.1/autoescuela_rabel/validar_usuario.php", usu);
            }
        });
        btn_crea_usu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Activity_Registro_Usuario.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Funcion para validar usuario-password TODO
     * y llamar a activity test
     * @param
     */

    private void validarUsuario(String URL, String usu){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            //Que tiene que hacer cuando nos loguemos comprobando si el response (lo que le pasamos esta vacio)
            public void onResponse(String response) {
                if (response.equals("1")){
                    Intent intent= new Intent(getApplicationContext(),Activity_temario_Test.class);
                    intent.putExtra("usuario",usu);
                    startActivity(intent);
                }else{
                    Toast.makeText(Activity_Login.this, "Usuario o contraseña erroneos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener(){
            //En caso de que ocurra un error
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Activity_Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Login.this, "Error de conexion", Toast.LENGTH_SHORT).show();

            }
        }){
            //Elemento en el que guardamos los datos introducidos por el usuario
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",et_nom.getText().toString());
                parametros.put("password",et_pass.getText().toString());
                return parametros;
            }
        };
        //Intancia de la conexion con los datos
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void verTemario_Test(View v)
    {
        Intent intent= new Intent(getApplicationContext(),Activity_temario_Test.class);
        intent.putExtra("usuario",et_nom.getText().toString());
        startActivity(intent);

    }
}