package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private PersonReader pr;
    private Writer w;

    public Main() {
        pr = new PersonReader();
        w = new Writer();
    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }



    private void execute(String filePath) {
        // Read file, return list of persons
        List<Person> personList = pr.readFile(filePath);

        Collections.sort(personList);
        //Write list to console
        w.write(personList);

    }




}
