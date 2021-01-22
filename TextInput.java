import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.lang.StringBuilder;

/**
 * Gets user input through console
 */
public class TextInput extends PlayerInput
{
	private Scanner kbReader;
	private HashMap<String, Character> symbols;

	/**
	 * Constructor
	 * @param controller Controller input is passed to
	 * @param kbReader Scanner to read input from
	 */
	public TextInput(Controller controller, Scanner kbReader)
	{
		super(controller);
		this.kbReader = kbReader;
		this.symbols = new HashMap<>();
		// Add symbols
		this.symbols.put("Player1", '1');
		this.symbols.put("Player2", '2');
		this.symbols.put("Zombie", 'Z');
		this.symbols.put("Wall", 'W');
	}

	/**
	 * Takes input from scanner and splits into words.
	 * @return Array of split words
	 */
	public String[] getInput()
	{
		System.out.print("Enter command: ");
		return this.kbReader.nextLine().split(" ");
	}

	/**
	 * Takes string and returns number representing direction.
	 * @param s String representing direction
	 * @return Number representing direction or -1 if string is not a direction
	 */
	private int resolveDirection(String s)
	{
		switch (s)
		{
			case "northwest":
			case "nw":
				return 0;
			case "north":
			case "n":
				return 1;
			case "northeast":
			case "ne":
				return 2;
			case "west":
			case "w":
				return 3;
			case "east":
			case "e":
				return 5;
			case "southwest":
			case "sw":
				return 6;
			case "south":
			case "s":
				return 7;
			case "southeast":
			case "se":
				return 8;
		}
		return -1;
	}

	/**
	 * Returns point that direction moves to from point.
	 * @param p Point to move from
	 * @param dir Direction to move in
	 * @return Point moved to
	 */
	private Point dirPoint(Point p, int dir) throws IllegalArgumentException
	{
		switch (dir)
		{
			case 0:
				return new Point(p.getX() - 1, p.getY() - 1);
			case 1:
				return new Point(p.getX(), p.getY() - 1);
			case 2:
				return new Point(p.getX() + 1, p.getY() - 1);
			case 3:
				return new Point(p.getX() - 1, p.getY());
			case 5:
				return new Point(p.getX() + 1, p.getY());
			case 6:
				return new Point(p.getX() - 1, p.getY() + 1);
			case 7:
				return new Point(p.getX(), p.getY() + 1);
			case 8:
				return new Point(p.getX() + 1, p.getY() + 1);
			default:
				throw new IllegalArgumentException("Direction does not exist.");
		}
	}

	/**
	 * Request point to move object to
	 * @param o Object to move
	 * @param board Board containing object
	 * @return Point that input moves to
	 */
	public Point requestMove(Controllable o, GridBoard board)
	{
		while (true)
		{
			String[] input = this.getInput();
			try
			{
				switch(input[0].toLowerCase())
				{
					case "help":
						this.comHelp(input);
						break;
					case "move":
						return this.comMove(input, o.getPos());
					case "pass":
						return this.comPass(input, o.getPos());
					case "check":
						this.comCheck(input, board, o);
						break;
					case "legend":
						this.comLegend(input);
						break;
					case "use":
						this.comUse(input, o);
						break;
					case "quit":
					case "q":
						return this.comQuit(input, o.getPos());
					default:
						throw new IllegalArgumentException("Invalid command.");
				}
			}
			catch (IllegalArgumentException e)
			{
				this.handleError("invalid");
			}
		}
	}

	/**
	 * Handles error/pass messages to console in general.
	 * @param id Error id
	 */
	public void handleError(String id) throws IllegalArgumentException
	{
		switch (id)
		{
			case "blocked":
				System.out.println("The way is blocked!");
				break;
			case "invalid":
				System.out.println("Invalid input!");
				break;
			default:
				throw new IllegalArgumentException("Invalid error: " + id);
		}
	}

