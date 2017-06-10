package view;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * The FormPage class creates a form page with various input areas for a user to
 * put data about their home improvement project.
 *
 * The class extends BorderPane for layout and formating of the UI
 *
 * class created by malinin on 5/24/17. Modified by Travis on 6/7/17 (Added
 * storage functionality to items/measurements) Modified by Brendan on 6/8/17
 * (added menu bar and text field restrictions.
 */
public class FormPage extends BorderPane
{
	// in this list, even indexes are item names,
	// odd indexes are item prices.
	ArrayList<TextField> fieldsList;
	ArrayList<TextField> everythingList;
	ArrayList<TextField> measurementFieldList;
	ArrayList<String> measurementList;
	int row = 9;
	int measureCount = 2;
	int priceCount = 2;
	int itemCount = 2;
	int col = 0;

	/**
	 * This constructor build the layout of the FormPage
	 *
	 * Coded by Malini Coded by Travis (Added action handling for the
	 * measurements and items.
	 */
	public FormPage()
	{
		// Instantiate lists for the necessarily fields
		fieldsList = new ArrayList<TextField>();
		everythingList = new ArrayList<TextField>();
		measurementFieldList = new ArrayList<TextField>();
		measurementList = new ArrayList<String>();

		GridPane gridPane = new GridPane();
		setCenter(gridPane);

		GridPane topGrid = new GridPane();
		setTop(topGrid);

		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(130, 0, 0, 0));

		setTop(new HIHMenu());

		// Label imageUpload = new Label("Select a Project Image:");
		// TextField projectImage = new TextField();
		// Button btnLoad = new Button("Upload");
		//
		// gridPane.add(imageUpload,0, 1);
		// gridPane.add(projectImage,1,1);
		// gridPane.add(btnLoad, 2, 1);
		//
		// //upload photo
		// FileChooser fileChooser = new FileChooser();

		// Project name, description
		Label projectName = new Label("Project Name:");
		TextField userTextField = new TextField();
		Label projectDescription = new Label("Project Description: ");
		TextField userTextField2 = new TextField();
		everythingList.add(userTextField);
		everythingList.add(userTextField2);

		// Default measurement (measurement 1)
		Label measure1 = new Label("Measurement 1:");
		TextField userTextField3 = new TextField();
		measurementFieldList.add(userTextField3);
		Button addMeasurement = new Button("Add More");
		everythingList.add(userTextField3);

		// Default item (item 1)
		Label itemName = new Label("Name of Item 1:");
		TextField nameField = new TextField();
		Button addName = new Button("Add More");
		fieldsList.add(nameField);
		everythingList.add(nameField);

		// Default item price
		Label priceItem = new Label("Price of Item 1 ($):");
		TextField userTextField5 = new TextField();
		fieldsList.add(userTextField5);
		everythingList.add(userTextField5);


		// Total cost
		Label totalCost = new Label("Total Cost of Project ($):");
		TextField totalCostField = new TextField();
		everythingList.add(totalCostField);


		// Add all the fields and buttons
		gridPane.add(projectName, 0, 2);
		gridPane.add(userTextField, 1, 2);
		gridPane.add(projectDescription, 0, 3);
		gridPane.add(userTextField2, 1, 3);

		gridPane.add(measure1, 0, 4);
		gridPane.add(userTextField3, 1, 4);
		gridPane.add(addMeasurement, 2, 4);

		gridPane.add(itemName, 0, 5);
		gridPane.add(nameField, 1, 5);
		gridPane.add(addName, 2, 5);

		gridPane.add(priceItem, 0, 6);
		gridPane.add(userTextField5, 1, 6);

		gridPane.add(totalCost, 0, 8);
		gridPane.add(totalCostField, 1, 8);

		Button btn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		gridPane.add(hbBtn, 1, 10);

		// Travis (Minor modification: Added the measurement textfield to the
		// arrays
		addMeasurement.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				TextField measurement = new TextField();
				measurementFieldList.add(measurement);
				gridPane.add(new Label("Measurement " + measureCount), 0, row);
				gridPane.add(measurement, 1, row);
				measureCount++;
				row++;
				gridPane.getChildren().remove(hbBtn);
				HBox hbBtn = new HBox(10);
				hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
				hbBtn.getChildren().add(btn);
				gridPane.add(hbBtn, 1, row + 1);
			}
		});

		/*
		 * addPrice.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { TextField price =
		 * new TextField(); gridPane.add(new Label("Price of item " +
		 * priceCount), 0, row); gridPane.add(price, 1, row); //gridPane.add(new
		 * Button("Add More"), 2, row); priceCount++; row++;
		 * gridPane.getChildren().remove(hbBtn); HBox hbBtn = new HBox(10);
		 * hbBtn.setAlignment(Pos.BOTTOM_RIGHT); hbBtn.getChildren().add(btn);
		 * gridPane.add(hbBtn, 1, row + 1); } });
		 */

		// Travis (Minor modification: Added the item textfields to the arrays
		//Brendan Restrictions and conditionals
		addName.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				TextField name = new TextField();
				gridPane.add(new Label("Name of Item " + itemCount + ":"), 0, row);
				gridPane.add(name, 1, row);
				fieldsList.add(name);
				// gridPane.add(new Button("Add More"), 2, row);
				itemCount++;
				row++;
				TextField price = new TextField();
				gridPane.add(new Label("Price of Item " + priceCount + " ($) :"), 0, row);
				gridPane.add(price, 1, row);
				fieldsList.add(price);
				row++;
				priceCount++;
				gridPane.getChildren().remove(hbBtn);
				HBox hbBtn = new HBox(10);
				hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
				hbBtn.getChildren().add(btn);
				gridPane.add(hbBtn, 1, row + 1);
			}
		});

		// Alisher and Brendan
		// Travis (Added export functionality for item and measurement arrays
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				boolean valid = true;
				// Add them to an Item object and store it in the temporary
				// list
				for (int i = 0; i < fieldsList.size(); i += 2)
				{
					if (!fieldsList.get(i + 1).getText().matches("[0-9.]*") || (fieldsList.get(i + 1).getText().matches("")))
					{
						valid = false;
					}
				}
				
				if (!(totalCostField.getText().matches("[0-9.]*") || (totalCostField.getText().matches("")
						|| userTextField.getText().matches("")))) valid = false;

				if (valid)
				{
					// Take the current values of the name, description, and
					// total
					// cost fields
					String projectName = userTextField.getText();
					String projectDescription = userTextField2.getText();
					String totalCost = totalCostField.getText();

					// Temporary item list
					ArrayList<Item> itemList = new ArrayList<Item>();

					// Loop through all item fields
					for (int i = 0; i < fieldsList.size(); i += 2)
					{
						// Add them to an Item object and store it in the
						// temporary
						// list
						Item temp = new Item(fieldsList.get(i).getText(), fieldsList.get(i + 1).getText());
						itemList.add(temp);

					}

					// Store all the measurements in a temporary list
					for (int i = 0; i < measurementFieldList.size(); i++)
					{
						measurementList.add(measurementFieldList.get(i).getText());
					}

					// Create a new dummy project and output it as a .ser file
					// inside the proper user's folder.
					Project dp = new Project(projectName, projectDescription, measurementList, itemList, totalCost);
					ObjectOutputStream oos = null;
					FileOutputStream fout = null;
					try
					{
						fout = new FileOutputStream("HIHUsers\\" + Login.username + "\\" + projectName + ".ser");
						oos = new ObjectOutputStream(fout);
						oos.writeObject(dp);
						HomePage.projects.add(dp);
					} catch (Exception ex)
					{
					} finally
					{
						if (oos != null)
						{
							try
							{
								oos.close();
							} catch (Exception ex)
							{
							}
						}
					}
					clearForms();
					HomePage.projects.clear();
					HomePage.importProjects();
					UI_Main.switchScene(UI_Main.homePageScene);
				}
				else
				{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Wrong format!");
                    alert.setContentText("Please enter fields properly.");

                    alert.showAndWait();
				}
			}
		});
	}
	
	public void clearForms()
	{
		for (int i = 0; i < everythingList.size(); i++)
		{
			everythingList.get(i).clear();;
		}
	}
}
