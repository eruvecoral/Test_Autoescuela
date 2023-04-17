package com.example.iscar.Activityes.Temario;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iscar.Padres.MyWebViewClient;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTemario;

import java.util.List;

public class ActivityTemarioWeb extends AppCompatActivity {


    private List<RecyclerTemario> listaElementos;
    private WebView webTemario;
    private Bundle datos;
    private TextView miUsuario,miSeccion, miTema;
    private String elUsuario;
    private String laSeccion;
    private String elTema;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temario_web);

        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        elTema = datos.getString("Tema");
        miUsuario = (TextView) findViewById(R.id.TVUsuarioWeb);
        miUsuario.setText(elUsuario);

        char countSeccion = laSeccion.charAt(laSeccion.length() - 2);
        char countTema = elTema.charAt(elTema.length() - 1);

        //enlazo el elemento webView
        webTemario = (WebView) findViewById(R.id.temarioWebView);
        url = "file:///android_asset/" + "Seccion_" + countSeccion + "/Tema_" + countTema + ".html";
        WebSettings settings = webTemario.getSettings();
        settings.setJavaScriptEnabled(true);

        webTemario.setWebViewClient(new MyWebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isNetworkUrl(url)) {
                    return false;
                }
                if (appInstalledOrNot(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    // do something if app is not installed
                }
                return true;
            }

        });

        webTemario.loadUrl(url);

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

}