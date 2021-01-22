/**
 * Implements behavior for having a Controller
 */
public interface Controllable extends Positionable
{
	/**
	 * Sets Controller for object.
	 * @param controller Controller
	 */
	public abstract void setController(Controller controller);
	
	/**
	 * Gets Controller.
	 * @return Controller of object
	 */
	public abstract Controller getController();
}