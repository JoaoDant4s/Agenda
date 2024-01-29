package controller;
import java.io.*;

public class Writer {
    private static Writer instance = null;
    private final File file;
    private final FileWriter fileWriter;
    private final BufferedWriter bufferedWriter;

    private Writer() throws IOException {
        this.file = new File("db.txt");
        if(!this.file.exists()){
            this.file.createNewFile();
        }
        this.fileWriter = new FileWriter(file, true);
        this.bufferedWriter = new BufferedWriter(fileWriter);
    }
    public static Writer getInstance() throws IOException {
        if(instance == null){
            instance = new Writer();
        }
        return instance;
    }
    public void closeWrites(){
        try{
            bufferedWriter.close();
            fileWriter.close();
            instance = null;
        } catch(Exception e){
            System.out.println("Erro ao desalocar o escritor de arquivos" + e.getMessage());
        }
    }

    public void saveContact(String data) throws Exception {
        try{
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch(Exception e){
            throw new Exception("Erro ao registrar um contato");
        }
    }
}
