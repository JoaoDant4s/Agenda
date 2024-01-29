package service;

import model.Phone;

import java.util.List;

public class PhoneService {
    private static PhoneService instance = null;
    private PhoneService(){}
    public Phone validate(String number, int id) throws Exception {
        String[] numberSplited = number.split(" ");
        String ddd = numberSplited[0];
        if(ddd.length() != 2) throw new Exception("É necessário 2 dígitos para o DDD");
        if(numberSplited[1].length() != 9) throw new Exception("É necessário 9 dígitos para o número de telefone");
        long telNumber = Long.parseLong(numberSplited[1]);
        return new Phone(id, ddd, telNumber);
    }
    public static PhoneService getInstance(){
        if(instance == null){
            instance = new PhoneService();
        }
        return instance;
    }
}
