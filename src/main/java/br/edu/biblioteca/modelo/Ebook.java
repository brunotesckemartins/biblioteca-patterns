package br.edu.biblioteca.modelo;

public class Ebook extends Item {

    public Ebook(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 7;
    }

    @Override
    public String getTipo() {
        return "E-book";
    }
}
