package gestin.remi.player.human;


import java.util.Scanner;

import gestin.remi.game.Ship;
import gestin.remi.player.Player;

public class Human extends Player {
	
	/**
	 * Make the attack against another player
	 * @param opponent : The opposing player
	 */
	public void attack(Player opponent) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the coordinates of your target :  \n");
				String coord = sc.nextLine();
				boolean hit = this.gameBoard.shot(opponent.getGameBoard(), coord);
				if (hit) {
					System.out.println("Hit !\n");
					if (opponent.getGameBoard().hasShipDestroyedOn(coord)) {
						System.out.println("Ship destroyed !\n");
					}
				}
				else {
					System.out.println("You missed !\n");
				}
				valid = true;
			}
			catch (Exception e) {
				System.out.println(e.getMessage()); 
			}
			
		}
	}
	
	/**
	 * The player places a boat on his game board.
	 * @param size : the length of the ship
	 */
	public void placeShip(int size) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String startCoord;
		String endCoord;
		
		while (!valid) {
			try {
				
				System.out.println("Please enter the start coordinates of the size " + size + " ship");
				startCoord = sc.nextLine();
				System.out.println("Please enter the end coordinates of the size " + size + " ship");
				endCoord = sc.nextLine();
				Ship boat = new Ship(startCoord, endCoord);
				this.gameBoard.addShip(boat, size);
				valid = true;
				System.out.println(this.gameBoard.showFleet());
			} 
			catch (Exception e) {
				System.out.println(e.getMessage()); 
			}
		}
		
		
		
	}
	
	

}
