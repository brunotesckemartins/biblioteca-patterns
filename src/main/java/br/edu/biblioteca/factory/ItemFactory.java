package br.edu.biblioteca.factory;

import br.edu.biblioteca.modelo.Item;

public abstract class ItemFactory {

    /** Factory Method: cada subclasse decide qual tipo concreto de Item instanciar. */
    protected abstract Item criarItem(String titulo, String autor);

    /** Lógica comum a todas as fábricas, que só depende da abstração Item. */
    public Item cadastrarItem(String titulo, String autor) {
        Item item = criarItem(titulo, autor);
        System.out.println("Item cadastrado no acervo: " + item
                + " (prazo: " + item.getPrazoEmprestimoDias() + " dias)");
        return item;
    }
}
