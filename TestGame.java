import java.util.Scanner;

/**
 * Test game
 */
public class TestGame
{
	public static void main(String args[])
	{
		// Setup Objects
		Scanner kbReader = new Scanner(System.in);
		GridBoard board = new GridBoard(10, 10);

		PlayerController player = new PlayerController(board);
		TextInput textInput = new TextInput(player, kbReader);
		player.setInput(textInput);
		EnemyController enemy = new EnemyController(board);

		Game game = new Game(board);
		game.add(player);
		game.add(enemy);

		// Walls
		game.add(genericWall(game, new Point(0, 2)));
		game.add(genericWall(game, new Point(1, 2)));
		game.add(genericWall(game, new Point(2, 2)));
		game.add(genericWall(game, new Point(5, 0)));
		game.add(genericWall(game, new Point(5, 1)));
		game.add(genericWall(game, new Point(7, 2)));
		game.add(genericWall(game, new Point(7, 3)));
		game.add(genericWall(game, new Point(7, 4)));
		game.add(genericWall(game, new Point(1, 6)));
		game.add(genericWall(game, new Point(2, 6)));
		game.add(genericWall(game, new Point(3, 6)));
		game.add(genericWall(game, new Point(4, 6)));
		game.add(genericWall(game, new Point(3, 7)));
		game.add(genericWall(game, new Point(3, 8)));
		game.add(genericWall(game, new Point(6, 8)));
		game.add(genericWall(game, new Point(7, 8)));
		game.add(genericWall(game, new Point(7, 7)));
		game.add(genericWall(game, new Point(8, 7)));

		// Entities
		// Players
		Player player1 = new Player(game, "Player1", "The first one.", new Point(0, 1), 100, 10);
		Player player2 = new Player(game, "Player2", "The second one.", new Point(1, 0), 100, 10);
		game.add(player1, player);
		game.add(player2, player);
		game.add(new HealthItem(game, "Jelly", "A mysterious jelly.", 1, 15), player1);
		game.add(new HealthItem(game, "Jelly", "A mysterious jelly.", 1, 15), player2);
		game.add(new HealthItem(game, "Bandage", "Absorbs blood.", 3, 10), player1);
		game.add(new HealthItem(game, "Cake", "A large cake.", 1, 50), player2);
		game.add(new HealthItem(game, "Spray", "Mends light cuts.", 5, 8), player1);
		game.add(new HealthItem(game, "Chocolate", "Sugar boost.", 3, 10), player2);
		game.add(new HealthItem(game, "Med-kit", "Emergency use.", 1, 70), player1);
		game.add(new HealthItem(game, "Painkillers", "Take the edge off.", 10, 4), player2);
		
		// Enemies
		game.add(genericZombie(game, new Point(3, 1)), enemy);
		game.add(genericZombie(game, new Point(8, 1)), enemy);
		game.add(genericZombie(game, new Point(9, 3)), enemy);
		game.add(genericZombie(game, new Point(2, 4)), enemy);
		game.add(genericZombie(game, new Point(6, 6)), enemy);
		game.add(genericZombie(game, new Point(8, 1)), enemy);
		game.add(genericZombie(game, new Point(0, 9)), enemy);
		game.add(genericZombie(game, new Point(2, 9)), enemy);
		game.add(genericZombie(game, new Point(9, 9)), enemy);

		// Start game
		System.out.println("Destroy all the enemies before they destroy you.");
		System.out.println("Enter 'help' for help.");
		while (true)
		{
			game.turn();
			// Check win/loss conditions (check if controllers are empty)
			if (player.getObjects().size() == 0)
			{
				System.out.println("You Lose!");
				break;
			}
			if (enemy.getObjects().size() == 0)
			{
				System.out.println("You Win!");
				break;
			}
		}

		kbReader.close();
	}

	/**
	 * Creates and returns generic zombie
	 * @param game Game object will go to
	 * @param p Initial point of object
	 * @return Generic zombie
	 */
	public static Combatant genericZombie(Game game, Point p)
	{
		return new Combatant(game, "Zombie", "A zombie.", p, 50, 5);
	}

	/**
	 * Creates and returns generic wall
	 * @param game Game object will go to
	 * @param p Initial point of object
	 * @return Generic wall
	 */
	public static Obstacle genericWall(Game game, Point p)
	{
		return new Obstacle(game, "Wall", "It stands imposingly in your path.", p);
	}
}
