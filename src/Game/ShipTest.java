package Game;

import static org.junit.Assert.*;


import org.junit.Test;

public class ShipTest {


	@Test
	public void testMoveRight() {
		int shipStartColumn=0;
		int shipStartRow =0;
		int expectedColumn =1;
		int expectedRow = 0;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveRight();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved to the right.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveLeft() {
		int shipStartColumn=1;
		int shipStartRow =0;
		int expectedColumn =0;
		int expectedRow = 0;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveLeft();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved to the left.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveUp() {
		int shipStartColumn=1;
		int shipStartRow =0;
		int expectedColumn =0;
		int expectedRow = 0;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveLeft();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved up.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);	
	}

	@Test
	public void testMoveDown() {
		int shipStartColumn=0;
		int shipStartRow =0;
		int expectedColumn =0;
		int expectedRow = 1;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveDown();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved down.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveUpLeft() {
		int shipStartColumn=1;
		int shipStartRow =1;
		int expectedColumn =0;
		int expectedRow = 0;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.MoveUpLeft();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved diagonally upwards and left.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveUpRight() {
		int shipStartColumn=1;
		int shipStartRow =1;
		int expectedColumn =2;
		int expectedRow = 0;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.MoveUpRight();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved diagonally upwards and right.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveDownRight() {
		int shipStartColumn=0;
		int shipStartRow =0;
		int expectedColumn =1;
		int expectedRow = 1;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveDownRight();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved diagonally downwards and right.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testMoveDownLeft() {
		int shipStartColumn=1;
		int shipStartRow =0;
		int expectedColumn =0;
		int expectedRow = 1;		
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new  BattleCruiser(shipStartRow, shipStartColumn,  grid.getRows(), grid.getColumns()); 
		battleCruiser.moveDownLeft();
		int shipEndRow = battleCruiser.getRowPosition();
		int shipEndColumn = battleCruiser.getColumnPosition();
		//checks that the battlecruiser has moved diagonally downwards and left.
		assertTrue(expectedColumn == shipEndColumn && expectedRow == shipEndRow);
	}

	@Test
	public void testRegisterObserver() {
		int startPosition =0;
		SkyWarsGUI skyWarsGUI = new SkyWarsGUI();
		Grid grid = new Grid(skyWarsGUI);
		BattleCruiser battleCruiser = new BattleCruiser(startPosition, startPosition,  grid.getRows(), grid.getColumns());
		battleCruiser.registerObserver(grid);
		//checks the battlecruiser has the grid as a registered observer.
		assertTrue(battleCruiser.getObservers().contains(grid));

	}
}
