package model;

public class Phone {
    private static long idIncrementer;
    private final long id;
    private String ddd;
    private long number;

    public Phone(String ddd, long number){
        this.id = idIncrementer;
        this.ddd = ddd;
        this.number = number;
        idIncrementer++;
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
