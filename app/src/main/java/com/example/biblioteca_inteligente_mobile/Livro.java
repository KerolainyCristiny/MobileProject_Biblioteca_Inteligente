package com.example.biblioteca_inteligente_mobile;

import java.io.Serializable;

public class Livro implements Serializable {
    private String titulo;
    private String autor;
    private String resumo;

    public Livro(String titulo, String autor, String resumo) {
        this.titulo = titulo;
        this.autor = autor;
        this.resumo = resumo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getResumo() {
        return resumo;
    }
}