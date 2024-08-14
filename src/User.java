package src;

public class User {
	private String name;
	private float money;

	public User(String name, float money) {
		this.name = name;
		this.money = money;
	}

	@Override
	public String toString() {
		return name + ": R$" + money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}
}
