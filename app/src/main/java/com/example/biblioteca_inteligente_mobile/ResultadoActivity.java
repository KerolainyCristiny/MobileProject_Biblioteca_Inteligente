package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityResultadoBinding;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    ActivityResultadoBinding binding;
    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<String> titulo, autor, resumo;
    CustomAdapterResultado customAdapterResultado;

    private String pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Menu bottom
        binding.bottomNav.setSelectedItemId(R.id.pesquisamenu);
        binding.bottomNav.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.pesquisamenu) {
                Intent intent = new Intent(ResultadoActivity.this, PesquisaActivity.class);
                startActivity(intent);
                finish();

                return true;
            } else if (itemId == R.id.home) {
                Intent intent = new Intent(ResultadoActivity.this, HomeActivity.class);
                intent.putExtra("matricula", "00001242"); // Passando diretamente o valor da matrícula
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.livros){
                Intent intent = new Intent(ResultadoActivity.this, BottomNav.class);
                startActivity(intent);
                finish();
                return true;
            }

            return false;
        });



        pesquisa = getIntent().getStringExtra("pesquisa");

        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHelper(this);
        titulo = new ArrayList<>();
        autor = new ArrayList<>();
        resumo = new ArrayList<>();

        if (pesquisa != null && !pesquisa.isEmpty()) {
            resultadoPesquisa(pesquisa);
        } else {
            Toast.makeText(this, "Dados não encontrados", Toast.LENGTH_SHORT).show();
        }


        customAdapterResultado = new CustomAdapterResultado(ResultadoActivity.this, titulo, autor, resumo);
        recyclerView.setAdapter(customAdapterResultado);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    private void resultadoPesquisa(String pesquisa) {
        if (db.pesquisaLivro(pesquisa) != null) {
            Cursor cursor = db.pesquisaLivro(pesquisa);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    titulo.add(cursor.getString(0));
                    autor.add(cursor.getString(1));
                    resumo.add(cursor.getString(2));
                }
                cursor.close(); // Não esqueça de fechar o cursor após usar
            }
        } else {
            Toast.makeText(this, "Erro ao fazer pesquisa", Toast.LENGTH_SHORT).show();
        }
    }


}
