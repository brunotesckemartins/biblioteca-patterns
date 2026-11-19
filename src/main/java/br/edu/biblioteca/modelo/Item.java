package br.edu.biblioteca.modelo;

public abstract class Item {

    private final String titulo;
    private final String autor;
    private boolean disponivel = true;

    protected Item(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    /** Cada tipo de item define seu próprio prazo de empréstimo. */
    public abstract int getPrazoEmprestimoDias();

    /** Nome legível do tipo, usado nas mensagens (ex.: "Livro físico"). */
    public abstract String getTipo();

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return getTipo() + ": \"" + titulo + "\" de " + autor;
    }
}