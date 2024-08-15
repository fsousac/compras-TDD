package src.compras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.User;

import static org.junit.jupiter.api.Assertions.*;

// O que devemos testar?
// [X] Ver produtos disponíveis
// [X] Adicionar Produto ao carrinho
// [X] Remover produto do carrinho
// [X] Mostrar produtos no carrinho
// [ ] Mostrar Valor do carrinho
// [ ] Finalizar compra

class CarrinhoTest {
	private Carrinho carrinho;

	@BeforeEach
	void init() {
		this.carrinho = new Carrinho();
	}

	@Test
	public void testProdutosDisponiveis() {
		assertEquals("Meias - R$20.0 x 8\n" +
				"Sapatos - R$70.0 x 3\n" +
				"Tenis - R$50.0 x 5\n", carrinho.getProdutosDisponiveis());
	}

	@Test
	public void testAdicionarProdutosCarrinho() throws Exception {
		User user = new User("testUser", 100);
		carrinho.addProdutoCarrinho("Tenis", 1, user);
		assertEquals("Meias - R$20.0 x 8\n" +
				"Sapatos - R$70.0 x 3\n" +
				"Tenis - R$50.0 x 4\n", carrinho.getProdutosDisponiveis());

		assertEquals("Tenis - R$50.0 x 1\n", carrinho.getCarrinho());

		carrinho.addProdutoCarrinho("Meias", 2, user);
		assertEquals("Meias - R$20.0 x 6\n" +
				"Sapatos - R$70.0 x 3\n" +
				"Tenis - R$50.0 x 4\n", carrinho.getProdutosDisponiveis());

		assertEquals("Tenis - R$50.0 x 1\n" +
				"Meias - R$20.0 x 2\n", carrinho.getCarrinho());


		Exception thrown = assertThrows(Exception.class, () -> carrinho.addProdutoCarrinho("Sapatos", 1, user));
		assertTrue(thrown.getMessage().contains("Dinheiro Insuficiente"));
	}

	@Test
	public void testRemoverProdutosCarrinho() throws Exception {
		User user = new User("testUser", 100);
		carrinho.addProdutoCarrinho("Tenis", 2, user);
		carrinho.removerProdutoCarrinho("Tenis", 1, user);
		assertEquals("Tenis - R$50.0 x 1\n", carrinho.getCarrinho());
		Exception error1 = assertThrows(Exception.class, () -> carrinho.removerProdutoCarrinho("Tenis", 2, user));
		assertTrue(error1.getMessage().contains("Quantidade inválida"));
		Exception erro2 = assertThrows(Exception.class, () -> carrinho.removerProdutoCarrinho("Sapato", 2, user));
		assertTrue(erro2.getMessage().contains("Produto não encontrado no Carrinho"));

	}

}
