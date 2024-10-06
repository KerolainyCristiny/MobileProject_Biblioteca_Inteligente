package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityBottomNavBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomNav extends AppCompatActivity {

    ActivityBottomNavBinding binding;

    DatabaseHelper db;
    RecyclerView recyclerView;
    ArrayList<Integer> book_id;
    ArrayList<String> book_titulo, book_autor;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBottomNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);

        //menu bottom
        binding.bottomNav.setSelectedItemId(R.id.livros);
        binding.bottomNav.setOnItemSelectedListener(item -> {


        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(BottomNav.this, HomeActivity.class);
            intent.putExtra("matricula", "00001242"); // Passando diretamente o valor da matrícula
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.pesquisamenu) {
            Intent intent = new Intent(BottomNav.this, PesquisaActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.livros){
            ;
            return true;
        }

        return false;
        });

        // listagem
        db = new DatabaseHelper(this);
        book_id = new ArrayList<>();
        book_titulo = new ArrayList<>();
        book_autor = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(BottomNav.this,this, book_id, book_titulo, book_autor);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BottomNav.this));

    } // class

    public void storeDataInArrays(){
        try (Cursor cursor = db.readAllData()) {
            if(cursor.getCount() == 0){
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            }else{
                while (cursor.moveToNext()){
                    book_id.add(cursor.getInt(0));
                    book_titulo.add(cursor.getString(1));
                    book_autor.add(cursor.getString(2));
                }
            }
        }
    }//metodo

}