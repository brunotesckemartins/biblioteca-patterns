package br.edu.biblioteca.factory;

import br.edu.biblioteca.modelo.Item;
import br.edu.biblioteca.modelo.LivroFisico;

public class LivroFisicoFactory extends ItemFactory {

    @Override
    protected Item criarItem(String titulo, String autor) {
        return new LivroFisico(titulo, autor);
    }
}
