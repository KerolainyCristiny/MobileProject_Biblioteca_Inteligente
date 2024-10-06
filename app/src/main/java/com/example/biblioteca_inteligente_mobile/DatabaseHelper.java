package com.example.biblioteca_inteligente_mobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "biblioteca.db";
    private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, databaseName, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String usuario = "CREATE TABLE usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "matricula INTEGER NOT NULL UNIQUE, " +
                    "senha TEXT NOT NULL" +
                    ");";

            String livro = "CREATE TABLE livro (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "autor TEXT NOT NULL," +
                    "resumo TEXT " +
                    ");";

            String emprestimo = "CREATE TABLE emprestimo (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "usuario_id INTEGER NOT NULL, " +
                    "livro_id INTEGER NOT NULL, " +
                    "data_devolucao TEXT, " +
                    "FOREIGN KEY(usuario_id) REFERENCES usuario(id), " +
                    "FOREIGN KEY(livro_id) REFERENCES livro(id)" +
                    ");";

            db.execSQL("PRAGMA foreign_keys = ON;");
            db.execSQL(emprestimo);
            db.execSQL(usuario);
            db.execSQL(livro);


            String INSERT_USUARIOS = "INSERT INTO usuario (matricula, senha) VALUES " +
                    "('00001242', '123'), " +
                    "('00001243', '456');";

            String INSERT_LIVROS = "INSERT INTO livro (titulo, autor, resumo) VALUES " +
                    "('Introdução à Programação', 'José da Silva', 'Uma introdução aos conceitos básicos de programação.'), " +
                    "('Estruturas de Dados', 'Maria Oliveira', 'Estudo das principais estruturas de dados e suas aplicações.'), " +
                    "('Algoritmos: Teoria e Prática', 'Carlos Pereira', 'Abordagem prática sobre algoritmos e suas complexidades.'), " +
                    "('Banco de Dados: Fundamentos', 'Ana Souza', 'Conceitos fundamentais sobre bancos de dados relacionais.'), " +
                    "('Sistemas Operacionais', 'Fernando Almeida', 'Análise dos principais sistemas operacionais modernos.');";

            String INSERT_EMPRESTIMO = "INSERT INTO emprestimo (usuario_id, livro_id, data_devolucao) VALUES" +
                    "('00001242', '1', '00/00/2000'), ('00001242', '2', '00/00/2000');";

            db.execSQL(INSERT_USUARIOS);
            db.execSQL(INSERT_EMPRESTIMO);
            db.execSQL(INSERT_LIVROS);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS usuario");
            db.execSQL("DROP TABLE IF EXISTS livro");
            db.execSQL("DROP TABLE IF EXISTS emprestimo");
            onCreate(db);
        }

        public boolean verificarUsuario(String matricula, String senha) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM usuario WHERE matricula = ? AND senha = ?";
            Cursor cursor = db.rawQuery(query, new String[]{matricula, senha});
            boolean existeUsuario = cursor.getCount() > 0;
            cursor.close();
            db.close();
            return existeUsuario;
        }

        public Cursor readAllData() {
            if (!tableExists("livro")) {
                createTableLivro();
            }

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT id, titulo, autor FROM livro";
            Cursor cursor = db.rawQuery(query, null);
            return cursor;
        }

    public Cursor readEmprestimo(String matricula) {
        if (!tableExists("emprestimo")) {
            createTableEmprestimo();
        }

        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT livro.titulo, livro.autor, emprestimo.data_devolucao " +
//                "FROM livro " +
//                "INNER JOIN emprestimo ON livro.id = emprestimo.livro_id " +
//                "INNER JOIN usuario ON emprestimo.usuario_id = usuario.id " +
//                "WHERE usuario.matricula = ?";



        String query = "SELECT livro.titulo, livro.autor, emprestimo.data_devolucao " +
                "FROM livro " +
                "INNER JOIN emprestimo ON livro.id = emprestimo.livro_id ";


        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }



        public boolean tableExists(String tableName) {
            SQLiteDatabase db = this.getReadableDatabase();
            int count = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?",
                    new String[]{"table", tableName}).getColumnName(0).equals("COUNT(*)") ? 1 : 0;
            db.close();
            return count > 0;
        }

        public void createTableLivro() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS livro (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, autor TEXT NOT NULL);");

            String INSERT_LIVROS = "INSERT INTO livro (titulo, autor, resumo) VALUES " +
                    "('Introdução à Programação', 'José da Silva', 'Uma introdução aos conceitos básicos de programação.'), " +
                    "('Estruturas de Dados', 'Maria Oliveira', 'Estudo das principais estruturas de dados e suas aplicações.'), " +
                    "('Algoritmos: Teoria e Prática', 'Carlos Pereira', 'Abordagem prática sobre algoritmos e suas complexidades.'), " +
                    "('Banco de Dados: Fundamentos', 'Ana Souza', 'Conceitos fundamentais sobre bancos de dados relacionais.'), " +
                    "('Sistemas Operacionais', 'Fernando Almeida', 'Análise dos principais sistemas operacionais modernos.');";

            db.execSQL(INSERT_LIVROS);

            db.close();
        }

    public void createTableEmprestimo() {
        SQLiteDatabase db = DatabaseHelper.this.getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS emprestimo (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario_id INTEGER NOT NULL,livro_id INTEGER NOT NULL,data_devolucao TEXT,FOREIGN KEY(usuario_id) REFERENCES usuario(id),FOREIGN KEY(livro_id) REFERENCES livro(id));");

        String INSERT_EMPRESTIMO = "INSERT INTO emprestimo (usuario_id, livro_id, data_devolucao) VALUES" +
                "('00001242', '5', '00/00/2000'), ('00001242', '2', '00/00/2000');";

        db.execSQL(INSERT_EMPRESTIMO);

        db.close();
    }


}
