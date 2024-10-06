package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisaActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
//    uso do objeto database helper para acessar o banco

//    construtor que inicia o databasehelper com o contexto fornecido
    public PesquisaActivity(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

//    metodo de buscar livros
    public void buscarLivros(String campo, String valor) {
    SQLiteDatabase db = databaseHelper.getReadableDatabase();
//    declaração de campos que vão ser consultados
    String[] columns = {"id", "titulo", "autor", "resumo", "quantidade_total", "quantidade_reserva"};
    String selection = campo + " LIKE ?";
    String[] selectionArgs = { "%" + valor + "%" };

//    usando o obejto "db" para executar a consulta
    Cursor cursor = db.query(
            "livro",
            columns,
            selection,
            selectionArgs,
            null,
            null,
            "_id ASC"
    );

//    saida dos dados
    StringBuilder resultado = new StringBuilder();
    while (cursor.moveToNext()) {
//        int id = cursor.getInt(0); nao precisa pegar o id da table para exibir
        String titulo = cursor.getString(1);
        String autor = cursor.getString(2);
        String resumo = cursor.getString(3);
        int quantidadeTotal = cursor.getInt(4);
        int quantidadeReservada = cursor.getInt(5);

//        resultado.append("ID: ").append(id).append("\n");
        resultado.append("Título: ").append(titulo).append("\n");
        resultado.append("Autor: ").append(autor).append("\n");
        resultado.append("Resumo: ").append(resumo).append("\n");
        resultado.append("Quantidade Total: ").append(quantidadeTotal).append("\n");
        resultado.append("Quantidade Reservada: ").append(quantidadeReservada).append("\n\n");
    }

    cursor.close();
    db.close();

    Log.d("ResultadoPesquisa", resultado.toString());

    // Aqui você pode chamar uma função em Resultados para exibir os resultados
    // Por exemplo: Resultados.exibirResultados(resultado.toString());
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}