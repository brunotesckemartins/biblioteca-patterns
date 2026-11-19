package br.edu.biblioteca.observer;

import br.edu.biblioteca.modelo.Emprestimo;

public class NotificadorEmail implements EmprestimoObserver {

    @Override
    public void aoEmprestar(Emprestimo e) {
        System.out.println("[E-MAIL para " + e.getUsuario().getEmail() + "] Olá, "
                + e.getUsuario().getNome() + "! Você pegou emprestado " + e.getItem()
                + ". Devolução até " + Emprestimo.FORMATO_DATA.format(e.getDataPrevistaDevolucao()) + ".");
    }

    @Override
    public void aoDevolver(Emprestimo e) {
        String mensagem = e.getDiasAtraso() > 0
                ? "Devolução com " + e.getDiasAtraso() + " dia(s) de atraso."
                : "Devolução dentro do prazo. Obrigado!";
        System.out.println("[E-MAIL para " + e.getUsuario().getEmail() + "] "
                + e.getItem().getTitulo() + " devolvido. " + mensagem);
    }
}