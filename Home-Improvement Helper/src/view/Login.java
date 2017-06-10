package view;

import javafx.application.Platform;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Login class creates the login scene that users
 * land on when executing the application. GridPane is extended
 * to setup the layout of the scene. The class asks a user to login with their
 * stored credentials. If they have never used the application before
 * they can register for a user profile and sign in.
 *
 * class created by malinin on 6/5/17.
 * Edited by Travis on 6/7/17 (Import files and create directories)
 *
 * UI Code by Malini
 */
public class Login extends GridPane {

	public static String username;
    Stage window;
    ArrayList<UserProfile> userlist;
    
    //Malini
    //Travis (Added import/export functionality for Profiles and other minor additions
    //Brendan register and button listeners + backend
    public Login()
 {
    	//Array to store the list of user profiles
    	userlist = new ArrayList<UserProfile>();
    	
        //Import users if a user profile file exists
    	if(UserProfile.importData() != null)
    	{
        	userlist = UserProfile.importData();
    	}
    	
    	//Create the Profiles folder
    	if (!Files.isDirectory(Paths.get("HIHUsers"))) 
		{
			 System.out.println("Creating folder HIHUSERS: "); 
			 File dir = new File("HIHUsers");
 			dir.mkdir();
		}
    	
    	//For each user, create a Profile folder for each to store their projects
    	for (int i = 0; i < userlist.size(); i++) 
    	{
    		if (!Files.isDirectory(Paths.get("HIHUsers\\" + userlist.get(i).getUsername()))) 
    		{
    			 System.out.println("Creating folder for user: " + userlist.get(i).getUsername()); 
    			 File dir = new File("HIHUsers\\" + userlist.get(i).getUsername());
     			dir.mkdir();
    		}
    	}
    	
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password: ");
        PasswordField pwBox = new PasswordField();

        add(userName, 0, 1);
        add(userTextField, 1, 1);
        add(pw, 0, 2);
        add(pwBox, 1, 2);

        setGridLinesVisible(false);

        Button btn = new Button("Sign in");
        Button newAccount = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        hbBtn.getChildren().add(newAccount);
        add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        add(actiontarget, 1, 6);

        //Brendan
        btn.setOnAction(event -> {

            if (userTextField.getText().equals("") || pwBox.getText().equals("")) {
                // error "One or more fields are empty.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error!");
                alert.setHeaderText("Field(s) Empty!");
                alert.setContentText("Please fill out both fields.");

                alert.showAndWait();
            } else {
                boolean found = false;

                //Loop through the user list to validate credentials
                for (int i = 0; i < userlist.size(); i++) {
                    if (userTextField.getText().toString().equals(userlist.get(i).getUsername())
                            && pwBox.getText().toString().equals(userlist.get(i).getPassword())) {
                    	
                    	//create global user variable
                    	username = userlist.get(i).getUsername();
                    	
                    	try {
                    		//Import projects for the selected user
                    		HomePage.importProjects();
                    		
                    		userTextField.setText("");
                    		pwBox.setText("");
							UI_Main.switchScene(UI_Main.homePageScene);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Wrong credentials!");
                    alert.setContentText("The username or password is invalid.");

                    alert.showAndWait();
                }
            }
        });

        //Brendan
        newAccount.setOnAction(event -> {
            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Register");
            dialog.setHeaderText("Create a new user.");

            // Set the icon (must be included in the project).

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane newActgrid = new GridPane();
            newActgrid.setHgap(10);
            newActgrid.setVgap(10);
            newActgrid.setPadding(new Insets(20, 150, 10, 10));

            TextField username = new TextField();
            username.setPromptText("Username");
            PasswordField password = new PasswordField();
            password.setPromptText("Password");

            newActgrid.add(new Label("Username:"), 0, 0);
            newActgrid.add(username, 1, 0);
            newActgrid.add(new Label("Password:"), 0, 1);
            newActgrid.add(password, 1, 1);

            // Enable/Disable login button depending on whether a username was
            // entered.
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            // Do some validation (using the Java 8 lambda syntax).
            username.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(newActgrid);

            // Request focus on the username field by default.
            Platform.runLater(() -> username.requestFocus());

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    if (!password.getText().equals("")) {
                    	
                    	
                    	//Check if username is take
                    	boolean exists = false;
                    	
                    	//Loop through the user list to check if the username is taken
                    	for (int i = 0; i < userlist.size(); i++) 
                    	{
                            if (username.getText().toString().equals(userlist.get(i).getUsername()))
                            {
                            	exists = true;
                            }
                    	}
                    	
                    	//If it isn't taken, add the user to the user list and create a Profile folder for that user
                    	if(!exists)
                    	{
                    		UserProfile newProfile = new UserProfile(username.getText(), password.getText());
                            userlist.add(newProfile);
                            UserProfile.export(userlist);
                            
                            if (!Files.isDirectory(Paths.get("HIHUsers\\" + username.getText()))) 
                    		{
                    			 System.out.println("Creating folder for user: " + username.getText()); 
                    			 File dir = new File("HIHUsers\\" + username.getText());
                     			dir.mkdir();
                    		}
                    	}
                    	else {
                            // error "One or more fields are empty.
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error!");
                            alert.setHeaderText("Username is already taken!");
                            alert.setContentText("Please choose an unused username.");

                            alert.showAndWait();
                        }

                    } else {
                        // error "One or more fields are empty.
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Password Empty!");
                        alert.setContentText("Please fill out a password.");

                        alert.showAndWait();
                    }
                }
                return null;
            });

            dialog.showAndWait();
        });
    }
}
