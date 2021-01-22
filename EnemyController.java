import java.util.ArrayList;
import java.util.Random;

/**
 * Controller for enemy objects (target and attack players).
 */
public class EnemyController extends Controller
{
	private Random moveRoll;

	/**
	 * Constructor
	 * @param board Board containing enemies
	 */
	public EnemyController(GridBoard board)
	{
		super(board);
		this.moveRoll = new Random();
	}

	/**
	 * Enemy moving behavior is to move randomly until can attack Player.
	 * @param o Enemy to move
	 * @param board Board containing enemy
	 */
	public Point getMove(Controllable o, GridBoard board) throws IllegalArgumentException
	{
		if (!this.contains(o))
		{
			throw new IllegalArgumentException("Controller does not contain object.");
		}
		// Create list of empty spaces around enemy
		ArrayList<Point> availableMoves = new ArrayList<>();
		availableMoves.add(o.getPos());
		for (int x = o.getPos().getX() - 1; x <= o.getPos().getX() + 1; x++)
		{
			for (int y = o.getPos().getY() - 1; y <= o.getPos().getY() + 1; y++)
			{
				Point currPoint = new Point(x, y);
				if (board.onBoard(currPoint))
				{
					Positionable obj = board.getPositionable(currPoint);
					if (obj == null)
					{
						availableMoves.add(currPoint);
					}
					// If Player around enemy, attack it (10% chance to ignore)
					else if (board.getPositionable(currPoint) instanceof Player && this.moveRoll.nextFloat() >= 0.1)
					{
						return currPoint;
					}
				}
			}
		}
		// Pick random empty space
		return availableMoves.get(this.moveRoll.nextInt(availableMoves.size()));
	}
}