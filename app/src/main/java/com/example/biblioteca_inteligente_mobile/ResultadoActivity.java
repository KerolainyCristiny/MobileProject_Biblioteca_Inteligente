package com.example.biblioteca_inteligente_mobile;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados); // Seu layout para resultados

        recyclerView = findViewById(R.id.recycler_view_resultado); // ID do seu RecyclerView

        // Recebe a lista de livros
        ArrayList<Livro> livros = (ArrayList<Livro>) getIntent().getSerializableExtra("livros");

        if (livros != null && !livros.isEmpty()) {
            livroAdapter = new LivroAdapter(livros);
            recyclerView.setAdapter(livroAdapter);
        } else {
            Toast.makeText(this, "Nenhum livro encontrado.", Toast.LENGTH_SHORT).show();
        }
    }
}