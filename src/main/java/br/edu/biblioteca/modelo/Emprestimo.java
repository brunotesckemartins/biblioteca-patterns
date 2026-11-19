package br.edu.biblioteca.modelo;

import br.edu.biblioteca.observer.EmprestimoObserver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Usuario usuario;
    private final Item item;
    private final List<EmprestimoObserver> observadores = new ArrayList<>();

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Item item) {
        this.usuario = usuario;
        this.item = item;
    }

    // ---- Subject: gerenciamento dos observadores ----

    public void adicionarObservador(EmprestimoObserver observador) {
        observadores.add(observador);
    }

    public void removerObservador(EmprestimoObserver observador) {
        observadores.remove(observador);
    }

    private void notificarEmprestimo() {
        for (EmprestimoObserver o : observadores) {
            o.aoEmprestar(this);
        }
    }

    private void notificarDevolucao() {
        for (EmprestimoObserver o : observadores) {
            o.aoDevolver(this);
        }
    }

    // ---- Regras do empréstimo ----

    public void realizar() {
        if (dataEmprestimo != null) {
            throw new IllegalStateException("Este empréstimo já foi realizado.");
        }
        if (!item.isDisponivel()) {
            throw new IllegalStateException("O item \"" + item.getTitulo() + "\" não está disponível.");
        }
        item.setDisponivel(false);
        dataEmprestimo = LocalDate.now();
        dataPrevistaDevolucao = dataEmprestimo.plusDays(item.getPrazoEmprestimoDias());
        notificarEmprestimo();
    }

    public void devolver() {
        devolver(LocalDate.now());
    }

    /** Permite informar a data da devolução, útil para simular atrasos na demonstração. */
    public void devolver(LocalDate data) {
        if (dataEmprestimo == null) {
            throw new IllegalStateException("Este empréstimo ainda não foi realizado.");
        }
        if (dataDevolucao != null) {
            throw new IllegalStateException("Este empréstimo já foi devolvido.");
        }
        dataDevolucao = data;
        item.setDisponivel(true);
        notificarDevolucao();
    }

    public long getDiasAtraso() {
        if (dataDevolucao == null) {
            return 0;
        }
        return Math.max(ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao), 0);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}