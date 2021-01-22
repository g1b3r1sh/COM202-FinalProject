/**
 * Object that can be used on another object
 */
public abstract class Item extends GameObject
{
	/**
	 * Constructor
	 * @param game Game containing object
	 * @param name Name
	 * @param description Description
	 */
	public Item(Game game, String name, String description)
	{
		super(game, name, description);
	}

	/**
	 * Use item on another Object.
	 * @param object Object to use item on
	 */
	public abstract void use(GameObject object);
}
