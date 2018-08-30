package Game;


import java.io.Serializable;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Random;



public abstract class Ship implements Observable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//keep track of ships position through the properties in the Ship.
	private int columnPosition;
	private int rowPosition;
	private int oldColumnPosition;
	private int oldRowPosition;
	private final int movement = 1;
	private final int minGridValue =0;
	private int maxHeight;
	private int maxWidth;
	private ArrayList<Observer> observers;



	public Ship(int rowNumber, int columnNumber, int maxHeight, int maxWidth) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.columnPosition = columnNumber;
		this.rowPosition= rowNumber;
		this.oldRowPosition = this.rowPosition;
		this.oldColumnPosition = this.columnPosition;
		observers = new ArrayList<Observer>();
	}

	//abstract class to be implemented by all classes that inherit from Ship.
	public abstract ShipType getVisual();



	//method to randomly move each enemy ship in a direction. If the attempted move will be off the grid
	// then moveCompleted will remain as false until a move attempt is successful and true is returned.
	public void move() {
		int possibleDirections =8;
		Boolean moveCompleted = false;
		Random random = new Random();
		while(moveCompleted == false) {
			int number = random.nextInt(possibleDirections);
			switch(number) {
			case 0:
				moveCompleted = moveRight();
				break;
			case 1 :
				moveCompleted = moveLeft();
				break;
			case 2:
				moveCompleted = moveDown();
				break;
			case 3:
				moveCompleted = moveUp();
				break;
			case 4:
				moveCompleted = MoveUpLeft();
				break;
			case 5:
				moveCompleted = MoveUpRight();
				break;
			case 6:
				moveCompleted = moveDownLeft();
				break;
			case 7:
				moveCompleted = moveDownRight();
				break;	
			}
		} 
	} //end move method

	//Next 8 methods are movements each ship can make. They each return true if the movement is sucessful and
	//false is the movement has failed due to the attempted position being outside of the grid.
	public boolean moveRight() {
		if(this.columnPosition+1 <this.maxWidth) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.columnPosition += this.movement;
			notifyObservers();
			return true;	
		}
		return false;
	}

	public boolean moveLeft() {
		if(this.columnPosition >minGridValue) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.columnPosition -= this.movement;
			notifyObservers();
			return true;
		}
		return false;
	}

	public boolean moveUp() {
		if(this.rowPosition > minGridValue) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition -=this.movement;
			notifyObservers();
			return true;
		}
		return false;

	}

	public boolean moveDown() {
		if(this.rowPosition+1 <this.maxHeight) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition += this.movement;
			notifyObservers();
			return true;
		}
		return false;
	}

	public boolean MoveUpLeft() {
		if(this.rowPosition > minGridValue && this.columnPosition >minGridValue ) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition -=this.movement;
			this.columnPosition -= this.movement;
			notifyObservers();
			return true;
		}
		return false;
	}

	public boolean MoveUpRight() {
		if(this.rowPosition > minGridValue && this.columnPosition+1 <this.maxWidth) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition -=movement;
			this.columnPosition +=movement;
			notifyObservers();
			return true;
		}
		return false;
	}

	public boolean moveDownRight() {
		if(this.rowPosition+1 <this.maxHeight && this.columnPosition+1 <this.maxWidth) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition +=movement;
			this.columnPosition +=movement;
			notifyObservers();
			return true;
		}
		return false;
	}	

	public boolean moveDownLeft() {
		if(this.rowPosition+1 <this.maxHeight && this.columnPosition >this.minGridValue) {
			this.oldRowPosition = this.rowPosition;
			this.oldColumnPosition = this.columnPosition;
			this.rowPosition +=movement;
			this.columnPosition -=movement;
			notifyObservers();
			return true;
		}
		return false;
	}	

	//Adds an observer to the observer arraylist.
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	//removes an observer from the observer arraylist.
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);	
	}

	//Notifies  each observer within the observer arraylist
	@Override
	public void notifyObservers() {
		for (Observer observer: this.observers) {
			observer.update(this);
		}
	}


	///getters and setters
	public int getColumnPosition() {
		return this.columnPosition;
	}

	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}

	public int getRowPosition() {
		return this.rowPosition;
	}

	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}

	public int getOldColumnPosition() {
		return this.oldColumnPosition;
	}

	public void setOldColumnPosition(int oldColumnPosition) {
		this.oldColumnPosition = oldColumnPosition;
	}

	public int getOldRowPosition() {
		return this.oldRowPosition;
	}

	public void setOldRowPosition(int oldRowPosition) {
		this.oldRowPosition = oldRowPosition;
	}

	public ArrayList<Observer> getObservers() {
		return this.observers;
	}

	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}


}
