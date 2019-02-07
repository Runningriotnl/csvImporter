package com.company;


import com.company.person.Person;

import java.util.Collections;
import java.util.List;

public class Main {
    private ReaderManager readerManager;
    private Writer writer;

    public Main() {
        readerManager = new ReaderManager();
        writer = new Writer();
    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }

    private void execute(String filePath) {
        // Manager reads filename and decides which reader parser and writer to use
        Reader reader = readerManager.chooseReader(filePath);

        List list = reader.readFile(filePath);
        System.out.println("Sorting the list based on first names.");
//        Collections.sort(personList);
        writer.write(list);
    }

}