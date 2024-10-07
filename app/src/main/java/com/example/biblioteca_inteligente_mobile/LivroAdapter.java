package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroViewHolder> {
    private final ArrayList<Livro> livros;
    private final Context context;

    // Construtor do adaptador
    public LivroAdapter(ArrayList<Livro> livros, Context context) {
        this.livros = livros;
        this.context = context;
    }


    @NonNull
    @Override
    public LivroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder holder, int position) {
        // Obtém o livro na posição atual
        Livro livro = livros.get(position);
        // Vincula os dados do livro à visualização
        holder.tituloTextView.setText(livro.getTitulo());
        holder.autorTextView.setText(livro.getAutor());
        holder.resumoTextView.setText(livro.getResumo());
    }

    @Override
    public int getItemCount() {
        // Retorna o tamanho da lista de livros
        return livros.size();
    }

    // ViewHolder para manter as referências das views
    public static class LivroViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView;
        public TextView autorTextView;
        public TextView resumoTextView;

        public LivroViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.titulo_text_view);
            autorTextView = itemView.findViewById(R.id.autor_text_view);
            resumoTextView = itemView.findViewById(R.id.resumo_text_view);
        }
    }
}
