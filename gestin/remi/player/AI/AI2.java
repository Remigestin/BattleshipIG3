package gestin.remi.player.AI;

import java.util.ArrayList;
import java.util.Stack;

import gestin.remi.config.Config;
import gestin.remi.coord.CoordCheck;
import gestin.remi.game.GameBoard;
import gestin.remi.player.Player;

public class AI2 extends AI {

	private ArrayList<String> shots;
	private Stack<String> stack;

	public AI2() {

		this.shots = new ArrayList<String>();


		this.stack = new Stack<String>();
		fillStack();
	}

	/**
	 * fill the stack of the AI2 with a grid cell on two
	 */
	private void fillStack() {
		StringBuffer tmp = new StringBuffer();
		int cpt = 0;

		for (int line = Config.minLine; line <= Config.maxLine; line++) {
			if(line%2==0) {
				cpt = 1;
			}else {
				cpt = 0;
			}
			
			for (int col = Config.minColumn; col <= Config.maxColumn; col++) {
				if (cpt %2 ==0) {
					tmp.append((char) col).append(line);
					this.stack.push(tmp.toString());
					tmp.delete(0, tmp.length());
				}
				cpt++;
			}
		}
	}
	
	
	/**
	 * Make the attack against another player
	 * @param opponent : The opposing player
	 */
	public void attack(Player opponent) {
		boolean ok = false;
		String coord = "";
		while (!ok) {
			coord = stack.pop();
			if (!this.shots.contains(coord)) {
				ok = true;
			}
		}
		boolean hit = false;
		try {
			hit = this.gameBoard.shot(opponent.getGameBoard(), coord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.shots.add(coord);
		if (hit) {
			StringBuffer tmp = new StringBuffer();
			int line = CoordCheck.getLine(coord);
			int col = CoordCheck.getColumn(coord);

			tmp.append((char)col).append(line+1);
			String coord_bas = tmp.toString();
			tmp.delete(0, tmp.length());

			tmp.append((char)(col-1)).append(line);
			String coord_gauche = tmp.toString();
			tmp.delete(0, tmp.length());

			tmp.append((char)(col+1)).append(line);
			String coord_droite = tmp.toString();
			tmp.delete(0, tmp.length());

			tmp.append((char)col).append(line-1);
			String coord_haute = tmp.toString();
			tmp.delete(0, tmp.length());

			if (CoordCheck.isCoordValid(coord_bas) && !this.shots.contains(coord_bas)) {
				this.stack.push(coord_bas);
			}

			if (CoordCheck.isCoordValid(coord_gauche) && !this.shots.contains(coord_gauche)) {
				this.stack.push(coord_gauche);
			}

			if (CoordCheck.isCoordValid(coord_droite) && !this.shots.contains(coord_droite)) {
				this.stack.push(coord_droite);
			}

			if (CoordCheck.isCoordValid(coord_haute) && !this.shots.contains(coord_haute)) {
				this.stack.push(coord_haute);
			}

		}
	}

}
