package modelo;

public class Tablero {

	private int width, height, mines;
	private Celda[][] display;

	public Tablero(int w, int h, int m) {
		width = w;
		height = h;
		mines = m;
		display = new Celda[h][w];
		fillCells();
		spreadMines();
		spreadNumbers();
	}

	private void spreadNumbers() {
		for (int a = 0; a < display[0].length; a++) {
			for (int b = 0; b < display.length; b++) {
				Celda actual = display[b][a];
				int num = 0;
				if (!actual.getState().equals("mine")) {
					try {
						if (display[b - 1][a - 1] != null
								&& display[b - 1][a - 1].getState().equals(
										"mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b][a - 1] != null
								&& display[b][a - 1].getState().equals("mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b + 1][a - 1] != null
								&& display[b + 1][a - 1].getState().equals(
										"mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b - 1][a] != null
								&& display[b - 1][a].getState().equals("mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b + 1][a] != null
								&& display[b + 1][a].getState().equals("mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b - 1][a + 1] != null
								&& display[b - 1][a + 1].getState().equals(
										"mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b][a + 1] != null
								&& display[b][a + 1].getState().equals("mine")) {
							num++;
						}
					} catch (Exception e) {

					}
					try {
						if (display[b + 1][a + 1] != null
								&& display[b + 1][a + 1].getState().equals(
										"mine")) {
							num++;
						}
					} catch (Exception e) {

					}
				}
				if(num > 0){
					actual.setState("number");
					actual.setNumber(num);
				}
			}
		}
	}

	public Celda[][] getDisplay() {
		return display;
	}

	public void setDisplay(Celda[][] display) {
		this.display = display;
	}

	private void spreadMines() {
		for (int a = 0; a < mines;) {
			int x = (int) (Math.random() * (height - 1));
			int y = (int) (Math.random() * (width - 1));
			if (!display[x][y].getState().equals("mine")) {
				display[x][y].setState("mine");
				a++;
			}
		}
	}

	private void fillCells() {
		for (int a = 0; a < display[0].length; a++) {
			for (int b = 0; b < display.length; b++) {
				display[b][a] = new Celda();
			}
		}
	}

	public String toString() {
		String out = "";
		try {
			for (int a = 0; a < display[0].length; a++) {
				if (a != 0) {
					out = out + "\n";
				}
				for (int b = 0; b < display.length; b++) {
					out = out + display[b][a].toString() + " ";
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return out;
		}

		return out;
	}
	
	public String getRealBoard(){
		String out = "";
		try {
			for (int a = 0; a < display[0].length; a++) {
				if (a != 0) {
					out = out + "\n";
				}
				for (int b = 0; b < display.length; b++) {
					out = out + display[b][a].getRealCell() + " ";
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return out;
		}

		return out;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

}
