package model;

import java.util.List;

public class Contact {
    private static long idIncrementer = 0;
    private final long id;
    private String name;
    private String lastName;
    private List<Phone> phones;

    public Contact(String name, String lastName, List<Phone> phones){
        this.id = idIncrementer;
        this.name = name;
        this.lastName = lastName;
        this.phones = phones;
        idIncrementer++;
    }
    public Contact(long id, String name, String lastName, List<Phone> phones){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    public List<Phone> getPhones() {
        return phones;
    }
    public static void setIdIncrementer(long lastId){
        Contact.idIncrementer = lastId;
    }
}
