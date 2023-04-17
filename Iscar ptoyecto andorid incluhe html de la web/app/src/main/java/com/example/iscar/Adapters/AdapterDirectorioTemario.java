package com.example.iscar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscar.R;
import com.example.iscar.Padres.RecyclerTemario;

import java.util.List;

/**
 * Clase adaptador para relacionar el elemento Recycler (RecyclerDirectorio) con los datos.
 */
public class AdapterDirectorioTemario extends RecyclerView.Adapter<AdapterDirectorioTemario.ViewHolder> {

    //Lista donde guardamos todos los datos declarados
    private List<RecyclerTemario> listaElementos;
    //Marca el ctxt desde donde usamos este adapter
    private Context ctxt;
    //Inflater (infla) el layout
    private LayoutInflater inf;

    //Variable listener para detectar el click en el item
    final OnItemClickListener listener;



    public AdapterDirectorioTemario(List<RecyclerTemario> lista,
                                      Context ctxt, OnItemClickListener listener)
    {
        listaElementos = lista;
        this.inf = LayoutInflater.from(ctxt);
        this.ctxt = ctxt;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Damos la referencia de como se va a ver
        View v = inf.inflate(R.layout.elemento_tema,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listaElementos.get(position));

    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    /**
     * Metodo para volver a redefinir los elementos de la lista
     * @param elementosNuevos
     */
    public void setElementos(List<RecyclerTemario> elementosNuevos)
    {
        listaElementos = elementosNuevos;
    }

    //Interface que detecta el click
    public interface OnItemClickListener{
        //se pasa por parametro el item a clickar
        void onItemClick(RecyclerTemario carpeta);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView icono;
        TextView tema, estado;

        ViewHolder(View v)
        {
            super(v);
            icono = v.findViewById(R.id.icono);
            tema = v.findViewById(R.id.txt_tema);
        }

        /**
         * Metodo para decidir los cambios en los textView
         * @param item
         */
        void bindData(final RecyclerTemario item)
        {

            tema.setText(item.getTema());
            //Declaracion metodo listener al que se le pasa el item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
