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
        });

    }

}