package controller;
import model.Contact;
import util.Formatter;

import java.io.*;
import java.util.List;

public class Writer {
    private static Writer instance = null;
    private final File file;
    private final FileWriter fileWriter;
    private final BufferedWriter bufferedWriter;
//    private final FileWriter fileWriterOverride;
//    private final BufferedWriter bufferedWriterOverride;

    private Writer() throws IOException {
        this.file = new File("db.txt");
        if(!this.file.exists()){
            this.file.createNewFile();
        }
        this.fileWriter = new FileWriter(file, true);
        this.bufferedWriter = new BufferedWriter(fileWriter);
//        this.fileWriterOverride = new FileWriter(file);
//        this.bufferedWriterOverride = new BufferedWriter(fileWriterOverride);
    }
    public void closeWriters(){
        try{
            bufferedWriter.close();
            fileWriter.close();
//            bufferedWriterOverride.close();
//            fileWriterOverride.close();
            instance = null;
        } catch(Exception e){
            System.out.println("Erro ao desalocar o escritor de arquivos" + e.getMessage());
        }
    }

    public void saveContact(String data, BufferedWriter bufferedWriter) throws Exception {
        try{
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch(Exception e){
            throw new Exception("Erro ao registrar um contato");
        }
    }
    public void overrideDB(List<Contact> contacts) throws Exception {
        for(Contact contact : contacts){
            String data = Formatter.contactToString(contact);
            saveContact(data, bufferedWriter);
        }
    }
    public static Writer getInstance() throws IOException {
        if(instance == null){
            instance = new Writer();
        }
        return instance;
    }

    public BufferedWriter getBufferedWriter(){
        return this.bufferedWriter;
    }

//    public BufferedWriter getBufferedWriterOverride(){
//        return this.bufferedWriterOverride;
//    }
}
