package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import modelo.Celda;
import modelo.Tablero;

public class Ejecutable {

	private static Tablero tablero;
	private static boolean mine;

	public static void main(String[] args) {
		startGame();
	}

	private static void startGame() {
		mine = false;
		System.out
				.println("Welcome to the afa's Minesweeper! \nEnter board's height, width and number of mines \n(eg.: 8 5 10 determines a 8x15 game board with 10 mines)");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String s = br.readLine();
			createBoard(s);
			while (!mine) {
				s = br.readLine();
				move(s);
			}
			System.out
					.println("Oops, you lost :c\n---------------------------\n");
			System.out.println(tablero.getRealBoard());
			startGame();
		} catch (IOException e) {
			System.out.println("Wrong input");
//			e.printStackTrace();
			startGame();
		}
	}

	private static void move(String s) {
		String[] input = s.split(" ");
		int x = Integer.parseInt(input[0]);
		int y = Integer.parseInt(input[1]);
		String action = input[2];

		Celda actual = tablero.getDisplay()[x - 1][y - 1];

		// uncover
		if (action.equalsIgnoreCase("u")) {

			if (actual.getState().equals("mine")) {
				mine = true;
				tablero.getDisplay()[x - 1][y - 1].setUnselected(false);
			} else if (actual.getState().equals("disable")
					|| actual.getState().equals("number")) {
				uncoverDisbabledAndNumbers(x, y);
			}
			displayBoard(tablero);

		}

		// mark
		else if (action.equalsIgnoreCase("m")) {
			if (actual.isFlag()) {
				actual.setUnselected(false);
				actual.setFlag(true);
			} else {
				actual.setUnselected(true);
				actual.setFlag(false);
			}
			displayBoard(tablero);
		}
		if (finished()) {
			check();
		}
	}

	private static void check() {
		int flags = 0;
		for (int a = 0; a < tablero.getDisplay()[0].length; a++) {
			for (int b = 0; b < tablero.getDisplay().length; b++) {
				Celda actual = tablero.getDisplay()[b][a];
				if (actual.getState().equals("mine")) {
					flags++;
				}
			}
		}
		if (tablero.getMines() == flags) {
			for (int a = 0; a < tablero.getDisplay()[0].length; a++) {
				for (int b = 0; b < tablero.getDisplay().length; b++) {
					Celda actual = tablero.getDisplay()[b][a];
					if (!(actual.isFlag() && actual.getState().equals("mine"))) {
						mine = true;
					}else{
						System.out.println("WINNER!!!\n---------------------------\n");
						startGame();
					}
				}
			}
		}
	}

	private static boolean finished() {
		boolean fin = true;
		for (int a = 0; a < tablero.getDisplay()[0].length; a++) {
			for (int b = 0; b < tablero.getDisplay().length; b++) {
				Celda actual = tablero.getDisplay()[b][a];
				if (actual.isUnselected()) {
					fin = false;
				}
			}
		}
		return fin;
	}

	private static void uncoverDisbabledAndNumbers(int a, int b) {
		try {
			if (a > 0 && a < tablero.getHeight() && b > 0
					&& b < tablero.getWidth()) {
				if (tablero.getDisplay()[b][a].isUnselected()
						&& (tablero.getDisplay()[b][a].getState().equals(
								"disable") || tablero.getDisplay()[b][a]
								.getState().equals("number"))) {
					tablero.getDisplay()[b][a].setUnselected(false);
					if (!tablero.getDisplay()[b][a].getState().equals("number")) {
						uncoverDisbabledAndNumbers(b - 1, a - 1);
						uncoverDisbabledAndNumbers(b, a - 1);
						uncoverDisbabledAndNumbers(b + 1, a - 1);
						uncoverDisbabledAndNumbers(b - 1, a);
						uncoverDisbabledAndNumbers(b + 1, a);
						uncoverDisbabledAndNumbers(b - 1, a + 1);
						uncoverDisbabledAndNumbers(b, a + 1);
						uncoverDisbabledAndNumbers(b + 1, a + 1);
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private static void createBoard(String s) {
		String[] input = s.split(" ");

		try {
			int[] values = new int[3];
			for (int a = 0; a < input.length; a++) {
				values[a] = (Integer.parseInt(input[a]));
			}
			tablero = new Tablero(values[0], values[1], values[2]);
			displayBoard(tablero);
		} catch (NumberFormatException nf) {
			//nf.printStackTrace();
			System.out.println("Wrong input");
		}
	}

	private static void displayBoard(Tablero t) {
		System.out.println(t.toString());
	}

}
