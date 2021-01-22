/**
 * Item that has a limited number of uses
 */
public abstract class Consumable extends Item
{
	private int uses;
	
	/**
	 * Constructor
	 * @param game Game containing this object
	 * @param name Name
	 * @param description Description
	 * @param uses Number of uses for this object
	 */
	public Consumable(Game game, String name, String description, int uses)
	{
		super(game, name, description);
		this.uses = uses;
	}

	/**
	 * Use this Item on an object. Make sure to call decrementUses() to decrease uses.
	 * @param object object to use this on
	 */
	public abstract void use(GameObject object);

	/**
	 * Decrement number of uses and removes this item from the game if it has no more uses.
	 */
	protected void decrementUses()
	{
		this.uses--;
		if (this.uses <= 0)
		{
			super.remove();
		}
	}

	/**
	 * Get number of uses left.
	 * @return number of uses
	 */
	public int getUses()
	{
		return this.uses;
	}
}
