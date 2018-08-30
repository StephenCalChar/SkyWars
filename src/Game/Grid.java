package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Grid implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Row> theRows;
	private final  int rows= 4;
	private final int columns =4;
	private final float chanceOfEnemy = 0.33f;
	private final int typesOfEnemy =3;
	private final int startPosition =0;
	private MasterShip masterShip;
	private int shipsOnSquareToBeDestroyed =2;
	private int totalScore =0;
	private final int offensiveModeShips =3;
	private final int defensiveModeShips = 2;
	private boolean gameOver = false;
	private SkyWarsGUI gui;
	private boolean autoMoving = false;
	private EnemyShipFactory enemyShipFactory;

	//creates Grid.
	public Grid(SkyWarsGUI skyWarsGUI) {
		//stores instance of the GUI inside the grid class.
		this.gui = skyWarsGUI;
		this.theRows = new ArrayList <Row> ();
		this.enemyShipFactory = new EnemyShipFactory(this);
		//creates grid on start up.
		for(int loop =0; loop < rows; loop++) {
			Row row = new Row();
			for(int loop2 =0; loop2 < columns; loop2++) {
				Square square = new Square();
				row.getTheSquares().add(square);
			}
			this.theRows.add(row);
		}
		//creates new instance of mastership on grid creation and places it in a random position.
		this.masterShip = new MasterShip(MasterShip.getRandomStart(this.rows), MasterShip.getRandomStart(this.columns), this.columns, this.rows);
		this.masterShip.registerObserver(this);
		updateShipPosition(this.masterShip);
	}

	//switches between Mastership Modes( defensive and offensive)
	public void switchModes() {
		if(this.shipsOnSquareToBeDestroyed == this.defensiveModeShips) {
			this.shipsOnSquareToBeDestroyed = this.offensiveModeShips;
		}
		else {
			this.shipsOnSquareToBeDestroyed = this.defensiveModeShips;
		}
	}

	//only used for zero player games. Space to add further functionality if necessary.
	public void mastershipTurn() {
		this.masterShip.move();
	}

	//all this code activates on the enemy ships turn.
	public void enemyShipTurn () {
		// ships are moved first, then there is a chance of an enemy spawning.
		//this has been done so that the new ship stays at 0,0 once spawned.
		ArrayList<Ship> tempArrayList = this.enemyShipFactory.activeEnemyShips;
		if(tempArrayList.size() >0) {
			for(Ship tempShip: tempArrayList) {
				tempShip.move();
			}
		}
		//Section of enemyships where new enemies are created.
		Random random = new Random();
		float number = random.nextFloat();
		//33% chance that a new enemy is created. The details
		if(number <= this.chanceOfEnemy) {
			//Here we create a random number based on the number of enemies there are. If more enemies are added.
			//then we would increase typesOfEnemy to increase the range of the random
			int enemyTypeNumber = random.nextInt(this.typesOfEnemy);
			//send details of the ship to be constructed to the factory.
			this.enemyShipFactory.createEnemyShip(enemyTypeNumber, this.startPosition);
		} //end creation of enemy.
	} //end computersTurn Method

	//returns an arraylist containing each ship type in the same square as the MasterShip, 
	//but does not return the mastership.
	public ArrayList<Ship> getShipsOnSquare() {
		ArrayList<Ship> shipsOnSquare;
		shipsOnSquare =  this.theRows.get(masterShip.getRowPosition()).getTheSquares().get(masterShip.getColumnPosition()).getTheShips();
		//copy arraylist so we can remove mastership and work with the list,  we want to create a new Arraylist
		//NOT a new reference to the array of ships in the square.
		ArrayList<Ship> enemyShipsFound = new ArrayList<Ship>(shipsOnSquare);
		Collections.copy(enemyShipsFound, shipsOnSquare);
		enemyShipsFound.remove(this.masterShip);
		return enemyShipsFound;
	}
	
	//Method which calculates any battles that occur on the board after the mastership and enemyships have moved.
	public void shipBattleResults() 
	{
		ArrayList<Ship> enemyShipsFound = getShipsOnSquare();
		// checks to see if there are enough ships to destroy mastership. This integer in this variable changes
		//when mastership changes modes.
		if(enemyShipsFound.size() >= this.shipsOnSquareToBeDestroyed) {
			this.theRows.get(masterShip.getRowPosition()).getTheSquares().get(masterShip.getColumnPosition()).getTheShips().remove(masterShip);
			this.setGameOver(true);
		}
		//only activates if mastership does not die.
		else {
			if(enemyShipsFound.size()> 0) {
				//calculate number of enemies killed in the battle.
				int killCount = enemyShipsFound.size();
				//removes ship from both grid arraylist and the list of all active enemyships.
				this.theRows.get(masterShip.getRowPosition()).getTheSquares().get(masterShip.getColumnPosition()).getTheShips().removeAll(enemyShipsFound);
				this.enemyShipFactory.activeEnemyShips.removeAll(enemyShipsFound);
				//increases the score by the number of enemies that have been destroyed.
				this.setTotalScore(this.getTotalScore()+ killCount);
			}
		}
	} //end shipBattleResults method


	//auto move function. Button has 2 different effects depending on whether the game is already auto-
	//running or not. If it is auto running, it will stop it. if not it will start auto running it.
	public void autoMove() {
		if(autoMoving == false) {
			autoMoving = true;
			this.gui.autoRunOnlyButtons();
			TimerTask autoPlay = new TimerTask() {
				public void run() {
					if(gameOver== false && autoMoving == true) {
						mastershipTurn();
					}
					else {
						cancel();
					}  
				}
			};
			Timer timer = new Timer();  
			long delay  = 0;
			long period = 1000L;
			timer.scheduleAtFixedRate(autoPlay, delay, period);
		}
		else {
			this.gui.loadZeroPlayerButtons();
			autoMoving = false;
		}
	} //end autoMove


	//Method which contains everything that needs to happen once the mastership has moves.
	//enemies take turn, checks ship battles, updates GUI grid and score label and checks for game over.
	public void computerGoAndResults() {
		this.enemyShipTurn();
		shipBattleResults();
		this.gui.updateGui(this);
		gui.currentScoreLabel.setText(Integer.toString(this.totalScore));
		if(this.gameOver == true) {
			gui.gameOver();
		}
	}

	//updates the ships position in the grid by taking it out of one square and inserting it into a new square
	//Also, if the update was related to the Mastership's turn.
	@Override
	public void update(Ship ship) {
		removeShip(ship);
		updateShipPosition(ship);
		if(ship instanceof MasterShip) {
			// After the master ship moves and updates the grid, the enemyships are then able to move.
			computerGoAndResults();
		}
	}

	//places ship in its new position in the grid square (within arraylist)
	public void updateShipPosition(Ship ship){
		this.theRows.get(ship.getRowPosition()).getTheSquares().get(ship.getColumnPosition()).getTheShips().add(ship);

	}

	//Removes ship from its old position in square (within arraylist)
	public void removeShip(Ship ship){
		this.theRows.get(ship.getOldRowPosition()).getTheSquares().get(ship.getOldColumnPosition()).getTheShips().remove(ship);		
	}

	
	//Start of getters and setters
	public ArrayList<Row> getTheRows() {
		return this.theRows;
	}
	public void setTheRows(ArrayList<Row> theRows) {
		this.theRows = theRows;
	}

	public int getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public boolean getGameOver() {
		return this.gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public MasterShip getMasterShip() {
		return this.masterShip;
	}

	public void setMasterShip(MasterShip masterShip) {
		this.masterShip = masterShip;
	}

	public int getShipsOnSquareToBeDestroyed() {
		return this.shipsOnSquareToBeDestroyed;
	}

	public void setShipsOnSquareToBeDestroyed(int shipsOnSquareToBeDestroyed) {
		this.shipsOnSquareToBeDestroyed = shipsOnSquareToBeDestroyed;
	}

	public int getOffensiveModeShips() {
		return this.offensiveModeShips;
	}

	public int getDefensiveModeShips() {
		return this.defensiveModeShips;
	}

	public EnemyShipFactory getEnemyShipFactory() {
		return this.enemyShipFactory;
	}

	public void setEnemyShipFactory(EnemyShipFactory enemyShipFactory) {
		this.enemyShipFactory = enemyShipFactory;
	}

	public SkyWarsGUI getGui() {
		return gui;
	}

	public void setGui(SkyWarsGUI gui) {
		this.gui = gui;
	}
}
