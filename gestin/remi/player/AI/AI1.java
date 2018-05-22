package gestin.remi.player.AI;

import java.util.ArrayList;

import gestin.remi.coord.CoordCheck;
import gestin.remi.player.Player;

public class AI1 extends AI {

	private ArrayList<String> shots;
	
	public AI1( ) {
		this.shots = new ArrayList<String>();
	}
	
	

	/**
	 * Make the attack against another player
	 * @param opponent : The opposing player
	 */
	public void attack(Player opponent) {
		
		boolean ok = false;
		String coord = "";
		

		while(!ok) {
			coord = CoordCheck.getRandomCoordinate();
			if (!this.shots.contains(coord)) {
				ok = true;
				this.shots.add(coord);
			}

		}
		try {
			this.gameBoard.shot(opponent.getGameBoard(), coord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
