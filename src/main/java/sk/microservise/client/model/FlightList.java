package sk.microservise.client.model;

import java.util.ArrayList;
import java.util.List;

public class FlightList {

    private List<Flight> list;

    public FlightList() {
        list = new ArrayList<>();
    }

    public FlightList(List<Flight> list) {
        this.list = list;
    }

    public List<Flight> getList() {
        return list;
    }

    public void setList(List<Flight> list) {
        this.list = list;
    }
}
