package br.edu.biblioteca.observer;

import br.edu.biblioteca.modelo.Usuario;

public class NotificadorEmail implements Observer {
    private final Usuario usuario;

    public NotificadorEmail(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[Email] " + usuario.getEmail() + ": " + mensagem);
    }
}
