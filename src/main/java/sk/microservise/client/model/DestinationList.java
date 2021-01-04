package sk.microservise.client.model;

import java.util.List;

public class DestinationList {

    private List<String> list;

    public DestinationList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

