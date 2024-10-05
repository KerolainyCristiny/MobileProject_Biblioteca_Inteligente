package com.example.biblioteca_inteligente_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteca_inteligente_mobile.databinding.ActivityBottomNavBinding;
import com.example.biblioteca_inteligente_mobile.databinding.ActivityHomeBinding;

public class BottomNav extends AppCompatActivity {

    ActivityBottomNavBinding binding;

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


        binding.bottomNav.setSelectedItemId(R.id.livros);
        binding.bottomNav.setOnItemSelectedListener(item -> {


        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            // Lógico para o item home
            return true;
        } else if (itemId == R.id.pesquisamenu) {

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