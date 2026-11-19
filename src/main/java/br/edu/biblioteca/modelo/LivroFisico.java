package br.edu.biblioteca.modelo;

public class LivroFisico extends Item {

    public LivroFisico(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 14;
    }

    @Override
    public String getTipo() {
        return "Livro físico";
    }
}