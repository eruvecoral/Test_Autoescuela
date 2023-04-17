package com.example.iscar.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * Clase adaptador para relacionar el elemento Recycler (RecyclerDirectorio) con los datos.
 */
public class AdapterRecyclerSeccionGrafico extends RecyclerView.Adapter<AdapterRecyclerSeccionGrafico.ViewHolder> {


    private Context context;
    public static List<TablaResultados> arrayListTest;
    public TablaResultados item;

    public ArrayList<Float> n_aciertos;
    public ArrayList<Double> porcentajes;
    public List<String> leyendas;
    public int maxValue;

    public PieChart pieChart;
    public BarChart barChar;
    public LineChart lineChart;

    public int[] colors = new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA,
            Color.rgb(255, 156, 5), Color.rgb(167, 255, 5), Color.rgb(255, 5, 108),
            Color.rgb(195, 155, 211), Color.rgb(135, 54, 0), Color.rgb(174, 214, 241),
            Color.rgb(22, 160, 133), Color.rgb(249, 231, 159)};


    public AdapterRecyclerSeccionGrafico() {

    }

    public AdapterRecyclerSeccionGrafico(Activity_GridLayout_graficos_secciones context, List<TablaResultados> arrayListTest) {
        this.context = context;
        this.arrayListTest = arrayListTest;
        this.n_aciertos = new ArrayList<Float>();

    }

    @NonNull
    @Override
    public AdapterRecyclerSeccionGrafico.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.elemento_secciones_grafico, parent, false);
        AdapterRecyclerSeccionGrafico.ViewHolder viewHolder = new AdapterRecyclerSeccionGrafico.ViewHolder(view);
        return viewHolder;
    }

    public static List<TablaResultados> returnList() {
        return arrayListTest;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerSeccionGrafico.ViewHolder holder, int position) {


        item = arrayListTest.get(position);

        holder.barChar = createBarCharts();
        holder.pieChart = createPieCharts();

//        String base64String = arrayListTest.get(position).getImagen();
//        String base64Image = base64String.split(",")[1];
//
//        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//        holder.image.setImageBitmap(decodedByte);
//        holder.TVPregunta.setText(arrayListTest.get(item.getId() - 1).getPregunta());
//        holder.radioButtonOpcionA.setText(arrayListTest.get(item.getId() - 1).getOpcA().getName());
//        holder.radioButtonOpcionB.setText(arrayListTest.get(item.getId() - 1).getOpcB().getName());
//        holder.radioButtonOpcionC.setText(arrayListTest.get(item.getId() - 1).getOpcC().getName());
//        holder.radioButtonOpcionB.setChecked(arrayListTest.get(item.getId() - 1).getOpcB().isSelected());
//        holder.radioButtonOpcionC.setChecked(arrayListTest.get(item.getId() - 1).getOpcC().isSelected());
//        holder.radioButtonOpcionA.setChecked(arrayListTest.get(item.getId() - 1).getOpcA().isSelected());
    }

    @Override
    public int getItemCount() {
        return arrayListTest.size();
    }

    public enum OnItemClickListener {}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout graficItem;
        private PieChart pieChart;
        private BarChart barChar;
        private LineChart lineChart;
        private Context context;
        private List<TablaResultados> arrayListTest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            this.rowItem = itemView.findViewById(R.id.rowitem);
            this.pieChart = (PieChart) itemView.findViewById(R.id.pieChart);
            this.barChar = (BarChart) itemView.findViewById(R.id.barChar);
//            this.lineChart = (LineChart) itemView.findViewById(R.id.lineChart);

        }
    }

    private Chart getSameChart(Chart chart, String descripcion, int textColor, int backgrounColor, int animateY) {
        chart.getDescription().setText(descripcion);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(backgrounColor);
        chart.animateY(animateY);
        legend(chart, leyendas);
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
            myEntry.label = leyendas.get(i);
            entries.add(myEntry);
        }

        legend.setCustom(entries);

    }

    private ArrayList<BarEntry> getBarEntries() {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        int position = 0;
        for (int i = 0; i < n_aciertos.size(); i++) {
            entries.add(new BarEntry(i, n_aciertos.get(position)));
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
        axis.setSpaceTop(maxValue + 5);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) {
        axis.setEnabled(false);
    }

    public BarChart createBarCharts() {
        BarChart barChartTemp = new BarChart(getContext());
        barChartTemp = (BarChart) getSameChart(barChartTemp, "Aciertos", Color.BLACK, Color.LTGRAY, 3000);
        barChartTemp.setDrawGridBackground(true);
        barChartTemp.setDrawBarShadow(false);

        barChartTemp.setData(getBarData());
        barChartTemp.invalidate();

        axisX(barChartTemp.getXAxis());
        axisLeft(barChartTemp.getAxisLeft());
        axisRight(barChartTemp.getAxisRight());

        return barChartTemp;
    }

    public PieChart createPieCharts() {
        PieChart pieChartTemp = new PieChart(getContext());
        pieChartTemp = (PieChart) getSameChart(pieChartTemp, "Porcentaje", Color.BLACK, Color.LTGRAY, 3000);
        pieChartTemp.setTransparentCircleRadius(5);
        pieChartTemp.setDrawHoleEnabled(true);
        pieChartTemp.setData(getPieData());
        pieChartTemp.invalidate();
        return pieChartTemp;
    }

    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
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


    public void setArrayListTest(List<TablaResultados> arrayListTest) {
        this.arrayListTest = arrayListTest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static List<TablaResultados> getArrayListTest() {
        return arrayListTest;
    }

    public TablaResultados getItem() {
        return item;
    }

    public void setItem(TablaResultados item) {
        this.item = item;
    }

    public ArrayList<Float> getN_aciertos() {
        return n_aciertos;
    }

    public void setN_aciertos(ArrayList<Float> n_aciertos) {
        this.n_aciertos = n_aciertos;
    }

    public ArrayList<Double> getPorcentajes() {
        return porcentajes;
    }

    public void setPorcentajes(ArrayList<Double> porcentajes) {
        this.porcentajes = porcentajes;
    }

    public List<String> getLeyendas() {
        return leyendas;
    }

    public void setLeyendas(List<String> leyendas) {
        this.leyendas = leyendas;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public BarChart getBarChar() {
        return barChar;
    }

    public void setBarChar(BarChart barChar) {
        this.barChar = barChar;
    }

    public LineChart getLineChart() {
        return lineChart;
    }

    public void setLineChart(LineChart lineChart) {
        this.lineChart = lineChart;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }
}
