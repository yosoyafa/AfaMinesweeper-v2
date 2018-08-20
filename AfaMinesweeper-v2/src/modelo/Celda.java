package modelo;

public class Celda {

	private String state;
	private boolean unselected, flag;
	private int number;

	public Celda() {
		state = "disable";
		unselected = true;
		number = 0;
		flag = false;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isUnselected() {
		return unselected;
	}

	public void setUnselected(boolean unselected) {
		this.unselected = unselected;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String toString() {
		String out = "";
		if (flag) {
			out = "P";
		} else if (!unselected) {
			if (state.equals("number")) {
				out = "" + number;
			} else if (state.equals("mine")) {
				out = "*";
			} else if (state.equals("disable")) {
				out = "-";
			} else {
				out = "";
			}
		} else {
			out = ".";
		}

		return out;
	}

	public String getRealCell() {
		String out = "";
		if (state.equals("number")) {
			out = "" + number;
		} else if(state.equals("disable")){
			out = "-";
		}else if(state.equals("mine")){
			out = "*";
		}

		return out;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
