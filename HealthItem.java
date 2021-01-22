/**
 * Consumable that heals
 */
public class HealthItem extends Consumable
{
	private int restore;

	/**
	 * Constructor
	 * @param game Game containing this object
	 * @param name Name
	 * @param description Description
	 * @param uses Number of uses for this object
	 * @param restore Amount of health to restore
	 */
	public HealthItem(Game game, String name, String description, int uses, int restore)
	{
		super(game, name, description, uses);
		this.restore = restore;
	}

	/**
	 * Heals object.
	 * @param object Object to heal
	 */
	public void use(GameObject object) throws IllegalArgumentException
	{
		if (!(object instanceof Damageable))
		{
			throw new IllegalArgumentException("Target is not Damageable");
		}
		((Damageable) object).heal(this.restore);
		super.decrementUses();
	}

	/**
	 * Gets amount this heals.
	 * @return Healing amount
	 */
	public int getRestore()
	{
		return this.restore;
	}
}
