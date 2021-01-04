package sk.microservise.client.model;

import java.util.ArrayList;
import java.util.List;

public class PlaneList {

    private List<Plane> list;

    public PlaneList() {
        list = new ArrayList<>();
    }

    public PlaneList(List<Plane> list) {
        this.list = list;
    }

    public List<Plane> getList() {
        return list;
    }

    public void setList(List<Plane> list) {
        this.list = list;
    }
}
