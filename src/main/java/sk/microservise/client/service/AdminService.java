package sk.microservise.client.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.microservise.client.Tokens;
import sk.microservise.client.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    private RestTemplate restTemplate;
    private Gson gson;
    private int perPage;
    private int pageInd;


    private static final String adminUrl = "/admin";

    @Autowired
    public AdminService() {
        restTemplate = new RestTemplate();
        gson = new Gson();
        perPage = 10;
        pageInd = 0;
    }

    public boolean addDestination(String name){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        JsonObject element = new JsonObject();
        element.addProperty("name",name);
        HttpEntity<String> entity = new HttpEntity<>(element.toString(),httpHeaders);

        ResponseEntity<Boolean> rsp = restTemplate.exchange(Tokens.flightUrl+adminUrl+"/destination/add",HttpMethod.POST,entity,Boolean.class);
        if(!rsp.hasBody() || rsp.getBody()==null)
            return false;

        return rsp.getBody();

    }

    public boolean addPlane(long chasses, String name,int capacity){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        PlaneForm form = new PlaneForm(chasses,name,capacity);


        String json_str = gson.toJson(form);
        System.out.println(json_str);

        HttpEntity<String> entity = new HttpEntity<>(json_str,httpHeaders);

        ResponseEntity<Boolean> rsp = restTemplate.exchange(Tokens.flightUrl+adminUrl+"/plane/add",HttpMethod.POST,entity,Boolean.class);

        if(!rsp.hasBody() || rsp.getBody()==null)
            return false;

        return rsp.getBody();
    }

    public boolean addFlight(long plane, int distance, int price, int capacity, String originName, String destinationName){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        FlightForm form = new FlightForm(plane,distance,price,capacity,originName,destinationName);

        String json_str = gson.toJson(form);
        System.out.println(json_str);

        HttpEntity<String> entity = new HttpEntity<>(json_str,httpHeaders);
        ResponseEntity<Boolean> rsp;
        try{
            rsp = restTemplate.exchange(Tokens.flightUrl+adminUrl+"/flight/add",HttpMethod.POST,entity,Boolean.class);
        }catch (Exception e){
            System.err.println("Greska");
            return false;
        }

        if(!rsp.hasBody() || rsp.getBody()==null)
            return false;

        return rsp.getBody();
    }

    public boolean adminLogIn(String username,String password){
        LoginForm form = new LoginForm();
        form.setEmail(username);
        form.setPassword(password);

        String json_form = gson.toJson(form);
        System.out.println(json_form);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);
        ResponseEntity<Void> rsp;
        try{
            rsp = restTemplate.exchange(Tokens.userUrl+"/login", HttpMethod.POST,entity,Void.class);
        }catch (Exception e) {
            return false;
        }
        if(!rsp.getStatusCode().equals(HttpStatus.OK)) {
            System.out.println(rsp.getStatusCode());
            return false;
        }

        String auth = Objects.requireNonNull(rsp.getHeaders().get(Tokens.HEADER_STRING)).get(0);
        Tokens.setAdminToken(auth);

        return true;
    }

    public List<Plane> getAllPlanes(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<PlaneList> rsp = restTemplate.exchange(Tokens.flightUrl+adminUrl+"/plane/all",HttpMethod.GET,entity,PlaneList.class);
        if(!rsp.hasBody() || rsp.getBody()==null)
            return null;

        return rsp.getBody().getList();
    }

    public List<Plane> getUnusedPlane(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<PlaneList> rsp = restTemplate.exchange(Tokens.flightUrl+adminUrl+"/plane/unused",HttpMethod.GET,entity,PlaneList.class);
        if(!rsp.hasBody() || rsp.getBody()==null)
            return null;

        return rsp.getBody().getList();
    }

    public List<String> getDestination(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<DestinationList> rsp = restTemplate.exchange(Tokens.flightUrl+"/destination/all",HttpMethod.GET,entity,DestinationList.class);
        if(!rsp.hasBody() || rsp.getBody()==null)
            return null;

        return rsp.getBody().getList();
    }

    public List<Flight> getFlights(List<FlightCriteria> critrias,int pageInd){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());
        List<Flight> result = null;
        System.out.println();
        if(critrias.isEmpty()){
            System.out.println("Nooooo we are lookin everwheer");
            HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
            ResponseEntity<FlightList> rsp = restTemplate.exchange(Tokens.flightUrl+"/flights2/all/"+pageInd+"/"+perPage,HttpMethod.GET,entity,FlightList.class);
            if(!rsp.hasBody() || rsp.getBody()==null)
                return null;

            result = rsp.getBody().getList();
            System.out.println(result.toString());
        }else{
            httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

            FlightCriteriaList fcl = new FlightCriteriaList(critrias);
            String json_form = gson.toJson(fcl);
            System.out.println(json_form);
            HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);
            ResponseEntity<FlightList> rsp = restTemplate.exchange(Tokens.flightUrl+"/flights/"+pageInd+"/"+perPage,HttpMethod.POST,entity,FlightList.class);
            if(!rsp.hasBody() || rsp.getBody()==null)
                return null;

            result = rsp.getBody().getList();
        }
        return result;
    }

    public boolean cancelFlights(long fid){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());

        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        try {
            ResponseEntity<Boolean> rsp = restTemplate.exchange(Tokens.flightUrl + adminUrl + "/flight/cancel/" + fid, HttpMethod.DELETE, entity, Boolean.class);

            if (!rsp.hasBody() || rsp.getBody() == null)
                return false;

            return rsp.getBody();
        }catch (Exception e){
            return false;
        }

    }

    public boolean deletePlanes(long pid){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getAdminToken());

        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        try {
            ResponseEntity<Boolean> rsp = restTemplate.exchange(Tokens.flightUrl + adminUrl + "/plane/delete/" + pid, HttpMethod.DELETE, entity, Boolean.class);
            if(!rsp.hasBody() || rsp.getBody()==null)
                return false;

            return rsp.getBody();
        }catch (Exception e){
            return false;
        }

    }

}
