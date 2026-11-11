import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private HashMap<String, Produto> produtos;
    private int tamanho;

    public Estoque(int tamanho) {
        this.tamanho = tamanho;
        this.produtos = new HashMap<>();
    }

    public boolean adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getCodigo())) {
            return false; // Produto já existe
        }
        if (produtos.size() >= tamanho) {
            return false; // Estoque cheio
        }
        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public Produto buscarProduto(String codigo) {
        return produtos.get(codigo);
    }

    public boolean removerProduto(String codigo) {
        if (produtos.containsKey(codigo)) {
            produtos.remove(codigo);
            return true;
        }
        return false;
    }

    public boolean verificarExistencia(String codigo) {
        return produtos.containsKey(codigo);
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        for (Map.Entry<String, Produto> entry : produtos.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void produtosComEstoqueBaixo() {
        boolean achou = false;
        for (Produto p : produtos.values()) {
            if (p.getQuantidadeEstoque() < 5) {
                System.out.println(p);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum produto com estoque baixo.");
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
