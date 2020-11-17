package com.example.bloodbank.model;

public class Donor {
    String name,blood_group,city;
    int cell;

    public Donor(String name, String blood_group, String city, int cell) {
        this.name = name;
        this.blood_group = blood_group;
        this.city = city;
        this.cell = cell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }
}
