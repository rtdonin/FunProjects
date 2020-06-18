/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.dto;

public class Cat {
    private int initialI;
    private int initialJ;
    private int i = 0;
    private int j = 0;
    
    public Cat (int initialI, int initialJ) {
        this.initialI = initialI;
        this.i = initialI;
        this.initialJ = initialJ;
        this.j = initialJ;
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
    
    public void resetCat() {
        this.i = initialI;
        this.j = initialJ;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
