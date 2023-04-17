package com.example.iscar.Activityes.Graficos;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iscar.R;
import com.example.iscar.Padres.TablaResultados;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_GridLayout_graficos_temas extends AppCompatActivity {

    private List<String> elementos;
    private Bundle datos;
    private TextView miUsuario, miSeccion, miTema;
    private String stringTablaRespuestas;
    private List<TablaResultados> losResultados;
    private String laSeccion,elUsuario,elTema;
    public ArrayList<Float> n_aciertos;
    public ArrayList<Double> porcentajes;
    public List<String> leyendas;
    public int maxValue;
    public PieChart pieChart;
    public BarChart barChar;

    public int[] colors = new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA,
            Color.rgb(255, 156, 5), Color.rgb(167, 255, 5), Color.rgb(255, 5, 108),
            Color.rgb(195, 155, 211), Color.rgb(135, 54, 0), Color.rgb(174, 214, 241),
            Color.rgb(22, 160, 133), Color.rgb(249, 231, 159)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_graficos_temas);


        //Aqui recogemos el usuario logeado
        datos = getIntent().getExtras();
        elUsuario = datos.getString("usuario");
        laSeccion = datos.getString("Sección");
        elTema = datos.getString("Tema");
        miUsuario = (TextView) findViewById(R.id.tVUser);
        miSeccion = (TextView) findViewById(R.id.tVSeccionEditSeciones);
        miTema = (TextView) findViewById(R.id.tVTemaEditTemas);
        miUsuario.setText(elUsuario);
        String countSeccion = String.valueOf(laSeccion.charAt(laSeccion.length() - 2));
        miSeccion.setText(countSeccion);
        String countTema = String.valueOf(elTema.charAt(elTema.length() - 1));
        stringTablaRespuestas = "tabla_puntuacion_usuario_" + elUsuario;
        miTema.setText(countTema);
        losResultados= new ArrayList<TablaResultados>();
        pieChart = (PieChart) findViewById(R.id.pieChart);
        barChar = (BarChart) findViewById(R.id.barChar);
        obtenerPuntuacionPorSeccion("http://192.168.56.1/autoescuela_rabel/obtenerPuntuacionPorTema.php", stringTablaRespuestas, countSeccion,countTema);

    }

    public void obtenerPuntuacionPorSeccion(String responseBody, String stringTabla, String seccion, String tema) {

        List<TablaResultados> resultados = new ArrayList<TablaResultados>();
        losResultados = new ArrayList<TablaResultados>();
        StringRequest stringRequest = new StringRequest(POST, responseBody, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("puntuaciones");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject data = result.getJSONObject(i);

                        TablaResultados tablaResultados = new TablaResultados();
                        tablaResultados.setIdCuestionario(data.optInt("idCuestionario"));
                        tablaResultados.setUsuarioCuestion(data.optString("usuarioCuestion"));
                        tablaResultados.setSeccion(data.optString("seccion"));
                        tablaResultados.setTema(data.optString("tema"));
                        tablaResultados.setN_correctas(data.optInt("n_correctas"));
                        tablaResultados.setPorcentaje_Correctas(data.optDouble("porcentaje_correctas"));
                        losResultados.add(tablaResultados);

                    }

                    creaArraysParaCharts();
                    createBarCharts();
//                    inflateViewRecycler();
//                    createIntent();

                } catch (JSONException e) {
                    Toast.makeText(Activity_GridLayout_graficos_temas.this, e.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Activity_GridLayout_graficos_temas.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_GridLayout_graficos_temas.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_GridLayout_graficos_temas.this, "Error response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tabla", stringTabla);
                params.put("seccion", seccion);
                params.put("tema", tema);
                return params;
            }
        };
        //Intancia de la conexion con los datos
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void creaArraysParaCharts() {
        n_aciertos = new ArrayList<Float>();
        for (TablaResultados miNum : losResultados) {
            n_aciertos.add((float) miNum.getN_correctas());
        }

        porcentajes = new ArrayList<Double>();
        for (TablaResultados miNum : losResultados) {
            porcentajes.add(miNum.getPorcentaje_Correctas());
        }
        leyendas = new ArrayList<String>();
        for (TablaResultados miNum : losResultados) {
            leyendas.add("Int.: " + miNum.getIdCuestionario());
        }

        maxValue = 0;
        for (TablaResultados miNum : losResultados) {
            if (this.maxValue < miNum.getN_correctas()) {
                this.maxValue = miNum.getN_correctas();
            }
        }
    }

    private void createIntent() {
        Intent intent = new Intent(getApplicationContext(), Activity_Temas_Graficos.class);
        intent.putExtra("Sección", laSeccion);
        intent.putExtra("usuario", elUsuario);
        startActivity(intent);
    }

    private Chart getSameChart(Chart chart, String descripcion, int textColor, int backgrounColor, int animateY) {
        chart.getDescription().setText(descripcion);
        chart.getDescription().setTextSize(10);
        chart.setBackgroundColor(backgrounColor);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setDrawInside(false);
        legend.setMaxSizePercent(0.4f);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        ArrayList<LegendEntry> entries = new ArrayList<LegendEntry>();
        for (int i = 0; i < leyendas.size(); i++) {
            LegendEntry myEntry = new LegendEntry();
            myEntry.formColor = colors[i];
            myEntry.label = leyendas.get(i);
            entries.add(myEntry);
        }

        legend.setCustom(entries);

    }

    private ArrayList<BarEntry> getBarEntries() {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        int position = 0;
        for (int i = 0; i < n_aciertos.size(); i++) {
            entries.add(new BarEntry(i, n_aciertos.get(i)));
        }
        return entries;
    }

    private ArrayList<PieEntry> getPieEntries() {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i < porcentajes.size(); i++) {
            float porcentaje = Float.parseFloat(String.valueOf(porcentajes.get(i)));
            entries.add(new PieEntry(porcentaje));
        }
        return entries;
    }

    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(leyendas));
    }

    private void axisLeft(YAxis axis) {
        axis.setSpaceTop(30*losResultados.size());
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) {
        axis.setEnabled(false);
    }

    public void createBarCharts() {
        barChar = (BarChart) getSameChart(barChar, "Aciertos", Color.BLACK, Color.LTGRAY, 3000);
        barChar.setDrawGridBackground(true);
        barChar.setDrawBarShadow(false);

        barChar.setData(getBarData());
        barChar.invalidate();

        axisX(barChar.getXAxis());
        axisLeft(barChar.getAxisLeft());
        axisRight(barChar.getAxisRight());


        pieChart = (PieChart) getSameChart(pieChart, "Porcentaje", Color.BLACK, Color.LTGRAY, 3000);
        pieChart.setTransparentCircleRadius(5);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(getPieData());
        pieChart.invalidate();

    }

    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData() {
        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(), ""));

        barDataSet.setBarShadowColor(Color.DKGRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData() {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(), ""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}