import java.util.ArrayList;

/**
 * Stores objects on board in 2D grid
 */
public class GridBoard extends Board
{
	// Grid is array of columns so accessing objects becomes grid.get(x).get(y)
	private ArrayList<ArrayList<Positionable>> grid;
	private int width;
	private int height;

	/**
	 * Constructor
	 * @param width Width of grid (x-axis)
	 * @param height Height of grid (y-axis)
	 */
	public GridBoard(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
		
		// Create grid
		this.grid = new ArrayList<>(width);
		for (int r = 0; r < width; r++)
		{
			this.grid.add(new ArrayList<>(height));
			for (int c = 0; c < height; c++)
			{
				this.grid.get(this.grid.size() - 1).add(null);
			}
		}
	}
	
	/**
	 * Move object on point to empty space.
	 * @param a Point to move from
	 * @param b Point with empty space
	 * @return If movement was successful (Point b was empty)
	 */
	public boolean moveObject(Point a, Point b) throws IndexOutOfBoundsException
	{
		if (!(this.onBoard(a) || this.onBoard(b)))
		{
			throw new IndexOutOfBoundsException("Point is not on board.");
		}
		if (a.equals(b))
		{
			return true;
		}
		else if (this.isEmpty(b))
		{
			this.setPoint(b, this.getPositionable(a));
			this.getPositionable(b).updatePos(b);
			this.setPoint(a, null);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Add object to board.
	 * @param o Positionable object to add to board
	 */
	public void add(Positionable o) throws IndexOutOfBoundsException
	{
		Point p = o.getPos();
		if (!this.onBoard(p))
		{
			throw new IndexOutOfBoundsException("Board does not contain object.");
		}
		if (this.getPositionable(p) == null)
		{
			this.grid.get(p.getX()).set(p.getY(), o);
		}
	}

	/**
	 * Remove object from board.
	 * @param o Positionable object to add to board
	 */
	public void remove(Positionable o) throws IllegalArgumentException
	{
		Point p = o.getPos();
		if (!this.hasObject(o))
		{
			throw new IllegalArgumentException("Board does not contain object.");
		}
		this.grid.get(p.getX()).set(p.getY(), null);
	}
	
	/**
	 * Get object from point.
	 * @param p Point to retrieve from
	 * @return Positionable object on that point
	 */
	public Positionable getPositionable(Point p) throws IndexOutOfBoundsException
	{
		if (!this.onBoard(p))
		{
			throw new IndexOutOfBoundsException("Board does not contain point.");
		}
		return this.grid.get(p.getX()).get(p.getY());
	}

	/**
	 * Check if point is empty.
	 * @param p Point to check
	 * @return If point is empty
	 */
	public boolean isEmpty(Point p)
	{
		return this.getPositionable(p) == null;
	}

	/**
	 * Check if Point in range of board.
	 * @param p Point to range-check
	 * @return If Point in range of board
	 */
	public boolean onBoard(Point p)
	{
		return  !(p == null) && (p.getX() >= 0 && p.getX() < width) && (p.getY() >= 0 && p.getY() < height);
	}

	/**
	 * Check if board contains object.
	 * @param o Positionable object to check for
	 * @return If object on board
	 */
	public boolean hasObject(Positionable o)
	{
		return getPositionable(o.getPos()) == o;
	}
	
	/**
	 * Get width of board.
	 * @return width
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Get height of board.
	 * @return height
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Place object on board.
	 * @param p Point to place on
	 * @param o Object to place
	 */
	private void setPoint(Point p, Positionable o) throws IndexOutOfBoundsException
	{
		if (!this.onBoard(p))
		{
			throw new IndexOutOfBoundsException("Board does not contain object.");
		}
		this.grid.get(p.getX()).set(p.getY(), o);
	}
}
