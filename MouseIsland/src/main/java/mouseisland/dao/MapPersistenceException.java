/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.dao;

import java.io.FileNotFoundException;

public class MapPersistenceException extends Exception {

    public MapPersistenceException (String message) {
        super(message);
    }
    
    public MapPersistenceException (String message, Throwable cause) {
        super(message, cause);
    }

}
