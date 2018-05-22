package gestin.remi.player;

public interface IPlaying {
	
	/**
	 * Make the attack against another player
	 * @param opponent : The opposing player
	 */
	public void attack(Player opponent);
	
	/**
	 * The player places a boat on his game board.
	 * @param size : the length of the ship
	 */
	public void placeShip(int size);
	
	/**
	 * @return true if the player has lost the game (ie: all his ships are destroyed)
	 */
	public boolean hasLost();
	
}
