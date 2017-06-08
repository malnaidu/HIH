package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;

/**
 * Created by malinin on 5/24/17.
 */
public class FormPage extends Application{

    Stage window = Main.window;
    Scene loginScene = Main.loginScene, homePageScene = Main.homePageScene, viewPageScene = Main.viewPageScene, formPageScene = Main.formPageScene;
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        formPageScene = new Scene(root, 550, 700);

        GridPane gridPane = new GridPane();
        root.setCenter(gridPane);

        GridPane topGrid = new GridPane();
        root.setTop(topGrid);

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(130,0,0,0));

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Menu home = new Menu("Home");

        Menu options = new Menu("Options");
        CheckMenuItem about = new CheckMenuItem("About Home Improvement Helper");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem printMenuItem = new MenuItem("Print");
        MenuItem exitMenuItem = new MenuItem("Exit");

        Menu compare = new Menu("Compare Projects");

        options.getItems().addAll(about, saveMenuItem, printMenuItem,
                new SeparatorMenuItem(), exitMenuItem);


        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuBar.getMenus().addAll(home, options, compare);

        root.setTop(menuBar);

        primaryStage.setTitle("Adding Menus");
        primaryStage.setScene(formPageScene);
        primaryStage.show();


        formPageScene.getStylesheets().
                add(FormPage.class.getResource("FormPage.css").toExternalForm());
        primaryStage.setTitle("Home Improvement Helper");
        primaryStage.setScene(formPageScene);
        primaryStage.show();


        Label imageUpload = new Label("Select a Project Image:");
        TextField projectImage = new TextField();
        Button btnLoad = new Button("Upload");

        gridPane.add(imageUpload,0, 1);
        gridPane.add(projectImage,1,1);
        gridPane.add(btnLoad, 2, 1);

        //upload photo
        FileChooser fileChooser = new FileChooser();

        Label projectName = new Label("Project Name:");
        TextField userTextField = new TextField();
        Label projectDescription = new Label("Project Description: ");
        TextField userTextField2 = new TextField();
        Label measure1 = new Label("First Measurement:");
        TextField userTextField3 = new TextField();
        Label measure2 = new Label("Second Measurement:");
        TextField userTextField4 = new TextField();

        Button addMeasurement= new Button("Add More");
        addMeasurement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


        //add more measurements here

        Label priceItem = new Label("Price of Item 1:");
        TextField userTextField5 = new TextField();
        Button addPrice= new Button("Add More");
        addPrice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        Label priceItem2 = new Label("Price of Item 2:");
        TextField userTextField6 = new TextField();

        //add more items prices

        Label totalCost = new Label("Total Cost of Project:");
        TextField totalCostField = new TextField();

        gridPane.add(projectName,0,2);
        gridPane.add(userTextField, 1,2);
        gridPane.add(projectDescription,0,3);
        gridPane.add(userTextField2,1,3);

        gridPane.add(measure1,0,4);
        gridPane.add(userTextField3, 1,4);
        gridPane.add(addMeasurement, 2,4);
//        gridPane.add(measure2,0,5);
//        gridPane.add(userTextField4,1,5);

        gridPane.add(priceItem,0,6);
        gridPane.add(userTextField5, 1,6);
        gridPane.add(addPrice, 2, 6);
//        gridPane.add(priceItem2,0,7);
//        gridPane.add(userTextField6,1,7);

        gridPane.add(totalCost,0,8);
        gridPane.add(totalCostField,1,8);

        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        gridPane.add(hbBtn, 1, 10);

        Button homeBtn = new Button("Home");
        HBox Hhome = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(homeBtn);
        gridPane.add(Hhome, 1, 0);
        homeBtn.setOnAction(event -> {
            try {
                Main.homePage.start(window);
                //window.setScene(loginScene);

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        //Alisher
        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                String projectName = userTextField.getText();
                                String projectDescription = userTextField2.getText();
                                String measurement1 = userTextField3.getText();
                                String measurement2 = userTextField4.getText();
                                String price1 = userTextField5.getText();
                                String price2 = userTextField6.getText();
                                String totalCost = totalCostField.getText();

                                System.out.println(projectName + projectDescription + measurement1);

                                DummyProject dp = new DummyProject(projectName,
                                        projectDescription, measurement1,
                                        measurement2, price1, price2,
                                        totalCost);
                                ObjectOutputStream oos = null;
                                FileOutputStream fout = null;
                                try{
                                    fout = new FileOutputStream("project.ser", true);
                                    oos = new ObjectOutputStream(fout);
                                    oos.writeObject(dp);
                                } catch (Exception ex) {}
                                finally {
                                    if(oos != null){
                                        try {oos.close();} catch (Exception ex) {}
                                    }
                                }
                                window.setScene(homePageScene);
                            }
                        }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
