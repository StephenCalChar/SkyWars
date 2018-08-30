package Game;

import java.io.Serializable;

public class Gamestate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid;
	private GameType gameType;

	//getters and setters
	public Grid getGrid() {
		return this.grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public GameType getGameType() {
		return this.gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
}
