package com.company;

import java.util.List;

public class Main {
    private ReaderManager readerManager;
    private Writer writer;
    private MakeHttpRequest request;
    private String baseUrl = "http://10.78.40.157";

    public Main() {
        readerManager = new ReaderManager();
        request = new MakeHttpRequest(baseUrl);
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

        request.postAddressableToServer(list);


//        Collections.sort(personList);
        writer.write(list);
    }

}