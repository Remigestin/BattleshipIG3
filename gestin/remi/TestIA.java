package gestin.remi;

import java.io.File;
import java.io.PrintWriter;

import gestin.remi.player.AI.AI;
import gestin.remi.player.AI.AI0;
import gestin.remi.player.AI.AI1;
import gestin.remi.player.AI.AI2;


public class TestIA {

	public static void main(String args[]) throws Exception {

		int ai0_victory = 0;
		int ai1_victory = 0;

		PrintWriter pw = new PrintWriter(new File("ai_proof.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("AI NAME");
		sb.append(",");
		sb.append("Score");
		sb.append(",");
		sb.append("AI NAME2");
		sb.append(",");
		sb.append("Score2");
		sb.append("\n");

		AI[] tab_AI = new AI[2];
		for(int match = 0; match <3; match++) {

			switch (match) {

			case 0: 
				tab_AI[0] = new AI0();
				tab_AI[1] = new AI1();
				break;


			case 1: 
				tab_AI[0] = new AI0();
				tab_AI[1] = new AI2();
				break;


			case 2: 
				tab_AI[0] = new AI1();
				tab_AI[1] = new AI2();
				break;

			}
			
			for (int i =0; i<100; i++) {


				AI ai0 = tab_AI[0];
				AI ai1 = tab_AI[1];

				int[] ship_size = {5,4,3,3,2};
				for (AI ai : tab_AI) {
					for (int size : ship_size) {
						ai.placeShip(size);
					}
				}

				boolean dead = false;


				int j = i%2; //permet de changer l'ordre de commencement a chaque nouvelle partie
				while(!dead) {

					if (j%2 ==0) {
						ai0.attack(ai1);
						if (ai1.hasLost()) {
							ai0_victory++;
							dead = true;
						}

					} else {
						ai1.attack(ai0);
						if (ai0.hasLost()) {
							ai1_victory++;
							dead = true;
						}
					}
					j++;
				}
				
				switch (match) {

				case 0: 
					tab_AI[0] = new AI0();
					tab_AI[1] = new AI1();
					break;


				case 1: 
					tab_AI[0] = new AI0();
					tab_AI[1] = new AI2();
					break;


				case 2: 
					tab_AI[0] = new AI1();
					tab_AI[1] = new AI2();
					break;

				}
			}
					
			switch (match) {

			case 0: 
				sb.append("AI Level Beginner");
				sb.append(",");
				sb.append(ai0_victory);
				sb.append(",");
				sb.append("AI Level Medium");
				sb.append(",");
				sb.append(ai1_victory);
				sb.append("\n");
				break;


			case 1: 
				sb.append("AI Level Beginner");
				sb.append(",");
				sb.append(ai0_victory);
				sb.append(",");
				sb.append("AI Level Hard");
				sb.append(",");
				sb.append(ai1_victory);
				sb.append("\n");
				break;


			case 2: 
				sb.append("AI Level Medium");
				sb.append(",");
				sb.append(ai0_victory);
				sb.append(",");
				sb.append("AI Level Hard");
				sb.append(",");
				sb.append(ai1_victory);
				sb.append("\n");
				break;

			}
			ai0_victory = 0;
			ai1_victory = 0;
		}
		pw.write(sb.toString());
		pw.close();
	}

}
