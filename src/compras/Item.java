package src.compras;

public class Item {
	private String name;
	private float price;
	private int quantity = 1;

	public Item(String name, float price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return name + " - R$" + price + " x " + quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
