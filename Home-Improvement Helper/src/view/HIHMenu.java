package view;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * This class creates the menu bar that is set at the top
 * of each scene in the application
 * Coded and created by Brendan
 */
//Brendan
public class HIHMenu extends MenuBar
{ 
	public HIHMenu()
	{
        Menu file = new Menu("Navigation");

        MenuItem homeMenuItem = new MenuItem("Home");
        MenuItem compareMenuItem = new MenuItem("Compare");
        MenuItem exitMenuItem = new MenuItem("Exit");

        file.getItems().addAll(homeMenuItem, compareMenuItem, exitMenuItem);

        homeMenuItem.setOnAction(actionEvent -> UI_Main.switchScene(UI_Main.homePageScene));
        compareMenuItem.setOnAction(actionEvent -> UI_Main.switchScene(UI_Main.sceneComparePage));
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        getMenus().addAll(file);
	}
}
