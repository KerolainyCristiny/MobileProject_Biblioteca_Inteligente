package com.example.biblioteca_inteligente_mobile;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados); // Crie esse layout

        ArrayList<Livro> livros = (ArrayList<Livro>) getIntent().getSerializableExtra("livros");

        // Exiba os resultados (assumindo que você tem TextViews para título, autor e resumo)
        if (livros != null && !livros.isEmpty()) {
            TextView tituloTextView = findViewById(R.id.titulo_text_view);
            TextView autorTextView = findViewById(R.id.autor_text_view);
            TextView resumoTextView = findViewById(R.id.resumo_text_view);

            // Exiba o primeiro livro como exemplo
            Livro livro = livros.get(0);
            tituloTextView.setText(livro.getTitulo());
            autorTextView.setText(livro.getAutor());
            resumoTextView.setText(livro.getResumo());
        }
    }
}