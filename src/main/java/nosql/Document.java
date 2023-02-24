package nosql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import com.google.gson.Gson;

public class Document {
	int id;
	File fileHandle;
	Collection parentCollection;

	public Document(int id, Collection collection) {
		this.id = id;
		this.parentCollection = collection;
		this.fileHandle = new File(App.DB_NAME + "/" + collection.name + "/" + id + ".json");
		try {
			this.fileHandle.createNewFile();
		} catch (IOException err) {
			System.err.println("Error creating document: " + this.fileHandle);
		}
	}

	public void insert(Object object) {
		String serializedObject = new Gson().toJson(object);
		try {
			FileWriter fp = new FileWriter(fileHandle);
			fp.write(serializedObject);
			fp.close();
		} catch (IOException err) {
			System.err.println("Error writing to document: " + this.fileHandle);
		}
	}

	@Override
	public String toString() {
		try {
			return Files.readString(this.fileHandle.toPath());
		} catch (IOException err) {
			System.err.println("Error reading from document: " + this.fileHandle);
			return "";
		}
	}
}
