/*
Created by: Margaret Donin
Date created:
Date revised:
*/

package mouseisland.dto;

public class Game {
    private Map map;
    private Cat cat;
    private Mouse mouse;

    public Game(Cat cat, Map map, Mouse mouse) {
        this.map = map;
        this.cat = cat;
        this.mouse = mouse;
    }

    public Map getMap() {
        return map;
    }

    public Cat getCat() {
        return cat;
    }

    public Mouse getMouse() {
        return mouse;
    }
    
}
