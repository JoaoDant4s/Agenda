package repository;
import controller.Reader;
import controller.Writer;
import model.Contact;
import model.Phone;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private static ContactRepository instance = null;
    private List<Contact> contacts = new ArrayList<>();
    private Reader reader = null;
    private Writer writer = null;
    private ContactRepository() throws Exception {
        try{
            this.reader = Reader.getInstance();
            this.writer = Writer.getInstance();
        } catch (Exception e){
            throw new Exception("Ocorreu um erro ao tentar instanciar o ContactRepository: " + e.getMessage());
        }
    }
    public static ContactRepository getInstance() throws Exception {
        if(instance == null){
            instance = new ContactRepository();
        }
        return instance;
    }
    public void saveContact(Contact contact) throws Exception {
        contacts.add(contact);
        try {
            StringBuilder data = new StringBuilder(contact.getId() + "|" + contact.getName() + "|" + contact.getLastName() + "|");
            for(Phone phone : contact.getPhones()){
                data.append(phone.getId()).append(",").append(phone.getDdd()).append(" ").append(phone.getNumber());
            }
            writer.saveContact(data.toString());
        } catch (Exception e){
            throw e;
        }
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
}
