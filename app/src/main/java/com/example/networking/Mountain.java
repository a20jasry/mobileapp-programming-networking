package com.example.networking;

public class Mountain<String> {
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private String cost;

}

    public Mountain(String name, int size, String location){
        this.name = name;
        this.size = size;
        this.location = location;
    }

    public String getName(String name){
        return this.name;
    }

    public int getHeight(String size){
        return this.size;
    }

    public String getLocation(String location){
        return this.location;
    }


    @Override
    public String toString(){
        return name;
    }


