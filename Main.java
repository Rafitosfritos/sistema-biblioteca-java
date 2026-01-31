public void main() {
    Scanner leitor = new Scanner(System.in);
    ArrayList<Livro> listaDeLivros = new ArrayList<>();
    int opcao = 0;

    while (opcao != 6) {
        System.out.println("""
                \n***** MENU *****
                
                1. Cadastrar Livro
                2. Registrar Empréstimo
                3. Registrar Devolução
                4. Ver Biblioteca
                5. Pesquisar Livro
                6. Sair""");
        System.out.println("Escolha: ");
        opcao = leitor.nextInt();
        leitor.nextLine();

        switch (opcao){

            case 1:
                Livro livro = new Livro();

                System.out.println("\nDigite o título do livro: ");
                livro.titulo = leitor.nextLine();

                System.out.println("Digite o nome do autor: ");
                livro.autor = leitor.nextLine();

                System.out.println("Digite o número de páginas: ");
                livro.paginas = leitor.nextInt();
                leitor.nextLine();

                listaDeLivros.add(livro);
                break;

            case 2:
                System.out.println("\n***** REGISTRAR EMPRÉSTIMO *****");
                System.out.println("\nDigite o titulo do livro escolhido: ");
                String busca = leitor.nextLine();
                boolean encontrado = false;

                for (Livro item : listaDeLivros){
                    if (item.titulo.equalsIgnoreCase(busca)){
                        encontrado = true;

                        if (!item.emprestado) {
                            item.emprestado = true;
                            item.dataDeDevolucao = LocalDate.now().plusDays(15);

                            System.out.printf("""
                                    \nLivro registrado com sucesso!
                                    Data de devolução: %s""", item.formatarData(item.dataDeDevolucao));
                        } else {
                            System.out.printf("""
                        Livro indiponível no momento!
                        Data provável de devolução: %s""", item.formatarData(item.dataDeDevolucao));
                        }
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Livro não encontrado no sistema!");
                }
                break;

            case 3:
                System.out.println("\n***** REGISTRAR DEVOLUÇÃO *****");
                System.out.println("Digite o título do livro a ser devolvido: ");
                String tituloDevolvido = leitor.nextLine();
                boolean livroEncontrado = false;

                for (Livro item : listaDeLivros){
                    if (item.titulo.equalsIgnoreCase(tituloDevolvido)){
                        livroEncontrado = true;

                        if (item.emprestado) {
                            item.emprestado = false;
                            item.dataDeDevolucao = null;
                            System.out.printf("\nO livro %s foi devolvido com sucesso!", item.titulo);
                        } else {
                            System.out.println("Este livro não consta como emprestado no sistema!");
                        }
                        break;
                    }
                }

                if (!livroEncontrado) {
                    System.out.println("Livro não encontrado! Verifique se digitou o título corretamente");
                }
                break;

            case 4:
                for (Livro item : listaDeLivros) {
                    System.out.println("\n***** BIBLIOTECA *****");
                    item.exibirInformações();
                }
                break;

            case 5:
                System.out.println("\n***** PESQUISAR LIVRO *****");
                System.out.println("""
                        1. Título
                        2. Autor""");
                System.out.print("Escolha: ");
                int tipoBusca = leitor.nextInt();
                leitor.nextLine();
                boolean achou = false;

                if (tipoBusca == 1) {
                    System.out.println("Digite o título do livro: ");
                    String pesquisarTitulo = leitor.nextLine();
                    for (Livro item : listaDeLivros){
                        if (item.titulo.equalsIgnoreCase(pesquisarTitulo)){
                            item.exibirInformações();
                            achou = true;
                        }
                    }
                    if (!achou) {
                        System.out.println("Nenhum livro cadastrado com esse título!");
                    }
                    break;
                } else {
                    System.out.println("Digite o nome do autor: ");
                    String pesquisarAutor = leitor.nextLine();
                    for (Livro item : listaDeLivros){
                        if (item.autor.equalsIgnoreCase(pesquisarAutor)){
                            item.exibirInformações();
                            achou = true;
                        }
                    }
                    if (!achou) {
                        System.out.println("Nenhum livro cadastrado para este autor!");
                    }
                    break;
                }

        }
    }
}