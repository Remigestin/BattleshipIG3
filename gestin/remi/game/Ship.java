/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestin.remi.game;

import java.util.ArrayList;

import gestin.remi.coord.CoordCheck;

/**
 *
 * @author gesti
 */
public class Ship {

	private ArrayList<Case> coords;

/**
 * 
 * @param startCoord : the start coord of the ship
 * @param endCoord : the end coord of the ship 
 * @throws Exception if the boat is placed diagonally
 * @throws Exception if the boat has invalidate coordinates
 */
	public Ship (String startCoord, String endCoord) throws Exception {
		if (CoordCheck.isCoordValid(startCoord) && CoordCheck.isCoordValid(endCoord)) {
			this.coords = new ArrayList<Case>();


			int startLine = 0;
			int endLine = 0;
			char startColumn;
			char endColumn;

			startColumn = CoordCheck.getColumn(startCoord);
			endColumn =CoordCheck.getColumn(endCoord);
			
			startLine = CoordCheck.getLine(startCoord);
			endLine = CoordCheck.getLine(endCoord);

			
			if ((int) startColumn > (int) endColumn) {
				char tmp = startColumn;
				startColumn = endColumn;
				endColumn = tmp;
			}
			
			if (startLine > endLine) {
				int tmp = startLine;
				startLine = endLine;
				endLine = tmp;
			}

			if (CoordCheck.isHorizontal(startCoord, endCoord)) {

				int line = startLine;

				for (int i = (int) startColumn; i <= (int) endColumn; i++) {
					char column = (char) i;

					StringBuffer sb = new StringBuffer();
					sb = sb.append(column).append(line);
					Case c = new Case(sb.toString());
					this.coords.add(c);
				}
			} else if(CoordCheck.isVertical(startCoord, endCoord)) {

				char column = startColumn;

				for (int i = startLine; i <= endLine; i++) {
					int line = i;
					StringBuffer sb = new StringBuffer();
					sb = sb.append(column).append(line);
					Case c = new Case(sb.toString());
					this.coords.add(c);
				}
			}
			else {
				throw new Exception("You can not place the boat diagonally");
			}
		} else {
			throw new Exception("Invalid coordinates");
		}
	}


	public ArrayList<Case> getCoords() {
		return this.coords;
	}

	public int getSize() {
		return this.coords.size();
	}


	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the ship is on the coord
	 */
	public boolean isOn(String coord) {
		boolean result = false;
		for (Case c : this.coords) {
			if (c.getCoord().equals(coord)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param missileCoord : the coordinate of the missile
	 * @return true if the ship is hit by the missile
	 */
	public boolean isHit(String missileCoord) {
		boolean result = false;
		for (Case c : this.coords) {
			if (c.getCoord().equals(missileCoord)) {
				if (c.getTouche()) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Do a shot on the ship on the coord
	 * @param missileCoord : the coordinate of the missile
	 */
	public void hit(String missileCoord) {  
		for (Case c : this.coords) {
			if (c.getCoord().equals(missileCoord)) {
				c.touche();
			}
		}

	}

	/**
	 * 
	 * @return true if the boat is destroyed(ie : all his case are hit)
	 */
	public boolean isDestroyed() {
		boolean result = true;

		for (Case c : this.coords) {
			if (!(c.getTouche())) {
				result = false;
			}
		}
		return result;
	}






}
