package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

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

        binding.barraPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Toast.makeText(HomeActivity.this, "Acess Pesquisa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PesquisaActivity.class);
                    startActivity(intent);


            }
        });
        Button btnBarraPesquisa = findViewById(R.id.barraPesquisa);
        btnBarraPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navegarPesquisaActivity();
            }
        });

    }

    private void navegarPesquisaActivity(){
            Intent intent = new Intent(this, PesquisaActivity.class);
            startActivity(intent);
   }
}