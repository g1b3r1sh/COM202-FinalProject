/**
 * Object that prevents other objects from moving on it.
 */
public class Obstacle extends GameObject implements Positionable
{
	private Point pos;

	/**
	 * Constructor
	 * @param game Game containing object
	 * @param name Name
	 * @param description Description
	 * @param pos Initial position
	 */
	public Obstacle(Game game, String name, String description, Point pos)
	{
		super(game, name, description);
		this.pos = pos;
	}

	/**
	 * Gets current position.
	 * @return Point this object is on
	 */
	public Point getPos()
	{
		return this.pos;
	}

	/**
	 * Update current position.
	 * @param p New position
	 */
	public void updatePos(Point p)
	{
		this.pos = p;
	}
}
