import controller.Menu;
import controller.Reader;
import controller.Writer;

public class Main {
    public static void main(String[] args) throws Exception {
//        Reader reader = Reader.initializeReader();
//        if(reader == null){
//            throw new Exception("Erro ao inicializar o reader");
//        }
//        Writer writer = Writer.initializeWriter(reader.getFile());
//        if(writer == null){
//            throw new Exception("Erro ao inicializar o writer");
//        }
        Menu menu = new Menu();
        boolean toContinue = true;
        while(toContinue){
            toContinue = menu.mainMenu();
        }

        menu.closeScanner();
//        reader.closeReaders();
//        writer.closeWrites();
    }
}