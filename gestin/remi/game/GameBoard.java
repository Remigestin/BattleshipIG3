/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestin.remi.game;

import java.util.ArrayList;

import gestin.remi.config.Config;
import gestin.remi.coord.CoordCheck;

/**
 *
 * @author gestin
 */
public class GameBoard {

	ArrayList<Ship> fleet;
	ArrayList<Case> shots;

	public GameBoard() {
		this.fleet = new ArrayList<Ship>();
		this.shots = new ArrayList<Case>();
	}

	/**
	 * Add a ship to the gameboard's fleet
	 * @param boat : the ship to add
	 * @param size : the size of the ship to add
	 * @throws Exception if the size of the ship is not equal to the param size
	 * @throws Exception if the ship is on a coordinate already taken
	 */
	public void addShip(Ship boat, int size) throws Exception{


		if (size == boat.getSize()) {
			if (!(this.isOnAnotherShip(boat))) {
				this.fleet.add(boat);
			} else {
				throw new Exception("Coordinates already taken");
			}
		} else {
			throw new Exception("the length of the ship is not correct");
		}
	}


	/**
	 * Shot the opponent player on the coord, return true if the missile hit him
	 * @param opponent : the gameboard of the opponent player
	 * @param coord : the coordinate's target
	 * @return true if the missile hit the opponent's gameboard
	 * @throws Exception if the coordinate is syntactically incorrect
	 */
	public boolean shot(GameBoard opponent, String coord) throws Exception {
		boolean result = false;
		if(CoordCheck.isCoordValid(coord)) {
			if (!(this.hasAlreadyShoot(coord))) {
				Case c = new Case(coord);
				this.shots.add(c);
				if(opponent.hit(coord)) {
					c.touche();
					result = true;
				}
			}

		} else {
			throw new Exception("Invalid coordinates");
		}

		return result;

	}

	/**
	 * Hit the gameboard on the coord, return true if the missile hit
	 * @param coord : the coordinate's target
	 * @return true if the missile hit
	 * @throws Exception if the coordinate is syntactically incorrect
	 */
	private boolean hit(String coord) throws Exception {
		boolean result = false;
		if(CoordCheck.isCoordValid(coord)) {
			for (Ship boat : this.fleet) {
				boat.hit(coord);
				if (boat.isHit(coord)) {
					result = true;
				}
			}
		} else {
			throw new Exception("Invalid coordinates");
		}
		return result;
	}

	/**
	 * 
	 * @param bat : the ship whose coordinates are compared
	 * @return true if the ship has coordinates taken by another ship of the player
	 */
	public Boolean isOnAnotherShip(Ship bat) {
		boolean result = false;
		for (Ship s : this.fleet) {
			ArrayList<Case> coords = s.getCoords();
			for (Case c : coords) {
				if (bat.isOn(c.getCoord())) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @return true if all the ships of the gameboard's fleet are destroyed
	 */
	public Boolean isDestroyed() {
		boolean result = true;
		for (Ship s : this.fleet) {
			if (s.isDestroyed() == false) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the gameboard's fleet has a ship destroyed on the coordinate
	 * @throws Exception if the coordinate is syntactically incorrect
	 */
	public boolean hasShipDestroyedOn(String coord) throws Exception {
		boolean result = false;

		if(CoordCheck.isCoordValid(coord)) {
			for (Ship s : this.fleet) {
				if (s.isOn(coord) && s.isDestroyed()) {
					result = true;
				}
			}
		} else {
			throw new Exception("Invalid coordinates");
		}

		return result;
	}



	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the player has already shoot on the coordinate
	 */
	public boolean hasAlreadyShoot(String coord) {
		boolean result = false;

		for (Case s : this.shots) {
			if (s.getCoord().equals(coord)) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the player has been hit on the coord
	 */
	public boolean isHitOn(String coord) {
		boolean result = false;

		for (Ship boat : this.fleet) {
			if (boat.isHit(coord)){
				result = true;
			}
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the player has hit the other player on the coord
	 */
	public boolean hasHitOn(String coord) {
		boolean result = false;

		for (Case c : this.shots) {
			if (c.getCoord().equals(coord)) {
				if(c.getTouche()) {
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the player has shot the other player on the coord
	 */
	public boolean hasShotOn(String coord) {

		boolean result = false;

		for (Case c : this.shots) {
			if (c.getCoord().equals(coord)) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * 
	 * @param coord : the coordinate to evaluate
	 * @return true if the player has a ship on the coord
	 */
	public boolean hasShipOn(String coord) {
		boolean result = false;

		for (Ship boat : this.fleet) {
			if (boat.isOn(coord)) {
				result = true;
			}
		}

		return result;
	}



	/**
	 * 
	 * @return the print of all the shots made by the player through the game board
	 */
	public String showShots() {
		String result = "   ";
		StringBuffer colonnes = new StringBuffer();

		//print the first line which represent the name of the columns
		for (int col = Config.minColumn; col <= Config.maxColumn; col++) {
			colonnes = colonnes.append((char) col).append(" ");
		}
		result += colonnes.toString();
		result += "\n";

		for (int line = Config.minLine; line <= Config.maxLine; line++) {

			if (line < 10) {
				result += " ";
			}
			result += line;
			for (int col = Config.minColumn; col <= Config.maxColumn; col++) {
				StringBuffer coord = new StringBuffer();
				coord = coord.append((char)col).append(line);

				if (this.hasHitOn(coord.toString())) {
					result += " o";
				}
				else if (this.hasShotOn(coord.toString())) {
					result += " x";
				}
				else {
					result += " .";
				}
			}
			result +="\n";

		}

		return result;
	}


	/**
	 * 
	 * @return the print of the gameboard's fleet
	 */
	public String showFleet() {
		String result = "   ";
		StringBuffer colonnes = new StringBuffer();

		//print the first line which represent the name of the columns
		for (int col = Config.minColumn; col <= Config.maxColumn; col++) {
			colonnes = colonnes.append((char) col).append(" ");
		}
		result += colonnes.toString();
		result += "\n";

		for (int line = Config.minLine; line <= Config.maxLine; line++) {

			if (line < 10) {
				result += " ";
			}
			result += line;
			for (int col = Config.minColumn; col <= Config.maxColumn; col++) {
				StringBuffer coord = new StringBuffer();
				coord = coord.append((char)col).append(line);

				if (this.isHitOn(coord.toString())) {
					result += " x";
				}
				else if (this.hasShipOn(coord.toString())) {
					result += " o";
				}
				else {
					result += " .";
				}
			}
			result +="\n";

		}

		return result;
	}
} 
