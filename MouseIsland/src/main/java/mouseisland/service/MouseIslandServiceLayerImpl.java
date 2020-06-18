/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.service;

import java.util.Random;
import mouseisland.dao.*;
import mouseisland.dto.*;
import static mouseisland.dto.Condition.*;
import static mouseisland.dto.Location.*;

public class MouseIslandServiceLayerImpl implements MouseIslandServiceLayer {
    private Random random;
    private final MouseIslandDao dao;
    private Cat cat;
    private Map map;
    private Mouse mouse;
    private int[] cheese = new int[2];
    
    public MouseIslandServiceLayerImpl(MouseIslandDao dao) {
        this.dao = dao;
    }

    @Override
    public void runSimulations() throws MapPersistenceException {
        long seed = System.currentTimeMillis();
        Game game = dao.getGame();
        this.cat = game.getCat();
        this.map = game.getMap();
        this.mouse = game.getMouse();
        random = new Random(seed);

        while (mouse.getRoundsLeft() > 0) {
            moveMouse();            
            int mouseI = mouse.getLocationOnMapI();
            int mouseJ = mouse.getLocationOnMapJ();
            
            Spot mouseSpot = map.getSpot(mouseI, mouseJ);
            map.getSpot(mouseI, mouseJ).incrementTimesLanded();
            System.out.println("round: " + mouse.getRoundsLeft() + ", i: " + mouseI + ", j: " + mouseJ + ", times landedmap: " + map.getSpot(mouseI, mouseJ).getTimesLanded());
            // so we can reset the location
            int catI = cat.getLocationOnMapI();
            int catJ = cat.getLocationOnMapJ();
            Spot catSpot;
            
            boolean hasError = true;
            
            while (hasError){
                try {
                    moveCat();
                    catSpot = map.getSpot(cat.getLocationOnMapI(), cat.getLocationOnMapJ());
                    
                    if (catSpot.getLoc().equals(WATER) || catSpot.getLoc().equals(BRIDGE)) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    
                    // I know it's repetitive.
                    catI = cat.getLocationOnMapI();
                    catJ = cat.getLocationOnMapJ();
    
                    hasError = false;
                    
                } catch (ArrayIndexOutOfBoundsException e) {
                    hasError = true;
                    cat.setI(catI);
                    cat.setJ(catJ);
                }
            }
            
            if (mouseI == catI && mouseJ == catJ
                && !map.getSpot(mouseI, mouseJ).getLoc().equals(MOUSE_HOLE)) {
                
                mouseState(EATEN);
                
            } else {
            
                switch(mouseSpot.getLoc()){
                    case WATER:
                        mouseState(DROWNED);
                        break;
                    case BRIDGE:
                        mouseState(ESCAPED);
                        break;
                    case CHEESE:
                        mouseState(FED);
                        break;
                    case LAND:
                        mouseSurvived();
                        break;
                    default:
                        // do nothing
                }

                
            }
            
        }

        Game finishedGame = new Game(cat, map, mouse);
        dao.returnGame(finishedGame, seed);
    }

    // decide on whether cat is moving in i or j direction
    private void moveMouse() {
        // if true i, if false j
        boolean iOrJ = random.nextBoolean();
        // if true positive, if false negative
        boolean posOrNeg = random.nextBoolean();
        
        int increment;
        if (iOrJ) {
                increment = valueToIncrement(posOrNeg);
                mouse.setLocationOnMapI(increment);
        } else {
            increment = valueToIncrement(posOrNeg);
            mouse.setLocationOnMapJ(increment);
        }
    }
    
    // decide on whether cat is moving in i or j direction
    private void moveCat() {
        // if true i, if false j
        boolean iOrJ = random.nextBoolean();
        // if true positive, if false negative
        boolean posOrNeg = random.nextBoolean();
        
        int increment;
        if (iOrJ) {
                increment = valueToIncrement(posOrNeg);
                cat.setLocationOnMapI(increment);
        } else {
            increment = valueToIncrement(posOrNeg);
            cat.setLocationOnMapJ(increment);
        }
    }
    
    // positive or negative increment
    private int valueToIncrement(boolean posOrNeg) {
        if (posOrNeg) {
            return 1;
        } else {
            return -1;
        }
    }

    // what to do when the mouse dies.
    private void mouseDied() {
        mouse.decRoundsLeft();
        
        // reset mouse position and hunger
        mouse.resetMouse();
        
        // reset cat position
        cat.resetCat();
        
        // set the cheese back
        map.getSpot(cheese[0], cheese[1]).setLoc(CHEESE);
    }

    private void mouseState(Condition state) {
        switch (state) {
            
            case DROWNED:
                mouse.incDrowned();
                mouseDied();
                break;
            case EATEN:
                mouse.incEatenByCat();
                mouseDied();
                break;
            case ESCAPED:
                mouse.incEscapes();
                mouseDied();
                break;
            case FED:
                cheese[0] = mouse.getLocationOnMapI();
                cheese[1] = mouse.getLocationOnMapJ();
                map.getSpot(cheese[0], cheese[1]).setLoc(LAND);
                break;
            case STARVED:
                mouse.incStarved();
                mouseDied();
                break;
            default: 
                // do nothing
        }
    }

    private void mouseSurvived() {
        mouse.decUntilStarved();
        
        if(mouse.getUntilStarved() == 0) {
            mouseState(STARVED);
        }
    }
}
