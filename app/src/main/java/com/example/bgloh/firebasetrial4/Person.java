package com.example.bgloh.firebasetrial4;

/**
 * Created by bgloh on 2017-07-21.
 */

public class Person {

    // name and address string
    public String name;
    public String address;

    public Person() {
        // default constructor
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
