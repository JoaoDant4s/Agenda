package service;

import model.Contact;
import model.Phone;
import repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private final ContactRepository contactRepository;
    private final PhoneService phoneService;
    public ContactService() throws Exception {
        this.contactRepository = ContactRepository.getInstance();
        this.phoneService = PhoneService.getInstance();
    }
    public Contact validate(String name, String lastName, List<String> numbers) throws Exception {
        List<Phone> phones = new ArrayList<>();
        Phone phone;
        for(int i = 0; i < numbers.size(); i++){
            phone = phoneService.validate(numbers.get(i), i);
            phones.add(phone);
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

}
