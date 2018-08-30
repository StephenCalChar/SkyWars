package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Square> theSquares; 

	public Row() {
		// TODO Auto-generated constructor stub
		theSquares = new ArrayList <Square> ();

	}

	public ArrayList<Square> getTheSquares() {
		return this.theSquares;
	}

	public void setTheSquares(ArrayList<Square> theSquares) {
		this.theSquares = theSquares;
	}

}
