package view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * The UI_Main class extends Application, and creates the main class
 * that switches between all the scenes of the application.
 */
public class UI_Main extends Application {

    Project d;

    static Stage window;
    static Scene loginScene, homePageScene, sceneFormPage, sceneViewPage, sceneComparePage;
    private ObservableList<Project> projects = FXCollections.observableArrayList();
    static ObservableList<Project> myProjects = FXCollections.observableArrayList();
    private List<Project> myList = new ArrayList<Project>();

    public static void main(String[] args)
    {
    	initScenes();
        launch(args);
    }

    /**
     * The start method creates the window of the application.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	window = primaryStage;

    	window.setTitle("Home Improvement Helper");
        window.setScene(loginScene);

        window.setResizable(false);
        window.show();
    }
    
    //Brendan
    public static void initScenes()
    {
    	loginScene = new Scene(new Login(), 550, 600);
        loginScene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
    	homePageScene = new Scene(new HomePage(), 550, 600);
    	homePageScene.getStylesheets().add(HomePage.class.getResource("HomePage.css").toExternalForm());
    	sceneFormPage = new Scene(new FormPage(), 550, 600);
		sceneFormPage.getStylesheets().add(FormPage.class.getResource("FormPage.css").toExternalForm());
        sceneComparePage = new Scene(new ComparePage(), 550, 600);
        sceneComparePage.getStylesheets().add(FormPage.class.getResource("ComparePage.css").toExternalForm());
    }
    
    /**
     * Switching code by Brendan
     */
    public static void switchScene(Scene scene)
    {
    	window.setScene(scene);
    }

    /**
     * This method stores all the project and displays them to the view page.
     * @param window
     */
    public void storedProject(Stage window) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        sceneViewPage = new Scene(new Group());

        grid.setGridLinesVisible(false);
        sceneViewPage.getStylesheets().
                add(UI_Main.class.getResource("/ComparePage.css").toExternalForm());
        window.setTitle("Home Improvement Helper");


        //column with projects.
        TableColumn projectsCol = new TableColumn("project name");
        projectsCol.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        projectsCol.setMinWidth(200);

        TableColumn costCol = new TableColumn("cost");
        costCol.setCellValueFactory(new PropertyValueFactory<Project, String>("cost"));
        costCol.setMinWidth(100);

        TableColumn descriptionCol = new TableColumn("decsription");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Project, String>("myProjectDescription"));
        descriptionCol.setMinWidth(150);

        TableColumn measurement1Col = new TableColumn("measurement 1");
        measurement1Col.setCellValueFactory(new PropertyValueFactory<Project, String>("myMeasurement1"));
        measurement1Col.setMinWidth(100);

        TableColumn measurement2Col = new TableColumn("measurement 2");
        measurement2Col.setCellValueFactory(new PropertyValueFactory<Project, String>("myMeasurement2"));
        measurement2Col.setMinWidth(100);

        TableColumn price1Col = new TableColumn("price 1");
        price1Col.setCellValueFactory(new PropertyValueFactory<Project, String>("myPrice1"));
        price1Col.setMinWidth(100);

        TableColumn price2Col = new TableColumn("price 2");
        price2Col.setCellValueFactory(new PropertyValueFactory<Project, String>("myPrice2"));
        price2Col.setMinWidth(100);



        TableColumn materialCol = new TableColumn("material");
        materialCol.setCellValueFactory(new PropertyValueFactory<Project, String>("material"));
        materialCol.setMinWidth(150);

        if (d == null) {
            System.out.println("itsnull");
        }
        projects.addAll(myList);
    }
}