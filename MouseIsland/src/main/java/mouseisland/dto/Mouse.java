/*
Created by: Margaret Donin
Date created:
Date revised:
*/

package mouseisland.dto;

public class Mouse {
    private int roundsLeft;
    private final int rounds;
    private int escapes;
    private int eatenByCat;
    private int drowned;
    private int starved;
    private int untilStarved;
    private int initialI;
    private int initialJ;
    private int i;
    private int j;

    public Mouse(int rounds) {
        this.rounds = rounds;
        this.roundsLeft = rounds;
        this.untilStarved = 100;
        this.escapes = 0;
        this.eatenByCat = 0;
        this.drowned = 0;
        this.starved = 0;
    }
    
    public void setInitial(int initialI, int initialJ) {
        this.initialI = initialI;
        this.i = initialI;
        this.initialJ = initialJ;
        this.j = initialJ;
    }

    public int getInitialI() {
        return initialI;
    }

    public int getInitialJ() {
        return initialJ;
    }
    
    public void setLocationOnMapI(int i) {
        this.i += i;
    }
    
    public void setLocationOnMapJ(int j) {
        this.j += j;
    }
    
    public int getLocationOnMapI() {
        return i;
    }

    public int getLocationOnMapJ() {
        return j;
    }
    
    public int getRoundsLeft() {
        return roundsLeft;
    }

    public void decRoundsLeft() {
        this.roundsLeft--;
    }

    public int getEscapes() {
        return escapes;
    }

    public void incEscapes() {
        this.escapes++;
    }

    public int getEatenByCat() {
        return eatenByCat;
    }

    public void incEatenByCat() {
        this.eatenByCat++;
    }

    public int getDrowned() {
        return drowned;
    }

    public void incDrowned() {
        this.drowned++;
    }

    public int getStarved() {
        return starved;
    }

    public void incStarved() {
        this.starved++;
    }

    public int getRound() {
        return rounds;
    }

    public int getUntilStarved() {
        return untilStarved;
    }

    public void ateCheese(){
       untilStarved = 100;
    }
    
    public void decUntilStarved() {
        this.untilStarved--;
    }
    
    public void resetMouse() {
        this.i = initialI;
        this.j = initialJ;
        this.untilStarved = 100;
    }
}
