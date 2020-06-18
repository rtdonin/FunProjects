/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.dto;

public class Map {
    private Spot[][] map;
    private String name;
    
    public Map(Location[][] map, String name) {
        this.map = new Spot[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                this.map[i][j] = new Spot(map[i][j]);
            }
        }
        this.name = name;
    }
    
    public void setSpot(int a, int b, Spot spot){
        this.map[a][b] = spot;
    }
    
    public Spot getSpot(int a, int b) {
        return map[a][b];
    }
    
    public String getName() {
        return name;
    }

    public String getFreqString() {
        String freq = "";
        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                int temp = map[i][j].getTimesLanded();
                
                if (temp < 10 ) {
                    freq += " ";
                }
                
                if (temp < 100) {
                    freq += " ";
                }
                
                freq += String.valueOf(temp) + " ";
            }
            freq += "\n";
        }
        
        return freq;
    }
    
}
