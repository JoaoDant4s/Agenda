package repository;
import controller.Reader;
import controller.Writer;
import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private static ContactRepository instance = null;
    private List<Contact> contacts = new ArrayList<>();
    private final Reader reader;
    private final Writer writer;
    private ContactRepository() {
        reader = Reader.initializeReader();
        assert reader !=  null;
        writer = Writer.initializeWriter(reader.getFile());
    }
    public static ContactRepository getInstance(){
        if(instance == null){
            instance = new ContactRepository();
        }
        return instance;
    }
    public void saveContact(Contact contact){
        System.out.println(reader == null);
        System.out.println(writer == null);
        System.out.println(contact == null);
        contacts.add(contact);
        writer.saveContact(contact);
    }

}
