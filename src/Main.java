import controller.Menu;
import controller.Reader;
import controller.Writer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        boolean toContinue = true;
        while(toContinue){
            toContinue = menu.mainMenu();
        }
        menu.closeScanner();
        Writer.getInstance().closeWriters();
        Reader.getInstance().closeReaders();
    }
}