package Game;
import java.util.Random;

public class MasterShip extends Ship{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MasterShip(int rowNumber, int columnNumber, int maxWidth, int maxHeight) {
		super(rowNumber, columnNumber, maxWidth, maxHeight);
	}

	public ShipType getVisual()
	{
		return ShipType.masterShip;
	}

	//generates random position between 0 and the number passed in as parameter.
	public static int getRandomStart(int dimension) {
		int number;
		Random random = new Random();
		number = random.nextInt(dimension);
		return number;
	}
} //end class
