package util;

import model.Contact;
import model.Phone;

public class Formatter {
    public static String contactToString(Contact contact){
        StringBuilder data = new StringBuilder(contact.getId() + "|" + contact.getName() + "|" + contact.getLastName() + "|");
        for(Phone phone : contact.getPhones()){
            data.append(phone.getId()).append(",").append(phone.getDdd()).append(" ").append(phone.getNumber());
        }
        return data.toString();
    }

}
