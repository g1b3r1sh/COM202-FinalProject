/**
 * Implements behavior for being damageable
 */
public interface Damageable
{
	/**
	 * Take damage to health.
	 * @param damage Amount of damage to take
	 */
	public void takeDamage(int damage);
	
	/**
	 * Heal health.
	 * @param health Amount to heal by
	 */
	public void heal(int health);

	/**
	 * Gets health of object.
	 * @return health
	 */
	public int getHealth();
}