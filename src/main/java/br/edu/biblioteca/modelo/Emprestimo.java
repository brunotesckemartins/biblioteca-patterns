package br.edu.biblioteca.modelo;

import br.edu.biblioteca.observer.Observer;
import br.edu.biblioteca.observer.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo implements Subject {
    private final Item item;
    private final Usuario usuario;
    private final List<Observer> observers = new ArrayList<>();
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private boolean ativo;

    public Emprestimo(Item item, Usuario usuario) {
        this.item = item;
        this.usuario = usuario;
    }

    public void realizarEmprestimo() {
        if (ativo || !item.isDisponivel()) {
            throw new IllegalStateException("Item indisponível para empréstimo.");
        }
        dataEmprestimo = LocalDate.now();
        dataPrevistaDevolucao = dataEmprestimo.plusDays(item.getPrazoEmprestimoDias());
        item.setDisponivel(false);
        ativo = true;
        notificarObservers("Empréstimo de " + item.getTitulo() + " para " + usuario.getNome()
                + ". Devolução prevista: " + dataPrevistaDevolucao);
    }

    public void devolver() {
        if (!ativo) {
            throw new IllegalStateException("Não há empréstimo ativo.");
        }
        item.setDisponivel(true);
        ativo = false;
        notificarObservers("Devolução de " + item.getTitulo() + " realizada por " + usuario.getNome());
    }

    public void verificarAtraso() {
        if (ativo && LocalDate.now().isAfter(dataPrevistaDevolucao)) {
            notificarObservers("Empréstimo em atraso: " + item.getTitulo());
        }
    }

    @Override
    public void adicionarObserver(Observer observer) {
        if (!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers(String mensagem) {
        for (Observer observer : List.copyOf(observers)) observer.atualizar(mensagem);
    }
}
