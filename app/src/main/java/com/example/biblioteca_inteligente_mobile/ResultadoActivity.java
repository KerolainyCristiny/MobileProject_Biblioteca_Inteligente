package com.example.biblioteca_inteligente_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;




    public class ResultadoActivity extends Activity {

//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_resultados);
//
//            TextView textView = findViewById(R.id.list_view_resultados);
//
//            // Aqui você recebe os resultados da classe Pesquisa
//            String resultado = getIntent().getStringExtra("resultado");
//
//            if (resultado != null && !resultado.isEmpty()) {
//                textView.setText(resultado);
//            } else {
//                textView.setText("Nenhum livro encontrado.");
//            }
//        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resultados);

            // Obter os resultados da pesquisa
            String resultado = getIntent().getStringExtra("resultado");

            if (resultado != null && !resultado.isEmpty()) {
                // Criar um ArrayAdapter com os resultados
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, resultado.split("\n"));

                // Encontrar o ListView no layout
                ListView listView = findViewById(R.id.list_view_resultados);

                // Configurar o adaptador no ListView
                listView.setAdapter(arrayAdapter);
            } else {
                // Se não houver resultados, mostrar uma mensagem
                ListView listView = findViewById(R.id.list_view_resultados);
                listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"Nenhum livro encontrado."}));
            }
        }
    }

