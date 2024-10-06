package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<String> book_titulo,book_autor, data_devolucao;
    CustomAdapterHome customAdapterHome;

    private String matricula;
    // pegando o valor da matricula do login para passar pro bd


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Barra pesquisa
        binding.barraPesquisa.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Acess Pesquisa", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PesquisaActivity.class);
            startActivity(intent);
        });

        // Menu bottom
        binding.bottomNav.setSelectedItemId(R.id.home);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home || itemId == R.id.pesquisamenu) {
                Intent intent = new Intent(this, PesquisaActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.livros) {
                Intent intent = new Intent(this, BottomNav.class);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });

        // Emprestimo
        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHelper(this);
        book_titulo = new ArrayList<>();
        book_autor = new ArrayList<>();
        data_devolucao = new ArrayList<>();

        matricula = getIntent().getStringExtra("matricula");
        if (matricula != null && !matricula.isEmpty()) {
            dadosEmprestimo(matricula);
        } else {
            Toast.makeText(this, "Dados não encontrados", Toast.LENGTH_SHORT).show();
        }

        customAdapterHome = new CustomAdapterHome(this, book_titulo, book_autor, data_devolucao);
        recyclerView.setAdapter(customAdapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void dadosEmprestimo(String matricula) {
        if (db.readEmprestimo(matricula) != null) {
            Cursor cursor = db.readEmprestimo(matricula);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    book_titulo.add(cursor.getString(0));
                    book_autor.add(cursor.getString(1));
                    data_devolucao.add(cursor.getString(2));
                }
                cursor.close(); // Não esqueça de fechar o cursor após usar
            }
        } else {
            Toast.makeText(this, "Erro ao ler os emprestimos", Toast.LENGTH_SHORT).show();
        }
    }
}