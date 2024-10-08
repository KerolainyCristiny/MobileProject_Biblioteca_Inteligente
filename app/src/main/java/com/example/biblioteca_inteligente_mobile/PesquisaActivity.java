package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityPesquisaBinding;

public class PesquisaActivity extends AppCompatActivity {

    ActivityPesquisaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityPesquisaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.bottomNav.setSelectedItemId(R.id.pesquisamenu);
        binding.bottomNav.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.pesquisamenu) {
                // Lógico para o item home

                return true;
            } else if (itemId == R.id.home) {
                Intent intent = new Intent(PesquisaActivity.this, HomeActivity.class);
                intent.putExtra("matricula", "00001242"); // Passando diretamente o valor da matrícula
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.livros){
                Intent intent = new Intent(PesquisaActivity.this, BottomNav.class);
                startActivity(intent);
                finish();
                return true;
            }

            return false;
        });

        //pesquisa
        binding.buttonPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pesquisa = binding.pesquisa.getText().toString();

                if(pesquisa.equals("")){
                    Toast.makeText(PesquisaActivity.this, "Campo de pesquisa vazio", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(PesquisaActivity.this, ResultadoActivity.class);
                    intent.putExtra("pesquisa",pesquisa);
                    startActivity(intent);

                }



            }
        });//metodo

    }
}