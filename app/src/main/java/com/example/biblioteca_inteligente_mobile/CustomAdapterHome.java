package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterHome extends RecyclerView.Adapter<CustomAdapterHome.MyViewHolder> {

   private Context context;
   private ArrayList book_titulo, book_autor,data_devolucao;

    public CustomAdapterHome(Context context, ArrayList book_titulo, ArrayList book_autor, ArrayList data_devolucao) {
        this.context = context;
        this.book_titulo = book_titulo;
        this.book_autor = book_autor;
        this.data_devolucao = data_devolucao;
    }

    @NonNull
    @Override
    public CustomAdapterHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHome.MyViewHolder holder, int position) {
        holder.titulo.setText(String.valueOf(book_titulo.get(position)));
        holder.autor.setText(String.valueOf(book_autor.get(position)));
        holder.dataDevolucao.setText(String.valueOf(data_devolucao.get(position)));
    }

    @Override
    public int getItemCount() {
        return book_titulo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, autor, dataDevolucao;
        LinearLayout mainLayoutHome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            autor = itemView.findViewById(R.id.autor);
            dataDevolucao = itemView.findViewById(R.id.dataDevolucao);
            mainLayoutHome = itemView.findViewById(R.id.mainLayoutHome);
        }
    }
}
