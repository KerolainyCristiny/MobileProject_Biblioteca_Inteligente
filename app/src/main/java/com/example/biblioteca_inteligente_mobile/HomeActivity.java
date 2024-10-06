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

        // barra pesquisa
        binding.barraPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Toast.makeText(HomeActivity.this, "Acess Pesquisa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PesquisaActivity.class);
                    startActivity(intent);


            }
        });
        // menu bottom
        binding.bottomNav.setSelectedItemId(R.id.home);
        binding.bottomNav.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                // Lógico para o item home
                return true;
            } else if (itemId == R.id.pesquisamenu) {
                startActivity(new Intent(getApplicationContext(), PesquisaActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.livros){
                startActivity(new Intent(getApplicationContext(), BottomNav.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });//menu fim

        //emprestimo
        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHelper(this);
        book_titulo = new ArrayList<>();
        book_autor = new ArrayList<>();
        data_devolucao = new ArrayList<>();


//        dadosEmprestimo();
//
//        customAdapterHome = new CustomAdapterHome (HomeActivity.this, book_titulo, book_autor, data_devolucao);
//        recyclerView.setAdapter(customAdapterHome);
//        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));


    }// metodo

    public void dadosEmprestimo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matricula = extras.getString("matricula");

            if (matricula != null && !matricula.isEmpty()) {
                try (Cursor cursor = db.readEmprestimo(matricula)) {
                    if(cursor.getCount() == 0){
                        Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                    }else{
                        while (cursor.moveToNext()){
                            book_titulo.add(cursor.getString(0));
                            book_autor.add(cursor.getString(1));
                            data_devolucao.add(cursor.getString(2));
                        }
                    }
                }
            }
        }


    }

}//class