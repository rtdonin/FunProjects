/*
Created by: Margaret Donin
Date created:
Date revised:
*/

package mouseisland.ui;

public class MouseIslandView {
    UserIO io;
    
    public MouseIslandView (UserIO io) {
        this.io = io;
    }

    public void displayFinishd() {
        io.print("Simulations completed.");
    }

    public void displayError(String error) {
        io.print(error);
    }
}
