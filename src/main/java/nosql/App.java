package nosql;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class App {
    public static final String DB_NAME = ".db";

    static void init() {
        try {
            FileUtils.deleteDirectory(new File(DB_NAME));
        } catch (IOException err) {
            System.err.println("Unable to delete existing db");
        }
        new File(DB_NAME).mkdir();
    }

    public static void main(String[] args) {
        // TODO: A lot of missing exception handling due to time constraints
        init();
        Collection c1 = new Collection("collection1");
        Collection c2 = new Collection("collection2");
        Document d1 = c1.createDocument();
        Document d2 = c1.createDocument();
        Document d3 = c2.createDocument();
        c2.deleteDocument(d3);
        d3 = c2.createDocument();

        d1.insert(c1);
        String[] add1 = { "as", "pq", "asdoifa" };
        d2.insert(new Demo(5, "DB_NAME", 100.59f, add1));
        
        
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3.id);
    }
}
