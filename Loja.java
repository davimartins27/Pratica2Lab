import java.util.Scanner;

public class Loja {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação do estoque com tamanho 10 (exemplo)
        Estoque estoque = new Estoque(10);

        // Produtos pré-cadastrados
        Produto p1 = new Produto("Caneta", "P001", 2.5);
        Produto p2 = new Produto("Caderno", "P002", 15.0);
        Produto p3 = new Produto("Borracha", "P003", 1.5);

        estoque.adicionarProduto(p1);
        estoque.adicionarProduto(p2);
        estoque.adicionarProduto(p3);

        // Inicialmente, adicionar estoque básico
        p1.adicionarEstoque(10);
        p2.adicionarEstoque(5);
        p3.adicionarEstoque(3);

        Fatura fatura = new Fatura();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Comprar");
            System.out.println("2 - Ver Fatura");
            System.out.println("3 - Excluir item da fatura");
            System.out.println("4 - Alterar quantidade item");
            System.out.println("5 - Finalizar");
            System.out.println("6 - Listar produtos");
            System.out.println("7 - Produtos com estoque baixo");
            System.out.println("8 - Adicionar produto ao estoque");
            System.out.println("9 - Remover produto do estoque");
            System.out.println("10 - Repor estoque");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:  // Comprar
                    estoque.listarProdutos();
                    System.out.print("Informe o código do produto para comprar (ou vazio para voltar): ");
                    String codigoComprar = scanner.nextLine();
                    if (codigoComprar.isEmpty()) break;

                    Produto prodComprar = estoque.buscarProduto(codigoComprar);
                    if (prodComprar == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Informe a quantidade: ");
                    int qtdComprar = Integer.parseInt(scanner.nextLine());

                    if (prodComprar.getQuantidadeEstoque() < qtdComprar) {
                        System.out.println("Quantidade indisponível em estoque.");
                    } else {
                        prodComprar.retirarEstoque(qtdComprar);
                        fatura.adicionarItem(new Item(prodComprar, qtdComprar));
                        System.out.println("Compra realizada com sucesso.");
                    }
                    break;

                case 2:  // Ver fatura
                    fatura.mostrarFatura();
                    break;

                case 3:  // Excluir item
                    fatura.mostrarFatura();
                    System.out.print("Informe o índice do item a excluir (ou vazio para voltar): ");
                    String excluirStr = scanner.nextLine();
                    if (excluirStr.isEmpty()) break;

                    int indiceExcluir = Integer.parseInt(excluirStr);
                    fatura.removerItem(indiceExcluir);
                    System.out.println("Item removido.");
                    break;

                case 4:  // Alterar quantidade
                    fatura.mostrarFatura();
                    System.out.print("Informe o índice do item para alterar (ou vazio para voltar): ");
                    String alterarStr = scanner.nextLine();
                    if (alterarStr.isEmpty()) break;

                    int indiceAlterar = Integer.parseInt(alterarStr);
                    System.out.print("Nova quantidade: ");
                    int novaQtd = Integer.parseInt(scanner.nextLine());

                    Item itemAlterar = null;
                    if (indiceAlterar >= 0 && indiceAlterar < fatura.getQuantidadeItens()) {
                        itemAlterar = fatura.itens.get(indiceAlterar); // poderia criar getter público para itens
                    }

                    if (itemAlterar == null) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    Produto produtoItem = itemAlterar.getProduto();
                    int qtdEstoqueAtual = produtoItem.getQuantidadeEstoque() + itemAlterar.getQuantidade();

                    if (novaQtd <= qtdEstoqueAtual) {
                        // Repor estoque antes por causa da alteração
                        produtoItem.adicionarEstoque(itemAlterar.getQuantidade());
                        boolean retirou = produtoItem.retirarEstoque(novaQtd);
                        if (retirou) {
                            fatura.alterarQuantidadeItem(indiceAlterar, novaQtd);
                            System.out.println("Quantidade atualizada.");
                        } else {
                            System.out.println("Erro ao atualizar estoque.");
                        }
                    } else {
                        System.out.println("Quantidade solicitada maior que estoque disponível.");
                    }
                    break;

                case 5:  // Finalizar
                    fatura.mostrarFatura();
                    System.out.println("Compra finalizada. Obrigado!");
                    scanner.close();
                    return;

                case 6:  // Listar produtos
                    estoque.listarProdutos();
                    break;

                case 7:  // Produtos com estoque baixo
                    estoque.produtosComEstoqueBaixo();
                    break;

                case 8:  // Adicionar produto ao estoque
                    System.out.print("Código do novo produto: ");
                    String novoCodigo = scanner.nextLine();
                    if (estoque.verificarExistencia(novoCodigo)) {
                        System.out.println("Produto já existe.");
                        break;
                    }

                    System.out.print("Nome do produto: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Preço do produto: ");
                    double novoPreco = Double.parseDouble(scanner.nextLine());

                    Produto novoProduto = new Produto(novoNome, novoCodigo, novoPreco);
                    boolean adicionou = estoque.adicionarProduto(novoProduto);
                    if (adicionou) {
                        System.out.println("Produto adicionado ao estoque.");
                    } else {
                        System.out.println("Não foi possível adicionar produto (estocado cheio ou erro).");
                    }
                    break;

                case 9:  // Remover produto
                    System.out.print("Código do produto a remover: ");
                    String codigoRemover = scanner.nextLine();
                    boolean removido = estoque.removerProduto(codigoRemover);
                    if (removido) {
                        System.out.println("Produto removido.");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 10: // Repor estoque
                    System.out.print("Código do produto a repor: ");
                    String codigoRepor = scanner.nextLine();
                    Produto produtoRepor = estoque.buscarProduto(codigoRepor);
                    if (produtoRepor == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }
                    System.out.print("Quantidade para repor: ");
                    int qtdRepor = Integer.parseInt(scanner.nextLine());
                    produtoRepor.adicionarEstoque(qtdRepor);
                    System.out.println("Estoque atualizado.");
                    break;

                case 0: // Sair
                    System.out.println("Programa encerrado.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
