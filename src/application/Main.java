package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	//We need the use static becasue start and main method must see mainConsoller.
	static MainConsoller mainConsoller = new MainConsoller();

	public static void main(String[] args) {
//Chech the file exists and if it does not exist create, if it is check file is empty.
		File file = new File("AddressBook.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
				mainConsoller.empty();

		}

		launch(args);
		// program baþladýðýnda txt de varsa göster. id 1 den baþlayýp.
		// update search ý if ile kurabilirsin.
	}

	@Override
	public void start(Stage primaryStage) {
		try {
//Setting some textfields properties.
			mainConsoller.IDTextField.setPrefColumnCount(4);
			mainConsoller.IDTextField.setDisable(true);
			mainConsoller.nameTextField.setPromptText("Name");
			mainConsoller.streetTextField.setPromptText("Street");
			mainConsoller.cityTextField.setPromptText("City");
			mainConsoller.zipTextField.setPromptText("Zip");
			mainConsoller.update_searchbyIDTextField.setPromptText("ID");
			mainConsoller.genderTextField.setPrefColumnCount(1);
			mainConsoller.zipTextField.setPrefColumnCount(4);
			mainConsoller.cityTextField.setPrefColumnCount(12);
//Setting addButton event.
			mainConsoller.addButton.setOnAction(ActionEvent -> {
				try {
					mainConsoller.add();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting firstButton event.
			mainConsoller.firstButton.setOnAction(ActionEvent -> {
				try {
					mainConsoller.first();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting nextButton event.
			mainConsoller.nextButton.setOnAction(ActionEvent -> {
				try {
					mainConsoller.next();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting previousButton event.
			mainConsoller.previousButton.setOnAction(ActionEvent -> {
				try {
					mainConsoller.previous();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting lastButton event.
			mainConsoller.lastButton.setOnAction(ActionEvent -> {
				try {
					mainConsoller.last();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting updateByIDButton event.
			mainConsoller.updateByIDButton.setOnAction(ActionEvent -> {
				try {
					int update = Integer.parseInt(mainConsoller.update_searchbyIDTextField.getText());
					mainConsoller.updateById(update);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting searchByIDButton event.
			mainConsoller.searchByIDButton.setOnAction(ActionEvent -> {
				try {
					int search = Integer.parseInt(mainConsoller.update_searchbyIDTextField.getText());
					mainConsoller.searchById(search);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//Setting cleanTextFieldsButton event.
			mainConsoller.cleanTextFieldsButton.setOnAction(ActionEvent -> mainConsoller.cleantTextFields());
//Creating gridpane, set some properties and add labels , textfields.
			GridPane pane1 = new GridPane();
			pane1.setAlignment(Pos.CENTER);
			pane1.setHgap(5);
			pane1.setVgap(5);
//Creating horizontal box, adding label and textfields and add on gridpane this HBox.
			HBox pane4 = new HBox(5);
			pane1.add(mainConsoller.IDLabel, 0, 0);
			pane4.getChildren().addAll(mainConsoller.IDTextField, mainConsoller.update_searchbyIDLabel,
					mainConsoller.update_searchbyIDTextField);
			pane1.add(pane4, 1, 0);

			pane1.add(mainConsoller.nameLabel, 0, 1);
			pane1.add(mainConsoller.nameTextField, 1, 1);

			pane1.add(mainConsoller.streetLabel, 0, 2);
			pane1.add(mainConsoller.streetTextField, 1, 2);
			pane1.add(mainConsoller.cityLabel, 0, 3);

			HBox pane2 = new HBox(10);
			pane2.getChildren().addAll(mainConsoller.cityTextField, mainConsoller.genderLabel,
					mainConsoller.genderTextField, mainConsoller.zipLabel, mainConsoller.zipTextField);
			pane1.add(pane2, 1, 3);

			HBox pane3 = new HBox(5);
			pane3.getChildren().addAll(mainConsoller.addButton, mainConsoller.firstButton, mainConsoller.nextButton,
					mainConsoller.previousButton, mainConsoller.lastButton, mainConsoller.updateByIDButton,
					mainConsoller.searchByIDButton, mainConsoller.cleanTextFieldsButton);
			pane3.setAlignment(Pos.CENTER);
//Creating borderpane and adding gridpane and horizontal box.
			BorderPane borderPane = new BorderPane();
			borderPane.setCenter(pane1);
			borderPane.setBottom(pane3);
//Creating scene and adding borderpane.
			Scene scene = new Scene(borderPane, 550, 180);
			primaryStage.setTitle("Address Book");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
