package service;

import model.Contact;
import model.Phone;
import java.util.List;

public class PhoneService {
    private static PhoneService instance = null;
    private PhoneService(){}
    public Phone validate(String number, int id, List<Contact> listContacts) throws Exception {
        String[] numberSplited = number.split(" ");
        String ddd = numberSplited[0];

        if(ddd.length() != 2) throw new Exception("É necessário 2 dígitos para o DDD");
        if(numberSplited[1].length() != 9) throw new Exception("É necessário 9 dígitos para o número de telefone");

        long telNumber = Long.parseLong(numberSplited[1]);
        if(phoneAlreadyExists(ddd, telNumber, listContacts)) {
            throw new Exception("\nUm telefone informado está cadastrado em outro contato\n");
        }
        return new Phone(id, ddd, telNumber);
    }
    public Phone validate(String number, int id) throws Exception {
        String[] numberSplited = number.split(" ");
        String ddd = numberSplited[0];

        if(ddd.length() != 2) throw new Exception("É necessário 2 dígitos para o DDD");
        if(numberSplited[1].length() != 9) throw new Exception("É necessário 9 dígitos para o número de telefone");

        long telNumber = Long.parseLong(numberSplited[1]);
        return new Phone(id, ddd, telNumber);
    }

    public boolean phoneAlreadyExists(String ddd, long telNumber, List<Contact> listContacts) throws Exception {
        for(Contact contact : listContacts){
            for(Phone phone : contact.getPhones()){
                if(ddd.equals(phone.getDdd()) && telNumber == phone.getNumber()) return true;
            }
        }
        return false;
    }
    public Phone getPhoneFromString(String phoneData){
        String[] phoneFields = phoneData.split(",");
        long id = Long.parseLong(phoneFields[0]);
        String[] numberFields = phoneFields[1].split(" ");
        String ddd = numberFields[0];
        long number = Long.parseLong(numberFields[1]);
        return new Phone(id, ddd, number);
    }
    public static PhoneService getInstance() throws Exception {
        if(instance == null){
            instance = new PhoneService();
        }
        return instance;
    }
}
