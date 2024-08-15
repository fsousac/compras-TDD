package src.compras;

import src.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Carrinho {
	private final List<Item> produtosDisponiveis = new ArrayList<>();
	private final float precoTotal;
	private final List<Item> produtosCarrinho;

	public Carrinho() {
		this.precoTotal = 0;
		this.produtosCarrinho = new ArrayList<>();
		addProdutosDisponiveis(new Item("Tenis", 50f, 5));
		addProdutosDisponiveis(new Item("Sapatos", 70f, 3));
		addProdutosDisponiveis(new Item("Meias", 20f, 8));
	}


	private void addProdutosDisponiveis(Item item) {
		this.produtosDisponiveis.add(item);
		this.produtosDisponiveis.sort(Comparator.comparing(Item::getName));
	}

	public String getProdutosDisponiveis() {
		String stringResultado = "";
		for (Item item : produtosDisponiveis) {
			stringResultado += item.toString() + "\n";
		}

		return stringResultado;
	}

	public String getCarrinho() {
		String stringResultado = "";
		for (Item item : produtosCarrinho) {
			stringResultado += item.toString() + "\n";
		}

		return stringResultado;
	}

	public void removerProdutoCarrinho(String nomeProduto, int quantidade, User user) throws Exception {
		Item produtoRemover = null;
		for (Item item : produtosCarrinho) {
			if (item.getName().equals(nomeProduto)) {
				produtoRemover = item;
				if (item.getQuantity() < quantidade) {
					throw new Exception("Quantidade inválida");
				} else if (item.getQuantity() == quantidade) {
					produtosCarrinho.remove(item);
					addProdutosDisponiveis(item);
				} else {
					item.setQuantity(item.getQuantity() - quantidade);
					for (Item newItem : produtosDisponiveis) {
						if (newItem.getName().equals(nomeProduto)) {
							newItem.setQuantity(newItem.getQuantity() + quantidade);
							break;
						}
						if (newItem == produtosDisponiveis.getLast()) {
							addProdutosDisponiveis(new Item(item.getName(), item.getPrice(), quantidade));
						}
					}
				}
				user.setMoney(user.getMoney() + (item.getPrice() * quantidade));
				break;
			}
		}
		if (produtoRemover == null) {
			throw new Exception("Produto não encontrado no Carrinho");
		}
	}

	public void addProdutoCarrinho(String nomeProduto, int quantidade, User user) throws Exception {
		float cost = -1;
		int disponivel = 0;
		Item produto = null;
		for (Item item : produtosDisponiveis) {
			if (item.getName().equals(nomeProduto)) {
				cost = item.getPrice();
				disponivel = item.getQuantity();
				produto = item;
				break;
			}
		}
		if (cost == -1) throw new Exception("Produto não encontrado");
		else if (disponivel < quantidade || quantidade <= 0) throw new Exception("Quantidade invalida");
		else if (cost * quantidade > user.getMoney()) throw new Exception("Dinheiro Insuficiente");

		this.produtosCarrinho.add(new Item(nomeProduto, cost, quantidade));
		user.setMoney(user.getMoney() - (cost * quantidade));
		if (quantidade == disponivel) produtosDisponiveis.remove(produto);
		else {
			produto.setQuantity(disponivel - quantidade);
		}
	}
}
