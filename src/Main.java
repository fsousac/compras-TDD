package src;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String res = "y";
		String[] answers = {"y", "n", "s"};
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite seu nome: ");
		String name = sc.nextLine();
		System.out.println("Digite o quanto você vai gastar: ");
		float money = Float.parseFloat(sc.nextLine());
		User user = new User(name, money);
		do {
			// Compras do usuário

			while (Arrays.asList(answers).contains(res)) {
				System.out.println("Do you want to continue? (Y/N)");
				res = sc.nextLine().toLowerCase();
			}
		} while (!res.equals("n"));
	}
}
