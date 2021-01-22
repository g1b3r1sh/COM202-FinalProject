import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Stores Items and manages their usage
 */
public class Inventory
{
	private ArrayList<Item> items;

	/**
	 * Constructor
	 */
	public Inventory()
	{
		this.items = new ArrayList<>();
	}

	/**
	 * Uses item on object.
	 * @param item Item to use
	 * @param target Object to use item on
	 * @return If this contains item
	 */
	public boolean useItem(Item item, GameObject target) throws IllegalArgumentException
	{
		if (!this.contains(item))
		{
			throw new IllegalArgumentException("Inventory does not contain Item.");
		}
		item.use(target);
		if (item instanceof Consumable)
		{
			this.remIfUsed((Consumable) item);
		}
		return true;
	}

	/**
	 * Gets first instance of Item in inventory.
	 * @param s Name of Item
	 * @return Retrieved Item or null
	 */
	public Item getItem(String s) throws IllegalArgumentException
	{
		for (Item i : this.items)
		{
			if (i.getName().equalsIgnoreCase(s))
			{
				return i;
			}
		}
		throw new IllegalArgumentException("Inventory does not contain Item.");
	}

	/**
	 * Adds Item to inventory.
	 * @param item Item to add
	 */
	public void add(Item item)
	{
		if (!this.contains(item))
		{
			this.items.add(item);
		}
	}

	/**
	 * Removes Item from inventory.
	 * @parm item Item to remove
	 * @param item
	 */
	public void remove(Item item) throws IllegalArgumentException
	{
		if (!this.contains(item))
		{
			throw new IllegalArgumentException("Inventory does not contain Item.");
		}
		this.items.remove(item);
	}

	/**
	 * Returns if Item is in Inventory.
	 * @param item Item to check for
	 * @return If Item is in Inventory
	 */
	public boolean contains(Item item)
	{
		return this.items.contains(item);
	}

	/**
	 * Gets items in inventory.
	 * @return unmodifiable List of items in inventory
	 */
	public List<Item> getItems()
	{
		return Collections.unmodifiableList(this.items);
	}

	/**
	 * Removes Consumable item from inventory if it is used.
	 * @param item Consumable item to check for removal
	 */
	private void remIfUsed(Consumable item) throws IllegalArgumentException
	{
		if (!this.contains(item))
		{
			throw new IllegalArgumentException("Inventory does not contain Item.");
		}
		if (item.getUses() <= 0)
		{
			this.remove(item);
		}
	}

}