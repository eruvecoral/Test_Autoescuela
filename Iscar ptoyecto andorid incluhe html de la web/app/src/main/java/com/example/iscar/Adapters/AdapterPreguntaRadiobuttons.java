package com.example.iscar.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.Activityes.Cuestionarios.Activity_Test;
import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTest;

import java.util.List;

public class AdapterPreguntaRadiobuttons extends RecyclerView.Adapter<AdapterPreguntaRadiobuttons.ViewHolder> {

    private Context context;
    public static List<RecyclerTest> arrayListTest;
    public RecyclerTest item;
    public static Activity_Test elementActivityTest;

    public AdapterPreguntaRadiobuttons() {

    }

    public AdapterPreguntaRadiobuttons(Activity_Test context, List<RecyclerTest> arrayListTest) {
        this.context = context;
        this.arrayListTest = arrayListTest;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.elemento_test, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public static List<RecyclerTest> returnList() {
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
        holder.TVPregunta.setText(arrayListTest.get(item.getId() - 1).getPregunta());
        holder.radioButtonOpcionA.setText(arrayListTest.get(item.getId() - 1).getOpcA().getName());
        holder.radioButtonOpcionB.setText(arrayListTest.get(item.getId() - 1).getOpcB().getName());
        holder.radioButtonOpcionC.setText(arrayListTest.get(item.getId() - 1).getOpcC().getName());
        holder.radioButtonOpcionB.setChecked(arrayListTest.get(item.getId() - 1).getOpcB().isSelected());
        holder.radioButtonOpcionC.setChecked(arrayListTest.get(item.getId() - 1).getOpcC().isSelected());
        holder.radioButtonOpcionA.setChecked(arrayListTest.get(item.getId() - 1).getOpcA().isSelected());
    }

    public void openActivity() {
        // ... agrega aqui el codigo para abrir la activity
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getWidth(), v.getHeight());
        v.draw(c);
        return b;
    }
    @Override
    public int getItemCount() {
        return arrayListTest.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout rowItem;
        private ImageView image;
        private TextView TVPregunta;
        private RadioButton radioButtonOpcionA;
        private RadioButton radioButtonOpcionB;
        private RadioButton radioButtonOpcionC;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            this.rowItem = itemView.findViewById(R.id.rowitem);
            this.image = itemView.findViewById(R.id.imagen);
            this.TVPregunta = itemView.findViewById(R.id.pregunta);
            this.radioButtonOpcionA = itemView.findViewById(R.id.opcionA);
            this.radioButtonOpcionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = item.getId() - 1;
                    handleRadiobuttonChecks(posicion, "A");
                    radioButtonOpcionA.setChecked(arrayListTest.get(item.getId() - 1).getOpcA().isSelected());
                    radioButtonOpcionB.setChecked(arrayListTest.get(item.getId() - 1).getOpcB().isSelected());
                    radioButtonOpcionC.setChecked(arrayListTest.get(item.getId() - 1).getOpcC().isSelected());
                    notifyDataSetChanged();
                }
            });
            this.radioButtonOpcionB = itemView.findViewById(R.id.opcionB);
            this.radioButtonOpcionB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = item.getId() - 1;
                    handleRadiobuttonChecks(posicion, "B");
                    radioButtonOpcionA.setChecked(arrayListTest.get(item.getId() - 1).getOpcA().isSelected());
                    radioButtonOpcionB.setChecked(arrayListTest.get(item.getId() - 1).getOpcB().isSelected());
                    radioButtonOpcionC.setChecked(arrayListTest.get(item.getId() - 1).getOpcC().isSelected());
                    notifyDataSetChanged();
                }
            });
            this.radioButtonOpcionC = itemView.findViewById(R.id.opcionC);
            this.radioButtonOpcionC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = item.getId() - 1;
                    handleRadiobuttonChecks(posicion, "C");
                    radioButtonOpcionA.setChecked(arrayListTest.get(item.getId() - 1).getOpcA().isSelected());
                    radioButtonOpcionB.setChecked(arrayListTest.get(item.getId() - 1).getOpcB().isSelected());
                    radioButtonOpcionC.setChecked(arrayListTest.get(item.getId() - 1).getOpcC().isSelected());
                    notifyDataSetChanged();
                }
            });
        }
    }


    private void handleRadiobuttonChecks(int position, String radioID) {

        if (radioID == "A") {
            arrayListTest.get(position).getOpcA().setSelected(true);
            arrayListTest.get(position).getOpcB().setSelected(false);
            arrayListTest.get(position).getOpcC().setSelected(false);
            arrayListTest.get(position).setRespuesta("A");
            arrayListTest.get(position).setEsCorrecta(arrayListTest.get(position).getRespuesta(), arrayListTest.get(position).getSolucion());
        } else if (radioID == "B") {
            arrayListTest.get(position).getOpcA().setSelected(false);
            arrayListTest.get(position).getOpcB().setSelected(true);
            arrayListTest.get(position).getOpcC().setSelected(false);
            arrayListTest.get(position).setRespuesta("B");
            arrayListTest.get(position).setEsCorrecta(arrayListTest.get(position).getRespuesta(), arrayListTest.get(position).getSolucion());
        } else if (radioID == "C") {
            arrayListTest.get(position).getOpcA().setSelected(false);
            arrayListTest.get(position).getOpcB().setSelected(false);
            arrayListTest.get(position).getOpcC().setSelected(true);
            arrayListTest.get(position).setRespuesta("C");
            arrayListTest.get(position).setEsCorrecta(arrayListTest.get(position).getRespuesta(), arrayListTest.get(position).getSolucion());
        }
        notifyDataSetChanged();

        elementActivityTest.setArrayListTestRespuestas(arrayListTest, position);
    }

    public static List<RecyclerTest> getArrayListTest() {
        return arrayListTest;
    }

    public void setArrayListTest(List<RecyclerTest> arrayListTest) {
        this.arrayListTest = arrayListTest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
