/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import mouseisland.dto.*;
import static mouseisland.dto.Location.*;


public class MouseIslandDaoImpl implements MouseIslandDao {
    public Cat cat;
    public Map map;
    public Mouse mouse;
    public String MAP_FILE = "map.txt";
    
    public MouseIslandDaoImpl() {
        
    }
    
    @Override
    public Game getGame()  throws MapPersistenceException {
        loadFile();
        Game game = new Game(cat, map, mouse);
        return game;
    }
    
    @Override
    public void returnGame(Game game, long seed) throws MapPersistenceException {
        writeFile(game, seed);
    }
    
    @Override
    public void createMap(int[][] mapNumbers, String mapName, int width, int height) {
        Location[][] mapLocation = new Location[height][width];
        Location[] locEnum = Location.values();
        
        for (int i = 0; i < mapNumbers.length; i++) {
            for (int j = 0; j < mapNumbers[i].length; j++) {
                int numberLoc = mapNumbers[i][j];
                Location currentLoc = locEnum[numberLoc];
                
                switch (currentLoc) {
                    case MOUSE:
                        mouse.setInitial(i, j);
                        mapLocation[i][j] = LAND;
                        break;
                    case CAT:
                        cat = new Cat(i, j);
                        mapLocation[i][j] = LAND;
                        break;
                    default:
                        mapLocation[i][j] = currentLoc;
                        break;
                }
            }
        }
        
        this.map = new Map(mapLocation, mapName);
    }

    @Override
    public void loadFile() throws MapPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(MAP_FILE)));
        } catch(FileNotFoundException e) {
            System.out.println("Problem loading map.");
            throw new MapPersistenceException("Could not load Map", e);
        }

        String mapName = null;
        int width = 0;
        int height = 0;
        int[][] mapNumbers = null;
        int i = 0;
        
        int fl = 1;
        String currentLine;
        
        while (scanner.hasNext()) {
            
            currentLine = scanner.nextLine();
            switch(fl) {
                case 1:
                    mapName = currentLine;
                    break;
                case 2:
                    this.mouse = new Mouse(parseInt(currentLine));
                    break;
                case 3:
                    width = parseInt(currentLine);
                    break;
                case 4:
                    height = parseInt(currentLine);
                    mapNumbers = new int[height][width];
                    break;
                default: mapNumbers[i] = readingMap(currentLine, width);
                    i++;
                    break;
            }
            fl++;
            
        }
        
        // close scanner
        scanner.close();
        
        createMap(mapNumbers, mapName, width, height);
    }

    @Override
    public void writeFile(Game game, long seed) throws MapPersistenceException {
        PrintWriter out;
        LocalDateTime ld = LocalDateTime.now();
        String formatted = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss"));
        String returnFileName = "Results-{" + seed + "}-{" + formatted + "}.txt";

        //Results - {Seed#} - {DateTime Run}
        try {
            File returnFile = new File(returnFileName);
            out = new PrintWriter(new FileWriter(returnFile));
        } catch (IOException e) {
            System.out.println("Problem printing");
            throw new MapPersistenceException("Could not print answers.", e);
        }
        
        this.cat = game.getCat();
        this.map = game.getMap();
        this.mouse = game.getMouse();
        
        BigDecimal rounds = new BigDecimal(mouse.getRound());
        
        BigDecimal perEscapes = new BigDecimal(mouse.getEscapes());
        perEscapes = perEscapes.divide(rounds).movePointRight(2);
        
        BigDecimal perEaten = new BigDecimal(mouse.getEatenByCat());
        perEaten = perEaten.divide(rounds).movePointRight(2);
        
        BigDecimal perDrown = new BigDecimal(mouse.getDrowned());
        perDrown = perDrown.divide(rounds).movePointRight(2);
        
        BigDecimal perStarve = new BigDecimal(mouse.getStarved());
        perStarve = perStarve.divide(rounds).movePointRight(2);
        
        out.println("Map Name: " + map.getName()
                + "\nNumber of Simulations : " + mouse.getRound()
                + "\nRandom Seed: " + seed
                + "\nMouse Escapes: " + mouse.getEscapes() + " | {" + perEscapes.toPlainString() + "%}"
                + "\nMouse Eaten : " + mouse.getEatenByCat() + " | {" + perEaten.toPlainString() + "%}"
                + "\nMouse Drowns: " + mouse.getDrowned() + " | {" + perDrown.toPlainString() + "%}"
                + "\nMouse Starves: " + mouse.getStarved() + " | {" + perStarve.toPlainString() + "%}"
                + "\nTile Frequency Map: "
                + "\nEach number represents the number of times the mouse visited the tile."
                + "\n" + map.getFreqString());
        out.flush();
        out.close();
    }

    private int[] readingMap(String currentLine, int width) {
        int[] temp = new int[width];
        String[] tokens = currentLine.split(" ");
        for (int j = 0; j < width; j++) {
            temp[j] = parseInt(tokens[j]);
        }
        
        return temp;
    }

}
