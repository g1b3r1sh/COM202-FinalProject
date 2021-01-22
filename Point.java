/**
 * Point on 2D grid
 */
public class Point
{
	private final int x;
	private final int y;

	/**
	 * Constructor
	 * @param x X-coord of point
	 * @param y Y-coord of point
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns if Point is the same as another point.
	 * @param o Object to compare to
	 * @return If point has same coordinates as this point
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Point)
		{
			Point p = (Point) o;
			return this.x == p.getX() && this.y == p.getY();
		}
		else
		{
			return false;
		}
	}

	/**
	 * Gets x-coord.
	 * @return X-coord
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Gets y-coord.
	 * @return Y-coord
	 */
	public int getY()
	{
		return this.y;
	}
}