package gestin.remi.player.AI;

import gestin.remi.coord.CoordCheck;
import gestin.remi.game.GameBoard;
import gestin.remi.player.Player;


public class AI0 extends AI {
	
	
	/**
	 * Make the attack against another player
	 * @param opponent : The opposing player
	 */
	public void attack(Player opponent) {
	
		String coord =  CoordCheck.getRandomCoordinate();
		try {
			this.gameBoard.shot(opponent.getGameBoard(), coord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
