/*
Created by: Margaret Donin
Date created: 06/15/20
Date revised:
*/

package mouseisland.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import mouseisland.dao.MapPersistenceException;
import mouseisland.service.MouseIslandServiceLayer;
import mouseisland.ui.MouseIslandView;

public class MouseIslandController {

    MouseIslandView view;
    MouseIslandServiceLayer service;

    public MouseIslandController (MouseIslandView view, MouseIslandServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        try {
            service.runSimulations();
            view.displayFinishd();

        } catch (MapPersistenceException ex) {
            view.displayError("Yikes, this was a disaster.");
        }

    }
}
