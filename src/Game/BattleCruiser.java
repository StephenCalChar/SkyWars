package Game;

public class BattleCruiser extends Ship {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BattleCruiser(int rowNumber, int columnNumber, int maxHeight, int maxWidth) {
		super(rowNumber, columnNumber, maxHeight, maxWidth);
	}
	public ShipType getVisual()
	{
		return ShipType.battleCruiser;
	}

}
