package com.example.biblioteca_inteligente_mobile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList book_id, book_titulo, book_autor;

    //construtor
    public CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_titulo, ArrayList book_autor){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_titulo = book_titulo;
        this.book_autor = book_autor;
    }



    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_titulo_txt.setText(String.valueOf(book_titulo.get(position)));
        holder.book_autor_txt.setText(String.valueOf(book_autor.get(position)));
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_titulo_txt, book_autor_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_titulo_txt = itemView.findViewById(R.id.book_titulo_txt);
            book_autor_txt = itemView.findViewById(R.id.book_autor_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
