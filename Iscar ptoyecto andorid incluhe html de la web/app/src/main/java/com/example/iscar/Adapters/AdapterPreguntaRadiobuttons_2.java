package com.example.iscar.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTest;

import java.util.List;

public class AdapterPreguntaRadiobuttons_2 extends RecyclerView.Adapter<AdapterPreguntaRadiobuttons_2.ViewHolder> {

    private Context context;
    public static List<RecyclerTest> arrayListTest;
    public RecyclerTest item;

    public AdapterPreguntaRadiobuttons_2(Context context, List<RecyclerTest> arrayListTest) {
        this.context = context;
        this.arrayListTest = arrayListTest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static List<RecyclerTest> getArrayListTest() {
        return arrayListTest;
    }

    public static void setArrayListTest(List<RecyclerTest> arrayListTest) {
        AdapterPreguntaRadiobuttons_2.arrayListTest = arrayListTest;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.elemento_tst_resultados, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    public static List<RecyclerTest> returnList(){
        return arrayListTest;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        item = arrayListTest.get(position);

        String base64String = arrayListTest.get(position).getImagen();
        String base64Image = base64String.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.image.setImageBitmap(decodedByte);
        holder.TVPregunta.setText(item.getPregunta());
        holder.radioButtonOpcionA.setText(item.getOpcA().getName());
        holder.radioButtonOpcionA.setChecked(item.getOpcA().isSelected());
        holder.radioButtonOpcionA.setEnabled(false);
        holder.radioButtonOpcionB.setText(item.getOpcB().getName());
        holder.radioButtonOpcionB.setEnabled(false);
        holder.radioButtonOpcionB.setChecked(item.getOpcB().isSelected());
        holder.radioButtonOpcionC.setText(item.getOpcC().getName());
        holder.radioButtonOpcionC.setEnabled(false);
        holder.radioButtonOpcionC.setChecked(item.getOpcC().isSelected());
        holder.TVrespuesta.setText(item.getSolucion());
        holder.TVexplicacion.setText(item.getExplicacion());

        switch (item.getRespuesta()) {
            case "A":
                if(arrayListTest.get(item.getId() - 1).isEsCorrecta()) {
                    holder.radioButtonOpcionA.setBackgroundColor(Color.GREEN);
                }else{
                    holder.radioButtonOpcionA.setBackgroundColor(Color.RED);
                }
                break;
            case "B":
                if(arrayListTest.get(item.getId() - 1).isEsCorrecta()) {
                    holder.radioButtonOpcionB.setBackgroundColor(Color.GREEN);
                }else{
                    holder.radioButtonOpcionB.setBackgroundColor(Color.RED);
                }
                break;
            case "C":
                if(arrayListTest.get(item.getId() - 1).isEsCorrecta()) {
                    holder.radioButtonOpcionC.setBackgroundColor(Color.GREEN);
                }else{
                    holder.radioButtonOpcionC.setBackgroundColor(Color.RED);
                }
                break;
        }
        boolean acertado = item.isEsCorrecta();
        if(acertado==false){
            switch (item.getSolucion()) {
                case "A":
                        holder.radioButtonOpcionA.setBackgroundColor(Color.GREEN);

                    break;
                case "B":
                        holder.radioButtonOpcionB.setBackgroundColor(Color.GREEN);
                    break;
                case "C":
                        holder.radioButtonOpcionC.setBackgroundColor(Color.GREEN);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return arrayListTest.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rowItem;
        private ImageView image;
        private TextView TVPregunta;
        private RadioGroup radioGroup;
        private RadioButton radioButtonOpcionA;
        private RadioButton radioButtonOpcionB;
        private RadioButton radioButtonOpcionC;
        private TextView TVrespuesta;
        private TextView TVexplicacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            int posicion = item.getId() - 1;
            this.rowItem = itemView.findViewById(R.id.rowitem);
            this.image = itemView.findViewById(R.id.imagen);
            this.TVPregunta = itemView.findViewById(R.id.pregunta);
            this.radioGroup = itemView.findViewById(R.id.radioGroup);
            this.radioButtonOpcionA = itemView.findViewById(R.id.opcionA);
            this.radioButtonOpcionB = itemView.findViewById(R.id.opcionB);
            this.radioButtonOpcionC = itemView.findViewById(R.id.opcionC);
            this.TVrespuesta = itemView.findViewById(R.id.respuesta);
            this.TVexplicacion = itemView.findViewById(R.id.explicacion);
//            String laRespuesta = arrayListTest.get(posicion).getSolucion();
//            switch (laRespuesta) {
//                case "A":
//                    if(arrayListTest.get(posicion).isEsCorrecta()) {
//                        radioButtonOpcionA.setBackgroundColor(Color.GREEN);
//                    }else{
//                        radioButtonOpcionA.setBackgroundColor(Color.RED);
//                    }
//                    break;
//                case "B":
//                    if(arrayListTest.get(posicion).isEsCorrecta()) {
//                        radioButtonOpcionB.setBackgroundColor(Color.GREEN);
//                    }else{
//                        radioButtonOpcionB.setBackgroundColor(Color.RED);
//                    }
//                    break;
//                case "C":
//                    if(arrayListTest.get(posicion).isEsCorrecta()) {
//                        radioButtonOpcionC.setBackgroundColor(Color.GREEN);
//                    }else{
//                        radioButtonOpcionC.setBackgroundColor(Color.RED);
//                    }
//                    break;
//            }

        }
    }
}
