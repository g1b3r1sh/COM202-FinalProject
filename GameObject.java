/**
 * Object class for game - all objects in game are subclass of this class.
 */
public abstract class GameObject
{
	private String name;
	private String description;
	private Game game;

	/**
	 * Contructor
	 * @param game Game containing object
	 * @param name Name
	 * @param description Description
	 */
	public GameObject(Game game, String name, String description)
	{
		this.game = game;
		this.name = name;
		this.description = description;
	}

	/**
	 * Remove self from game (should delete this object).
	 */
	public void remove()
	{
		this.game.remove(this);
	}

	/**
	 * Get name.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Get description.
	 * @return description
	 */
	public String getDescription()
	{
		return this.description;
	}
}