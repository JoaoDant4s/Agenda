package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Reader {
    private static Reader instance = null;
    private final File file;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    private Reader(File file, FileReader fileReader, BufferedReader bufferedReader){
        this.file = file;
        this.fileReader = fileReader;
        this.bufferedReader = bufferedReader;
    }

    public File getFile() {
        return file;
    }
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    private static Reader getInstance(File file, FileReader readerFile, BufferedReader readerBuffer){
        if(instance == null){
            instance = new Reader(file, readerFile, readerBuffer);
        }
        return instance;
    }

    public static Reader initializeReader(){
        File file = new File("db.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader readerFile = new FileReader(file);
            BufferedReader readerBuffer = new BufferedReader(readerFile);
            return Reader.getInstance(file, readerFile, readerBuffer);
        } catch (Exception e){
            System.out.println("Erro ao inicializar o leitor de arquivos: " + e.getMessage());
        }
        return null;
    }
    public void closeReaders(){
        try{
            bufferedReader.close();
            fileReader.close();
            instance = null;
        } catch(Exception e){
            System.out.println("Erro ao desalocar o leitor de arquivos" + e.getMessage());
        }
    }
}
