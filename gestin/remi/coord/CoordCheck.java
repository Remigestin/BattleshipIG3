package gestin.remi.coord;

import gestin.remi.config.Config;

public class CoordCheck {
	
	/**
	 * 
	 * @param coord : the coordinate that we want to evaluate
	 * @return true if the coordinate is is syntactically correct
	 */
    public static boolean isCoordValid(String coord) {
       boolean result = true;
       int column = (int) coord.charAt(0);
       int line = 0;
       if (coord.length() == 2) {
    	   line = Integer.parseInt(new StringBuilder().append(coord.charAt(1)).toString());
       }
       else if (coord.length() == 3){
    	   line = Integer.parseInt(new StringBuilder().append(coord.charAt(1)).append(coord.charAt(2)).toString());
       }
       else {
    	   result = false;
       }
       
       if (column < (int) Config.minColumn || column > (int) Config.maxColumn || line < Config.minLine || line > Config.maxLine){
    	   result = false;
       }
       
       return result;
       
    }
    
    
  /**
   * 
   * @param startCoord : the start coordinate of the ship
   * @param endCoord : the end coordinate of the ship
   * @return true if the ship is placed horizontally
   */
  	public static boolean isHorizontal(String startCoord, String endCoord) {
  		boolean result = false;

  		if (startCoord.charAt(1) == endCoord.charAt(1) && startCoord.length() == endCoord.length()) {
  			result = true;
  		}

  		return result;
  	}
  	
  	/**
     * 
     * @param startCoord : the start coordinate of the ship
     * @param endCoord : the end coordinate of the ship
     * @return true if the ship is placed vertically
     */
  	public static boolean isVertical(String startCoord, String endCoord) {
  		boolean result = false;

  		if (startCoord.charAt(0) == endCoord.charAt(0)) {
  			result = true;
  		}

  		return result;
  	}
  	
  	/**
  	 * 
  	 * @return a random coordinate on the grid
  	 */
  	public static String getRandomCoordinate() {
  		char column = (char) ((Math.random() * ((Config.maxColumn - Config.minColumn)+1)) + Config.minColumn);
		int line = (int)((Math.random() * ((Config.maxLine - Config.minLine)+1)) + Config.minLine);
		
		StringBuffer coord = new StringBuffer();
		coord = coord.append(column).append(line);
		return coord.toString();
  	}
  	
  	/**
  	 * 
  	 * @param Coord : the coordinate we want the column
  	 * @return the column of a coordinate
  	 */
  	public static char getColumn(String Coord) {
  		return Coord.charAt(0);
  	}
  	
  	/**
  	 * 
  	 * @param Coord : the coordinate we want the line
  	 * @return the line of a coordinate
  	 */
  	public static int getLine(String Coord) {
  		int line = 0;
  		if (Coord.length() == 2){
			line = Integer.parseInt(new StringBuilder().append(Coord.charAt(1)).toString());
		}
		else if (Coord.length() == 3){
			line = Integer.parseInt(new StringBuilder().append(Coord.charAt(1)).append(Coord.charAt(2)).toString());
		}
  		return line;
  	}

}
