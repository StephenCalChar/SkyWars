package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class EnemyShipFactory implements Serializable {
	private Grid grid;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Ship> activeEnemyShips;
	private final int battlestarType =0;
	private final int battlecruiserType =1;

	public EnemyShipFactory(Grid grid) {
		this.grid = grid;
		activeEnemyShips = new ArrayList<Ship>();
	}

	//Creates a new instance of type ship. New types of ship can be added here.
	public void createEnemyShip(int shipType, int startPosition) {
		Ship ship;
		if(shipType  == battlestarType) {
			ship = new BattleStar(startPosition, startPosition,  grid.getRows(), grid.getColumns());
		}
		else if(shipType  == battlecruiserType) {
			ship = new BattleCruiser(startPosition, startPosition, grid.getRows(), grid.getColumns());
		}

		else {
			ship = new BattleShooter(startPosition, startPosition, grid.getRows(), grid.getColumns());
		}
		//The type ship is added to the array of active ships and the observers are registered.
		ship.registerObserver(this.grid);
		ship.notifyObservers();
		this.activeEnemyShips.add(ship);
	}
}

