package gestin.remi.player;

import gestin.remi.game.GameBoard;

public abstract class Player implements IPlaying {
	
	protected GameBoard gameBoard;
	
	public Player() {
		this.gameBoard = new GameBoard();
	}
	
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}
	
	/**
	 * @return true if the player has lost the game (ie: all his ships are destroyed)
	 */
	public boolean hasLost() {
		return this.gameBoard.isDestroyed();
	}
	
	
}
