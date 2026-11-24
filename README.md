# Biblioteca Patterns

Projeto acadêmico em Java que demonstra os padrões de projeto Factory Method e Observer em um sistema de biblioteca.

## Factory Method

Delega a criação dos itens às subclasses de `ItemFactory`, mantendo a lógica comum de cadastro no método `cadastrarItem`.

- `Item`: classe abstrata dos itens do acervo.
- `LivroFisico` e `Ebook`: itens concretos, com prazos de empréstimo de 14 e 7 dias, respectivamente.
- `ItemFactory`: fábrica abstrata que define o método `criarItem`.
- `LivroFisicoFactory` e `EbookFactory`: fábricas concretas.

## Observer

Permite notificar diferentes observadores sobre eventos de empréstimo sem depender de notificadores concretos.

- `Subject`: interface para adicionar, remover e notificar observadores.
- `Observer`: interface que define o método `atualizar`.
- `Emprestimo`: objeto observado, responsável por realizar empréstimos, devoluções e verificar atrasos.
- `NotificadorEmail` e `NotificadorSMS`: observadores concretos que simulam notificações no console, sem envio real.

## Estrutura

- `factory`: fábricas de itens.
- `modelo`: itens, usuário e empréstimo.
- `observer`: interfaces e notificadores.
- `Main`: demonstração dos dois padrões integrados.

## Como executar

Requisitos: Java 17 e Maven. Na raiz do projeto, execute:

```bash
mvn compile exec:java
```

O programa cadastra um livro físico e um e-book usando as fábricas, realiza empréstimos e devoluções e demonstra o cadastro e a remoção de observadores. Após remover o observador de SMS do empréstimo do livro, apenas o e-mail recebe a notificação de devolução. Os prazos são calculados conforme o tipo de item. A verificação de atraso só notifica se a data prevista já tiver passado; a execução normal não simula a passagem do tempo.
