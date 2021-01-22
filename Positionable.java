/**
 * Implements behavior for having a position on a GridBoard
 */
public interface Positionable
{
	/**
	 * Gets current position
	 * @return Point this object is on
	 */
	public Point getPos();
	
	/**
	 * Update current position
	 * @param p New position
	 */
	public void updatePos(Point p);
}
