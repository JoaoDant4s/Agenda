package repository;
import controller.Reader;
import controller.Writer;
import model.Contact;
import model.Phone;
import util.Formatter;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private static ContactRepository instance = null;
    private List<Contact> contacts = new ArrayList<>();
    private final Reader reader;
    private final Writer writer;
    private ContactRepository() throws Exception {
        try{
            this.reader = Reader.getInstance();
            this.writer = Writer.getInstance();
        } catch (Exception e){
            throw new Exception("Ocorreu um erro ao tentar instanciar o ContactRepository: " + e.getMessage());
        }
    }
    public void saveContact(Contact contact) throws Exception {
        contacts.add(contact);
        try {
            String data = Formatter.contactToString(contact);
            writer.saveContact(data, writer.getBufferedWriter());
        } catch (Exception e){
            throw e;
        }
    }

    public Contact deleteContactById(long id) throws Exception {
        Contact findedContact = null;
        for(Contact contact : contacts){
            if(id == contact.getId()){
                findedContact = contact;
            }
        }
        if(findedContact == null) throw new Exception("No contact was found with the specified ID");
        return findedContact;
    }

    public void subscribeDataBase(List<Contact> contacts) throws Exception {
        writer.overrideDB(contacts);
    }
    public List<String> getData() throws Exception {
        return reader.readDataFile();
    }
    public void addContact(Contact contact){
        this.contacts.add(contact);
    }
    public List<Contact> getContactList(){
        return this.contacts;
    }
    public void setLastId(long lastId){
        Contact.setIdIncrementer(lastId);
    }
    public static ContactRepository getInstance() throws Exception {
        if(instance == null){
            instance = new ContactRepository();
        }
        return instance;
    }
}
