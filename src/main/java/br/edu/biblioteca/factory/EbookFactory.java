package br.edu.biblioteca.factory;

import br.edu.biblioteca.modelo.Ebook;
import br.edu.biblioteca.modelo.Item;

public class EbookFactory extends ItemFactory {

    @Override
    protected Item criarItem(String titulo, String autor) {
        return new Ebook(titulo, autor);
    }
}
