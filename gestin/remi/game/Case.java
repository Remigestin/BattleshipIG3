/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestin.remi.game;

/**
 *
 * @author gesti
 */
public class Case {
    
    private String coord;
    private Boolean touche;
    
    /**
     * 
     * @param coo : the coordinate of the case
     */
    public Case (String coo) {
        this.coord = coo;
        this.touche = false;
    }
    
    /**
     * pass the case to touch
     */
    public void touche() {
        this.touche = true;
    }
    
    /**
     * 
     * @return the coord of the case
     */
    public String getCoord() {
        return this.coord;
    }
    
    /**
     * 
     * @return if the case is hit
     */
    public Boolean getTouche() {
        return this.touche;
    }
}
