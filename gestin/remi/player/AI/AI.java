package gestin.remi.player.AI;

import gestin.remi.coord.CoordCheck;
import gestin.remi.game.GameBoard;
import gestin.remi.game.Ship;
import gestin.remi.player.Player;

public abstract class AI extends Player {

	
	public GameBoard getGameboard() {
		return this.gameBoard;
	}
	
	
	/**
	 * The player places a boat on his game board.
	 * @param size : the length of the ship
	 */
	public void placeShip(int taille) {

		boolean valid = false;
		String startCoord = "";
		String endCoord = "";
		
		
		

		while(!valid) {
			try {
				StringBuffer tmp = new StringBuffer();
				startCoord = CoordCheck.getRandomCoordinate();
				int vertical = (int)((Math.random() * ((1 - 0)+1)));
				if (vertical == 1) {
					
					tmp = tmp.append(CoordCheck.getColumn(startCoord)).append(CoordCheck.getLine(startCoord)+(taille-1));
					endCoord = tmp.toString();
					if (!CoordCheck.isCoordValid(endCoord)) {
						tmp.delete(0, tmp.length());
						tmp = tmp.append(CoordCheck.getColumn(startCoord)).append(CoordCheck.getLine(startCoord)-(taille-1));
						endCoord = tmp.toString();
					}
				}else {
					
					tmp.delete(0, tmp.length());
					tmp = tmp.append((char)(CoordCheck.getColumn(startCoord) + (taille - 1))).append(CoordCheck.getLine(startCoord));
					endCoord = tmp.toString();
					if (!CoordCheck.isCoordValid(endCoord)) {
						tmp.delete(0, tmp.length());
						tmp = tmp.append((char)(CoordCheck.getColumn(startCoord) - (taille - 1))).append(CoordCheck.getLine(startCoord));
						endCoord = tmp.toString();
					}
				}
				
				Ship boat = new Ship(startCoord, endCoord);
				this.gameBoard.addShip(boat, taille);
				valid = true;
			} catch (Exception e) {
				
			}
		}
		
		

	}
	
	

}
