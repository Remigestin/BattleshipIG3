/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestin.remi;

import java.util.Scanner;

import gestin.remi.player.Player;
import gestin.remi.player.AI.AI0;
import gestin.remi.player.AI.AI1;
import gestin.remi.player.AI.AI2;
import gestin.remi.player.human.Human;

/**
 *
 * @author gesti
 */
public class BattleShip {


	public static void main(String[] args) {



		Player p1 = new Human();
		Player p2 = new Human();

		boolean replay = true;

		while (replay) {

			Scanner sc = new Scanner(System.in);
			boolean valid = false;
			while(!valid) {
				System.out.println("Please chose your game mode : \n 1- Human vs Human \n 2- Human vs AI\n");

				String choice = sc.nextLine();

				if (choice.equals("1")) {

					valid = true;

				} else if (choice.equals("2")) {

					System.out.println("Please chose your difficulty : \n 0- Beginer  \n 1- Medium \n 2- Hard\n");
					String choiceLvl = sc.nextLine();

					if (choiceLvl.equals("0")) {
						p2 = new AI0();
						valid = true;
					}
					else if (choiceLvl.equals("1")) {
						p2 = new AI1();
						valid = true;
					}
					else if (choiceLvl.equals("2")) {
						p2 = new AI2();
						valid = true;
					}
					else {
						System.out.println("Incorrect choice, please retry");
					}



				} else {
					System.out.println("Incorrect choice, please retry");
				}
			}
			
			//the size of all the ships for each player
			int[] ship_size = {5,4,3,3,2};

			Player[] tab_player = {p1,p2};

			// setting up each player's ship
			int numPlayer = 1;
			for (Player current : tab_player ) {
				System.out.println("Player " + numPlayer + " it's your turn !\n");
				for (int size : ship_size) {
					current.placeShip(size);
				}
				numPlayer++;
			}


			//begin of the game
			Player current;
			Player opponent;
			String coord;
			boolean fin = false;
			int i = 0;
			while (!fin) {
				current = tab_player[i%2];
				opponent = tab_player[((i+1)%2)];
				numPlayer = (i%2)+1;

				if (current instanceof Human) {
					System.out.println("Player " + numPlayer + " it's your turn !\n");
					System.out.println("Player " + numPlayer + " this is your fleet : \n");
					System.out.println(current.getGameBoard().showFleet());

					System.out.println("Player " + numPlayer + " this is your missile grid : \n");
					System.out.println(current.getGameBoard().showShots());
				}

				current.attack(opponent);

				if (opponent.hasLost()) {
					fin = true;
					System.out.println(current.getGameBoard().showShots());
					System.out.println(" Opponent's fleet destroyed !\n Congratulations Player " + numPlayer + " You won !\n");
				}


				i++;
			}
			
			System.out.println("Do you want to retry a game ? \n 1- Yes \n 2- No\n ");
			String choiceRetry = sc.nextLine();
			if (choiceRetry.equals("2")) {
				replay = false;
			}
		}
	}
}
