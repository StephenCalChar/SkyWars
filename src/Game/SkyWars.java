package Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

import javax.swing.JFrame;

public class SkyWars implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid;
	private GameType gameType;
	private final String FILE_NAME = "src/SavedGames/saveGame.ser";
	private SkyWarsGUI gui;


	public SkyWars(SkyWarsGUI skyWarsGUI) {
		this.gui = skyWarsGUI;
	}	

	public void newZeroPlayerGame() {
		//creates new instance of grid object and loads up controls for a zero player game
		//and updates grid to show mastership position.
		this.grid = new Grid(gui);
		this.gameType = GameType.zeroPlayer;
		this.gui.updateGui(this.grid);
	}

	public void newOnePlayerGame() {
		//creates new instance of the grid object, loads controls for one player game
		//and updates grid to show mastership position.
		this.grid = new Grid(gui);
		this.gameType = GameType.onePlayer;
		this.gui.updateGui(this.grid);
	}

	public void saveGame() {
		//creates a new instance of the Gamestate class and passes the 2 objects which require saving in to 
		//be stored as parameters. The Gamestate object is then serialised.
		Gamestate gamestate = new Gamestate();
		gamestate.setGrid(this.grid);
		gamestate.setGameType(this.gameType);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(gamestate);
			fileOutputStream.close();
			objectOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGame() {
		//Creates a new instance of the Gamestate class and loads the serialized object stored within the saveGame file
		//into this instance, casting it as a gamestate object. The properties contained within gamestate are then used
		//to set the grid to the saved grid and the gametype to the saved gametype.
		Gamestate gamestate = new Gamestate();
		try {
			FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			gamestate = (Gamestate) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
		this.grid= gamestate.getGrid();
		this.gameType = gamestate.getGameType();
		this.grid.setGui(this.gui);
	}

	//getters and setters
	public GameType getGameType() {
		return this.gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
