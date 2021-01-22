/**
 * Entity that has the ability to attack and has an inventory (like enemies, player).
 */
public class Combatant extends Entity implements CanAttack, HasInventory
{
	private Inventory inventory;
	private int baseDamage;
	private int addDamage = 0;

	/**
	 * Constructor
	 * @param game Game containing this object
	 * @param name Name
	 * @param description Description
	 * @param pos Initial position
	 * @param startHealth Initial health
	 * @param baseDamage Base damage
	 */
	public Combatant(Game game, String name, String description, Point pos, int startHealth, int baseDamage)
	{
		super(game, name, description, pos, startHealth);
		this.baseDamage = baseDamage;
		this.inventory = new Inventory();
	}

	/**
	 * Attacks object.
	 * @param o Damageable object to attack
	 */
	public void attack(Damageable o)
	{
		o.takeDamage(baseDamage + addDamage);
	}

	/**
	 * Retrieves total damage of Combatant (base damage + additional damage).
	 * @return Total damage
	 */
	public int getDamage()
	{
		return this.baseDamage + this.addDamage;
	}

	/**
	 * Gets inventory of object.
	 * @return Inventory
	 */
	public Inventory getInventory()
	{
		return this.inventory;
	}
}
