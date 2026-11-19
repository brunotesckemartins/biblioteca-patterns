package br.edu.biblioteca.observer;

import br.edu.biblioteca.modelo.Emprestimo;

public interface EmprestimoObserver {

    void aoEmprestar(Emprestimo emprestimo);

    void aoDevolver(Emprestimo emprestimo);
}