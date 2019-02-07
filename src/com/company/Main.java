package com.company;


public class Main {
    private ReaderManager readerManager;

    public Main() {
        readerManager = new ReaderManager();
    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }

    private void execute(String filePath) {
        // Manager reads filename and decides which reader parser and writer to use
        readerManager.chooseReader(filePath);
    }

}