package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static Reader instance = null;
    private final File file;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    private Reader() throws IOException {
        this.file = new File("db.txt");
        if(!this.file.exists()){
            this.file.createNewFile();
        }
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public static Reader getInstance() throws IOException {
        if(instance == null){
            instance = new Reader();
        }
        return instance;
    }

    public List<String> readDataFile() throws Exception {
        try{
            List<String> data = new ArrayList<>();
            while (bufferedReader.ready()){
                data.add(bufferedReader.readLine());
            }
            return data;
        } catch (Exception e){
            throw new Exception("Erro ao ler arquivo: " + e.getMessage());
        }
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
