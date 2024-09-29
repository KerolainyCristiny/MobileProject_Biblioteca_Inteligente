package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "biblioteca.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "biblioteca.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "matricula INTEGER NOT NULL UNIQUE, " +
                "senha TEXT NOT NULL" +
                ");";


//        String CREATE_TABLE_LIVRO = "CREATE TABLE livro (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "titulo TEXT NOT NULL, " +
//                "autor TEXT NOT NULL, " +
//                "resumo TEXT, " +
//                "quantidade_total INTEGER NOT NULL, " +
//                "quantidade_reserva INTEGER NOT NULL" +
//                ");";


//        String CREATE_TABLE_RESERVA = "CREATE TABLE reserva (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "usuario_id INTEGER NOT NULL, " +
//                "livro_id INTEGER NOT NULL, " +
//                "data_prevista_retirada TEXT, " +
//                "FOREIGN KEY(usuario_id) REFERENCES usuario(id), " +
//                "FOREIGN KEY(livro_id) REFERENCES livro(id)" +
//                ");";


//        String CREATE_TABLE_EMPRESTIMO = "CREATE TABLE emprestimo (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "usuario_id INTEGER NOT NULL, " +
//                "livro_id INTEGER NOT NULL, " +
//                "data_devolucao TEXT, " +
//                "FOREIGN KEY(usuario_id) REFERENCES usuario(id), " +
//                "FOREIGN KEY(livro_id) REFERENCES livro(id)" +
//                ");";

        // Executar os creates
        db.execSQL(CREATE_TABLE_USUARIO);
//        db.execSQL(CREATE_TABLE_LIVRO);
//        db.execSQL(CREATE_TABLE_RESERVA);
//        db.execSQL(CREATE_TABLE_EMPRESTIMO);


        // Inserir Usuario
        String INSERT_USUARIOS = "INSERT INTO usuario (matricula, senha) VALUES " +
                "('00001242', '123'), " +
                "('00001243', '456');";


        // Inserir Livro
//        String INSERT_LIVROS = "INSERT INTO Livro (titulo, autor, resumo, quantidade_total, quantidade_reserva) VALUES " +
//                "('Dom Quixote', 'Miguel de Cervantes', 'Um romance clássico sobre as aventuras de um cavaleiro errante.', 5, 2), " +
//                "('1984', 'George Orwell', 'Um romance distópico sobre uma sociedade totalitária.', 8, 3), " +
//                "('O Senhor dos Anéis', 'J.R.R. Tolkien', 'Uma fantasia épica sobre a luta contra o mal.', 10, 4);";


        // Executar os inserts
        db.execSQL(INSERT_USUARIOS);
//        db.execSQL(INSERT_LIVROS);

    } // fim onCreate


//SEPARAR METODOS, 1 MATRICULA - 1 SENHA
    // Método para verificar se um usuário existe
    public boolean verificarUsuario(String matricula, String senha) {
        SQLiteDatabase db = this.getReadableDatabase(); // Abre o banco no modo leitura
        String query = "SELECT * FROM Usuario WHERE matricula = ? AND senha = ?";

        // Utilizar um Cursor para armazenar o resultado da consulta
        Cursor cursor = db.rawQuery(query, new String[]{matricula, senha});

        boolean existeUsuario = cursor.getCount() > 0; // Verifica se há algum registro retornado
        // se ele retornar 1 = true


        cursor.close();
        db.close();

        return existeUsuario; // Retorna true se o usuário for encontrado, senão false
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
//        db.execSQL("DROP TABLE IF EXISTS livro");
//        db.execSQL("DROP TABLE IF EXISTS reserva");
//        db.execSQL("DROP TABLE IF EXISTS emprestimo");
        onCreate(db);
    }



}
