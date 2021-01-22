// NOTE Do not submit this

public abstract class Equipment extends Item
{
	private GameObject attached;

	public Equipment(Game game, String name, String description)
	{
		super(game, name, description);
	}

	public void equipped(GameObject object)
	{
		if (object instanceof CanEquip)
		{
			this.attached = object;
		}
	}
}
