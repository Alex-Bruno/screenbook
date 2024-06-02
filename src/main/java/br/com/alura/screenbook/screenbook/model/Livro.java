package br.com.alura.screenbook.screenbook.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String idExterno;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;
    private Idioma idioma;
    private Double downloads;

    public Livro() {}

    public Livro(DadosLivro d) {
        this.idExterno = d.idExterno();
        this.titulo = d.titulo();
        this.idioma = d.idiomas().get(0);
        this.downloads = d.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "----- LIVRO -----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de downloads: " + downloads + "\n" +
                "--------------------\n";
    }
}
