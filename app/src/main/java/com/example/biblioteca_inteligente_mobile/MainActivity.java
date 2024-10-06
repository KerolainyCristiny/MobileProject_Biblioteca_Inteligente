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

import com.example.biblioteca_inteligente_mobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DatabaseHelper db = new DatabaseHelper(this);
        binding.btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String matricula = binding.matricula.getText().toString();
                String senha = binding.password.getText().toString();


                if (senha.equals("") ||  matricula.equals("")){
                   Toast.makeText(MainActivity.this, "matricula ou senha vazio", Toast.LENGTH_SHORT).show();

                }else {
                    Boolean checkUsuario = db.verificarUsuario(matricula,senha);

                    if( checkUsuario == true){
                        Toast.makeText(MainActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("matricula", matricula); // Passando diretamente o valor da matrícula
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Usuario invalido", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

    }
}