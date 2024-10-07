package com.example.biblioteca_inteligente_mobile;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PesquisaActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText campoEditText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar o layout de pesquisa
        View pesquisaLayout = findViewById(R.id.layout_pesquisa);
        campoEditText = pesquisaLayout.findViewById(R.id.pesquisa_edit_text);

        // Inicializar o RecyclerView
        View resultadosLayout = findViewById(R.id.layout_resultados);
        recyclerView = resultadosLayout.findViewById(R.id.recycler_view_resultado);

        // Configurar o RecyclerView
        livroAdapter = new LivroAdapter(this);
        recyclerView.setAdapter(livroAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar o botão de pesquisa
        Button pesquisarButton = pesquisaLayout.findViewById(R.id.pesquisar_button);
        pesquisarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarLivros();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    private void pesquisarLivros() {
        String valor = campoEditText.getText().toString();
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

        ArrayList<Livro> livros = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                livros.add(new Livro(
                        cursor.getString(cursor.getColumnIndex("titulo")),
                        cursor.getString(cursor.getColumnIndex("autor")),
                        cursor.getString(cursor.getColumnIndex("resumo"))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Inicie ResultadoActivity passando os livros
        Intent intent = new Intent(PesquisaActivity.this, ResultadoActivity.class);
        intent.putExtra("livros", livros); // Certifique-se de que Livro implementa Serializable
        startActivity(intent);
    }
}
