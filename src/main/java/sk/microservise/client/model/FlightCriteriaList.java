package sk.microservise.client.model;

import java.util.List;

public class FlightCriteriaList {

    private List<FlightCriteria> list;

    public FlightCriteriaList(List<FlightCriteria> list) {
        this.list = list;
    }

    public List<FlightCriteria> getList() {
        return list;
    }

    public void setList(List<FlightCriteria> list) {
        this.list = list;
    }
}
