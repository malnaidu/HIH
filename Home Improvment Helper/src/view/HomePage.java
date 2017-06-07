package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by malinin on 6/5/17.
 */
public class HomePage extends Application {
    Stage window;
    Scene homePageScene, formPageScene, sceneViewPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        homePage(primaryStage);

        window.setTitle("Home Improvement Helper");
        primaryStage.setResizable(false);
        window.show();
    }


    public void homePage(Stage window) throws Exception {
        BorderPane root = new BorderPane();

        GridPane gridPane = new GridPane();
        root.setCenter(gridPane);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0,0,100,0));

        homePageScene = new Scene(root, 550, 700);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(window.widthProperty());
        root.setTop(menuBar);

        Menu home = new Menu("Home");

        Menu options = new Menu("Options");
        CheckMenuItem about = new CheckMenuItem("About Home Improvement Helper");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem printMenuItem = new MenuItem("Print");
        MenuItem exitMenuItem = new MenuItem("Exit");

        Menu compare = new Menu("Compare Projects");

        options.getItems().addAll(about, saveMenuItem, printMenuItem, new SeparatorMenuItem(), exitMenuItem);

        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuBar.getMenus().addAll(home, options, compare);

        window.setTitle("Adding Menus");
        window.setScene(homePageScene);
        window.show();

        homePageScene.getStylesheets().add(HomePage.class.getResource("HomePage.css").toExternalForm());
        window.setTitle("Home Improvement Helper");
        window.setScene(homePageScene);
        window.show();

        Button projectButton = new Button("Create A New Project");
        gridPane.add(projectButton, 0,0);

        Button existingButton = new Button("View Existing Projects");
        gridPane.add(existingButton,5,0);

        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);

        projectButton.setOnAction(e -> window.setScene(formPageScene));
        existingButton.setOnAction(e ->window.setScene(sceneViewPage));
    }
}
