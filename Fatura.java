import java.util.ArrayList;

public class Fatura {
    private ArrayList<Item> itens;
    private double valorTotal;

    public Fatura() {
        itens = new ArrayList<>();
        valorTotal = 0.0;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        recalcularValorTotal();
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
            recalcularValorTotal();
        }
    }

    public void alterarQuantidadeItem(int indice, int novaQuantidade) {
        if (indice >= 0 && indice < itens.size()) {
            Item item = itens.get(indice);
            item.setQuantidade(novaQuantidade);
            recalcularValorTotal();
        }
    }

    public void mostrarFatura() {
        if (itens.isEmpty()) {
            System.out.println("Fatura vazia.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            System.out.println(i + ": " + itens.get(i));
        }
        System.out.println("Valor total da fatura: " + valorTotal);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private void recalcularValorTotal() {
        valorTotal = 0;
        for (Item item : itens) {
            valorTotal += item.getValorTotal();
        }
    }

    public int getQuantidadeItens() {
        return itens.size();
    }
}
