package model;

public class Phone {
    private final long id;
    private String ddd;
    private long number;

    public Phone(long id, String ddd, long number){
        this.id = id;
        this.ddd = ddd;
        this.number = number;
    }
    public long getId() {
        return id;
    }
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    public String getDdd() {
        return ddd;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public long getNumber() {
        return number;
    }
}
