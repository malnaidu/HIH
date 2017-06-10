package view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * The ComparePage shows a list of the current user's projects
 * Created by brian
 * table design by alisher
 * Travis (Updated and adjusted GUI elements to properly display data)
 */
public class ComparePage extends BorderPane {

    ObservableList<Project> myProjects = HomePage.projects;

    /**
     A table view of all the projects
     */
    private TableView<Project> myTable;

    public ComparePage() {
    	
    	ArrayList<TableColumn> allColumns = new ArrayList<TableColumn>();
    	
        GridPane gridPane = new GridPane();
        setCenter(gridPane);
        //table that will show all projects of user.
        myTable = new TableView<Project>();

        //column with projects.
        TableColumn projectsCol = new TableColumn("Name");
        projectsCol.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        //projectsCol.setMinWidth(60);
        allColumns.add(projectsCol);

        TableColumn costCol = new TableColumn("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<Project, String>("Cost"));
        //costCol.setMinWidth(150);
        allColumns.add(costCol);
        
        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Project, String>("myProjectDescription"));
        descriptionCol.setMinWidth(300);
        //descriptionCol.setMaxWidth(200);

        myTable.setItems(myProjects);
        myTable.getColumns().addAll(projectsCol, costCol, descriptionCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(myTable);
        gridPane.add(vbox, 0, 0);
        //((Group) sceneComparePage.getRoot()).getChildren().addAll(vbox);

        GridPane topGrid = new GridPane();
        setTop(topGrid);

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(130,40,10,40));

        setTop(new HIHMenu());
    }
}
