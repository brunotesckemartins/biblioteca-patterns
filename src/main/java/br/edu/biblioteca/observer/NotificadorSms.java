package br.edu.biblioteca.observer;

import br.edu.biblioteca.modelo.Emprestimo;

public class NotificadorSms implements EmprestimoObserver {

    @Override
    public void aoEmprestar(Emprestimo e) {
        System.out.println("[SMS para " + e.getUsuario().getTelefone() + "] Emprestimo de \""
                + e.getItem().getTitulo() + "\" confirmado. Devolver ate "
                + Emprestimo.FORMATO_DATA.format(e.getDataPrevistaDevolucao()) + ".");
    }

    @Override
    public void aoDevolver(Emprestimo e) {
        String atraso = e.getDiasAtraso() > 0
                ? " Atraso de " + e.getDiasAtraso() + " dia(s)."
                : "";
        System.out.println("[SMS para " + e.getUsuario().getTelefone() + "] Devolucao de \""
                + e.getItem().getTitulo() + "\" registrada." + atraso);
    }
}