public class Item {
    private Produto produto;
    private int quantidade;
    private double valorTotal;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.getPreco() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setQuantidade(int novaQuantidade) {
        quantidade = novaQuantidade;
        valorTotal = produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "produto=" + produto.getNome() +
                ", codigo=" + produto.getCodigo() +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
