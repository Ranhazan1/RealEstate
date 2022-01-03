public class Property {
    private Address address;
    private int roomNumber;
    private double price;
    private int type;
    private boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private User user;

    public Property(Address address,int roomNumber, double price,int type, boolean forRent,int houseNumber,
                    int floorNumber, User user){
        this.address=address;
        this.roomNumber=roomNumber;
        this.price=price;
        this.type=type;
        this.forRent=forRent;
        this.houseNumber=houseNumber;
        this.floorNumber=floorNumber;
        this.user=user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public String toString(){
        StringBuilder output=new StringBuilder();
        output.append("address: ").append(this.address).append("\n");
        output.append("room number: ").append(this.roomNumber).append("\n");
        output.append("price: ").append(this.price).append("\n");
        if (this.type==1){
            output.append("type: regular apartment in building \n");
            output.append("floor number: ").append(floorNumber).append("\n");
        }else if (this.type==2){
            output.append("type: penthouse \n");
        }else {
            output.append("type: private house").append("\n");
        }
        output.append("for rent: ").append(this.forRent).append("\n");
        output.append("house number: ").append(this.houseNumber).append("\n");
        output.append("address: ").append(this.address).append("\n");
        if (type==1){
            output.append("floor: ").append(this.floorNumber).append("\n");
        }
        output.append("user: ").append(this.user).append("\n");
        return output.toString();
    }
}
