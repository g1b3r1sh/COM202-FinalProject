/**
 * Represents a board, which is responsible for storing objects in specific positions on it.
 */
public abstract class Board
{
	/**
	 * Add an object to the board.
	 * @param o Positionable object to be added.
	 */
	public abstract void add(Positionable o);
	/**
	 * Remove an object from the board.
	 */
	public abstract void remove(Positionable o) throws IllegalArgumentException;
}