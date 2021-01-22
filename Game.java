/**
 * Represents game. Stores all parts of the game and controls game.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Game
{
	private GridBoard board;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<Controller> controllers;

	/**
	 * Constructor
	 * @param board Board of game
	 */
	public Game(GridBoard board)
	{
		this.board = board;
		this.gameObjects = new ArrayList<>();
		this.controllers = new ArrayList<>();
	}

	/**
	 * Get all Controllers to move.
	 */
	public void turn()
	{
		for (Controller c : this.controllers)
		{
			c.moveObjects();
		}
	}

	/**
	 * Link object to Controller.
	 * @param o Controllable object to link
	 * @param con Controller to link to
	 */
	public void linkToController(Controllable o, Controller con) throws IllegalArgumentException
	{
		if (!this.contains((GameObject) o))
		{
			throw new IllegalArgumentException("Game does not contain object.");
		}
		else if (!this.contains(con))
		{
			throw new IllegalArgumentException("Game does not contain Controller.");
		}
		con.add(o);
	}

	/**
	 * Add object to game.
	 * @param o Object to be added
	 */
	public void add(GameObject o)
	{
		if (!this.gameObjects.contains(o))
		{
			this.gameObjects.add(o);
			if (o instanceof Positionable)
			{
				this.board.add((Positionable) o);
			}
		}
	}

	/**
	 * Add object to game and link it to controller.
	 * @param o Object to be added and linked
	 * @param con Controller to link to
	 */
	public void add(Controllable o, Controller con) throws IllegalArgumentException
	{
		if (!this.contains(con))
		{
			throw new IllegalArgumentException("Game does not contain Controller.");
		}
		this.add((GameObject) o);
		this.linkToController(o, con);
	}

	/**
	 * Add item to game and link it to inventory of object.
	 * @param item Item to be added
	 * @param o Object with Inventory to add item to
	 */
	public void add(Item item, HasInventory o)
	{
		o.getInventory().add(item);
		this.add(item);
	}

	/**
	 * Add controller to game.
	 * @param con Controller to add
	 */
	public void add(Controller con)
	{
		if (!this.controllers.contains(con))
		{
			this.controllers.add(con);
		}
	}

	/**
	 * Remove object from game.
	 * @param o Object to be removed
	 */
	public void remove(GameObject o) throws IllegalArgumentException
	{
		if (!this.contains(o))
		{
			throw new IllegalArgumentException("Game does not contain object.");
		}
		this.gameObjects.remove(o);
		// Remove all references to object in all components of game
		if (o instanceof Positionable)
		{
			this.board.remove((Positionable) o);
		}
		if (o instanceof Controllable)
		{
			for (Controller c : this.controllers)
			{
				if (c.contains((Controllable) o))
				{
					c.remove((Controllable) o);
				}
			}
		}
	}

	/**
	 * Remove controller from game.
	 * @param con Controller to remove
	 */
	public void remove(Controller con) throws IllegalArgumentException
	{
		if (!this.contains(con))
		{
			throw new IllegalArgumentException("Game does not contain Controller.");
		}
		this.controllers.remove(con);
	}

	/**
	 * Check if game contains object.
	 * @param o Object to check game for
	 * @return If object is in game
	 */
	public boolean contains(GameObject o)
	{
		return this.gameObjects.contains(o);
	}

	/**
	 * Check if game contains Controller.
	 * @param c Controller to check game for
	 * @return If Controller is in game
	 */
	public boolean contains(Controller c)
	{
		return this.controllers.contains(c);
	}

	/**
	 * Get unmodifiable list of all objects in game.
	 * @return List of objects in game that is unmodifiable
	 */
	public List<GameObject> getObjects()
	{
		return Collections.unmodifiableList(this.gameObjects);
	}
}
