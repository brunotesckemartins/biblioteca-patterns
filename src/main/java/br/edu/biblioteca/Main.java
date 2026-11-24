package br.edu.biblioteca;

import br.edu.biblioteca.factory.*;
import br.edu.biblioteca.modelo.*;
import br.edu.biblioteca.observer.*;

public class Main {
    public static void main(String[] args) {
        ItemFactory livroFactory = new LivroFisicoFactory();
        ItemFactory ebookFactory = new EbookFactory();
        Item livro = livroFactory.cadastrarItem("Dom Casmurro", "Machado de Assis");
        Item ebook = ebookFactory.cadastrarItem("O Alienista", "Machado de Assis");
        Usuario usuario = new Usuario("Ana", "ana@example.com", "(48) 99999-0000");
        Observer email = new NotificadorEmail(usuario);
        Observer sms = new NotificadorSMS(usuario);

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimo.adicionarObserver(email);
        emprestimo.adicionarObserver(sms);
        emprestimo.realizarEmprestimo();
        emprestimo.verificarAtraso();
        emprestimo.removerObserver(sms);
        emprestimo.devolver();
        System.out.println("Livro disponível: " + livro.isDisponivel());

        Emprestimo emprestimoEbook = new Emprestimo(ebook, usuario);
        emprestimoEbook.adicionarObserver(email);
        emprestimoEbook.realizarEmprestimo();
        emprestimoEbook.devolver();
        System.out.println("E-book disponível: " + ebook.isDisponivel());
    }
}
