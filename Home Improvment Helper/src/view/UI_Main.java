package view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class UI_Main extends Application {

    DummyProject d;

    Stage window;
    Scene sceneViewPage;
    private TableView<DummyProject> myTable;
    private ObservableList<DummyProject> projects = FXCollections.observableArrayList();
    private ObservableList<DummyProject> myProjects = FXCollections.observableArrayList();
    private List<DummyProject> myList = new ArrayList<DummyProject>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        storedProject(primaryStage);

        window.setTitle("Home Improvement Helper");
        primaryStage.setResizable(false);
        window.show();
    }

    public void storedProject(Stage window) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        sceneViewPage = new Scene(new Group());

        grid.setGridLinesVisible(false);
        sceneViewPage.getStylesheets().
                add(UI_Main.class.getResource("ComparePage.css").toExternalForm());
        window.setTitle("Home Improvement Helper");

        /*
         *
         */
        //myTable = new TableView<DummyProject>();

        //column with projects.
        TableColumn projectsCol = new TableColumn("project name");
        projectsCol.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("projectName"));
        projectsCol.setMinWidth(200);

        TableColumn costCol = new TableColumn("cost");
        costCol.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("cost"));
        costCol.setMinWidth(100);

        TableColumn descriptionCol = new TableColumn("decsription");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("myProjectDescription"));
        descriptionCol.setMinWidth(150);

        TableColumn measurement1Col = new TableColumn("measurement 1");
        measurement1Col.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("myMeasurement1"));
        measurement1Col.setMinWidth(100);

        TableColumn measurement2Col = new TableColumn("measurement 2");
        measurement2Col.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("myMeasurement2"));
        measurement2Col.setMinWidth(100);

        TableColumn price1Col = new TableColumn("price 1");
        price1Col.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("myPrice1"));
        price1Col.setMinWidth(100);

        TableColumn price2Col = new TableColumn("price 2");
        price2Col.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("myPrice2"));
        price2Col.setMinWidth(100);



        TableColumn materialCol = new TableColumn("material");
        materialCol.setCellValueFactory(new PropertyValueFactory<DummyProject, String>("material"));
        materialCol.setMinWidth(150);



        //DummyProject dp = new DummyProject("");
        //dp = importProject();

        List<DummyProject> list = new ArrayList<DummyProject>();
        /*for (int i = 1; i < 4; i++) {
        	list.add(new DummyProject("project " + i, "thisIsDecsription", "ms1", "ms2", "thisprice1", "thisprice2", "finalCost"));
        }*/


        //myList.add(d);

        if (d == null) {
            System.out.println("itsnull");
        }
        projects.addAll(myList);


        //myTable.setItems(projects);
        //myTable.getColumns().addAll(projectsCol, descriptionCol, measurement1Col, measurement2Col, price1Col, price2Col, costCol);


        /*final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(myTable);


        ((Group) sceneViewPage.getRoot()).getChildren().addAll(vbox);


        window.setScene(sceneViewPage);
        window.show();*/
    }

    public static DummyProject importProject () {
        ObjectInputStream ois = null;
        FileInputStream fin = null;

        DummyProject temp = null;

        try{
            fin = new FileInputStream("project.ser");
            ois = new ObjectInputStream(fin);
            temp = (DummyProject) ois.readObject();

//            this.tagName = temp.gettagName();
//            this.email = temp.getemail();
        } catch (Exception ex) {}
        finally {
            if(ois != null){
                try {ois.close();} catch (Exception ex) {}
            }
        }

        System.out.println("successful import");
        return temp;
    }
}