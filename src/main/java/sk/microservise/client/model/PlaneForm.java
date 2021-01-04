package sk.microservise.client.model;

public class PlaneForm {

    private long chassis;

    private String name;
    private int capacity;

    public PlaneForm(long chassis, String name, int capacity) {
        this.chassis = chassis;
        this.name = name;
        this.capacity = capacity;
    }

    public PlaneForm() {
    }

    public long getChassis() {
        return chassis;
    }

    public void setChassis(long chassis) {
        this.chassis = chassis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
