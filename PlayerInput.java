/**
 * Represents object that can get user input
 */
public abstract class PlayerInput
{
	private Controller controller;

	/**
	 * Constructor
	 * @param controller Controller input is passed to
	 */
	public PlayerInput(Controller controller)
	{
		this.controller = controller;
	}

	/**
	 * Destroys controller.
	 */
	public void destructController()
	{
		this.controller.destructor();
	}

	/**
	 * Request move from user input.
	 * @param o Object to request move for
	 * @param board Board containing object
	 * @return Point retrived from input
	 */
	public abstract Point requestMove(Controllable o, GridBoard board);

	/**
	 * Handle error (ex. invalid move, invalid input).
	 * @param id Error id
	 */
	public abstract void handleError(String id) throws IllegalArgumentException;

	/**
	 * Called when initially requesting move from user
	 * @param o Object to request move for
	 * @param board Board containing object
	 */
	public abstract void initial(Controllable o, GridBoard board);
}
