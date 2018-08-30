package Game;

public class BattleStar extends Ship {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BattleStar(int rowNumber, int columnNumber, int maxHeight, int maxWidth) {
		super(rowNumber, columnNumber, maxHeight, maxWidth);
	}

	public ShipType getVisual()
	{
		return ShipType.battleStar;
	}
}
