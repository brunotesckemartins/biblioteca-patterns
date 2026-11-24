package br.edu.biblioteca.observer;

import br.edu.biblioteca.modelo.Usuario;

public class NotificadorSMS implements Observer {
    private final Usuario usuario;

    public NotificadorSMS(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[SMS] " + usuario.getTelefone() + ": " + mensagem);
    }
}
