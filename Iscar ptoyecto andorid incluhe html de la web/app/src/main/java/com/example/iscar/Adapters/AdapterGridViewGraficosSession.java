package com.example.iscar.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.iscar.Activityes.Graficos.Activity_GridLayout_graficos_secciones;
import com.example.iscar.R;
import com.example.iscar.Padres.TablaResultados;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
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

import java.util.ArrayList;
import java.util.List;

public class AdapterGridViewGraficosSession extends BaseAdapter {

    Context context;
    public static List<TablaResultados> arrayListTest;
    public TablaResultados item;

    public List<Float> n_aciertos;
    public ArrayList<Double> porcentajes;
    public List<String> leyendas;
    public int maxValue;

    public PieChart pieChart;
    public BarChart barChar;
    public LineChart lineChart;

    public int[]colors= new int[]{Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW,Color.MAGENTA,
            Color.rgb(255,156,5),Color.rgb(167,255,5),Color.rgb(255,5,108),
            Color.rgb(195,155,211),Color.rgb(135,54,0),Color.rgb(174,214,241),
            Color.rgb(22,160,133),Color.rgb(249,231,159)};

    public AdapterGridViewGraficosSession(Activity_GridLayout_graficos_secciones context, List<TablaResultados> arrayListTest){
        this.context = context;
        this.arrayListTest = arrayListTest;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return arrayListTest.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(String.valueOf(LayoutInflater.from(context).inflate(R.layout.elemento_secciones_grafico,null,false)));
            convertView = layoutInflater.inflate(R.layout.elemento_secciones_grafico,null);
        }
        n_aciertos = new ArrayList<Float>();
        for (TablaResultados miNum: arrayListTest) {
            n_aciertos.add((float) miNum.getN_correctas());
        }

        porcentajes  = new ArrayList<Double>();
        for (TablaResultados miNum: arrayListTest) {
            porcentajes.add(miNum.getPorcentaje_Correctas());
        }
        leyendas = new ArrayList<String>();
        for (TablaResultados miNum: arrayListTest) {
            if(!(leyendas.contains(miNum.getSeccion()))) {
                leyendas.add(miNum.getSeccion());
            }
        }
        maxValue = 0;
        for (TablaResultados miNum: arrayListTest) {
            if(maxValue<miNum.getN_correctas()) {
                maxValue = miNum.getN_correctas() ;
            }
        }
//            this.rowItem = itemView.findViewById(R.id.rowitem);
        pieChart = (PieChart) convertView.findViewById(R.id.pieChart);
        barChar = (BarChart) convertView.findViewById(R.id.barChar);
//        lineChart = (LineChart) convertView.findViewById(R.id.lineChart);

        createCharts();
        return convertView;
    }

    private Chart getSameChart(Chart chart, String descripcion, int textColor, int backgrounColor, int animateY) {
        chart.getDescription().setText(descripcion);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(backgrounColor);
        chart.animateY(animateY);
        legend(chart,leyendas);
        return chart;
    }

    private void legend(Chart chart, List<String> leyendas) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<LegendEntry>();
        List<String> leyendas1 = leyendas;
        for (int i = 0; i < leyendas.size(); i++) {
            LegendEntry myEntry = new LegendEntry();
            myEntry.formColor = colors[i];
            myEntry.label=leyendas.get(i);
            entries.add(myEntry);
        }

        legend.setCustom(entries);

    }

    private ArrayList<BarEntry> getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        int position = 0;
        for (int i = 0; i< n_aciertos.size(); i++){
            entries.add(new BarEntry(i, Float.parseFloat(n_aciertos.get(position).toString())));
        }
        return entries;
    }

    private ArrayList<PieEntry> getPieEntries(){

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i < porcentajes.size(); i++) {
            float porcentaje = Float.parseFloat(porcentajes.get(i).toString());
            entries.add(new PieEntry(porcentaje));
        }
        return entries;
    }

    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(leyendas));
    }

    private void axisLeft(YAxis axis){
        axis.setSpaceTop(maxValue + 5);
        axis.setAxisMinimum(0);
    }
    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    public void createCharts(){
        barChar=(BarChart) getSameChart(barChar, "Aciertos", Color.BLACK, Color.LTGRAY,3000);
        barChar.setDrawGridBackground(true);
        barChar.setDrawBarShadow(false);

        barChar.setData(getBarData());
        barChar.invalidate();

        axisX(barChar.getXAxis());
        axisLeft(barChar.getAxisLeft());
        axisRight(barChar.getAxisRight());

        pieChart = (PieChart) getSameChart(pieChart,"Porcentaje", Color.BLACK,Color.LTGRAY,3000);
        pieChart.setTransparentCircleRadius(5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setData(getPieData());
        pieChart.invalidate();
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.DKGRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){

        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }

    public void setArrayListTest(List<TablaResultados> arrayListTest) {
        this.arrayListTest = arrayListTest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
