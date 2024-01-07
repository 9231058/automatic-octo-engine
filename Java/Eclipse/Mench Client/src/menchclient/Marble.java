package menchclient;

public class Marble { // DONE

	private boolean goal;
	private boolean active;
	private int n;
	private int turn;
	private int place;
	private int counter;
	private int firstPlace;
	private int lastPlace;

	public Marble(int turn, int n) {

		this.n = n;
		this.turn = turn;

		place = ((turn - 1) * 4 + 40 + n);

		firstPlace = (turn - 1) * 10;

		lastPlace = 55 + n + ((turn - 1) * 4);

		active = false;
		goal = false;

		counter = 0;
	}

	public int getCounter() {

		return counter;
	}

	public void addToCounter(int n) {

		counter += n;
		place += n;
		place = place % 40;
		if (counter > 39) {
			goal = true;
			place = lastPlace;
		}
	}

	public void activate() {

		active = true;
		place = firstPlace;
	}

	public void deactivate() {

		active = false;
		place = ((turn - 1) * 4 + 40 + n);
	}

	public boolean isActivated() {

		if (active)
			return true;

		return false;
	}

	public boolean isGoal() {

		return goal;
	}

	public int getPlace() {

		return place;
	}
	
	public int getFirstPlace() {
		return firstPlace;
	}
}