package com.xmlandcss;

public class City {
    private String cityName;
    private String street;
    private int house;
    private int floor;

    public City(String cityName, String street, int house, int floor){
        this.cityName = cityName;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public String getCityName(){
        return this.cityName;
    }

    public String getStreet(){
        return this.street;
    }

    public int getHouse(){
        return this.house;
    }

    public int getFloor(){
        return this.floor;
    }

    @Override
    public String toString(){
        return "City {Name= " + this.cityName + ", Street= " + this.street + ", house= " + this.house + ", floor ="+ this.floor + "}";
    }
}