	/**
	 * Returns String representation of board.
	 * @param board Board to convert
	 * @return String representation of board
	 */
	public String drawBoard(GridBoard board)
	{
		StringBuilder stringBoard = new StringBuilder(board.getHeight() * board.getWidth() + board.getHeight());
		for (int y = 0; y < board.getHeight(); y++)
		{
			for (int x = 0; x < board.getWidth(); x++)
			{
				GameObject o = (GameObject) board.getPositionable(new Point(x, y));
				if (o == null)
				{
					stringBoard.append('.');
				}
				else if (this.symbols.containsKey(o.getName()))
				{
					stringBoard.append(this.symbols.get(o.getName()));
				}
				else
				{
					stringBoard.append('□');
				}
			}
			stringBoard.append('\n');
		}
		return stringBoard.toString();
	}

	/**
	 * Returns String representation of description of object
	 * @param o Object to describe
	 * @return String representation of description of object
	 */
	public String describeGameObject(GameObject o)
	{
		StringBuilder str = new StringBuilder();
		str.append(o.getName());
		if (o instanceof Damageable)
		{
			str.append("|Health: ").append(((Damageable) o).getHealth()).append("|");
		}
		if (o instanceof CanAttack)
		{
			str.append("|Damage: ").append(((CanAttack) o).getDamage()).append("|");
		}
		if (o instanceof Consumable)
		{
			str.append("|Uses: ").append(((Consumable) o).getUses()).append("|");
		}
		if (o instanceof HealthItem)
		{
			str.append("|Restores: ").append(((HealthItem) o).getRestore()).append("|");
		}
		str.append(": \n").append(o.getDescription());
		return str.toString();
	}

	/**
	 * Returns String representation of Inventory
	 * @param inv Inventory to represent
	 * @return String representation of Inventory
	 */
	public String viewInventory(Inventory inv)
	{
		StringBuilder str = new StringBuilder();
		List<Item> items = inv.getItems();
		str.append("{");
		if (items.size() > 0)
		{
			str.append(items.get(0).getName());
			for (int i = 1; i < items.size(); i++)
			{
				str.append(", ").append(items.get(i).getName());
			}
		}
		str.append("}");
		return str.toString();
	}

	/**
	 * Called when initially requesting move from user
	 * @param o Object to request move for
	 * @param board Board containing object
	 */
	public void initial(Controllable o, GridBoard board)
	{
		System.out.print(this.drawBoard(board));
		System.out.println(this.describeGameObject((GameObject) o));
	}

