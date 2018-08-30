package Game;



public class BattleShooter extends Ship {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BattleShooter(int rowNumber, int columnNumber, int maxHeight, int maxWidth) {
		super(rowNumber, columnNumber, maxHeight, maxWidth);
	}
	public ShipType getVisual()
	{
		return ShipType.battleShooter;
	}

}
