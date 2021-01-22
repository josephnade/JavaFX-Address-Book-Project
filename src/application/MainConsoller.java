package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
//IDE says these libraries is never used but they used.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainConsoller extends FileManager {
	// index provides where function go in the array.
	public int index = 0;
	// These are labels.
	Label IDLabel = new Label("ID");
	Label nameLabel = new Label("Name");
	Label streetLabel = new Label("Street");
	Label cityLabel = new Label("City");
	Label zipLabel = new Label("Zip");
	Label genderLabel = new Label("Gender");
	Label update_searchbyIDLabel = new Label("Search/Update by ID");
//These are textfields.
	TextField IDTextField = new TextField();
	TextField nameTextField = new TextField();
	TextField streetTextField = new TextField();
	TextField cityTextField = new TextField();
	TextField zipTextField = new TextField();
	TextField genderTextField = new TextField();
	TextField update_searchbyIDTextField = new TextField();
//These are buttons.
	Button addButton = new Button("Add");
	Button firstButton = new Button("First");
	Button nextButton = new Button("Next");
	Button previousButton = new Button("Previous");
	Button lastButton = new Button("Last");
	Button updateByIDButton = new Button("UpdateByID");
	Button searchByIDButton = new Button("SearchByID");
	Button cleanTextFieldsButton = new Button("Clean Text Fields");

//dialog function provides creating alert and which we entered paramaters is adding alert.
	public void dialog(AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

//empty function check file length and if file length bigger than zero add these info into array and textfields.
	public void empty() {
		if (file.length() > 0) {
			try {
				index = 0;
				readFile(index);
				read(index);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//add function check textfields length and if it is available then adds from textfields into array and txt file.
	public void add() throws IOException {
//this for loop provides last index founds.
//person.length() function does not help becasue it returns 100(array size).
		for (index = 0; index < person.length; index++) {
			try {
				index = person[index].getID() - 1;
			} catch (Exception e) {
				break;
			}
		}
		if (IDTextField.getText().length() <= 4 && nameTextField.getText().length() <= 32
				&& streetTextField.getText().length() <= 32 && cityTextField.getText().length() <= 20
				&& genderTextField.getText().length() <= 1 && zipTextField.getText().length() <= 5
				&& nameTextField.getText().length() > 0 && streetTextField.getText().length() > 0
				&& cityTextField.getText().length() > 0 && genderTextField.getText().length() > 0
				&& zipTextField.getText().length() > 0) {
			person[index] = new Person(index + 1, null, null, null, null, null);
			person[index].setName(nameTextField.getText());
			person[index].setStreet(streetTextField.getText());
			person[index].setCity(cityTextField.getText());
			person[index].setZip(zipTextField.getText());
			person[index].setGender(genderTextField.getText());
			IDTextField.setText(String.valueOf(person[index].getID()));
			writeFile(index);
			dialog(AlertType.INFORMATION, "Information Dialog", "Look, an Information Dialog",
					"Record is added successfully");
		} else {
			dialog(AlertType.ERROR, "Error Dialog", "ERROR",
					"You entered too long text or you entered null text.Please enter again");
		}
	}

	public void read(int index) throws FileNotFoundException {
//read functions read the array's data and set textfields.
		IDTextField.setText(String.valueOf(person[index].getID()));
		nameTextField.setText(String.valueOf(person[index].getName()));
		streetTextField.setText(String.valueOf(person[index].getStreet()));
		cityTextField.setText(String.valueOf(person[index].getCity()));
		zipTextField.setText(String.valueOf(person[index].getZip()));
		genderTextField.setText(String.valueOf(person[index].getGender()));
	}
//first function go first index and show.
	public void first() throws FileNotFoundException {
		index = 0;
		read(index);
	}
//next function go next index and show.
	public void next() throws FileNotFoundException {
		try {
			index += 1;
			read(index);
		} catch (Exception e) {
//if it does not work properly go previous index,so index is does not change.
			index -= 1;
			dialog(AlertType.ERROR, "INDEX ERROR", null, "You can not show next because you are on last element.");
		}

	}

	public void previous() throws FileNotFoundException {
//previous function go previous index and show.
		try {
			index -= 1;
			read(index);
		} catch (Exception e) {
//if it does not work properly go previous index,so index is does not change.
			index += 1;
			dialog(AlertType.ERROR, "INDEX ERROR", null, "You can not show previous because you are on first element.");
		}

	}
//last function go last index and show.
	public void last() throws FileNotFoundException {
		int len = 0;
//This for loops provides go last index.
		for (index = 0; index < person.length; index++) {
			try {
				len = person[index].getID();
			} catch (Exception e) {
				index = len - 1;
				read(len - 1);
				break;
			}
		}

	}

	public void updateById(int number) throws IOException {
//updateById function provides update array data which index is entered number(ID)-1, delete file and then create file finally,write the txt updated datafields. 
		try {
			index = number - 1;
			if (person[index].getID() == number) {
				IDTextField.setText(String.valueOf(person[index].getID()));
				person[index].setName(nameTextField.getText());
				person[index].setStreet(streetTextField.getText());
				person[index].setCity(cityTextField.getText());
				person[index].setZip(zipTextField.getText());
				person[index].setGender(genderTextField.getText());
				file.delete();
				file.createNewFile();
				for (index = 0; index < person.length; index++) {
					try {
						index = person[index].getID() - 1;
						writeFile(index);
					} catch (Exception e) {
						break;
					}
				}
				index = number - 1;
				update_searchbyIDTextField.clear();
				dialog(AlertType.INFORMATION, "Information Dialog", "Look, an Information Dialog",
						"Record is updated successfully");
			}

		} catch (Exception e) {
			dialog(AlertType.ERROR, "ID ERROR", null, "ID is not found, please enter again.");
		}

	}
//searchByID function provides search array data which index=number(ID)-1 and show.
	public void searchById(int number) throws FileNotFoundException {
		try {
			index = number - 1;
			if (person[index].getID() == number) {
				IDTextField.setText(String.valueOf(number));
				nameTextField.setText(person[index].getName());
				streetTextField.setText(person[index].getStreet());
				cityTextField.setText(person[index].getCity());
				zipTextField.setText(person[index].getZip());
				genderTextField.setText(person[index].getGender());
				update_searchbyIDTextField.clear();
			}
		} catch (Exception e) {
			dialog(AlertType.ERROR, "ID ERROR", null, "ID is not found, please enter again.");
		}
	}
//cleanTextFields function provides clean all textfields.
	public void cleantTextFields() {
		IDTextField.clear();
		nameTextField.clear();
		streetTextField.clear();
		cityTextField.clear();
		zipTextField.clear();
		genderTextField.clear();
		update_searchbyIDTextField.clear();
	}
}
