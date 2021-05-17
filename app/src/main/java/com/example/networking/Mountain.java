package com.example.networking;

public class Mountain<string> {
    private String name;
    private String location;
    private int size;



    public Mountain(String name, int size, String location){
        this.name = name;
        this.size = size;
        this.location = location;
    }

    public String getName(String name){
        return this.name;
    }

    public int getSize(String size){
        return this.size;
    }

    public String getLocation(String location){
        return this.location;
    }


    @Override
    public String toString() { return name; }


}




