package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Square implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Ship> theShips;

	public Square() {
		// TODO Auto-generated constructor stub
		theShips = new ArrayList <Ship> ();

	}

	public ArrayList<Ship> getTheShips() {
		return this.theShips;
	}

	public void setTheShips(ArrayList<Ship> theShips) {
		this.theShips = theShips;
	}
}

