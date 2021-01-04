package sk.microservise.client.model;

public class Flight {

    private long id;

    private int distance;

    private int price;
    private int capacity;

    private String plane;

    private String  originName;
    private String destinationName;


    public Flight(long id,int distance, int price, int capacity, String plane,
                  String originName, String destinationName) {
        this.id = id;
        this.distance = distance;
        this.price = price;
        this.capacity = capacity;
        this.plane = plane;
        this.originName = originName;
        this.destinationName = destinationName;
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane='" + plane + '\'' +
                '}';
    }
}
