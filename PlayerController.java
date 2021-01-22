/**
 * Controller controlled by user.
 */
public class PlayerController extends Controller
{
	private PlayerInput input;
	
	/**
	 * Controller
	 * @param board Board with objects under this control
	 */
	public PlayerController(GridBoard board)
	{
		super(board);
	}

	/**
	 * Gets move for single object under control.
	 * @param o Controllable object to get move for
	 * @param board Board containing object
	 * @return Point for object to move to
	 */
	public Point getMove(Controllable o, GridBoard board) throws IllegalArgumentException
	{
		if (!this.contains(o))
		{
			throw new IllegalArgumentException("Controller does not control object.");
		}
		this.input.initial(o, board);
		// Keeps requesting move until valid move retrieved
		while (true)
		{
			Point p = this.input.requestMove(o, board);
			if (this.verifyMove(board, o, p))
			{
				return p;
			}
			else
			{
				this.input.handleError("blocked");
			}
		}
	}

	/**
	 * Verifies if move is possible (ex. not blocked by obstacle).
	 * @param board Board containing object
	 * @param object Object to move
	 * @param p Point to move to
	 * @return If move is possible
	 */
	public boolean verifyMove(GridBoard board, Controllable player, Point p)
	{
		if (board.onBoard(p))
		{
			Positionable object = board.getPositionable(p);
			return object == null || player.getPos().equals(object.getPos()) || (player instanceof CanAttack && object instanceof Damageable && ((Entity) object).getController() != this);
		}
		return false;
	}

	/**
	 * Sets input method.
	 * @param input Object to get input from
	 */
	public void setInput(PlayerInput input)
	{
		this.input = input;
	}
}
