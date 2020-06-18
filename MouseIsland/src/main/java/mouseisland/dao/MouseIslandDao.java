/*
Created by: Margaret Donin
Date created:
Date revised:
*/

package mouseisland.dao;

import mouseisland.dto.Game;

public interface MouseIslandDao {
    
    public Game getGame()  throws MapPersistenceException ;
    
    public void returnGame(Game game, long seed) throws MapPersistenceException;
    
    public void createMap(int[][] mapNumbers, String mapName, int width, int height);

    public void loadFile() throws MapPersistenceException;
    
    public void writeFile(Game game, long seed) throws MapPersistenceException;
}
