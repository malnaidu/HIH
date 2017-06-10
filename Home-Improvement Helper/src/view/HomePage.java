package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 * The HomePage class creates the landing page of the application.
 * BorderPane is extended to set up the layout of the scene. The HomePage
 * contains a "Create Project" button where users can click and create a new project.
 *
 * Also, HomePage contains a ComboBox that holds all the existing projects created
 * by users in the past (stored projects).
 *
 * class ceated by malinin on 6/5/17.
 *
 * All UI code by Malini
 */
public class HomePage extends BorderPane {
    Stage window;
    Scene homePageScene, formPageScene, sceneViewPage;
    static Project selected_project;
    static ObservableList<Project> projects = FXCollections.observableArrayList();;

    public HomePage() {

        GridPane gridPane = new GridPane();
        setCenter(gridPane);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0,0,100,0));

        //Brendan
        setTop(new HIHMenu());

        Button projectButton = new Button("Create A New Project");
        gridPane.add(projectButton, 0,0);


        final ComboBox<Project> existingProjects = new ComboBox<Project>();
        existingProjects.setPromptText("Existing Projects");
        existingProjects.setVisibleRowCount(5);
        existingProjects.setItems(projects);
        gridPane.add(existingProjects,0, 5);

        // Handle existing project selection event.
        // project selected opens project view w that project
        // brian
        existingProjects.setOnAction((event) -> {
        	Project selected = existingProjects.getSelectionModel().getSelectedItem();
            selected_project = selected;
            //need to pass this project into view page
            try {
            	UI_Main.sceneViewPage = new Scene(new ViewPage(), 550, 600);
        		UI_Main.sceneViewPage.getStylesheets().add(FormPage.class.getResource("ViewPage.css").toExternalForm());
            	
            	
                UI_Main.switchScene(UI_Main.sceneViewPage);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });

        // Define rendering of the list of values in ComboBox drop down.
        // brian
        existingProjects.setCellFactory((comboBox) -> {
            return new ListCell<Project>() {
                @Override
                protected void updateItem(Project item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getProjectName());
                    }
                }
            };
        });

        // Define rendering of selected value shown in ComboBox.
        // brian
        existingProjects.setConverter(new StringConverter<Project>() {
            @Override
            public String toString(Project project) {
                if (project == null) {
                    return null;
                } else {
                    return project.getProjectName();
                }
            }

            @Override
            public Project fromString(String projName) {
                return null; // No conversion fromString needed.
            }
        });


        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);

        //projectButton.setOnAction(e -> window.setScene(formPageScene));

        projectButton.setOnAction(e -> {

            try {
                UI_Main.switchScene(UI_Main.sceneFormPage);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
        
        //Brendan
        Button logout = new Button("Logout");
        logout.setId("logout");
        gridPane.add(logout, 0, 10);
        GridPane.setHalignment(logout, HPos.CENTER);
        
        logout.setOnAction(e -> {

            try {
            	HomePage.projects.clear();
                UI_Main.switchScene(UI_Main.loginScene);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
    }

    //Travis (Created import functionality)
    public static void importProjects()
    {
    	//Check for existing projects for the current user. All projects are imported to the static Projects arraylist
        String path = "HIHUsers\\" + Login.username;
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File f : directoryListing) {
                String fileName = f.getName();
                ObjectInputStream ois = null;
                FileInputStream fin = null;

                Project temp = null;

                try{
                    fin = new FileInputStream(path + "\\" + fileName);
                    ois = new ObjectInputStream(fin);
                    temp = (Project) ois.readObject();
                    projects.add(temp);

                } catch (Exception ex) {}
                finally {
                    if(ois != null){
                        try {ois.close();} catch (Exception ex) {}
                    }
                }
            }
        } else {
            System.out.println("Invalid directory");
        }
    }
}