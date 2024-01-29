package controller;

import model.Contact;
import model.Phone;

import java.io.*;

public class Writer {
    private static Writer instance = null;
    private final FileWriter fileWriter;

    private final BufferedWriter bufferedWriter;

    private Writer(FileWriter fileWriter, BufferedWriter bufferedWriter){
        this.fileWriter = fileWriter;
        this.bufferedWriter = bufferedWriter;
    }

    private static Writer getInstance(FileWriter fileWriter, BufferedWriter bufferedWriter){
        if(instance == null){
            instance = new Writer(fileWriter, bufferedWriter);
        }
        return instance;
    }

    public void saveContact(Contact contact){
        try{
            StringBuilder data = new StringBuilder(contact.getId() + "|" + contact.getName() + contact.getLastName() + "|");
            for(Phone phone : contact.getPhones()){
                data.append(phone.getId()).append(",").append(phone.getDdd()).append(" ").append(phone.getNumber()).append("|");
            }
            bufferedWriter.write("TESTEEEE");
            bufferedWriter.write(1);
            bufferedWriter.append("teste");
            bufferedWriter.newLine();
        } catch(Exception e){
            System.out.println("Erro ao registrar um contato: " + e.getMessage());
        }
    }
    public static Writer initializeWriter(File file){
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            return Writer.getInstance(fileWriter, bufferedWriter);
        } catch (Exception e){
            System.out.println("Erro ao inicializar o escritor de arquivos: " + e.getMessage());
        }
        return null;
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
}
