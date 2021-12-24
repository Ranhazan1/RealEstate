public class Address {
    private String city;
    private String street;

    private Address(String city,String street){
        this.city=city;
        this.street=street;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street=street;
    }
}