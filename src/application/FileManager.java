package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileManager {
	File file = new File("AddressBook.txt");
	Person[] person = new Person[100];

//readFile function provides read the file and save person[index].
	public void readFile(int counter) throws FileNotFoundException {
		File file = new File("AddressBook.txt");
		Scanner reader = new Scanner(file);
		while (reader.hasNextLine() == true) {
			person[counter] = new Person(counter, null, null, null, null, null);
			person[counter].setID(Integer.parseInt(reader.nextLine()));
			person[counter].setName(reader.nextLine());
			person[counter].setStreet(reader.nextLine());
			person[counter].setCity(reader.nextLine());
			person[counter].setZip(reader.nextLine());
			person[counter].setGender(reader.nextLine());
			counter+=1;
		}
		reader.close();
	}
//writeFile functions read the array data and write file.
	public void writeFile(int counter) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("AddressBook.txt", true));
		writer.write(String.valueOf(counter + 1));
		writer.newLine();
		writer.write(person[counter].getName());
		writer.newLine();
		writer.write(person[counter].getStreet());
		writer.newLine();
		writer.write(person[counter].getCity());
		writer.newLine();
		writer.write(person[counter].getZip());
		writer.newLine();
		writer.write(person[counter].getGender());
		writer.newLine();
		writer.close();
	}
	}

