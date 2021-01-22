// Next time, extend all interfaces relating to GameObjects from GameObject interface to reduce explicit casts

/**
 * Implements behavior for attacking other objects.
 */
public interface CanAttack
{
	/**
	 * Attacks another object.
	 * @param object Object to attack
	 */
	public void attack(Damageable object);
	/**
	 * Get the raw damage stat of the object.
	 * @return Raw damage of this object.
	 */
	public int getDamage();
}