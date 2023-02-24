package nosql;

import java.io.File;

public class Collection {
	String name;
	public int idCounter = 1;

	public Collection(String name) {
		this.name = name;
		new File(App.DB_NAME + "/" + name).mkdirs();
	}

	public Document createDocument() {
		Document res = new Document(this.idCounter, this);
		this.idCounter++;
		return res;
	}

	public void deleteDocument(Document document) {
		this.idCounter--;
		document.fileHandle.delete();
	}
}
