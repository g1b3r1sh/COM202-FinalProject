/**
 * GameObject that can be placed on a board
 */
public class Entity extends GameObject implements Controllable, Damageable
{
	private int maxHealth;
	private int health;
	private Point pos;
	private int defense = 0;
	private Controller controller;

	/**
	 * Constructor
	 * @param game Game containing object
	 * @param name Name
	 * @param description Description
	 * @param pos Initial position
	 * @param maxHealth Maximum health
	 */
	public Entity(Game game, String name, String description, Point pos, int maxHealth)
	{
		super(game, name, description);
		this.pos = pos;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	/**
	 * Take damage to health.
	 * @param damage Amount of damage to take
	 */
	public void takeDamage(int damage)
	{
		// Don't take damage if its less than 1
		if (damage >= 1)
		{
			// Defense reduces damage up to 1
			if (damage <= this.defense + 1)
			{
				this.health -= 1;
			}
			else
			{
				this.health -= damage - this.defense;
			}
		}
		// Remove object from board if no health
		if (this.health <= 0)
		{
			super.remove();
		}
	}

	/**
	 * Heal health.
	 * @param health Amount to heal by
	 */
	public void heal(int health)
	{
		// Don't heal past maxHealth
		if (this.maxHealth - this.health <= health)
		{
			this.health = this.maxHealth;
		}
		else
		{
			this.health += health;
		}
		// Remove object from board if no health
		if (this.health < 0)
		{
			super.remove();
		}
	}

	/**
	 * Gets health of object.
	 * @return health
	 */
	public int getHealth()
	{
		return this.health;
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
	
	/**
	 * Gets Controller.
	 * @return Controller of object
	 */
	public Controller getController()
	{
		return this.controller;
	}

	/**
	 * Sets Controller for object.
	 * @param controller Controller
	 */
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
}