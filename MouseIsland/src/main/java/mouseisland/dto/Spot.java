/*
Created by: Margaret Donin
Date created:
Date revised:
*/

package mouseisland.dto;

public class Spot {
    private int timesLanded;
    private Location loc;

    public Spot(Location loc) {
        this.loc = loc;
        this.timesLanded = 0;
    }

    public int getTimesLanded() {
        return timesLanded;
    }

    public void incrementTimesLanded() {
        this.timesLanded++;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
    
    
}
