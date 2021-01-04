package sk.microservise.client.model;

public class Plane {

    private long chassis;
    private String name;
    private int capacity;

    private boolean used;

    public Plane(long chassis, String name, int capacity, boolean used) {
        this.chassis = chassis;
        this.name = name;
        this.capacity = capacity;
        this.used = used;
    }

    public Plane() {
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