	/**
	 * Prints output for "help" command to console
	 * @param args Arguments to command
	 */
	private void comHelp(String[] args) throws IllegalArgumentException
	{		
		if (args.length == 1)
		{
			System.out.println("The eight directions are north(n), south(s), east(e), west(w), northwest(nw), northeast(ne), southwest(sw), southeast(se).");
			System.out.println("To attack an enemy, attempt to move onto their space.");
			System.out.println("Commands: move, pass, check, legend, use, quit");
			System.out.println("Use help [command] for more information about that command.");
		}
		else if (args.length == 2)
		{
			switch (args[1])
			{
				case "move":
					System.out.println("move [direction]: Move in any direction. Ends turn.");
					break;
				case "pass":
					System.out.println("pass: Don't move. Ends turn.");
					break;
				case "check":
					System.out.println("check [direction]: Get description of object in that direction.");
					System.out.println("check self: Get description of self.");
					System.out.println("check inventory: Get list of items in inventory.");
					System.out.println("check inventory [item]: Get description of item in inventory.");
					System.out.println("check board: Draw board again.");
					break;
				case "legend":
					System.out.println("legend: View legend of what symbols represent.");
				case "use":
					System.out.println("use inventory [item]: Use item in inventory.");
					break;
				case "quit":
					System.out.println("quit/q: Exit game.");
					break;
				default:
					throw new IllegalArgumentException("Invalid command.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}

	/**
	 * Prints output for "move" command to console
	 * @param args Arguments to command
	 * @param p Position of moving object
	 * @return Resulting Point of object
	 */
	private Point comMove(String[] args, Point p) throws IllegalArgumentException
	{
		if (args.length == 2)
		{
			return this.dirPoint(p, this.resolveDirection(args[1]));
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}
	
	/**
	 * Prints output for "pass" command to console
	 * @param args Arguments to command
	 * @param p Position of moving object
	 * @return Resulting Point of object
	 */
	private Point comPass(String[] args, Point p) throws IllegalArgumentException
	{
		if (args.length == 1)
		{
			return p;
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}

	/**
	 * Prints output for "check" command to console
	 * @param args Arguments to command
	 * @param board Board containing checking object
	 * @param o Checking object
	 */
	private void comCheck(String[] args, GridBoard board, Controllable o) throws IllegalArgumentException
	{
		Point p = o.getPos();
		if (args.length == 2)
		{
			if (this.resolveDirection(args[1]) != -1)
			{
				Point dirP = this.dirPoint(p, this.resolveDirection(args[1]));
				if (board.onBoard(dirP))
				{
					GameObject dirObject = (GameObject) board.getPositionable(dirP);
					if (dirObject == null)
					{
						System.out.println("An empty tile.");
					}
					else
					{
						System.out.println(this.describeGameObject(dirObject));
					}
				}
				else
				{
					throw new IllegalArgumentException("Invalid command.");
				}
			}
			else if (args[1].equalsIgnoreCase("self"))
			{
				System.out.println(this.describeGameObject((GameObject) o));
			}
			else if (args[1].equalsIgnoreCase("inventory") && o instanceof HasInventory)
			{
				System.out.println(this.viewInventory(((HasInventory) o).getInventory()));
			}
			else if (args[1].equalsIgnoreCase("board"))
			{
				System.out.println(this.drawBoard(board));
			}
			else
			{
				throw new IllegalArgumentException("Invalid command.");
			}
		}
		else if (args.length == 3)
		{
			if (args[1].equalsIgnoreCase("inventory") && o instanceof HasInventory)
			{
				Item i = ((HasInventory) o).getInventory().getItem(args[2]);
				if (i != null)
				{
					System.out.println(this.describeGameObject(i));
				}
				else
				{
					throw new IllegalArgumentException("Invalid command.");
				}
			}
			else
			{
				throw new IllegalArgumentException("Invalid command.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}

	/**
	 * Prints output for "legend" command to console
	 * @param args Arguments to command
	 */
	private void comLegend(String[] args) throws IllegalArgumentException
	{
		if (args.length == 1)
		{
			System.out.println("Legend: ");
			// https://stackoverflow.com/questions/16246821/how-to-get-values-and-keys-from-hashmap
			System.out.println(".: Empty Space");
			this.symbols.forEach((String name, Character symbol) -> {System.out.printf("%c: %s\n", symbol, name);});
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}

	/**
	 * Prints output for "use" coommand to console
	 * @param args Arguments to command
	 * @param p Current object
	 */
	private void comUse(String[] args, Controllable o) throws IllegalArgumentException
	{
		if (args.length == 3 && args[1].equalsIgnoreCase("inventory"))
		{
			if (o instanceof HasInventory)
			{
				Inventory inventory = ((HasInventory) o).getInventory();
				Item i = inventory.getItem(args[2]);
				inventory.useItem(i, (GameObject) o);
				System.out.println(this.describeGameObject((GameObject) o));
			}
			else
			{
				throw new IllegalArgumentException("Invalid command.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}

	/**
	 * Prints output for "quit" command to console
	 * @param args Arguments to command
	 * @param p Position of current object
	 * @return Position of current object
	 */
	private Point comQuit(String[] args, Point p) throws IllegalArgumentException
	{
		if (args.length == 1)
		{
			super.destructController();
			return p;
		}
		else
		{
			throw new IllegalArgumentException("Invalid command.");
		}
	}
}
