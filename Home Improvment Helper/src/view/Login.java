package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malinin on 6/5/17.
 */
public class Login extends Application {

    Stage window;
    Scene loginScene, homePageScene;
    ArrayList<UserProfile> userlist;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        loginPage(primaryStage);

        window.setScene(loginScene);
        window.setTitle("Home Improvement Helper");
        primaryStage.setResizable(false);
        window.show();
    }
    //Malini
    public void loginPage(Stage window) throws Exception {
        
    	userlist = new ArrayList<UserProfile>();
    	
        //import users
    	if(UserProfile.importData() != null)
    	{
//    		System.out.println("Imported!");
    		
        	userlist = UserProfile.importData();
    	}
    	
//    	System.out.println("Before printing list");
//    	
//    	System.out.println("List size: " + userlist.size());
    	
    	//print userlist
    	//probably checks folders?
    	for (int i = 0; i < userlist.size(); i++) 
    	{
    		if (!Files.isDirectory(Paths.get("c:\\HIHUsers\\" + userlist.get(i).getUsername()))) 
    		{
    			 System.out.println("Creating folder for user: " + userlist.get(i).getUsername()); 
    			 File dir = new File("c:\\HIHUsers\\" + userlist.get(i).getUsername());
     			dir.mkdir();
    		}
    	}
    	
    	System.out.println("After printing list");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        loginScene = new Scene(grid, 550, 700);

        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password: ");
        PasswordField pwBox = new PasswordField();

        grid.add(userName, 0, 1);
        grid.add(userTextField, 1, 1);
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);

        grid.setGridLinesVisible(false);

        Button btn = new Button("Sign in");
        Button newAccount = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        hbBtn.getChildren().add(newAccount);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

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

                for (int i = 0; i < userlist.size(); i++) {
                    if (userTextField.getText().toString().equals(userlist.get(i).getUsername())
                            && pwBox.getText().toString().equals(userlist.get(i).getPassword())) {
                        window.setScene(homePageScene);
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
                    	
                    	for (int i = 0; i < userlist.size(); i++) 
                    	{
                            if (username.getText().toString().equals(userlist.get(i).getUsername()))
                            {
                            	exists = true;
                            }
                    	}
                    	
                    	if(!exists)
                    	{
                    		UserProfile newProfile = new UserProfile(username.getText(), password.getText());
                            userlist.add(newProfile);
                            UserProfile.export(userlist);
                            
                            if (!Files.isDirectory(Paths.get("c:\\HIHUsers\\" + username.getText()))) 
                    		{
                    			 System.out.println("Creating folder for user: " + username.getText()); 
                    			 File dir = new File("c:\\HIHUsers\\" + username.getText());
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
        loginScene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
    }
}
