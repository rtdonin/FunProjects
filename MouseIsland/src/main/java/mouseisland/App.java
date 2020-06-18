/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland;

import mouseisland.controller.MouseIslandController;
import mouseisland.dao.MapPersistenceException;
import mouseisland.dao.MouseIslandDao;
import mouseisland.dao.MouseIslandDaoImpl;
import mouseisland.service.MouseIslandServiceLayer;
import mouseisland.service.MouseIslandServiceLayerImpl;
import mouseisland.ui.MouseIslandView;
import mouseisland.ui.UserIO;
import mouseisland.ui.UserIOConsoleImpl;

public class App {
    
    public static void main(String[] args) throws MapPersistenceException {
        // inititate user io
        UserIO myIO = new UserIOConsoleImpl();
        // initiate view that takes io
        MouseIslandView myView = new MouseIslandView(myIO);
        // initiate the dao
        MouseIslandDao myDao = new MouseIslandDaoImpl();
        // initiate the service layer
        MouseIslandServiceLayer myService = new MouseIslandServiceLayerImpl(myDao);
        // intitiate the controller
        MouseIslandController controller = new MouseIslandController(myView, myService);

        // kick of program
        controller.run();
    }

}
