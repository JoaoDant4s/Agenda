package service;

import model.Contact;
import model.Phone;
import repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private static ContactService instance = null;
    private final ContactRepository contactRepository;
    private final PhoneService phoneService;
    private ContactService() throws Exception {
        this.contactRepository = ContactRepository.getInstance();
        this.phoneService = PhoneService.getInstance();
    }
    public Contact validate(String name, String lastName, List<String> numbers) throws Exception {
        List<Phone> phones = new ArrayList<>();
        Phone phone;
        List<Contact> listContacts = contactRepository.getContactList();
        if(listContacts != null){
            if(listContacts.isEmpty()){
                listAllContact();
            }
            for(int i = 0; i < numbers.size(); i++){
                phone = phoneService.validate(numbers.get(i), i, listContacts);
                phones.add(phone);
            }
        } else {
            for(int i = 0; i < numbers.size(); i++){
                phone = phoneService.validate(numbers.get(i), i);
                phones.add(phone);
            }
        }
        return new Contact(name, lastName, phones);
    }

    public void newContact(String name, String lastName, List<String> numbers) throws Exception {
        try{
            Contact contact = validate(name, lastName, numbers);
            contactRepository.saveContact(contact);
        }catch (Exception e){
            throw e;
        }
    }

    public void deleteContact(long id) throws Exception {
        List<Contact> contacts = contactRepository.getContactList();
        if(contacts.isEmpty()) throw new Exception("Não há contatos para deletar");
        Contact contact = contactRepository.deleteContactById(id);
        contacts.remove(contact);
        contactRepository.subscribeDataBase(contacts);
    }

    public void listAllContact() throws Exception {
        List<String> data = contactRepository.getData();
        Contact contact;
        for(String item : data){
            List<Phone> phones = new ArrayList<>();
            String[] fields = item.split("\\|");
            long idContact = Long.parseLong(fields[0]);
            String name = fields[1];
            String lastName = fields[2];
            for(int i = 3; i < fields.length; i++){
                Phone phone = phoneService.getPhoneFromString(fields[i]);
                phones.add(phone);
            }
            contact = new Contact(idContact, name, lastName, phones);
            contactRepository.addContact(contact);
        }
        contactRepository.setLastId(contactRepository.getContactList().getLast().getId() + 1);
    }

    public void printAllContacts() throws Exception {
        if(contactRepository.getContactList().isEmpty()){
            listAllContact();
        }
        System.out.println("\n>>>> Contatos <<<<");
        System.out.println("Id | Nome");
        for(Contact contact : contactRepository.getContactList()){
            System.out.printf("%d  | %s %s\n", contact.getId(), contact.getName(), contact.getLastName());
        }
        System.out.println(">>>> -------- <<<<");
    }

    public static ContactService getInstance() throws Exception {
        if(instance == null){
            instance = new ContactService();
        }
        return instance;
    }
}
