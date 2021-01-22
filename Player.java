/**
 * Combatant controlled by user. Targeted by enemies
 */
public class Player extends Combatant
{
	/**
	 * Constructor
	 * @param game Game containing object
	 * @param name Name
	 * @param description Description
	 * @param pos Initial position
	 * @param maxHealth Initial health
	 * @param baseDamage Base damage
	 */
	public Player(Game game, String name, String description, Point pos, int maxHealth, int baseDamage)
	{
		super(game, name, description, pos, maxHealth, baseDamage);
	}
}
