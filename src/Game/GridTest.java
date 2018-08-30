package Game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GridTest {

	@Test
	public void testUpdateShipPosition() {
		int shipStartColumn =0;
		int shipStartRow= 0;
		int newColumn =1;
		int newRow =1;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		//inserts battle cruiser at starting position.
		BattleCruiser battleCruiser = new BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns());
		//moces the battleship to new row and column
		battleCruiser.setColumnPosition(newColumn);
		battleCruiser.setRowPosition(newRow);
		//updates Squares in the grid with the new position of the ship
		grid.updateShipPosition(battleCruiser);
		//asserts that the ship is contained within the arraylist inside the position's corresponding square object.
		assertTrue(grid.getTheRows().get(newRow).getTheSquares().get(newColumn).getTheShips().contains(battleCruiser));	
	}

	@Test
	public void testRemoveShip() {
		int shipStartColumn =0;
		int shipStartRow= 0;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		//inserts a battlecruiser object into a square on the grid.
		BattleCruiser battleCruiser = new BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns());
		//notifies the grid of the battlecruiser and its position and adds it to the grid.
		grid.updateShipPosition(battleCruiser);
		//removes the ship from the grid
		grid.removeShip(battleCruiser);
		//asserts that the ship is no longer contained within the arraylist inside the position's corresponding square object.
		assertTrue(grid.getTheRows().get(shipStartRow).getTheSquares().get(shipStartColumn).getTheShips().contains(battleCruiser)==false);
	}

	@Test
	public void testSwitchModes() {
		// Creates Grid which starts in defensive mode. Checks to see if the variable has been changed after switchMode method.
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		grid.switchModes();
		//check to ensure the mode has switched to offensiveMode
		assertTrue(grid.getShipsOnSquareToBeDestroyed() == grid.getOffensiveModeShips());	
	}

	@Test
	public void testMastershipTurn() {
		//this check will make sure that when the mastership moves, it is no longer in its current position.
		int rowPosition =0;
		int colPosition= 0;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		skyWarsGUI.skyWars.setGrid(grid);
		grid.getMasterShip().setColumnPosition(colPosition);
		grid.getMasterShip().setRowPosition(rowPosition);
		grid.mastershipTurn();
		grid.getMasterShip().notifyObservers();
		//checks that the mastership is no longer in the same position, confirming it has taken a turn and moved.
		assertTrue(grid.getTheRows().get(rowPosition).getTheSquares().get(colPosition).getTheShips().contains(grid.getMasterShip())==false);	
	}


	@Test
	public void testEnemyShipTurn () {
		//this check ensures that an enemy ship moves when the opponents turn method is called.
		int startRow = 3;
		int startColumn =3;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);		
		BattleCruiser battleCruiser = new BattleCruiser(startRow, startColumn,  grid.getRows(), grid.getColumns());
		grid.getEnemyShipFactory().activeEnemyShips.add(battleCruiser);
		battleCruiser.registerObserver(grid);
		battleCruiser.notifyObservers();
		grid.enemyShipTurn();
		battleCruiser.notifyObservers();
		//checks that the battlecruiser is no longer in its starting position, once the enemy ships have moved.
		assertTrue(grid.getTheRows().get(startRow).getTheSquares().get(startColumn).getTheShips().contains(battleCruiser)==false);
	}

	@Test
	public void testGetShipsOnSquare() {
		int rowPosition =0;
		int colPosition= 0;
		ArrayList<Ship> tempShipArray = new ArrayList<Ship>();
		int totalShipsInserted =3;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		//inserts ships into arraylist in square on the grid and also adds them to tempShipArray.
		for(int loop =0;loop< totalShipsInserted;loop++) {
			BattleCruiser battleCruiser = new BattleCruiser(rowPosition, colPosition,  grid.getRows(), grid.getColumns());
			tempShipArray.add(battleCruiser);
			grid.updateShipPosition(battleCruiser);
		}
		//Mastership object is required for this calculation. As it appears randomly when grid is created. 
		// It must be moved to the same grid location that the array of ships as been placed as the
		// method returns the ships on the same square as the mastership.
		grid.getMasterShip().setColumnPosition(colPosition);
		grid.getMasterShip().setRowPosition(rowPosition);
		grid.updateShipPosition(grid.getMasterShip());
		//ensures the correct array of ships is returned from the square they were placed in.
		assertTrue(tempShipArray.equals(grid.getShipsOnSquare()));	
	}


	@Test
	public void testShipBattleResults() {
		int rowPosition =0;
		int colPosition= 0;
		ArrayList<Ship> tempShipArray = new ArrayList<Ship>();
		//This test will ensure a single enemy is defeated when placed on the same square as the mastership.
		int totalShipsInserted =1;
		int enemyShipsAfterBattle =0;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		//inserts ship into arraylist in square on grid and also adds them to tempShipArray.
		for(int loop =0;loop< totalShipsInserted;loop++) {
			BattleCruiser battleCruiser = new BattleCruiser(rowPosition, colPosition,grid.getRows(), grid.getColumns());
			tempShipArray.add(battleCruiser);
		}
		//Mastership object is required for this test As it appears randomly when grid is created. It must be moved
		// to the same grid location that the array of ships as been placed
		grid.getMasterShip().setColumnPosition(colPosition);
		grid.getMasterShip().setRowPosition(rowPosition);
		//creates the battle between ships and the mastership on the square.
		grid.shipBattleResults();
		//ensures there are no enemy ships remaining on the square, meaning the ship has been destroyed.
		assertTrue(enemyShipsAfterBattle == grid.getShipsOnSquare().size());
	}


}
