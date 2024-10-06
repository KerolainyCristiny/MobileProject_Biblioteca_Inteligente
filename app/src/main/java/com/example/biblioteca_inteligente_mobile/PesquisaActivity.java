package com.example.biblioteca_inteligente_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PesquisaActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText campoEditText;
    private EditText valorEditText;
//    private TextView tituloTextView;
//    private TextView autorTextView;
//    private TextView resumoTextView;
    private RecyclerView recyclerView;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

//        recebendo os dados do input layout
        campoEditText = findViewById(R.id.pesquisa_edit_text);
        valorEditText = findViewById(R.id.pesquisa_edit_text);

        Button pesquisarButton = findViewById(R.id.pesquisar_button);
        pesquisarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarLivros();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    private void pesquisarLivros() {
        String valor = valorEditText.getText().toString();

        buscarLivros(valor);
    }

    @SuppressLint("Range")
    private void buscarLivros(String valor) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] columns = {"titulo", "autor", "resumo"};
        String selection = "titulo LIKE ?";
        String[] selectionArgs = {"%" + valor + "%"};

        Cursor cursor = db.query(
                "livro",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                "_id ASC"
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Process the cursor data
        } else {
            cursor.close();
            db.close();
            exibirResultados(null);
            return;
        }


        ArrayList<Livro> livros = new ArrayList<>();
        do {
            livros.add(new Livro(
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getString(cursor.getColumnIndex("resumo"))
            ));
        } while (cursor.moveToNext());


        cursor.close();
        db.close();

        exibirResultados(livros);
        // Inicie ResultadoActivity passando os livros
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("livros", livros); // Certifique-se de que Livro implementa Serializable
        startActivity(intent);
    }

    private void exibirResultados(ArrayList<Livro> livros) {
        livroAdapter = new LivroAdapter(livros);
        recyclerView.setAdapter(livroAdapter);
    }

//    private void exibirResultados(ArrayList<Livro> livros) {
//        if (livros.isEmpty()) {
//            tituloTextView.setText("");
//            autorTextView.setText("");
//            resumoTextView.setText("Nenhum livro enco ntrado.");
//            return;
//        }
//
//        Livro primeiroLivro = livros.get(0);
//        tituloTextView.setText(primeiroLivro.getTitulo());
//        autorTextView.setText(primeiroLivro.getAutor());
//        resumoTextView.setText(primeiroLivro.getResumo());
//    }
}
