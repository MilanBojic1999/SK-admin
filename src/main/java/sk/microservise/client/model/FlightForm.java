package sk.microservise.client.model;

public class FlightForm {

    private long plane;
    private int distance;
    private int price;
    private int capacity;
    private String originName;
    private String destinationName;

    public FlightForm(long plane, int distance, int price, int capacity, String originName, String destinationName) {
        this.plane = plane;
        this.distance = distance;
        this.price = price;
        this.capacity = capacity;
        this.originName = originName;
        this.destinationName = destinationName;
    }

    public FlightForm() {
    }

    public long getPlane() {
        return plane;
    }

    public void setPlane(long plane) {
        this.plane = plane;
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
}
