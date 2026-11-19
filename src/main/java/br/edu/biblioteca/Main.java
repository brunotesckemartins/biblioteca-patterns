package br.edu.biblioteca;

import br.edu.biblioteca.factory.EbookFactory;
import br.edu.biblioteca.factory.ItemFactory;
import br.edu.biblioteca.factory.LivroFisicoFactory;
import br.edu.biblioteca.modelo.Emprestimo;
import br.edu.biblioteca.modelo.Item;
import br.edu.biblioteca.modelo.Usuario;
import br.edu.biblioteca.observer.NotificadorEmail;
import br.edu.biblioteca.observer.NotificadorSms;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== 1. Cadastro do acervo (Factory Method) ===");
        ItemFactory fabricaFisica = new LivroFisicoFactory();
        ItemFactory fabricaEbook = new EbookFactory();

        Item livro = fabricaFisica.cadastrarItem("Clean Code", "Robert C. Martin");
        Item ebook = fabricaEbook.cadastrarItem("Design Patterns", "Erich Gamma");

        System.out.println("\n=== 2. Cadastro do usuário ===");
        Usuario usuario = new Usuario("Maria Silva", "maria@email.com", "(48) 99999-0000");
        System.out.println("Usuário cadastrado: " + usuario);

        System.out.println("\n=== 3. Empréstimo do livro físico (Observer) ===");
        Emprestimo emprestimo1 = new Emprestimo(usuario, livro);
        emprestimo1.adicionarObservador(new NotificadorEmail());
        emprestimo1.adicionarObservador(new NotificadorSms());
        emprestimo1.realizar();

        System.out.println("\n=== 4. Tentativa de emprestar item indisponível ===");
        try {
            Emprestimo emprestimoInvalido = new Emprestimo(usuario, livro);
            emprestimoInvalido.realizar();
        } catch (IllegalStateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        System.out.println("\n=== 5. Devolução com atraso (simulada) ===");
        LocalDate dataComAtraso = emprestimo1.getDataPrevistaDevolucao().plusDays(3);
        emprestimo1.devolver(dataComAtraso);

        System.out.println("\n=== 6. Empréstimo do e-book, só com notificação por e-mail ===");
        Emprestimo emprestimo2 = new Emprestimo(usuario, ebook);
        emprestimo2.adicionarObservador(new NotificadorEmail());
        emprestimo2.realizar();
        emprestimo2.devolver();
    }
}