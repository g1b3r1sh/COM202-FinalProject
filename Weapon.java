// NOTE do not submit this

public abstract class Weapon extends Equipment
{
	public Weapon(Game game, String name, String description)
	{
		super(game, name, description);
	}

	// Attack method, or just buff
	public abstract void use(GameObject o);
}
