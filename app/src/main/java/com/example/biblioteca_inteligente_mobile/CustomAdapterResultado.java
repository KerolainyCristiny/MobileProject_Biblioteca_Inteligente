package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterResultado extends RecyclerView.Adapter<CustomAdapterResultado.MyViewHolder> {

    private Context context;
    private ArrayList titulo, autor,resumo;

    public CustomAdapterResultado(Context context, ArrayList titulo, ArrayList autor, ArrayList resumo) {
        this.context = context;
        this.titulo = titulo;
        this.autor = autor;
        this.resumo = resumo;
    }

    @NonNull
    @Override
    public CustomAdapterResultado.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_resultado, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterResultado.MyViewHolder holder, int position) {

        holder.titulo.setText(String.valueOf(titulo.get(position)));
        holder.autor.setText(String.valueOf(autor.get(position)));
        holder.resumo.setText(String.valueOf(resumo.get(position)));
    }

    @Override
    public int getItemCount() {
        return titulo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, autor, resumo;
        LinearLayout mainLayoutResultado;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tituloRes);
            autor = itemView.findViewById(R.id.autorRes);
            resumo = itemView.findViewById(R.id.resumoRes);
            mainLayoutResultado = itemView.findViewById(R.id.mainLayoutResultado);
        }
    }
}
