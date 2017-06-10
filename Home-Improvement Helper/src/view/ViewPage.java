package view;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The ViewPage class creates a view page scene where a user can view the
 * details of previously created projects. BorderPane is extended to setup the
 * layout of the scene.
 *
 * UI Code by Malini Modified by Travis on 6/7/17 (Displayed the lists of
 * measurements and items)
 *
 */
public class ViewPage extends BorderPane
{

	Scene sceneViewPage;
	Stage window;
	
	public ViewPage()
	{
		try{
			// Load the current selected project
			Project currentProject = HomePage.selected_project;

			// Formatting for money
			DecimalFormat df = new DecimalFormat("#.00");

			GridPane gridPane = new GridPane();
			setCenter(gridPane);

			GridPane topGrid = new GridPane();
			setTop(topGrid);

			gridPane.setAlignment(Pos.TOP_CENTER);
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			gridPane.setPadding(new Insets(130, 40, 10, 40));

			//Brendan
			setTop(new HIHMenu());

			
			Label projectName = new Label();
			projectName.setText("Project Name: " + currentProject.getProjectName());
			Label projectDescription = new Label();
			projectDescription.setText("Project Description: " + currentProject.getMyProjectDescription());
			projectDescription.setMaxWidth(300);
			projectDescription.setWrapText(true);

			// Label projectMaterial = new Label(dp.getMaterial());
			// Label measure = new Label(dp.getMyMeasurement1());
			// Label price = new Label(dp.getMyPrice1());

			Label totalCost = new Label("Project Costs: $" + df.format(currentProject.getCost()));

			projectName.setStyle(
					"-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;" + "-fx-font-family: Open Sans");
			projectDescription.setStyle("-fx-text-fill: black; -fx-font-size: 15px; fx-font-family: Open Sans");

			// projectMaterial.setStyle("-fx-text-fill: black; -fx-font-size: 16px;
			// fx-font-family: Open Sans");
			// measure.setStyle("-fx-text-fill: black; -fx-font-size: 16px;
			// fx-font-family: Open Sans");
			// price.setStyle("-fx-text-fill: black; -fx-font-size: 16px;
			// fx-font-family: Open Sans");

			totalCost.setStyle("-fx-text-fill: black; -fx-font-size: 15px;  fx-font-family: Open Sans");

			gridPane.add(projectName, 0, 1);
			gridPane.add(projectDescription, 0, 2);

			int space_counter = 3; // Counter to continue adding items further down
									// the page

			// Get itemlist from the project
			ArrayList<Item> itemList = currentProject.getItems();

			// Loop through the item list add each item to a label
			for (int i = 0; i < itemList.size(); i++)
			{
				Label item = new Label(itemList.get(i).toString());
				item.setStyle("-fx-text-fill: black; -fx-font-size: 15px;  fx-font-family: Open Sans");
				gridPane.add(item, 0, space_counter);
				space_counter++;
			}
			// Get measurement list from the project
			ArrayList<String> measurementList = currentProject.getMyMeasurements();

			Label measurementSpacer = new Label("Measurements: ");
			measurementSpacer.setStyle("-fx-text-fill: black; -fx-font-size: 15px;  fx-font-family: Open Sans");
			gridPane.add(measurementSpacer, 0, space_counter);
			space_counter++;

			// Loop through measurements and add them to labels
			for (int i = 0; i < measurementList.size(); i++)
			{
				Label measurement = new Label(measurementList.get(i));
				measurement.setStyle("-fx-text-fill: black; -fx-font-size: 15px;  fx-font-family: Open Sans");
				gridPane.add(measurement, 0, space_counter);
				space_counter++;
			}

			gridPane.add(totalCost, 0, space_counter);

			Button btn = new Button("Delete");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn);
			gridPane.add(hbBtn, 1, 14);

			//Brendan
			btn.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					String path = "HIHUsers\\" + Login.username + "\\" + HomePage.selected_project.getProjectName()
							+ ".ser";
					File dir = new File(path);

					dir.delete();
					HomePage.projects.clear();
					HomePage.importProjects();
					UI_Main.switchScene(UI_Main.homePageScene);
				}
			});
		}
		catch (NullPointerException e)
		{
			
		}
	}
}