/**
 * Controls set of Controllable objects
 */
import java.util.ArrayList;

public abstract class Controller
{
	private ArrayList<Controllable> objects;
	private GridBoard board;

	/**
	 * Controller
	 * @param board Board with objects under control
	 */
	public Controller(GridBoard board)
	{
		this.board = board;
		this.objects = new ArrayList<>();
	}

	/**
	 * Moves all objects under control.
	 */
	public void moveObjects()
	{
		// Since objects can delete themselves from controller during iteration, copy of object list needs to be iterated through instead
		// https://stackoverflow.com/questions/10431981/remove-elements-from-collection-while-iterating
		ArrayList<Controllable> copy = new ArrayList<>(this.objects);
		for (Controllable o : copy)
		{
			if (this.board.hasObject(o))
			{
				// If need to move multiple times, can call this multiple times
				Point p = this.getMove(o, board);
				if (!this.board.moveObject(o.getPos(), p))
				{
					// Attacking should probably be a different method
					if (o instanceof CanAttack && this.board.getPositionable(p) instanceof Damageable)
					{
						((CanAttack) o).attack((Damageable) this.board.getPositionable(p));
						// Since objects independantly remove themselves from the board, they don't need to update board!
						// That also violates several programming principles like single responsibility but I don't know about them yet
						this.board.moveObject(o.getPos(), p);
					}
				}
			}
		}
	}

	/**
	 * Adds object to control.
	 * @param p Controllable object to be controlled
	 */
	public void add(Controllable p)
	{
		if (!this.contains(p))
		{
			this.objects.add(p);
			p.setController(this);
		}
	}

	/**
	 * Removes object from control.
	 * @param p Controllable object to remove from control
	 */
	public void remove(Controllable p) throws IllegalArgumentException
	{
		if (!this.contains(p))
		{
			throw new IllegalArgumentException("Controller does not contain object.");
		}
		this.objects.remove(p);
		p.setController(null);
	}

	/**
	 * Checks if object is under control.
	 * @param p Controllable object to check
	 * @return If p is under control
	 */
	public boolean contains(Controllable p)
	{
		return this.objects.contains(p);
	}

	/**
	 * Gets objects under control.
	 * @return ArrayList of Controllable objects under control
	 */
	public ArrayList<Controllable> getObjects()
	{
		return this.objects;
	}

	/**
	 * Removes all objects under control from game (ex. Used when Player quits game).
	 */
	public void destructor()
	{
		ArrayList<Controllable> copy = new ArrayList<>(this.objects);
		for (Controllable o : copy)
		{
			((GameObject) o).remove();
		}
	}

	/**
	 * Gets move for single object under control.
	 * @param o Controllable object to get move for
	 * @param board Board containing object
	 * @return Point for object to move to
	 */
	public abstract Point getMove(Controllable o, GridBoard board) throws IllegalArgumentException;
}